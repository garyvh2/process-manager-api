package ac.cr.ucenfotec.process_manager.entities;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection ="usertypes")
public class UserType {
	@Id 
	String userTypeId;
	@NotNull @NotBlank	@NotEmpty
	@Size(min=2, message="User type name should have atleast 2 characters")
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
