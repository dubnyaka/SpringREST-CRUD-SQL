package com.example.springmvc.Dao;

import com.example.springmvc.model.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class StudentsDaoImpl implements StudentsDao {
    // Хранилище студентов
    private static final Map<Long, Student> STUDENT_REPOSITORY_MAP = new ConcurrentHashMap<>();

    // Переменная для генерации ID студентов
    private static final AtomicLong STUDENT_ID_HOLDER = new AtomicLong();

    @Override
    public void save(Student student) {
        final long clientId = STUDENT_ID_HOLDER.incrementAndGet();
        student.setId(clientId);
        STUDENT_REPOSITORY_MAP.put(clientId, student);
    }

    @Override
    public List<Student> getAll() {
        return new ArrayList<>(STUDENT_REPOSITORY_MAP.values());
    }

    @Override
    public List<Student> getAllByGroupId(long groupId) {
        List<Student> studentsByGroup = new ArrayList<>();
        for (Student student : STUDENT_REPOSITORY_MAP.values()) {
            if(student.getGroupID() == groupId){
                studentsByGroup.add(student);
            }
        }
        return studentsByGroup;
    }

    @Override
    public Student get(long id) {
        return STUDENT_REPOSITORY_MAP.get(id);
    }

    @Override
    public boolean update(Student student) {
        if (STUDENT_REPOSITORY_MAP.containsKey(student.getId())) {
            STUDENT_REPOSITORY_MAP.put(student.getId(), student);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(long id) {
        return STUDENT_REPOSITORY_MAP.remove(id) != null;
    }
}
