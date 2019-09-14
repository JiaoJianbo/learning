package com.bravo.demo.springbootdemo2.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Bobby
 * @since 2019/9/14 14:00
 */
@Data
@Entity
@Table(name = "Attachment")
@EntityListeners(AuditingEntityListener.class) // 配合 @CreatedDate 和 @LastModifiedDate
/*
 * @DynamicInsert 和 @DynamicUpdate 只在 SQL 语句中插入或修改有值的字段
 * 使用前：
 *    insert into attachment (id, create_by, create_date, modify_by, modify_date, name, url)
 *    values (null, ?, ?, ?, ?, ?, ?)
 * 使用后：
 *     insert into attachment (create_date, modify_date, name, id)
 *     values (?, ?, ?, null)
 */
@DynamicInsert
@DynamicUpdate
public class Attachment {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false, length = 200)
    private String name;

    @Column(length = 500)
    private String url;

    @Column(nullable = false, updatable = false)
    @CreatedDate
    private Date createDate;

    @Column(nullable = false)
    @LastModifiedDate
    private Date modifyDate;

    private String createBy;

    private String modifyBy;
}
