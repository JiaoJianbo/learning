package com.bravo.demo.springbootdemo2.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Bobby
 * @since 2019/8/11 20:30
 */
@Getter
@Setter
@Entity
public class Department {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(targetEntity = Employee.class, fetch = FetchType.LAZY, mappedBy = "department")
    private Set<Employee> employees = new HashSet<>();
}
