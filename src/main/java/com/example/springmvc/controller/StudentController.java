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
public class StudentController {

    private final StudentServiceImpl studentsService;

    @Autowired
    public StudentController(StudentServiceImpl studentsService) {
        this.studentsService = studentsService;

        Student tempStudent = new Student();
        tempStudent.setName("FirstStudent");
        tempStudent.setEmail("GGG@GGG");
        tempStudent.setPhone("14441255");
        tempStudent.setGroupID(1);
        studentsService.create(tempStudent);
    }

    //Rest get object list
    @GetMapping(value = "/")
    public ResponseEntity<List<Student>> read() {
        final List<Student> students = studentsService.readAll();

        return students != null &&  !students.isEmpty()
                ? new ResponseEntity<>(students, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/new")
    public ModelAndView createStudent() {
        Student student = new Student();
        return new ModelAndView("student/studentEdit")
                .addObject("student", student);
    }

    @PostMapping("/save")
    public ModelAndView saveStudent(Student student) {
        if(student.getId() != null){
            studentsService.update(student,student.getId());
        }else {
            studentsService.create(student);
        }

        // redirect позволит не избежать повторного сохранения
        // если пользователь нажмет F5
        return new ModelAndView("redirect:/students/" + student.getId());
    }

    @GetMapping("/{id}/edit")
    public ModelAndView editStudent(@PathVariable("id") int id) {
        // загружаем объект из базы
        Student student = studentsService.read(id);
        // используем то же view что и для создания
        return new ModelAndView("student/studentEdit")
                .addObject("student", student);
    }

    @GetMapping("/{id}")
    public ModelAndView viewStudent(@PathVariable("id") int id) {
        Student student = studentsService.read(id);
        if (student == null) {
            throw new ResourceNotFoundException();
        }
        // путь относительно viewResolver
        ModelAndView model = new ModelAndView("student/studentView");
        // добавляем данные в модель
        model.addObject("student", student);
        return model;
    }

    @PostMapping(value = "/delete")
    public ModelAndView delete(Student studentID) {
        Student student = studentsService.read(studentID.getId());
        if (student == null) {
            throw new ResourceNotFoundException();
        }else{
            studentsService.delete(studentID.getId());
        }
        return new ModelAndView("redirect:/students/");
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public class ResourceNotFoundException extends RuntimeException {

    }

}
