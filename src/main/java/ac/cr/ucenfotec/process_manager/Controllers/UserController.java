package ac.cr.ucenfotec.process_manager.Controllers;
import ac.cr.ucenfotec.process_manager.entities.User;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	
	ApiResponse apiResp = new ApiResponse();

    @GetMapping
    public ApiResponse getUser() {
        apiResp = new ApiResponse();
        apiResp.message = "Action was executed.";
        
    	return (apiResp);
    }
    
    @GetMapping
    public ApiResponse getUser(Object object) {
        apiResp = new ApiResponse();
        apiResp.message = "Action was executed.";
        
    	return (apiResp);
    }
    
    
    @PostMapping
    public ApiResponse PostUser(Object usuario) {
    	apiResp = new ApiResponse();
        apiResp.message = "Action was executed.";
        
    	return (apiResp);
    }
    
    @PutMapping
    public ApiResponse PutUser(Object usuario) {
    	apiResp = new ApiResponse();
        apiResp.message = "Action was executed.";
        
    	return (apiResp);
    }
    
    @DeleteMapping
    public ApiResponse DeleteUser(Object usuario) {
    	apiResp = new ApiResponse();
        apiResp.message = "Action was executed.";
        
    	return (apiResp);
    }
    
}
