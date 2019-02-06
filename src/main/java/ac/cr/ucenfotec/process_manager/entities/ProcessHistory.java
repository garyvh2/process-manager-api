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
	
	public ProcessHistory(Task task) {
		dateTimeHistory = new Date();
		asignee =  task.getAsignee();
		taskDone = task;
		
	}
	
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


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof ProcessHistory))
			return false;
		ProcessHistory other = (ProcessHistory) obj;
		if (asignee == null) {
			if (other.asignee != null)
				return false;
		} else if (!asignee.equals(other.asignee))
			return false;
		if (dateTimeHistory == null) {
			if (other.dateTimeHistory != null)
				return false;
		} else if (!dateTimeHistory.equals(other.dateTimeHistory))
			return false;
		if (taskDone == null) {
			if (other.taskDone != null)
				return false;
		} else if (!taskDone.equals(other.taskDone))
			return false;
		return true;
	}	
	
	
	

}
