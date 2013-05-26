// Ishag Alexanian  
// Login page to authenticate the users 

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

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Login() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.getRequestDispatcher("/WEB-INF/wiki/Login.jsp").forward(
				request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {
			ConnectDb c = new ConnectDb();

			String sql = "select * from users where user_name = ? and password = ?;";

			PreparedStatement pstmt = c.getConnected().prepareStatement(sql);
			pstmt.setString(1, request.getParameter("username"));
			pstmt.setString(2, request.getParameter("password"));
			ResultSet rs = pstmt.executeQuery();

			boolean flag = false;

			while (rs.next()) {
				Integer id = rs.getInt("id");

				if (id != null) {
					flag = true;
					break;
				}

			}

			if (flag) {
				request.getSession().setAttribute("user",
						request.getParameter("username"));
				response.sendRedirect(request.getContextPath() + "/wiki/index");
			} else {
				response.sendRedirect("Login");
			}

			c.getConnected().close();
		} catch (SQLException e) {
			throw new ServletException(e);
		}

	}

}
