package com.github.axinger.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity(name = "teach_school_student")
public class SchoolStudent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @Id
    // @GeneratedValue(strategy = GenerationType.AUTO, generator = "myid")
    // @GenericGenerator(name = "myid", strategy = "com.git.hui.boot.jpa.generator.ManulInsertGenerator")
    // @Column(name = "id")
    private Long id;

    @Column
    private String name;


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "teach_school_student_to_subject", joinColumns = @JoinColumn(name = "school_student_id"),
            inverseJoinColumns = @JoinColumn(name = "school_subject_id"))
    /**
     * 实际生活中，选修这个动作是由学生来完成，所以这里，学生和课程的关联关系维护方，也指定给学生。
     */
    private List<SchoolSubject> schoolSubjectList = new ArrayList<>();

}
