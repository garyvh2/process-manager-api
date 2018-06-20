package ac.cr.ucenfotec.process_manager.entities;

import java.util.Date;
import javax.validation.constraints.NotNull;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection ="processhistories")
public class ProcessHistory 
{
	@NotNull
	private Date DateTimeHistory;
	@NotNull
	private User Asignee;
	@NotNull
	private Task TaskDone;
	
	public Date getDateTimeHistory() {
		return DateTimeHistory;
	}
	public void setDateTimeHistory(Date dateTimeHistory) {
		DateTimeHistory = dateTimeHistory;
	}
	public User getAsignee() {
		return Asignee;
	}
	public void setAsignee(User asignee) {
		Asignee = asignee;
	}
	public Task getTaskDone() {
		return TaskDone;
	}
	public void setTaskDone(Task taskDone) {
		TaskDone = taskDone;
	}
	
	

}
