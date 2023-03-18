package com.bravo.demo.springbootdemo2.repo;

import com.bravo.demo.springbootdemo2.entity.EmployeeDept;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @auther bobby
 * @since 2023/3/18 上午10:54
 */
@Repository
public interface EmployeeDeptRepository extends JpaRepository<EmployeeDept, Long> {

    @Query(value = "select e.*, d.name as dname from employee e,department d where d.id=e.dept_id",
            nativeQuery = true)
    List<EmployeeDept> findAllEmpWithDeptName();
}
