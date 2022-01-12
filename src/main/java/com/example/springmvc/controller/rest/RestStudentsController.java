package com.example.springmvc.controller.rest;

import com.example.springmvc.model.Student;
import com.example.springmvc.model.StudentGroup;
import com.example.springmvc.service.StudentGroupsServiceImpl;
import com.example.springmvc.service.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RequestMapping("/rest/students")
@RestController
public class RestStudentsController {

    private final StudentServiceImpl studentsService;

    @Autowired
    public RestStudentsController(StudentServiceImpl studentsService, StudentGroupsServiceImpl studentGroupsService) {
        this.studentsService = studentsService;
    }

    // Rest return students list
    @GetMapping(value = "")
    @ResponseBody
    public ResponseEntity<List<Student>> read() {
        final List<Student> students = studentsService.getAllStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Student> readStudentById(@PathVariable("id") long id) {
        final Student student = studentsService.getStudent(id);
        if(student == null) return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @PostMapping("")
    @ResponseBody
    public ResponseEntity<?> create(@Valid @RequestBody Student student) {
        studentsService.saveStudent(student);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable("id") long id,@Valid @RequestBody Student student) {
        if(studentsService.getStudent(id) == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        student.setId(id);
        studentsService.updateStudent(student);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") long id) {
        studentsService.deleteStudent(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public class ResourceNotFoundException extends RuntimeException {

    }

}
