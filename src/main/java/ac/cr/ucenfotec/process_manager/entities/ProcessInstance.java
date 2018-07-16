package ac.cr.ucenfotec.process_manager.entities;

import ac.cr.ucenfotec.process_manager.enums.Status;
import ac.cr.ucenfotec.process_manager.entities.ProcessTemplate;

public class ProcessInstance extends RootProcess{
	
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
	public ProcessInstance(ProcessTemplate pt) {
		this.description = pt.description;
		this.requester = pt.requester;
		
	}

	private Status status;

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	

}
