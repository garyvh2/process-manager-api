package ac.cr.ucenfotec.process_manager.entities;

import org.springframework.data.annotation.Id;
public class UserType {
	@Id String userTypeId;
	String userTypeName;
	
	public String getUserTypeId() {
		return userTypeId;
	}

	public String getUserTypeName() {
		return userTypeName;
	}
	public void setUserTypeName(String userTypeName) {
		this.userTypeName = userTypeName;
	}
	
}
