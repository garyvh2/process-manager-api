package ac.cr.ucenfotec.process_manager.entities;

import ac.cr.ucenfotec.process_manager.enums.Status;
import ac.cr.ucenfotec.process_manager.entities.ProcessTemplate;
public class ProcessInstance extends ProcessTemplate{
	/**
	 * 
	 */
	public ProcessInstance() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	/**
	 * @param status
	 */
	public ProcessInstance(Status status) {
		super();
		this.status = status;
	}


	private Status status;

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	

}
