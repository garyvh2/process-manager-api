package ac.cr.ucenfotec.process_manager.processmanagerapi.controllers;



import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ac.cr.ucenfotec.process_manager.entities.ProcessTemplate;
import ac.cr.ucenfotec.process_manager.processmanagerapi.exceptions.NotFoundException;
import ac.cr.ucenfotec.process_manager.processmanagerapi.repositories.ProcessRepository;

@RestController
@RequestMapping("/processes")
public class ProcessController {
	
	@Autowired
	private ProcessRepository repository;
	
	@CrossOrigin
	@GetMapping("/getAll")
    public List<ProcessTemplate> getAll()
	{		 
		return  repository.findAll();
    }
	
	@CrossOrigin
	@GetMapping("/getProcess/{processId}")
	public ProcessTemplate getProcess (@PathVariable String processId) {
		
		Optional<ProcessTemplate> process = repository.findById(processId);
		if(!process.isPresent()) {
			throw new NotFoundException("Couldn't find process: id- " + processId);
		}
		
		return process.get();
	}
	
	@CrossOrigin
	@PostMapping
	public ProcessTemplate postProcess(@RequestBody ProcessTemplate process) {
		
		return repository.save(process);
	}
	
	@CrossOrigin
	@PutMapping("/{processId}")
	public ProcessTemplate updateProcess(@PathVariable String processId, @Valid @RequestBody  ProcessTemplate process) {
		
		Optional<ProcessTemplate> processTmp = repository.findById(processId);
		if(!processTmp.isPresent()) {
			throw new NotFoundException("Couldn't find process: id- " + processId);
		}
		process.setNumeroTramite(processId);
		repository.save(process);
		return repository.save(process); 
	}
	
	@CrossOrigin
	@RequestMapping(value = "/{processId}", method = RequestMethod.DELETE)
	public ProcessTemplate deleteProcess(@PathVariable String processId){
		
		Optional<ProcessTemplate> userT = repository.findById(processId);
		if(!userT.isPresent()) {
			throw new NotFoundException("Couldn't find process: id- " + processId);
		}
		repository.deleteById(processId);
		
		return userT.get(); 
	}
	
}
