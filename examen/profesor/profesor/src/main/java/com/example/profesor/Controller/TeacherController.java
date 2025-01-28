package com.example.profesor.Controller;

import com.example.profesor.Entity.Teacher;
import com.example.profesor.Service.TeacherService;
import com.example.profesor.Exception.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    // Create a new teacher
    @PostMapping
    public ResponseEntity<?> createTeacher(@Valid @RequestBody Teacher teacher, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = Validation.getValidationErrors(result);
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        Teacher newTeacher = teacherService.saveTeacher(teacher);
        return new ResponseEntity<>(newTeacher, HttpStatus.CREATED);
    }

    // Get a teacher by ID
    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getTeacher(@PathVariable Long id) {
        return teacherService.getTeacherById(id)
                .map(teacher -> new ResponseEntity<>(teacher, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Get all teachers
    @GetMapping
    public ResponseEntity<List<Teacher>> getAllTeachers() {
        List<Teacher> teachers = teacherService.getAllTeachers();
        return new ResponseEntity<>(teachers, HttpStatus.OK);
    }

    // Update a teacher by ID
    @PutMapping("/{id}")
    public ResponseEntity<?> updateTeacher(@PathVariable Long id, @Valid @RequestBody Teacher updatedTeacher, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = Validation.getValidationErrors(result);
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        try {
            Teacher teacher = teacherService.updateTeacher(id, updatedTeacher);
            return new ResponseEntity<>(teacher, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a teacher by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Long id) {
        try {
            teacherService.deleteTeacher(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
