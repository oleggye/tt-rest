package by.bsac.timetable.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import by.bsac.timetable.entity.Faculty;
import by.bsac.timetable.entity.Group;
import by.bsac.timetable.entity.Record;
import by.bsac.timetable.service.IFacultyService;
import by.bsac.timetable.service.IGroupService;
import by.bsac.timetable.service.IRecordService;
import by.bsac.timetable.service.exception.ServiceException;
import by.bsac.timetable.service.exception.ServiceValidationException;

@RestController
public class TimetableController {

  @Autowired
  private IRecordService service;

  @Autowired
  private IGroupService groupService;

  @Autowired
  private IFacultyService facultyService;


  @RequestMapping(value = "/timetable/{idGroup}", method = RequestMethod.GET)
  public List<Record> getGroupTimetable(@PathVariable Short idGroup,
      @RequestParam(value = "from") Date dateFrom, @RequestParam(value = "to") Date dateTo)
      throws ServiceException, ServiceValidationException {

    Group group = groupService.getGroupById(idGroup);
    return service.getAllRecordListForGroup(group, dateFrom, dateTo);
  }

  @RequestMapping(value = "/timetable/faculty", method = RequestMethod.GET)
  public List<Faculty> getFacultyList() throws ServiceException, ServiceValidationException {
    return facultyService.getAllFaculties();
  }

  @RequestMapping(value = "/timetable/faculty/{id}/group", method = RequestMethod.GET)
  public List<Group> getGroupListByFaculty(@PathVariable(value = "id") Byte idFaculty,
      @RequestParam(value = "educationLevel", required = false) Byte educationLevel)
      throws ServiceException, ServiceValidationException {

    Faculty faculty = facultyService.getFacultyById(idFaculty);

    if (educationLevel != null) {
      return groupService.getGroupListByFacultyAndEduLevel(faculty, educationLevel);
    } else {
      return groupService.getGroupListByFaculty(faculty);
    }
  }
}
