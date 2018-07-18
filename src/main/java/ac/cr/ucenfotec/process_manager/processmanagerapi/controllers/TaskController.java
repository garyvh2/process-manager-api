package ac.cr.ucenfotec.process_manager.processmanagerapi.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ac.cr.ucenfotec.process_manager.entities.ProcessTemplate;
import ac.cr.ucenfotec.process_manager.entities.RootProcess;
import ac.cr.ucenfotec.process_manager.entities.ProcessInstance;
import ac.cr.ucenfotec.process_manager.entities.Task;
import ac.cr.ucenfotec.process_manager.entities.UserType;
import ac.cr.ucenfotec.process_manager.enums.Status;
import ac.cr.ucenfotec.process_manager.processmanagerapi.exceptions.NotFoundException;
import ac.cr.ucenfotec.process_manager.processmanagerapi.repositories.ProcessInstanceRepository;
import ac.cr.ucenfotec.process_manager.processmanagerapi.repositories.ProcessRepository;
import java.util.Optional;

import javax.validation.Valid;
@RestController
@RequestMapping("/tasks")
public class TaskController {
	@Autowired
	private ProcessRepository repository;
	
	@Autowired
	private ProcessInstanceRepository instanceRepository;
	
	@CrossOrigin
	@GetMapping("/getUserGroupTask/{idUserGroup}")
	public ArrayList<Task> getUserTask(@PathVariable String idUserGroup){
	
		ArrayList<Task> userTaskList = new ArrayList<>();
		ObjectId mongoId = new ObjectId(idUserGroup);
		getFirstTaskInProcess(userTaskList, mongoId);
		getTaskInInstaces(userTaskList, mongoId);
		
		return userTaskList;
	}
	
	@CrossOrigin
	@PutMapping
	public void updateTask(@Valid @RequestBody Task updateTask) {
		
		Optional<ProcessTemplate> initProcess = repository.findById(updateTask.getFatherProcess());
		ProcessInstance pInstance;
		if(initProcess.isPresent()) {
			ArrayList<Task> tasks = initProcess.get().getTasks();
			updateTaskList(updateTask, tasks);
			pInstance = new ProcessInstance(initProcess.get());
			pInstance.setTasks(tasks);
			pInstance.setStatus(Status.EN_PROCESO);
		}else {
			
			Optional<ProcessInstance>  updateInstace = instanceRepository.findById(updateTask.getFatherProcess());
			if(updateInstace.isPresent()) {
				pInstance = updateInstace.get();
				ArrayList<Task> tasks = pInstance.getTasks();
				updateTaskList(updateTask, tasks);
				pInstance.setTasks(tasks);
				Task lastTask = pInstance.getTasks().get(pInstance.getTasks().size() - 1);
				if( lastTask.getDescription().equals(updateTask.getDescription())) {
					pInstance.setStatus(Status.COMPLETADO);
				}
			} else {
				throw new NotFoundException("Task does not exist");
			}
			
		}
		instanceRepository.save(pInstance);
		
		
		
	}

	private void updateTaskList(Task updateTask, List<Task> tasks) {
		
		int index;
		Task lastTask = tasks.get(tasks.size() - 1);
		for (Task task : tasks) {
			if(task.getDescription().equals(updateTask.getDescription())) {
				index = tasks.indexOf(task);
				updateTask.setTaskStaus(Status.COMPLETADO);
				tasks.set(index, updateTask);
				
				if(!task.getDescription().equals(lastTask.getDescription())) {
					tasks.get(index + 1).setTaskStaus(Status.EN_PROCESO);
				}
			}
		}
	}
	
	private void getFirstTaskInProcess(List<Task> userTasks, ObjectId pUserType) {
		
		List<ProcessTemplate> listaProcesos = repository.findByFirstTask(pUserType);
		for(ProcessTemplate process : listaProcesos) {
			Task userTask = process.getTasks().get(0);
			userTask.setFatherProcess(process.getNumeroTramite());
			userTasks.add(userTask);
		}
	}
	
	private void getTaskInInstaces(List<Task> userTasks, ObjectId pUserType) {
		
		List<ProcessInstance> InstaceProcessList =  instanceRepository.findInstanceTask(pUserType, Status.EN_PROCESO);
		for (ProcessInstance processInstance : InstaceProcessList) {
			Task userTask = processInstance.getTasks()
							.stream()
							.filter(task -> pUserType.toString().equals(task.getUserGroup().getUserTypeId()))
							.findAny()
							.orElse(null);
			userTask.setFatherProcess(processInstance.getNumeroTramite());
			userTasks.add(userTask);
		}
	}

	
}
