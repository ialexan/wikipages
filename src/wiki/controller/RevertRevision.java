package wiki.controller;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/RevertRevision")
public class RevertRevision extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public RevertRevision() {
        super();
       
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// check if a user has logged in or not
		if( request.getSession().getAttribute( "user" ) == null )
	    {
			response.sendRedirect( request.getContextPath()+"/Login" );
			return;
	    }
		
		
		
		Integer wikiId = Integer.parseInt(request.getParameter( "wikiId" ));
		Integer revisionId = Integer.parseInt(request.getParameter( "revisionId" ));
		
        String path="";
		try
		{
			ConnectDb c = new ConnectDb();

			// Get the content and the author of the from the old revision 
			String sql = "select content,author_id,time_stamp from revisions where id= ?;";
			
			PreparedStatement pstmt = c.getConnected().prepareStatement( sql );
			pstmt.setInt( 1, revisionId );
			ResultSet rs = pstmt.executeQuery();
			
			
			while( rs.next() )
			{
				String content = rs.getString("content");
				Integer authorId = rs.getInt("author_id");
				Timestamp timestamp = rs.getTimestamp("time_stamp");
			
			
				String sql2 = "insert into revisions (wikipage_id, content, author_id, time_stamp) values (?, ?, ?, ?);";
				PreparedStatement pstmt2 = c.getConnected().prepareStatement( sql2 );
				pstmt2.setInt( 1, wikiId );
				pstmt2.setString( 2, content );
				pstmt2.setInt( 3, authorId );
				pstmt2.setTimestamp(4, timestamp);
				pstmt2.executeUpdate();
				
			}
			
			//Get the path
			sql = "select path from wikipages where id= ?;";
			pstmt = c.getConnected().prepareStatement( sql );
			pstmt.setInt( 1, wikiId );
			rs = pstmt.executeQuery();
			while( rs.next() ){
				path = rs.getString("path");
			}
			
			

			c.getConnected().close();
		}
		catch( SQLException e )
		{
			throw new ServletException( e );
		}



		response.sendRedirect( request.getContextPath()+"/wiki/" + path);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
