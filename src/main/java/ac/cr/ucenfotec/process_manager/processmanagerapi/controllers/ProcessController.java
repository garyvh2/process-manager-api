package ac.cr.ucenfotec.process_manager.processmanagerapi.controllers;

import java.util.List;
import java.util.Optional;

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
	public ResponseEntity<Process> getProcess (@PathVariable int processId) {
		Optional<Process> process = repository.findOneByNumeroTramite(processId);
		if(!process.isPresent()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<Process>(process.get(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Process> postProcess(@RequestBody Process process) {
		return new ResponseEntity<Process>( repository.save(process), HttpStatus.OK );
	}
	
	@PutMapping("/{processId}")
	public ResponseEntity<?> updateProcess(@PathVariable int processId,@RequestBody  Process process) {
		Optional<Process> processTmp = repository.findOneByNumeroTramite(processId);
		if(!processTmp.isPresent()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		process.setNumeroTramite(processId);
		repository.save(process);
		return new ResponseEntity<>(HttpStatus.OK); 
	}
	
	@RequestMapping(value = "/{processId}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteProcess(@PathVariable int processId){
		Optional<Process> userT = repository.findOneByNumeroTramite(processId);
		if(!userT.isPresent()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		repository.deleteByNumeroTramite(processId);
		
		return new ResponseEntity<>(HttpStatus.OK); 
	}

}
