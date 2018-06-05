package ac.cr.ucenfotec.process_manager.entities;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection ="groups")
public class Group {
	
	private String GroupName;

	public String getGroupName() {
		return GroupName;
	}

	public void setGroupName(String groupName) {
		GroupName = groupName;
	}	

}
