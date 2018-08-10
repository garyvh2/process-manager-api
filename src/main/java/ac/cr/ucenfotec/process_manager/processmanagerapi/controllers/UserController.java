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
import ac.cr.ucenfotec.process_manager.entities.User;
import ac.cr.ucenfotec.process_manager.processmanagerapi.exceptions.NotFoundException;
import ac.cr.ucenfotec.process_manager.processmanagerapi.repositories.UserRepository;


@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserRepository repository;
	
	@GetMapping
    public List<User> getAll(){		 
		return  repository.findAll();
    }
	
	@CrossOrigin
	@GetMapping("/{userId}")
	public User getUser (@PathVariable String userId) {
		
		Optional<User> user = repository.findById(userId);
		
		if(!user.isPresent()) {
			throw new NotFoundException("Couldn't find user");
		}		
		return user.get();
	}
	
	@CrossOrigin
	@PostMapping
	public User postUser(@Valid @RequestBody User user) {
		return repository.save(user);
	}
	
	@CrossOrigin
	@PutMapping("/{userId}")
	public User updateUser(@PathVariable String userId,@Valid @RequestBody  User user) {
		
		Optional<User> userT = repository.findById(userId);
		
		if(!userT.isPresent()) {
			throw new NotFoundException("Couldn't find user");
		}
		user.setUserId(userId);
		return repository.save(user);
		 
	}
	
	@CrossOrigin
	@RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
	public User deleteUser(@PathVariable String userId){
		
		Optional<User> user = repository.findById(userId);
		
		if(!user.isPresent()) {
			throw new NotFoundException("Couldn't find user");
		}
		repository.deleteById(userId);
		
		return user.get(); 
	}
	
	@CrossOrigin
	@PostMapping("/authenticateUser")
	public User authenticateUser (@RequestBody  User user) {
		
		Optional<User> authUser =  repository.findByUserEmailAndUserPassword(user.getUserEmail(), user.getUserPassword());
		if(!authUser.isPresent()) {
			throw new NotFoundException("Incorrect user o password");
		}

		return authUser.get();
	}
}
