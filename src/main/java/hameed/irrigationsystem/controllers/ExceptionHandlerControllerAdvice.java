package hameed.irrigationsystem.controllers;

import hameed.irrigationsystem.exceptions.NotFoundErrorResponse;
import hameed.irrigationsystem.exceptions.SensorInactiveAlert;
import hameed.irrigationsystem.exceptions.SensorInactiveException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerControllerAdvice {

    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<NotFoundErrorResponse> handleEntityNotFound(EntityNotFoundException exception) {
        NotFoundErrorResponse error = new NotFoundErrorResponse();

        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exception.getMessage());
        error.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SensorInactiveException.class)
    public ResponseEntity<SensorInactiveAlert> handleSensorInactiveException(SensorInactiveException exception) {
        SensorInactiveAlert alert = new SensorInactiveAlert();

        alert.setStatus("Inactive");
        alert.setMessage(exception.getMessage());
        alert.setTimestamp(System.currentTimeMillis());
        alert.setMaxRetryAttempts(5);

        return new ResponseEntity<>(alert, HttpStatus.NOT_FOUND);
    }

}
