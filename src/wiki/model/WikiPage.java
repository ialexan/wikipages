// CS320  Homework     Ishag Alexanian 
// WikiPage

package wiki.model;

import java.util.ArrayList;
import java.util.Date;

public class WikiPage {

	private Integer id;

	private String path;

	private Integer viewCounter;

	private ArrayList<Revision> revisions = new ArrayList<Revision>();

	public WikiPage() {

	}

	// Used in index
	public WikiPage(Integer id, String path, Integer viewCounter) {
		this.path = path;
		this.id = id;
		this.viewCounter = viewCounter;
	}

	// Used in the page List
	public WikiPage(Integer id, String path, String author, Date timestamp) {
		this.id = id;
		this.path = path;
		this.revisions.add(new Revision(author, timestamp));
	}

	// Used in the AddEntry and index
	public WikiPage(String path, String author, String content, Date timestamp) {
		this.revisions.add(new Revision(author, content, timestamp));
		this.path = path;
	}

	public Integer getViewCounter() {
		return viewCounter;
	}

	public void setViewCounter(Integer viewCounter) {
		this.viewCounter = viewCounter;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Revision getLastRevisionEntry() {
		return revisions.get(revisions.size() - 1);
	}

	public Revision getRevisionEntry(int revisionnumber) {
		Revision rev = new Revision();
		try {
			rev = revisions.get(revisionnumber);
		} catch (Exception e) {
		}
		return rev;
	}

	public ArrayList<Revision> getRevisions() {
		return revisions;
	}

	public void setRevisions(String author, String content, Date timestamp) {
		this.revisions.add(new Revision(author, content, timestamp));
	}

}
