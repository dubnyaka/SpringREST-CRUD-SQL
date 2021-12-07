package com.example.springmvc.service;

import com.example.springmvc.Dao.StudentGroupsDao;
import com.example.springmvc.Dao.StudentsDao;
import com.example.springmvc.model.Student;
import com.example.springmvc.model.StudentGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Service
public class StudentGroupsServiceImpl implements StudentGroupsService {

    private final StudentGroupsDao studentGroupsDao;

    @Autowired
    public StudentGroupsServiceImpl(StudentGroupsDao studentGroupsDao) {
        this.studentGroupsDao = studentGroupsDao;
    }

    public void saveStudentGroup(StudentGroup group) {
        studentGroupsDao.save(group);
    }

    public List<StudentGroup> getAllStudentGroups() {
        List<StudentGroup> allGroups = new ArrayList<>();
        Iterator<StudentGroup> studentIterator = studentGroupsDao.findAll().iterator();
        while (studentIterator.hasNext()) {
            allGroups.add(studentIterator.next());
        }
        return allGroups;
    }

    public StudentGroup getStudentGroup(long id) {
        if(studentGroupsDao.existsById((id))){
            return studentGroupsDao.findById(id).get();
        }else {
            return null;
        }
    }

    public boolean updateStudentGroup(StudentGroup group) {
        if(studentGroupsDao.existsById(group.getId())){
            studentGroupsDao.save(group);
            return true;
        }
        return false;
    }

    public boolean deleteStudentGroup(long id) {
        if(studentGroupsDao.existsById(id)){
            studentGroupsDao.deleteById(id);
            return true;
        }else {
            return false;
        }
    }
}
