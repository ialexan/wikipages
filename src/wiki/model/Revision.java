// CS320  Homework     Ishag Alexanian 
// Revisions

package wiki.model;

import java.util.Date;

public class Revision {
		
	private Integer id;
	
	private String content;
	
	private String author;
	
	private Date timestamp;

	
	public Revision()
	{
		
	}
	
    // Used in PageList
	public Revision(String author, Date timestamp)
	{
		this.author = author;
		this.timestamp = timestamp;
	}
	
	
	// Used in ListRevisions
	public Revision(Integer id)
	{
		this.id = id;
	}
	
	// Used in Index
	public Revision(String author, String content, Date timestamp)
	{ 
		this.author = author;
		this.content = content;
		this.timestamp = timestamp;
	}
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
}
