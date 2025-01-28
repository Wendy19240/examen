package com.example.asigantura.Service;

import com.example.asigantura.Entity.Subject;
import com.example.asigantura.Entity.SubjectTeacher;
import com.example.asigantura.Repository.SubjectRepository;
import com.example.asigantura.Repository.SubjectTeacherRepository;
import com.example.asigantura.Client.TeacherFeignClient;
import com.example.asigantura.Entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private SubjectTeacherRepository subjectTeacherRepository;

    @Autowired
    private TeacherFeignClient teacherFeignClient;

    // Create or update a subject
    public Subject saveSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    // Get a subject by ID
    public Optional<Subject> getSubjectById(Long id) {
        return subjectRepository.findById(id);
    }

    // Get all subjects
    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    // Update a subject
    public Subject updateSubject(Long id, Subject updatedSubject) {
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subject not found"));

        subject.setName(updatedSubject.getName());
        return subjectRepository.save(subject);
    }

    // Delete a subject by ID
    public void deleteSubject(Long id) {
        if (!subjectRepository.existsById(id)) {
            throw new RuntimeException("Subject not found");
        }
        subjectRepository.deleteById(id);
    }

    // Assign a teacher to a subject
    public SubjectTeacher assignTeacherToSubject(Long subjectId, Long teacherId) {
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new RuntimeException("Subject not found"));

        // Use TeacherFeignClient to get the teacher
        teacherFeignClient.getTeacher(teacherId);

        // Check if the teacher is already assigned to the subject
        Optional<SubjectTeacher> existingAssignment = subjectTeacherRepository.findBySubjectIdAndTeacherId(subjectId, teacherId);
        if (existingAssignment.isPresent()) {
            throw new RuntimeException("Teacher is already assigned to the subject");
        }

        // Create and save the relation between the subject and the teacher
        SubjectTeacher subjectTeacher = new SubjectTeacher();
        subjectTeacher.setSubject(subject);
        subjectTeacher.setTeacherId(teacherId);
        return subjectTeacherRepository.save(subjectTeacher);
    }

    // Get all teachers for a subject
    public List<Teacher> getTeachersForSubject(Long subjectId) {
        List<SubjectTeacher> subjectTeachers = subjectTeacherRepository.findBySubjectId(subjectId);

        List<Teacher> teachers = new ArrayList<>();
        for (SubjectTeacher subjectTeacher : subjectTeachers) {
            Long teacherId = subjectTeacher.getTeacherId();
            Teacher teacher = teacherFeignClient.getTeacher(teacherId).getBody(); // Get the full teacher details
            if (teacher != null) {
                teachers.add(teacher);
            }
        }
        return teachers;
    }

    // Remove a teacher from a subject
    public void removeTeacherFromSubject(Long subjectId, Long teacherId) {
        SubjectTeacher subjectTeacher = subjectTeacherRepository.findBySubjectIdAndTeacherId(subjectId, teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher not assigned to the subject"));

        subjectTeacherRepository.delete(subjectTeacher);
    }

    // Get subjects for a specific teacher
    public List<Subject> getSubjectsForTeacher(Long teacherId) {
        List<SubjectTeacher> subjectTeachers = subjectTeacherRepository.findByTeacherId(teacherId);
        List<Subject> subjects = new ArrayList<>();

        for (SubjectTeacher subjectTeacher : subjectTeachers) {
            subjects.add(subjectTeacher.getSubject());
        }

        return subjects;
    }
}
