package ac.cr.ucenfotec.process_manager.entities;

import java.util.ArrayList;

import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection ="tasks")
public class Task {
	
	private User Asignee;
	private String Description;
	private ArrayList<Question> Questions;
	private UserType userGroup;
	
	public User getAsignee() {
		return Asignee;
	}
	public void setAsignee(User asignee) {
		Asignee = asignee;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public ArrayList<Question> getQuestions() {
		return Questions;
	}
	public void setQuestions(ArrayList<Question> questions) {
		Questions = questions;
	}
	public UserType getUserGroup() {
		return userGroup;
	}
	public void setUserGroup(UserType userGroup) {
		this.userGroup = userGroup;
	}
	
	

}
