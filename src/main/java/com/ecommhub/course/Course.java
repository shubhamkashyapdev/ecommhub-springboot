package com.ecommhub.course;

import com.ecommhub.teacher.Teacher;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course {
    @Id
    @SequenceGenerator(
            name = "course_id_sequence",
            sequenceName = "course_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "course_id_sequence",
            strategy = GenerationType.SEQUENCE
    )
    private Long id;
    private String title;
    private Integer credit;


    // course to teacher relation
    @ManyToOne
    @JoinColumn(
            name="teacher_id",
            referencedColumnName = "id"
    )
    private Teacher teacher;

    // course material relation
    @OneToOne
    @JoinColumn(
            name = "course_material_id",
            referencedColumnName = "id"
    )
    private CourseMaterial courseMaterial;
}
