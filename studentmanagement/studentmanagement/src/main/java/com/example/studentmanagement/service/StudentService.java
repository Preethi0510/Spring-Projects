package com.example.studentmanagement.service;
import java.util.List;
import org.springframework.stereotype.Service;
import com.example.studentmanagement.entity.Student;
import com.example.studentmanagement.repository.StudentRepository;
import com.example.studentmanagement.exception.StudentNotFoundException;

@Service
public class StudentService {
    private final StudentRepository repo;

    public StudentService(StudentRepository repo) {
        this.repo = repo;
    }

    public Student addStudent(Student student) {
        if (repo.existsByEmail(student.getEmail())) {
            throw new IllegalArgumentException("Email already registered");
        }
        return repo.save(student);
    }

    public List<Student> getAllStudents() {
        return repo.findAll();
    }

    public Student getStudent(int id) {
        return repo.findById(id).orElseThrow(() -> new StudentNotFoundException("Student not found with ID: " + id));
    }

    public Student updateStudent(int id, Student student) {
        Student old = getStudent(id);
        old.setName(student.getName());
        old.setEmail(student.getEmail());
        old.setDepartment(student.getDepartment());
        old.setAge(student.getAge());
        return repo.save(old);
    }

    public void deleteStudent(int id) {
        Student student = getStudent(id);
        repo.delete(student);
    }

    public long countStudents() {
        return repo.count();
    }
}