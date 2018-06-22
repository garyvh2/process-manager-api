package ac.cr.ucenfotec.process_manager.processmanagerapi.exceptionhandler;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ac.cr.ucenfotec.process_manager.entities.ExceptionMessage;

@ControllerAdvice
@RestController
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{

	public ApiExceptionHandler() {
		// TODO Auto-generated constructor stub
	}
	 @Override
	 protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
	    HttpHeaders headers, HttpStatus status, WebRequest request) {
		 ExceptionMessage errorDetails = new ExceptionMessage(new Date(), "Validation Failed",
	    ex.getBindingResult().toString());
	    return new ResponseEntity<Object>(errorDetails, HttpStatus.BAD_REQUEST);
	} 

}
