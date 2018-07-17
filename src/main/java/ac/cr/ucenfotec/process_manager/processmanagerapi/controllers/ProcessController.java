package ac.cr.ucenfotec.process_manager.processmanagerapi.controllers;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ac.cr.ucenfotec.process_manager.entities.ProcessTemplate;
import ac.cr.ucenfotec.process_manager.entities.Task;
import ac.cr.ucenfotec.process_manager.entities.UserType;
import ac.cr.ucenfotec.process_manager.enums.Status;
import ac.cr.ucenfotec.process_manager.processmanagerapi.exceptions.NotFoundException;
import ac.cr.ucenfotec.process_manager.processmanagerapi.repositories.ProcessRepository;

@RestController
@RequestMapping("/processes")
public class ProcessController {
	
	@Autowired
	private ProcessRepository repository;
	
	@GetMapping
    public List<ProcessTemplate> getAll(){		 
		return  repository.findAll();
    }
	
	@GetMapping("/{processId}")
	public ResponseEntity<ProcessTemplate> getProcess (@PathVariable String processId) {
		
		Optional<ProcessTemplate> process = repository.findById(processId);
		if(!process.isPresent()) {
			throw new NotFoundException("id- " + processId);
		}
		
		return new ResponseEntity<ProcessTemplate>(process.get(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<ProcessTemplate> postProcess(@Valid @RequestBody ProcessTemplate process) {
		
		ProcessTemplate newProcess = repository.save(process);
		return new ResponseEntity<ProcessTemplate>( newProcess, HttpStatus.OK );
	}
	
	@PutMapping("/{processId}")
	public ResponseEntity<?> updateProcess(@PathVariable String processId, @Valid @RequestBody  ProcessTemplate process) {
		
		Optional<ProcessTemplate> processTmp = repository.findById(processId);
		if(!processTmp.isPresent()) {
			throw new NotFoundException("id- " + processId);
		}
		process.setNumeroTramite(processId);
		repository.save(process);
		return new ResponseEntity<ProcessTemplate>(repository.save(process), HttpStatus.OK); 
	}
	
	@RequestMapping(value = "/{processId}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteProcess(@PathVariable String processId){
		
		Optional<ProcessTemplate> userT = repository.findById(processId);
		if(!userT.isPresent()) {
			throw new NotFoundException("id- " + processId);
		}
		repository.deleteById(processId);
		
		return new ResponseEntity<ProcessTemplate>(userT.get(), HttpStatus.OK); 
	}
	
	

}
