package ac.cr.ucenfotec.process_manager.entities;

import java.util.ArrayList;


public class Process {
	private int NumeroTramite;
	private String Description;
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
