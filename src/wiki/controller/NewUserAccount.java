// Ishag Alexnaian
// New user account creation controller

package wiki.controller;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/NewUserAccount") 
public class NewUserAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public NewUserAccount() {
        super();
   
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.getRequestDispatcher( "/WEB-INF/wiki/NewUserAccount.jsp" ).forward(
				request, response );
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String usernameParam = request.getParameter( "username" );
		String passwordParam = request.getParameter( "password" );		
		String retypepasswordParam = request.getParameter( "retypepassword" );
		String firstnameParam = request.getParameter( "firstname" );
		String lastnameParam = request.getParameter( "lastname" );
		String emailParam = request.getParameter( "email" );

		
		String message="n";
		
		if (usernameParam==""||passwordParam==""||retypepasswordParam==""||firstnameParam==""||lastnameParam==""||emailParam=="")
		{
			message = "Required fields are empty.";
		}
		else if(usernameParam.length()<4)
		{
			message = "Username is shorter than 4 characters.";
		}
		
		else if(passwordParam.length()<4)
		{
			message = "Password is shorter than 4 characters.";
		}
		else if(!passwordParam.equals(retypepasswordParam))
		{
			message = "Password and re-typed password do not match.";
		}
		else
		{
		try
		{
			ConnectDb c = new ConnectDb();		
			
			// Checking the email
			String sql = "select * from users where email = ?;";

			PreparedStatement pstmt = c.getConnected().prepareStatement( sql );
			pstmt.setString( 1, emailParam );
			ResultSet rs = pstmt.executeQuery();

			while( rs.next() )
			{
				String email = rs.getString("email"); 
				if(email != null)
				{
					message = "Email conflicts with an existing email.";
					break;
				}
			}

			//checking for username conflicts
			sql = "select * from users where user_name = ?;";

			pstmt = c.getConnected().prepareStatement( sql );
			pstmt.setString( 1, usernameParam);
			rs = pstmt.executeQuery();

			while( rs.next() )
			{
				String usernamedb = rs.getString("user_name"); 
				if(usernamedb != null)
				{
					message = "Username conflicts with an existing username.";
					break;
				}
			}
			//------
			
			
			if (message=="n"){
				//Insert the new user in the database
			    
				
				sql = "insert into users (user_name, password, first_name, last_name, email) values (?, ?, ?, ?, ?);";
				pstmt = c.getConnected().prepareStatement( sql );
				pstmt.setString( 1, usernameParam );
				pstmt.setString( 2, passwordParam );
				pstmt.setString( 3, firstnameParam );
				pstmt.setString( 4, lastnameParam );
				pstmt.setString( 5, emailParam );
				pstmt.executeUpdate();
				
				
				response.sendRedirect( request.getContextPath()+"/Login" );
			}

			c.getConnected().close();
		}
		catch( SQLException e )
		{
			throw new ServletException( e );
		}
		}

		
		if (message!="n"){
			request.setAttribute("message", message);
			request.getRequestDispatcher( "/WEB-INF/wiki/NewUserAccount.jsp" ).forward(
					request, response );

		}	
		
	}

}
