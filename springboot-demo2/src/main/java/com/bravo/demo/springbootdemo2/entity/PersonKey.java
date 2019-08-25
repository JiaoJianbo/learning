package com.bravo.demo.springbootdemo2.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Bobby
 * @since 2019/8/25 22:55
 */
@Data
public class PersonKey implements Serializable {
    private String name;

    private String idCard;
}
