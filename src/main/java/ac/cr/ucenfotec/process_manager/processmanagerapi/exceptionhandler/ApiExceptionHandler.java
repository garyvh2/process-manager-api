package ac.cr.ucenfotec.process_manager.processmanagerapi.exceptionhandler;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ac.cr.ucenfotec.process_manager.entities.ExceptionMessage;
import ac.cr.ucenfotec.process_manager.processmanagerapi.exceptions.NotFoundException;

@ControllerAdvice
@RestController
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{

	  @ExceptionHandler(Exception.class)
	  public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
		  ExceptionMessage errorDetails = new ExceptionMessage(new Date(), ex.getMessage(),
	        request.getDescription(false));
	    return new ResponseEntity<Object>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	  }
	  
	  @ExceptionHandler(NotFoundException.class)
	  public final ResponseEntity<Object> handleUserTypeNotFoundException(NotFoundException ex, WebRequest request){
		  ExceptionMessage errorDetails = new ExceptionMessage(new Date(), ex.getMessage(),
			        request.getDescription(false));
			    return new ResponseEntity<Object>(errorDetails, HttpStatus.NOT_FOUND);
	  }
	 @Override
	 protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
	    HttpHeaders headers, HttpStatus status, WebRequest request) {
		 ExceptionMessage errorDetails = new ExceptionMessage(new Date(), "Validation Failed",
	    ex.getBindingResult().getFieldError().toString());
	    return new ResponseEntity<Object>(errorDetails, HttpStatus.BAD_REQUEST);
	} 

}
