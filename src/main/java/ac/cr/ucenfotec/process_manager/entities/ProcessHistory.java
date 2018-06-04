package ac.cr.ucenfotec.process_manager.entities;

import java.util.Date;


public class ProcessHistory 
{
	private Date DateTimeHistory;
	private User Asignee;
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
