package com.bravo.demo.springbootdemo2.entity;

import lombok.Data;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;

/**
 * @author Bobby
 * @since 2019/8/11 20:29
 */

@Data
@Entity
@Table(name = "employee")
@Proxy(lazy = false)
public class Employee {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String name;

    private Integer age;

    private String email;

    @ManyToOne(targetEntity = Department.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "dept_id")
    private Department department;
}
