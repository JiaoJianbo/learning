package com.bravo.demo.springbootdemo2.repo;

import com.bravo.demo.springbootdemo2.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author Bobby
 * @since 2019/8/11 23:51
 */
public interface EmployeeRepository
        extends JpaRepository <Employee, Long>, JpaSpecificationExecutor<Employee> {
}
