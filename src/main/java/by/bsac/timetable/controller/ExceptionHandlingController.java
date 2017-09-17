package by.bsac.timetable.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import by.bsac.timetable.service.exception.ServiceException;
import by.bsac.timetable.service.exception.ServiceValidationException;

@RestController
public class ExceptionHandlingController {

  @ExceptionHandler(ServiceValidationException.class)
  public String validationError(Exception ex) {
    return "invalid params";
  }

  @ExceptionHandler(ServiceException.class)
  public String dataBaseError(Exception ex) {
    return "dataBaseError";
  }
}
