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
		    this.setTimestamp(timestamp);
		    this.setMessage(message);
		    this.setDetails(details);
	}
	
	public Date getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getDetails() {
		return details;
	}
	
	public void setDetails(String details) {
		this.details = details;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((details == null) ? 0 : details.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExceptionMessage other = (ExceptionMessage) obj;
		if(this.details.equals(other.getDetails()) && this.message.equals(other.getMessage()) && this.timestamp.equals(other.getTimestamp()))
			return true;
		else
			return false;
	}
	
	
}
