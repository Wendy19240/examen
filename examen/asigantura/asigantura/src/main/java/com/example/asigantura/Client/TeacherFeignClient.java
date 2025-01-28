package com.example.asigantura.Client;

import com.example.asigantura.Entity.Teacher;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "teacher", url = "http://localhost:8003/teacher")
public interface TeacherFeignClient {
    @GetMapping("/{id}")
    ResponseEntity<Teacher> getTeacher(@PathVariable Long id);

    @GetMapping
    List<Teacher> getAllTeachers();
}
