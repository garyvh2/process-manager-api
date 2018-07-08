package ac.cr.ucenfotec.process_manager.entities;

import ac.cr.ucenfotec.process_manager.enums.Status;

public class ProcessInstance extends Process{
	private Status status;

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	

}
