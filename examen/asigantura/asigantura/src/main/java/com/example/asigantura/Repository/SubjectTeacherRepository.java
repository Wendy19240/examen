package com.example.asigantura.Repository;

import com.example.asigantura.Entity.Subject;
import com.example.asigantura.Entity.SubjectTeacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubjectTeacherRepository extends JpaRepository<SubjectTeacher, Long> {
    List<SubjectTeacher> findBySubjectId(Long subjectId);
    List<SubjectTeacher> findByTeacherId(Long teacherId);
    Optional<SubjectTeacher> findBySubjectIdAndTeacherId(Long subjectId, Long teacherId);
}
