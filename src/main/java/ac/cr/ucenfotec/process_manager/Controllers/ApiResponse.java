package ac.cr.ucenfotec.process_manager.Controllers;

public class ApiResponse {
	public String message;
	public Object data;
	
	
	public ApiResponse() {
		super();
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public Object getData() {
		return data;
	}


	public void setData(Object data) {
		this.data = data;
	}
	

}
