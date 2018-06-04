package ac.cr.ucenfotec.process_manager.entities;

import org.springframework.data.annotation.Id;

public class User {

	@Id String userId;
	String userName;
	String userPassword;
	String userEmail;
	UserType userGroup;
	public User() {
		
	}
	public User(String userId, String userName, String userPassword, String userEmail) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userPassword = userPassword;
		this.userEmail = userEmail;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public UserType getUserGroup() {
		return userGroup;
	}
	public void setUserGroup(UserType userGroup) {
		this.userGroup = userGroup;
	}
	
	
}
