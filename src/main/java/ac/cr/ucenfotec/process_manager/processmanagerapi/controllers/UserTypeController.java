package ac.cr.ucenfotec.process_manager.processmanagerapi.controllers;


import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

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

import ac.cr.ucenfotec.process_manager.entities.UserType;
import ac.cr.ucenfotec.process_manager.processmanagerapi.repositories.UserTypeRepository;

@RestController
@RequestMapping("/usertypes")
public class UserTypeController {

	@Autowired
	private UserTypeRepository repository;
	@GetMapping
    public List<UserType> getAll(){
		 
		return  repository.findAll();
    }
	
	@PostMapping
	public void PostUserType(@RequestBody UserType ut) {
		repository.save(ut);
	}
	
	@PutMapping("/{userId}")
	public ResponseEntity<?> UpdateUserType(@PathVariable String userId,@RequestBody  UserType ut) {
		Optional<UserType> userT = repository.findById(userId);
		if(!userT.isPresent()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		//TODO: Validation and other things
		repository.save(ut);
		return new ResponseEntity<>(HttpStatus.OK); 
	}
	
}
