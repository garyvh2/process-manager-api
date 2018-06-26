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
import ac.cr.ucenfotec.process_manager.entities.User;
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
	
	@GetMapping("/{userId}")
	public ResponseEntity<User> getUser (@PathVariable String userId) {
		Optional<User> user = repository.findById(userId);
		
		if(!user.isPresent()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}		
		return new ResponseEntity<User>(user.get(), HttpStatus.OK);
	}
	
	@PostMapping("/create")
	public ResponseEntity<User> postUser(@Valid @RequestBody User user) {
		return new ResponseEntity<User>( repository.save(user), HttpStatus.OK );
	}
	
	@PutMapping("/{userId}")
	public ResponseEntity<?> updateUser(@PathVariable String userId,@Valid @RequestBody  User user) {
		Optional<User> userT = repository.findById(userId);
		
		if(!userT.isPresent()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		user.setUserId(userId);
		repository.save(user);
		return new ResponseEntity<>(HttpStatus.OK); 
	}
	
	@RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteUser(@PathVariable String userId){
		Optional<User> userT = repository.findById(userId);
		
		if(!userT.isPresent()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		repository.deleteById(userId);
		
		return new ResponseEntity<>(HttpStatus.OK); 
	}
	
	@GetMapping("/authenticateUser/{userMail}/{userPassword}")
	public User authenticateUser (@PathVariable String userMail, @PathVariable String userPassword) {

		List<User> user = repository.findAll();
		
		if(!user.isEmpty()) {
			for (User userDB : user) {
				if(userDB.getUserEmail().equals(userMail) &&
				   userDB.getUserPassword().equals(userPassword))
					return userDB;
			}			
		}		
		return null;
	}

}
