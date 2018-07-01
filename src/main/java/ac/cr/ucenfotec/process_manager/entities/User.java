package ac.cr.ucenfotec.process_manager.entities;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection ="users")
public class User {
	
	@Id 
	String userId;
	
	@NotNull @NotEmpty @NotBlank
	String userName;
	String userPassword;
	
	@Email
	String userEmail;
	
	@NotNull
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
