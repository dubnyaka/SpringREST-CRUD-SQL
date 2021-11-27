package com.example.springmvc.service;
import com.example.springmvc.model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class StudentServiceImpl implements StudentService {

    // Хранилище клиентов
    private static final Map<Long, Student> CLIENT_REPOSITORY_MAP = new ConcurrentHashMap<>();

    // Переменная для генерации ID клиента
    private static final AtomicLong CLIENT_ID_HOLDER = new AtomicLong();

    @Override
    public void save(Student student) {
        final long clientId = CLIENT_ID_HOLDER.incrementAndGet();
        student.setId(clientId);
        CLIENT_REPOSITORY_MAP.put(clientId, student);
    }

    @Override
    public List<Student> getAll() {
        return new ArrayList<>(CLIENT_REPOSITORY_MAP.values());
    }

    @Override
    public Student get(long id) {
        return CLIENT_REPOSITORY_MAP.get(id);
    }

    @Override
    public boolean update(Student student, long id) {
        if (CLIENT_REPOSITORY_MAP.containsKey(id)) {
            student.setId(id);
            CLIENT_REPOSITORY_MAP.put(id, student);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(long id) {
        return CLIENT_REPOSITORY_MAP.remove(id) != null;
    }
}
