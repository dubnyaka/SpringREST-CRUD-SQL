package com.example.springmvc.controller;

import com.example.springmvc.model.Student;
import com.example.springmvc.model.StudentsGroup;
import com.example.springmvc.service.StudentGroupServiceImpl;
import com.example.springmvc.service.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentGroupsController {

    private final StudentGroupServiceImpl studentsGroupService;
    private final StudentServiceImpl studentService;

    @Autowired
    public StudentGroupsController(StudentGroupServiceImpl studentsGroupService, StudentServiceImpl studentService) {
        this.studentsGroupService = studentsGroupService;
        this.studentService = studentService;

        // Create test group object in repository for test
        StudentsGroup tempGroup = new StudentsGroup();
        tempGroup.setName("FirstGroup");
        studentsGroupService.save(tempGroup);
    }

    // Rest return studentsGroups list
    @GetMapping(value = "/studentGroups")
    public ResponseEntity<List<StudentsGroup>> read() {
        final List<StudentsGroup> studentsGroups = studentsGroupService.getAll();

        return studentsGroups != null && !studentsGroups.isEmpty()
                ? new ResponseEntity<>(studentsGroups, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Read all student in group
    @GetMapping(value = "/studentGroups/{id}/students")
    public ResponseEntity<List<Student>> readStudent(@PathVariable(name = "id") int groupID) {
        final List<Student> students = new ArrayList<>();

        for (Student x : studentService.getAll()) {
            if (x.getGroupID() == groupID) {
                students.add(x);
            }
        }

        return students != null && !students.isEmpty()
                ? new ResponseEntity<>(students, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Additional rest services
    @GetMapping(value = "/studentGroups/{id}")
    public ResponseEntity<StudentsGroup> read(@PathVariable(name = "id") int id) {
        final StudentsGroup studentsGroup = studentsGroupService.get(id);

        return studentsGroup != null
                ? new ResponseEntity<>(studentsGroup, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/studentGroups")
    public ResponseEntity<?> create(@RequestBody StudentsGroup studentsGroup) {
        studentsGroupService.save(studentsGroup);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/studentGroups/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody StudentsGroup studentsGroup) {
        final boolean updated = studentsGroupService.update(studentsGroup, id);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/studentGroups/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        final boolean deleted = studentsGroupService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}