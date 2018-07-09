package ac.cr.ucenfotec.process_manager.entities;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Document(collection ="processes")
public class ProcessTemplate {
	
	@Id
	String numeroTramite;
	
	@NotBlank @NotNull @NotEmpty
	private String description;
	
	@NotNull
	private ArrayList<Task> tasks;
	private User requester;
	
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
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof ProcessTemplate))
			return false;
		ProcessTemplate other = (ProcessTemplate) obj;
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
		return true;
	}	
	
}
