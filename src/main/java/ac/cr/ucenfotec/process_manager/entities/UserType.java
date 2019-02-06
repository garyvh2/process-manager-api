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
	@Size(min=2, message="User type name should have at least 2 characters")
	String userTypeName;
	
	public UserType() {
		
	}
	
	public UserType(String userTypeId, String userTypeName) {
		this.userTypeId = userTypeId;
		this.userTypeName = userTypeName;
	}
	
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


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserType other = (UserType) obj;
		if (userTypeId == null) {
			if (other.userTypeId != null)
				return false;
		} else if (!userTypeId.equals(other.userTypeId))
			return false;
		if (userTypeName == null) {
			if (other.userTypeName != null)
				return false;
		} else if (!userTypeName.equals(other.userTypeName))
			return false;
		return true;
	}	
	
}
