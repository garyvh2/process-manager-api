package ac.cr.ucenfotec.process_manager.entities;

import java.util.ArrayList;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.mongodb.core.mapping.Document;

import ac.cr.ucenfotec.process_manager.enums.Estado;


@Document(collection ="tasks")
public class Task {
	
	private User asignee;
	
	@NotNull @NotEmpty @NotBlank
	private String description;
	
	@NotNull
	private ArrayList<Question> questions;
	
	@NotNull
	private UserType userGroup;
	private Estado taskStatus;
	
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
	
	public Estado getTaskStaus() {
		return taskStatus;
	}
	
	public void setTaskStaus(Estado taskStaus) {
		this.taskStatus = taskStaus;
	}	
	
}
