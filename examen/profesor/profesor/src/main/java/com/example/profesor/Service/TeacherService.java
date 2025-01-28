package com.example.profesor.Service;

import com.example.profesor.Entity.Teacher;
import com.example.profesor.Repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    // Create or Update a teacher
    public Teacher saveTeacher(Teacher teacher) {
        // Aquí puedes agregar validaciones adicionales si necesitas verificar duplicados.
        return teacherRepository.save(teacher);
    }

    // Get a teacher by ID
    public Optional<Teacher> getTeacherById(Long id) {
        return teacherRepository.findById(id);
    }

    // Get all teachers
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    // Update a teacher by ID
    public Teacher updateTeacher(Long id, Teacher updatedTeacher) {
        return teacherRepository.findById(id)
                .map(existingTeacher -> {
                    existingTeacher.setNombreProfesor(updatedTeacher.getNombreProfesor());
                    existingTeacher.setInstrumento(updatedTeacher.getInstrumento());
                    existingTeacher.setFechaNacimiento(updatedTeacher.getFechaNacimiento());
                    return teacherRepository.save(existingTeacher);
                })
                .orElseThrow(() -> new RuntimeException("Teacher with ID " + id + " not found"));
    }

    // Delete a teacher by ID
    public void deleteTeacher(Long id) {
        if (teacherRepository.existsById(id)) {
            teacherRepository.deleteById(id);
        } else {
            throw new RuntimeException("Teacher with ID " + id + " not found");
        }
    }
}
