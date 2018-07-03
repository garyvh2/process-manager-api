package ac.cr.ucenfotec.process_manager.processmanagerapi.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ac.cr.ucenfotec.process_manager.entities.Process;
import ac.cr.ucenfotec.process_manager.entities.Task;
import ac.cr.ucenfotec.process_manager.entities.UserType;
import ac.cr.ucenfotec.process_manager.enums.Estado;
import ac.cr.ucenfotec.process_manager.processmanagerapi.repositories.ProcessRepository;

@RestController
@RequestMapping("/tasks")
public class TaskController {
	@Autowired
	private ProcessRepository repository;
	
	
	@PostMapping("/getUserGroupTask")
	public ArrayList<Task> getUserTask(@RequestBody UserType usertype){
	
		ArrayList<Task> userTaskList = new ArrayList<>();
		
		getFirstTaskInProcess(userTaskList, usertype);
		
		
		return userTaskList;
	}
	
	@PutMapping
	public void updateTask(Task updateTask) {
		
		
	}
	
	private void getFirstTaskInProcess(List<Task> userTasks, UserType pUserType) {
		List<Process> listaProcesos = repository.findByFirstTask(pUserType);
		for(Process process : listaProcesos) {
			userTasks.add(process.getTasks().get(0));
		}
	}

	
}
