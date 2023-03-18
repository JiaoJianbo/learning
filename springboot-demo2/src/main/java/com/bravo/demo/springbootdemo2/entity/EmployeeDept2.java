package com.bravo.demo.springbootdemo2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;

/**
 * @auther bobby
 * @since 2023/3/18 上午10:48
 */


@NamedNativeQuery(
        name = "getEmpByDeptId",
        query = "select e.*, d.name as dept_name from employee e, department d where e.dept_id=d.id and d.id = :did",
        resultClass = EmployeeDept2.class
)

///////////////////////////////////////////////////////////////

@NamedNativeQuery(
        name = "findEmployeeByDeptId",
        query = "select e.id, " +
                "e.name, " +
                "e.age, " +
                "e.email, " +
                "e.dept_id as deptId, " +
                "d.name as deptName " +
                "from employee e, department d " +
                "where e.dept_id=d.id " +
                "and d.id=:deptId ",
        resultSetMapping = "employeeDeptMapping"
)
@SqlResultSetMapping(
        name = "employeeDeptMapping",
        classes = {
                @ConstructorResult(
                        targetClass = EmployeeDept2.class,
                        columns = {
                                @ColumnResult(name = "id", type = Long.class),
                                @ColumnResult(name = "name", type = String.class),
                                @ColumnResult(name = "age", type = Integer.class),
                                @ColumnResult(name = "email", type = String.class),
                                @ColumnResult(name = "deptId", type = Long.class),
                                @ColumnResult(name = "deptName", type = String.class)
                        }
                )
        }
)

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDept2 {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer age;

    private String email;

    private Long deptId;

    private String deptName;
}
