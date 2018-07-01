package ac.cr.ucenfotec.process_manager.entities;

import java.util.Date;
import javax.validation.constraints.NotNull;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection ="processhistories")
public class ProcessHistory 
{
	@NotNull
	private Date dateTimeHistory;
	@NotNull
	private User asignee;
	@NotNull
	private Task taskDone;
	
	public Date getDateTimeHistory() {
		return dateTimeHistory;
	}
	
	public void setDateTimeHistory(Date dateTimeHistory) {
		this.dateTimeHistory = dateTimeHistory;
	}
	
	public User getAsignee() {
		return asignee;
	}
	
	public void setAsignee(User asignee) {
		this.asignee = asignee;
	}
	
	public Task getTaskDone() {
		return taskDone;
	}
	
	public void setTaskDone(Task taskDone) {
		this.taskDone = taskDone;
	}		

}
