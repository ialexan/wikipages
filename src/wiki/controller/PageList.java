// Ishag Alexanian  
// PageList Servlet
// This servlet will diplay the getServletContext() in a table.

package wiki.controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wiki.model.WikiPage;

@WebServlet("/PageList")
public class PageList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PageList() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.setAttribute("entries", getEntriesFromDB());
		request.getRequestDispatcher("/WEB-INF/wiki/PageList.jsp").forward(
				request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	List<WikiPage> getEntriesFromDB() throws ServletException {
		List<WikiPage> entries = new ArrayList<WikiPage>();

		try {
			ConnectDb c = new ConnectDb();

			Statement stmt = c.getConnected().createStatement();

			ResultSet rs = stmt
					.executeQuery("select w.id, w.path,concat(u.first_name, ' ', u.last_name) as name, r.time_stamp "
							+ "from  users u, revisions r, wikipages w where w.id=r.wikipage_id and u.id = r.author_id "
							+ "and r.id=(select max(r2.id) from revisions r2 where r2.wikipage_id = r.wikipage_id) "
							+ "group by r.id ORDER BY r.time_stamp DESC;");

			while (rs.next()) {
				Integer id = rs.getInt("id");
				String path = rs.getString("path");
				String author = rs.getString("name");

				Timestamp timestamp = rs.getTimestamp("time_stamp");

				entries.add(new WikiPage(id, path, author, timestamp));
			}

			c.getConnected().close();
		} catch (SQLException e) {
			throw new ServletException(e);
		}

		return entries;
	}

}
