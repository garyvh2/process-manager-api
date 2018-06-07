package ac.cr.ucenfotec.process_manager.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection ="usertypes")
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

	public void setUserTypeId(String userTypeId) {
		this.userTypeId = userTypeId;
	}
	
}
