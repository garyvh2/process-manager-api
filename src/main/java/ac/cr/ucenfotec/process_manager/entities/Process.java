package ac.cr.ucenfotec.process_manager.entities;

import java.util.ArrayList;

import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
@Document(collection ="processes")
public class Process {
	
	@NotNull @Positive
	private int NumeroTramite;
	@NotBlank @NotNull @NotEmpty
	private String Description;
	@NotNull
	private ArrayList<Task> Tasks;
	private User Requester;
	
	public int getNumeroTramite() {
		return NumeroTramite;
	}
	public void setNumeroTramite(int numeroTramite) {
		NumeroTramite = numeroTramite;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String descripcion) {
		Description = descripcion;
	}
	public ArrayList<Task> getTasks() {
		return Tasks;
	}
	public void setTasks(ArrayList<Task> tareas) {
		Tasks = tareas;
	}
	public void setTask(Task tarea) {
		if(Tasks == null )
			Tasks = new ArrayList<Task>();
		
		Tasks.add(tarea);
		
	}
	public User getSolicitante() {
		return Requester;
	}
	public void setSolicitante(User solicitante) {
		Requester = solicitante;
	}
}
