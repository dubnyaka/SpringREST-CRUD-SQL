package com.example.springmvc.controller;

import com.example.springmvc.model.Student;
import com.example.springmvc.model.StudentGroup;
import com.example.springmvc.service.StudentGroupsServiceImpl;
import com.example.springmvc.service.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentGroupsController {

    private final StudentGroupsServiceImpl studentsGroupService;
    private final StudentServiceImpl studentService;

    @Autowired
    public StudentGroupsController(StudentGroupsServiceImpl studentsGroupService, StudentServiceImpl studentService) {
        this.studentsGroupService = studentsGroupService;
        this.studentService = studentService;

        // Create test group object in repository for test
        StudentGroup tempGroup = new StudentGroup();
        tempGroup.setName("FirstGroup");
        studentsGroupService.saveStudentGroup(tempGroup);
    }

    // Rest return studentsGroups list
    @GetMapping(value = "/studentGroups")
    public ResponseEntity<List<StudentGroup>> read() {
        final List<StudentGroup> studentGroups = studentsGroupService.getAllStudentGroups();
        return new ResponseEntity<>(studentGroups, HttpStatus.OK);
    }

    // Read all student in group
    @GetMapping(value = "/studentGroups/{id}/students")
    public ResponseEntity<List<Student>> readStudent(@PathVariable(name = "id") int groupID) {
        if (studentsGroupService.getStudentGroup(groupID) != null) {
            List<Student> students = studentService.getAllStudentsByGroup(groupID);
            return new ResponseEntity<>(students, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Additional rest services
    @GetMapping(value = "/studentGroups/{id}")
    public ResponseEntity<StudentGroup> read(@PathVariable(name = "id") int id) {
        final StudentGroup studentGroup = studentsGroupService.getStudentGroup(id);

        return studentGroup != null
                ? new ResponseEntity<>(studentGroup, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/studentGroups")
    public ResponseEntity<?> create(@RequestBody StudentGroup studentGroup) {
        studentsGroupService.saveStudentGroup(studentGroup);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/studentGroups/{id}")
    public ResponseEntity<?> update(@RequestBody StudentGroup studentGroup) {
        final boolean updated = studentsGroupService.updateStudentGroup(studentGroup);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/studentGroups/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        final boolean deleted = studentsGroupService.deleteStudentGroup(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}