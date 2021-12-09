package com.example.springmvc.service;

import com.example.springmvc.Dao.StudentsDao;
import com.example.springmvc.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentsDao studentsDao;

    @Autowired
    public StudentServiceImpl(StudentsDao studentsDao) {
        this.studentsDao = studentsDao;
    }

    @Transactional
    public void saveStudent(Student student) {
        studentsDao.save(student);
    }

    public List<Student> getAllStudents() {
        List<Student> allStudents = new ArrayList<>();
        Iterator<Student> studentIterator = studentsDao.findAll().iterator();
        while (studentIterator.hasNext()) {
            allStudents.add(studentIterator.next());
        }
        return allStudents;
    }

    public List<Student> getAllStudentsByGroup(long groupId) {
        return studentsDao.findByGroupId(groupId);
    }

    public Student getStudent(long id) {
        if(studentsDao.existsById((id))){
            return studentsDao.findById(id).get();
        }else {
            return null;
        }
    }

    public boolean updateStudent(Student student) {
        if(studentsDao.existsById(student.getId())){
            studentsDao.save(student);
            return true;
        }
        return false;
    }

    public boolean deleteStudent(long id) {
        if(studentsDao.existsById(id)){
            studentsDao.deleteById(id);
            return true;
        }else {
            return false;
        }
    }
}
