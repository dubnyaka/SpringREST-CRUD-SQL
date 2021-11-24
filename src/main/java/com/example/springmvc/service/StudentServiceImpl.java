package com.example.springmvc.service;
import com.example.springmvc.model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class StudentServiceImpl implements StudentService {

    // Хранилище клиентов
    private static final Map<Integer, Student> CLIENT_REPOSITORY_MAP = new HashMap<>();

    // Переменная для генерации ID клиента
    private static final AtomicInteger CLIENT_ID_HOLDER = new AtomicInteger();

    @Override
    public void create(Student student) {
        final int clientId = CLIENT_ID_HOLDER.incrementAndGet();
        student.setId(clientId);
        CLIENT_REPOSITORY_MAP.put(clientId, student);
    }

    @Override
    public List<Student> readAll() {
        return new ArrayList<>(CLIENT_REPOSITORY_MAP.values());
    }

    @Override
    public Student read(int id) {
        return CLIENT_REPOSITORY_MAP.get(id);
    }

    @Override
    public boolean update(Student student, int id) {
        if (CLIENT_REPOSITORY_MAP.containsKey(id)) {
            student.setId(id);
            CLIENT_REPOSITORY_MAP.put(id, student);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        return CLIENT_REPOSITORY_MAP.remove(id) != null;
    }
}
