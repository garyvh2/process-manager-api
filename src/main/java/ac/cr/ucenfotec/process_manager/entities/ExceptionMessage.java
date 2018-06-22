package ac.cr.ucenfotec.process_manager.entities;

import java.util.Date;

public class ExceptionMessage {
	private Date timestamp;
	private String message;
	private String details;
	public ExceptionMessage() {
		// TODO Auto-generated constructor stub
	}
	public ExceptionMessage(Date timestamp, String message, String details) {
		    this.timestamp = timestamp;
		    this.message = message;
		    this.details = details;
		  }
	

}
