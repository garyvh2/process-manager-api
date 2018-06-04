package ac.cr.ucenfotec.process_manager.entities;

import java.util.ArrayList;



public class Task {
	
	private User Asignee;
	private String Description;
	private ArrayList<Question> Questions;
	private Group userGroup;
	
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
	public Group getUserGroup() {
		return userGroup;
	}
	public void setUserGroup(Group userGroup) {
		this.userGroup = userGroup;
	}
	
	

}
