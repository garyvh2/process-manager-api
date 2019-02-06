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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((numeroTramite == null) ? 0 : numeroTramite.hashCode());
		result = prime * result + ((requester == null) ? 0 : requester.hashCode());
		result = prime * result + ((tasks == null) ? 0 : tasks.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof RootProcess))
			return false;
		RootProcess other = (RootProcess) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (numeroTramite == null) {
			if (other.numeroTramite != null)
				return false;
		} else if (!numeroTramite.equals(other.numeroTramite))
			return false;
		if (requester == null) {
			if (other.requester != null)
				return false;
		} else if (!requester.equals(other.requester))
			return false;
		if (tasks == null) {
			if (other.tasks != null)
				return false;
		} else if (!tasks.equals(other.tasks))
			return false;
		return true;
	}
	
	

}
