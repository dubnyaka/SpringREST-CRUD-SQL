package com.example.springmvc.Dao;

import com.example.springmvc.model.StudentGroup;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudentGroupsDao extends CrudRepository<StudentGroup, Long> {

}