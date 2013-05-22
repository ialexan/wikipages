// Ishag Alexanian  
// Index Servlet
// This servlet is the main servlet, Where the index first initiated in the init method 
// The doGet will check if the page you are accessing exits or not, if it exits then show the content
// if it does not exist then diplay a message saying you wan to create a page with the specified url.


package wiki.controller;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;



import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//import wiki.models
import wiki.model.Revision;
import wiki.model.WikiPage;


@WebServlet(urlPatterns = {"/wiki/index","/wiki/*"}, loadOnStartup = 1)
public class index extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public index() {
		super();

	}


	public void init( ServletConfig config ) throws ServletException
	{
		super.init( config );

		try
		{
			Class.forName( "com.mysql.jdbc.Driver" );
		}
		catch( ClassNotFoundException e )
		{
			throw new ServletException( e );
		}
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String path = request.getRequestURI();
		String pathFirstPart = request.getContextPath() + "/wiki/";
		path = (path).substring(pathFirstPart.length(),path.length());

        
		
		// check if a user has logged in or out for display
		String loginout = "";
		if( request.getSession().getAttribute( "user" ) == null )
		{loginout = "Login";}
		else
		{loginout = "Logout";}


		// In here figure out which revision to show
		Integer revisionId = new Integer(0);
		try{
			revisionId = Integer.parseInt(request.getParameter( "revision" ));
		}catch(Exception e){
			revisionId = -1; // Show the latest revision if the parameter revision is not found
		}



		// In here findout if the page exits if it does show the latest revision or the which one that the revision variable is.
		// If the page does not exit just go to pageDoesNotExist jsp


		// Try to find the if the wikipage exits
		Integer wikiPageId=0;
		Integer viewCounter=0;
		try
		{
			ConnectDb c = new ConnectDb();
			
			String sql = "select id,view_counter from wikipages where path = ?;";

			PreparedStatement pstmt = c.getConnected().prepareStatement( sql );
			pstmt.setString( 1, path );
			ResultSet rs = pstmt.executeQuery();


			boolean flag = false;

			while( rs.next() )
			{
				wikiPageId = rs.getInt("id");
				viewCounter = rs.getInt("view_counter");

				if(wikiPageId != null)
				{
					flag = true;
					break;
				}

			}

			viewCounter = viewCounter + 1; // Incrementing the counter
			
			sql = "update wikipages set view_counter = ? where id = ?;";
            pstmt = c.getConnected().prepareStatement( sql );
            pstmt.setInt( 1, viewCounter );
            pstmt.setInt( 2, wikiPageId );
            pstmt.executeUpdate();;
	
			
			

			if (flag)
			{   
				// Find the Latest Revision or the revision that is specified 	
				// Always get the last revision entry

				//Connection c2 = DriverManager.getConnection( url, username, password );
				String sql2 = "select r.id, r.content, concat(u.first_name, ' ', u.last_name) as name, r.time_stamp from  users u, revisions r, wikipages w "+
						"where w.id = ? and r.wikipage_id=w.id and u.id=r.author_id and r.id=(select max(r2.id) from revisions r2 where r2.wikipage_id = r.wikipage_id) "+
						"group by w.path;";

				PreparedStatement pstmt2 = c.getConnected().prepareStatement( sql2 );
				pstmt2.setInt( 1, wikiPageId );
				ResultSet rs2 = pstmt2.executeQuery();

				WikiPage wikiEntry = new WikiPage(wikiPageId, path,viewCounter);

				Integer latestRevision = 0;
				
				while( rs2.next() )
				{
					latestRevision = rs2.getInt("id");
					String content = rs2.getString("content"); 
					String author = rs2.getString("name");
					Timestamp timestamp = rs2.getTimestamp("time_stamp");
	
					
					wikiEntry.setRevisions(author, content, timestamp); 
				}

				

				//c2.close();
				
				Revision revisionEntry = new Revision(); // This is used to display the content of the revision
				String revertRevision = "No";

				if ( revisionId == -1)  // Get the latest Revision 
				{					                   
					revisionEntry = wikiEntry.getLastRevisionEntry();
					 
				}
				else // Get the revision specified
				{
					
					//Connection c3 = DriverManager.getConnection( url, username, password );
					sql = "select r.id, r.content, concat(u.first_name, ' ', u.last_name) as name, r.time_stamp from  users u, revisions r, wikipages w where w.id= ? and r.id = ? and u.id=r.author_id;";

					PreparedStatement pstmt3 = c.getConnected().prepareStatement( sql );
					pstmt3.setInt( 1, wikiPageId );
					pstmt3.setInt( 2, revisionId );
					ResultSet rs3 = pstmt3.executeQuery();

					while( rs3.next() )
					{
						String content = rs3.getString("content"); 
						String author = rs3.getString("name");
						Timestamp timestamp = rs3.getTimestamp("time_stamp");
						Integer id = rs3.getInt("id");

						revisionEntry.setContent(content);
						revisionEntry.setAuthor(author);
						revisionEntry.setTimestamp(timestamp);
						revisionEntry.setId(id);

						
						if (latestRevision != id){
							revertRevision = "RevertRevision";
						}
					}
					//c3.close();


				}
                
				
				request.setAttribute( "revertRevision", revertRevision);
				request.setAttribute( "wikiEntry", wikiEntry);
				request.setAttribute( "loginout", loginout);
				request.setAttribute( "revisionEntry", revisionEntry);


				request.getRequestDispatcher( "/WEB-INF/wiki/ShowContentPage.jsp" ).forward(
						request, response );



			}
			else{// Go to the page does not exit 

				request.setAttribute( "path", path);
				request.getRequestDispatcher( "/WEB-INF/wiki/PageDoesNotExitPage.jsp" ).forward(
						request, response );

			}



			c.getConnected().close();
			
			
		}
		catch( SQLException e )
		{
			throw new ServletException( e );
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
