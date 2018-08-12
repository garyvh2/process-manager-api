package ac.cr.ucenfotec.process_manager.entities;

import java.util.ArrayList;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;

public class RootProcess {

	public RootProcess() {
		
	}
	
	@Id
	protected String numeroTramite;
	
	@NotBlank @NotNull @NotEmpty
	protected String description;
	
	@NotNull
	protected ArrayList<Task> tasks;
	
	protected User requester;
	
	public String getNumeroTramite() {
		return numeroTramite;
	}
	
	public void setNumeroTramite(String numeroTramite) {
		this.numeroTramite = numeroTramite;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String descripcion) {
		description = descripcion;
	}
	
	public ArrayList<Task> getTasks() {
		return tasks;
	}
	
	public void setTasks(ArrayList<Task> tareas) {
		tasks = tareas;
	}
	
	public void setTask(Task tarea) {
		if(tasks == null )
			tasks = new ArrayList<Task>();
		
		tasks.add(tarea);		
	}
	
	public User getRequester() {
		return requester;
	}
	
	public void setRequester(User requester) {
		this.requester = requester;
	}

}
