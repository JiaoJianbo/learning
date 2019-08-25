package com.bravo.demo.springbootdemo2.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * 联合主键的另一种写法
 *
 * @author Bobby
 * @since 2019/8/25 22:57
 */
@Entity
@Table(name = "Person")
@Data
@IdClass(PersonKey.class) // 主键类型
public class Person {
    @Id
    @Column(name = "name", length = 32)
    private String name;

    @Id
    @Column(name = "id_card_num", length = 18)
    private String idCard;

    @Column(name = "age", precision = 3, scale = 0)
    private Integer age;

    @Column(name = "address", length = 255)
    private String address;
}

