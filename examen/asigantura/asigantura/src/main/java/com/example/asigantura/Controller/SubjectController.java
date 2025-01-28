package com.example.asigantura.Controller;

import com.example.asigantura.Entity.Subject;
import com.example.asigantura.Entity.SubjectTeacher;
import com.example.asigantura.Entity.Teacher;
import com.example.asigantura.Exception.Validation;
import com.example.asigantura.Service.SubjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/subject")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    // Create a new subject
    @PostMapping
    public ResponseEntity<?> createSubject(@Valid @RequestBody Subject subject, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = Validation.getValidationErrors(result);
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        Subject newSubject = subjectService.saveSubject(subject);
        return new ResponseEntity<>(newSubject, HttpStatus.CREATED);
    }

    // Get a subject by ID
    @GetMapping("/{id}")
    public ResponseEntity<Subject> getSubjectById(@PathVariable Long id) {
        Optional<Subject> subject = subjectService.getSubjectById(id);
        return subject.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Get all subjects
    @GetMapping
    public ResponseEntity<List<Subject>> getAllSubjects() {
        List<Subject> subjects = subjectService.getAllSubjects();
        return new ResponseEntity<>(subjects, HttpStatus.OK);
    }

    // Update a subject
    @PutMapping("/{id}")
    public ResponseEntity<?> updateSubject(@PathVariable Long id, @Valid @RequestBody Subject subject, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = Validation.getValidationErrors(result);
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        try {
            Subject updatedSubject = subjectService.updateSubject(id, subject);
            return new ResponseEntity<>(updatedSubject, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // Delete a subject
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSubject(@PathVariable Long id) {
        try {
            subjectService.deleteSubject(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // Assign a teacher to a subject
    @PostMapping("/{subjectId}/teachers/{teacherId}")
    public ResponseEntity<?> assignTeacherToSubject(@PathVariable Long subjectId, @PathVariable Long teacherId) {
        try {
            SubjectTeacher subjectTeacher = subjectService.assignTeacherToSubject(subjectId, teacherId);
            return new ResponseEntity<>(subjectTeacher, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // Get all teachers for a subject
    @GetMapping("/{subjectId}/teachers")
    public ResponseEntity<List<Teacher>> getTeachersForSubject(@PathVariable Long subjectId) {
        List<Teacher> teachers = subjectService.getTeachersForSubject(subjectId);
        return new ResponseEntity<>(teachers, HttpStatus.OK);
    }

    // Remove a teacher from a subject
    @DeleteMapping("/{subjectId}/teachers/{teacherId}")
    public ResponseEntity<?> removeTeacherFromSubject(@PathVariable Long subjectId, @PathVariable Long teacherId) {
        try {
            subjectService.removeTeacherFromSubject(subjectId, teacherId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // Get all subjects for a teacher
    @GetMapping("/teacher/{teacherId}/subjects")
    public ResponseEntity<List<Subject>> getSubjectsForTeacher(@PathVariable Long teacherId) {
        List<Subject> subjects = subjectService.getSubjectsForTeacher(teacherId);
        if (subjects.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Return 404 if no subjects are found
        }
        return new ResponseEntity<>(subjects, HttpStatus.OK);
    }
}
