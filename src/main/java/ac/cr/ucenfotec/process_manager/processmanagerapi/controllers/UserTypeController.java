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

import ac.cr.ucenfotec.process_manager.entities.UserType;
import ac.cr.ucenfotec.process_manager.processmanagerapi.exceptions.UserTypeNotFoundException;
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
	
	@GetMapping("/{usertypeId}")
	public ResponseEntity<UserType> getUserType (@PathVariable String usertypeId) {
		Optional<UserType> userT = repository.findById(usertypeId);
		if(!userT.isPresent()) {
			throw new UserTypeNotFoundException("id- " + usertypeId);
		}
		
		return new ResponseEntity<UserType>(userT.get(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<UserType> postUserType(@Valid @RequestBody UserType ut) {
		return new ResponseEntity<UserType>( repository.save(ut), HttpStatus.OK );
	}
	
	@PutMapping("/{usertypeId}")
	public ResponseEntity<?> updateUserType(@PathVariable String usertypeId,@Valid @RequestBody  UserType ut) {
		Optional<UserType> userT = repository.findById(usertypeId);
		if(!userT.isPresent()) {
			throw new UserTypeNotFoundException("id- " + usertypeId);
		}
		ut.setUserTypeId(usertypeId);
		//TODO: Validation and other things
		UserType updatedUserType = repository.save(ut);
		return new ResponseEntity<UserType>(updatedUserType, HttpStatus.OK); 
	}
	
	@RequestMapping(value = "/{usertypeId}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteUserType(@PathVariable String usertypeId){
		Optional<UserType> userT = repository.findById(usertypeId);
		if(!userT.isPresent()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		repository.deleteById(usertypeId);
		
		return new ResponseEntity<UserType>(userT.get(), HttpStatus.OK); 
		
	}
	
}
