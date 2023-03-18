package com.bravo.demo.springbootdemo2.api;

import com.bravo.demo.springbootdemo2.entity.EmployeeDept;
import com.bravo.demo.springbootdemo2.entity.EmployeeDept2;
import com.bravo.demo.springbootdemo2.service.EmployeeDeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;




/**
 * @auther bobby
 * @since 2023/3/18 上午11:29
 */
@RequestMapping("/api")
@RestController
@Slf4j
public class ApiController {

    @Autowired
    private EmployeeDeptService employeeDeptService;

    @RequestMapping("/allempdept/v1")
    public List<EmployeeDept> getAllEmpWithDept() {
        return employeeDeptService.getAllEmpWithDept();
    }


    @RequestMapping("/empbydeptid/v1")
    public List<EmployeeDept2> getAllEmpWithDept(@PathParam("deptId") Long deptId) {
        log.info("deptId: {}", deptId);
        return employeeDeptService.getAllEmpByDeptId(deptId);
    }

    @RequestMapping("/findempdept/v1")
    public List<EmployeeDept2> findEmployeeByDeptId(@PathParam("deptId") Long deptId) {
        log.info("findEmployeeByDeptId deptId: {}", deptId);
        return employeeDeptService.findEmployeeByDeptId(deptId);
    }

    @RequestMapping("/findbyem/v1")
    public List<EmployeeDept2> findByEntityManager() {
        return employeeDeptService.findByEntityManager();
    }


    // 该方法返回的数据格式与其它的都不同
    /*
    [
        [1,20,"bobby1@test.com","Bobby1",1,"开发部"],
        [2,21,"bobby2@123.com","Bobby2",1,"开发部"],
        [3,22,"bob1@test.com","Bob1",1,"开发部"],
        [4,23,"bob2@123.com","Bob2",1,"开发部"]
    ]
    */
    @RequestMapping("/findbyem/v2")
    public List findByEntityManager2() {
        return employeeDeptService.findByEntityManager2();
    }

}
