package com.example.springmvc.controller;

import com.example.springmvc.model.Student;
import com.example.springmvc.service.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequestMapping("/students")
@RestController
public class StudentsController {

    private final StudentServiceImpl studentsService;

    @Autowired
    public StudentsController(StudentServiceImpl studentsService) {
        this.studentsService = studentsService;

        // Create test student object in repository for test
        Student tempStudent = new Student();
        tempStudent.setName("FirstStudent");
        tempStudent.setEmail("GGG@GGG");
        tempStudent.setPhone("14441255");
        tempStudent.setGroupID(1);
        studentsService.save(tempStudent);
    }

    // Rest return students list
    @GetMapping(value = "/")
    public ResponseEntity<List<Student>> read() {
        final List<Student> students = studentsService.getAll();

        return students != null &&  !students.isEmpty()
                ? new ResponseEntity<>(students, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // CRUD Create
    @GetMapping("/new")
    public ModelAndView createStudent() {
        Student student = new Student();
        return new ModelAndView("student/studentEdit")
                .addObject("student", student);
    }

    // CRUD Read
    @GetMapping("/{id}")
    public ModelAndView viewStudent(@PathVariable("id") long id) {
        Student student = studentsService.get(id);
        if (student == null) {
            throw new ResourceNotFoundException();
        }

        ModelAndView model = new ModelAndView("student/studentView");

        model.addObject("student", student);
        return model;
    }

    // CRUD Update
    @GetMapping("/{id}/edit")
    public ModelAndView editStudent(@PathVariable("id") int id) {
        Student student = studentsService.get(id);

        return new ModelAndView("student/studentEdit")
                .addObject("student", student);
    }

    // CRUD Delete
    @PostMapping(value = "/delete")
    public ModelAndView delete(Student studentID) {
        Student student = studentsService.get(studentID.getId());
        if (student == null) {
            throw new ResourceNotFoundException();
        }else{
            studentsService.delete(studentID.getId());
        }
        return new ModelAndView("redirect:/students/");
    }

    // For create and update class for save obj in repository
    @PostMapping("/save")
    public ModelAndView saveStudent(Student student) {
        if(studentsService.get(student.getId()) != null){
            studentsService.update(student,student.getId());
        }else {
            studentsService.save(student);
        }

        return new ModelAndView("redirect:/students/" + student.getId());
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public class ResourceNotFoundException extends RuntimeException {

    }

}
