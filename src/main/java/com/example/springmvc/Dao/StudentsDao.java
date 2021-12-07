package com.example.springmvc.Dao;

import com.example.springmvc.model.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudentsDao extends CrudRepository<Student, Long> {

    List<Student> findByGroupId(long groupId);

}