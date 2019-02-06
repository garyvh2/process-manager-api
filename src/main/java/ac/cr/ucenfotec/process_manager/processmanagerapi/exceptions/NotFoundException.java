package ac.cr.ucenfotec.process_manager.processmanagerapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException{

	private static final long serialVersionUID = 8752752767561613624L;

	public NotFoundException(String exception) {
		    super(exception);
	}
}
