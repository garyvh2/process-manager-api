package ac.cr.ucenfotec.process_manager.entities;

import java.util.ArrayList;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.mongodb.core.mapping.Document;

import ac.cr.ucenfotec.process_manager.enums.Status;


@Document(collection ="tasks")
public class Task {
	
	private User asignee;
	
	@NotNull @NotEmpty @NotBlank
	private String description;
	
	@NotNull
	private ArrayList<Question> questions;
	
	@NotNull
	private UserType userGroup;
	private Status taskStatus;
	private String fatherProcess;
	
	public User getAsignee() {
		return asignee;
	}
	
	public void setAsignee(User asignee) {
		this.asignee = asignee;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public ArrayList<Question> getQuestions() {
		return questions;
	}
	
	public void setQuestions(ArrayList<Question> questions) {
		this.questions = questions;
	}
	
	public UserType getUserGroup() {
		return userGroup;
	}
	
	public void setUserGroup(UserType userGroup) {
		this.userGroup = userGroup;
	}
	
	public Status getTaskStaus() {
		return taskStatus;
	}
	
	public void setTaskStaus(Status taskStaus) {
		this.taskStatus = taskStaus;
	}

	public String getFatherProcess() {
		return fatherProcess;
	}

	public void setFatherProcess(String fatherProcess) {
		this.fatherProcess = fatherProcess;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Task))
			return false;
		Task other = (Task) obj;
		if (asignee == null) {
			if (other.asignee != null)
				return false;
		} else if (!asignee.equals(other.asignee))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (fatherProcess == null) {
			if (other.fatherProcess != null)
				return false;
		} else if (!fatherProcess.equals(other.fatherProcess))
			return false;
		if (questions == null) {
			if (other.questions != null)
				return false;
		} else if (!questions.equals(other.questions))
			return false;
		if (taskStatus != other.taskStatus)
			return false;
		if (userGroup == null) {
			if (other.userGroup != null)
				return false;
		} else if (!userGroup.equals(other.userGroup))
			return false;
		return true;
	}	
	
	
}
