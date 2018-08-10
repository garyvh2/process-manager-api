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

import ac.cr.ucenfotec.process_manager.entities.UserType;
import ac.cr.ucenfotec.process_manager.processmanagerapi.exceptions.NotFoundException;
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
	
	@CrossOrigin
	@GetMapping("/{usertypeId}")
	public UserType getUserType (@PathVariable String usertypeId) {
		
		Optional<UserType> userT = repository.findById(usertypeId);
		if(!userT.isPresent()) {
			throw new NotFoundException("id- " + usertypeId);
		}
		
		return  userT.get();
	}
	
	@CrossOrigin
	@PostMapping
	public UserType postUserType(@Valid @RequestBody UserType ut) {
		return  repository.save(ut);
	}
	
	@CrossOrigin
	@PutMapping("/{usertypeId}")
	public UserType updateUserType(@PathVariable String usertypeId,@Valid @RequestBody  UserType ut) {
		
		Optional<UserType> userT = repository.findById(usertypeId);
		if(!userT.isPresent()) {
			throw new NotFoundException("id- " + usertypeId);
		}
		ut.setUserTypeId(usertypeId);
		UserType updatedUserType = repository.save(ut);
		return updatedUserType; 
	}
	
	@CrossOrigin
	@RequestMapping(value = "/{usertypeId}", method = RequestMethod.DELETE)
	public UserType deleteUserType(@PathVariable String usertypeId){
		
		Optional<UserType> userT = repository.findById(usertypeId);
		if(!userT.isPresent()) {
			throw new NotFoundException("id- " + usertypeId);
		}
		repository.deleteById(usertypeId);
		
		return userT.get(); 		
	}	
}
