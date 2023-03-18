package com.bravo.demo.springbootdemo2.service;

import com.bravo.demo.springbootdemo2.entity.EmployeeDept;
import com.bravo.demo.springbootdemo2.entity.EmployeeDept2;
import com.bravo.demo.springbootdemo2.repo.EmployeeDept2Repository;
import com.bravo.demo.springbootdemo2.repo.EmployeeDeptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @auther bobby
 * @since 2023/3/18 上午10:47
 */
@Service
public class EmployeeDeptService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private EmployeeDeptRepository employeeDeptRepository;


    @Autowired
    private EmployeeDept2Repository employeeDeptRepository2;

    public EmployeeDeptService() {
    }

    public List<EmployeeDept> getAllEmpWithDept(){
        return employeeDeptRepository.findAllEmpWithDeptName();
    }

    public List<EmployeeDept2> getAllEmpByDeptId(Long deptId){
        return employeeDeptRepository2.getEmpWithDeptByDeptId(deptId);
    }

    public List<EmployeeDept2> findEmployeeByDeptId(Long deptId){
        return employeeDeptRepository2.findEmployeeByDeptId(deptId);
    }

    public List<EmployeeDept2> findByEntityManager() {
        List<EmployeeDept2> resultList = entityManager
                .createNativeQuery("select e.*, d.name as dept_name from employee e,department d where d.id=e.dept_id",
                        EmployeeDept2.class)
                .getResultList();
        return resultList;
    }

    public List findByEntityManager2() {
        List resultList = entityManager
                .createNativeQuery("select e.*, d.name as deptName from employee e,department d where d.id=e.dept_id")
                .getResultList();
        return resultList;
    }

}
