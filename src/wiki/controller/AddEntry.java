// Ishag Alexanian  
// AddEntry Servlet
// This servlet in the do getwill first diplay a form to get the name of the user and the content. and it will get the 
// specified url automatically by request.getParameter which is passed by the index servlet. 
// The do post will get the result and put it in  getServletContext() and redirect to the index page to display the
// content of the page.

package wiki.controller;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AddEntry")
public class AddEntry extends HttpServlet {
	private String path = "";
	private String author = "";
	private Integer authorId;

	private static final long serialVersionUID = 1L;

	public AddEntry() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// check if a user has logged in or not
		if (request.getSession().getAttribute("user") == null) {
			response.sendRedirect(request.getContextPath() + "/Login");
			return;
		} else {

			try {
				ConnectDb c = new ConnectDb();

				Statement stmt = c.getConnected().createStatement();
				ResultSet rs = stmt
						.executeQuery("select id, user_name, concat(first_name, ' ', last_name) as name from users");

				while (rs.next()) {
					if ((request.getSession().getAttribute("user").equals(rs
							.getString("user_name")))) {
						authorId = rs.getInt("id");
						author = rs.getString("name");
						break;
					}

				}

				c.getConnected().close();
			} catch (SQLException e) {
				throw new ServletException(e);
			}

		}

		path = request.getParameter("path");

		request.setAttribute("path", path);
		request.setAttribute("author", author);

		request.getRequestDispatcher("/WEB-INF/wiki/AddEntry.jsp").forward(
				request, response);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String content = request.getParameter("content");

		try {
			ConnectDb c = new ConnectDb();

			Statement stmt = c.getConnected().createStatement();
			ResultSet rs = stmt
					.executeQuery("select max(id) as lastid from wikipages;");

			Integer wikiPageId = new Integer(1);
			while (rs.next()) {
				wikiPageId = rs.getInt("lastid") + 1;
				break;
			}

			String sql = "insert into wikipages (id, path, view_counter) values (?,?,?);";
			PreparedStatement pstmt = c.getConnected().prepareStatement(sql);
			pstmt.setInt(1, wikiPageId);
			pstmt.setString(2, path);
			pstmt.setInt(3, 0); // This will set the view counter to 0
			pstmt.executeUpdate();

			String sql2 = "insert into revisions (wikipage_id, content, author_id, time_stamp) values (?, ?, ?, now());";
			PreparedStatement pstmt2 = c.getConnected().prepareStatement(sql2);
			pstmt2.setInt(1, wikiPageId);
			pstmt2.setString(2, content);
			pstmt2.setInt(3, authorId);
			pstmt2.executeUpdate();

			c.getConnected().close();
		} catch (SQLException e) {
			throw new ServletException(e);
		}

		response.sendRedirect(request.getContextPath() + "/wiki/" + path);

	}

}
