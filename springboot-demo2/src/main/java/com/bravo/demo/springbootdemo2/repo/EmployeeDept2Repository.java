package com.bravo.demo.springbootdemo2.repo;

import com.bravo.demo.springbootdemo2.entity.EmployeeDept2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @auther bobby
 * @since 2023/3/18 上午10:54
 */
@Repository
public interface EmployeeDept2Repository extends JpaRepository<EmployeeDept2, Long> {

    @Query(name = "getEmpByDeptId", nativeQuery = true)
    List<EmployeeDept2> getEmpWithDeptByDeptId(@Param("did") Long deptId);

    @Query(name = "findEmployeeByDeptId", nativeQuery = true)
    List<EmployeeDept2> findEmployeeByDeptId(@Param("deptId") Long deptId);
}
