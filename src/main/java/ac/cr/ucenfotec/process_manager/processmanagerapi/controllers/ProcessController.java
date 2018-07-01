package ac.cr.ucenfotec.process_manager.processmanagerapi.controllers;

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

import ac.cr.ucenfotec.process_manager.entities.Process;
import ac.cr.ucenfotec.process_manager.processmanagerapi.exceptions.NotFoundException;
import ac.cr.ucenfotec.process_manager.processmanagerapi.repositories.ProcessRepository;

@RestController
@RequestMapping("/processes")
public class ProcessController {
	
	@Autowired
	private ProcessRepository repository;
	
	@GetMapping
    public List<Process> getAll(){		 
		return  repository.findAll();
    }
	
	@GetMapping("/{processId}")
	public ResponseEntity<Process> getProcess (@PathVariable String processId) {
		Optional<Process> process = repository.findById(processId);
		if(!process.isPresent()) {
			throw new NotFoundException("id- " + processId);
		}
		
		return new ResponseEntity<Process>(process.get(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Process> postProcess(@Valid @RequestBody Process process) {
		Process newProcess = repository.save(process);
		return new ResponseEntity<Process>( newProcess, HttpStatus.OK );
	}
	
	@PutMapping("/{processId}")
	public ResponseEntity<?> updateProcess(@PathVariable String processId, @Valid @RequestBody  Process process) {
		Optional<Process> processTmp = repository.findById(processId);
		if(!processTmp.isPresent()) {
			throw new NotFoundException("id- " + processId);
		}
		process.setNumeroTramite(processId);
		repository.save(process);
		return new ResponseEntity<Process>(repository.save(process), HttpStatus.OK); 
	}
	
	@RequestMapping(value = "/{processId}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteProcess(@PathVariable String processId){
		Optional<Process> userT = repository.findById(processId);
		if(!userT.isPresent()) {
			throw new NotFoundException("id- " + processId);
		}
		repository.deleteById(processId);
		
		return new ResponseEntity<Process>(userT.get(), HttpStatus.OK); 
	}

}
