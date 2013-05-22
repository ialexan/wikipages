// Ishag Alexanian  
// EditPage allows you to change the content of already existed page

package wiki.controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/EditPage")
public class EditPage extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private String path = "";
	private String author = "";
	private Integer authorId;

	public EditPage()
	{
		super();
	}

	protected void doGet( HttpServletRequest request,
			HttpServletResponse response ) throws ServletException, IOException
			{

		// check if a user has logged in or not
		if( request.getSession().getAttribute( "user" ) == null )
		{
			response.sendRedirect( request.getContextPath()+"/Login" );
			return;
		}
		else
		{	


			try
			{
				ConnectDb c = new ConnectDb();
				
				Statement stmt = c.getConnected().createStatement();
				ResultSet rs = stmt.executeQuery( "select id, user_name, concat(first_name, ' ', last_name) as name from users");


				while( rs.next() )
				{
					if( (request.getSession().getAttribute( "user" ).equals(rs.getString( "user_name" ))) )
					{
						authorId = rs.getInt("id");
						author = rs.getString("name");
						break;
					}

				}


				c.getConnected().close();
			}
			catch( SQLException e )
			{
				throw new ServletException( e );
			}



		}


		path = request.getParameter( "path" );

		// Looking for The latest revision of the path
		String content ="";
		Integer id = 1;
		try
		{
			ConnectDb c = new ConnectDb();
			
			String sql = "select w.id, r.content from revisions r, wikipages w where w.path = ? and r.wikipage_id = w.id and " +  
					"r.id=(select max(r2.id) from revisions r2 where r2.wikipage_id = r.wikipage_id) "+
					"group by w.path;";

			PreparedStatement pstmt = c.getConnected().prepareStatement( sql );
			pstmt.setString( 1, path );
			ResultSet rs = pstmt.executeQuery();


			while( rs.next() )
			{
				id = rs.getInt("id");
				content = rs.getString( "content" );
			}

			c.getConnected().close();
		}
		catch( SQLException e )
		{
			throw new ServletException( e );
		}



		request.setAttribute( "path", path);
		request.setAttribute( "author", author);
		request.setAttribute( "wikiPageId", id);
		request.setAttribute( "content", content);


		request.getRequestDispatcher( "/WEB-INF/wiki/EditPage.jsp" ).forward(
				request, response );



			}

	protected void doPost( HttpServletRequest request,
			HttpServletResponse response ) throws ServletException, IOException
			{

		String content = request.getParameter( "content" );
		Integer wikiPageId = Integer.parseInt(request.getParameter( "wikiPageId" ));



		try
		{
			ConnectDb c = new ConnectDb();

			String sql2 = "insert into revisions (wikipage_id, content, author_id, time_stamp) values (?, ?, ?, now());";
			PreparedStatement pstmt2 = c.getConnected().prepareStatement( sql2 );
			pstmt2.setInt( 1, wikiPageId );
			pstmt2.setString( 2, content );
			pstmt2.setInt( 3, authorId );
			pstmt2.executeUpdate();

			c.getConnected().close();
		}
		catch( SQLException e )
		{
			throw new ServletException( e );
		}



		response.sendRedirect( request.getContextPath()+"/wiki/" + path);

		}

}