package in.shuvam.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UsersExceptionController {
	@ExceptionHandler(value= IdException.class)
	public ResponseEntity<Object> exception(IdException exception){
		return new ResponseEntity<Object>("Id not found.",HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(value= RoleException.class)
	public ResponseEntity<Object> exception(RoleException exception){
		return new ResponseEntity<Object>("You cannot sign up as Admin.\nTry contacting support.",HttpStatus.BAD_REQUEST);
	}
	

}
