// Ishag Alexanian  
// List of Revisions that list all the revisions of a certain page

package wiki.controller;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wiki.model.Revision;

@WebServlet("/listRevisions")
public class listRevisions extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public listRevisions() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String path = request.getParameter("path");

		ArrayList<Revision> revisions = new ArrayList<Revision>();

		// Get all the revision of the path
		Integer revisionId = 0;
		try {
			ConnectDb c = new ConnectDb();

			String sql = "select r.id from revisions r, wikipages w where w.path = ? and r.wikipage_id = w.id group by r.id;";

			PreparedStatement pstmt = c.getConnected().prepareStatement(sql);
			pstmt.setString(1, path);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				revisionId = rs.getInt("id");

				revisions.add(new Revision(revisionId));
			}

			c.getConnected().close();
		} catch (SQLException e) {
			throw new ServletException(e);
		}

		request.setAttribute("revisions", revisions);

		request.setAttribute("path", path);

		request.getRequestDispatcher("/WEB-INF/wiki/ListRevisions.jsp")
				.forward(request, response);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
