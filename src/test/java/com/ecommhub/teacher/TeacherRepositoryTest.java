package com.ecommhub.teacher;

import com.ecommhub.course.CourseRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class TeacherRepositoryTest {
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void saveTeacher(){
        Teacher teacher = Teacher.builder()
                .firstName("Brad")
                .lastName("Traversy")
                .build();
        Teacher db_teacher = teacherRepository.save(teacher);
        System.out.println(db_teacher);
    }
}