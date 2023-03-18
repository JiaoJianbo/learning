package com.bravo.demo.springbootdemo2.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @auther bobby
 * @since 2023/3/18 上午10:48
 */
@Data
@Entity
//@Table(name = "employee")
public class EmployeeDept {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String name;

    private Integer age;

    private String email;

    private Long deptId;

    private String dname;
}
