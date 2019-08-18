package com.bravo.demo.springbootdemo2.repo;

import com.bravo.demo.springbootdemo2.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Bobby
 * @since 2019/8/11 23:57
 */
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
