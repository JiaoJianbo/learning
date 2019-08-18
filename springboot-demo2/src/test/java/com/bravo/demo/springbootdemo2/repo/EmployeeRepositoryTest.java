package com.bravo.demo.springbootdemo2.repo;

import com.bravo.demo.springbootdemo2.entity.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.criteria.*;
import java.util.List;
import java.util.Optional;

/**
 * @author Bobby
 * @since 2019/8/12 0:00
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void testFindAll() {
        List<Employee> employees = employeeRepository.findAll();
        employees.forEach(System.out::println);
    }
    
    @Test
    public void testGetOne(){
        Long id = 1L;
        Employee employee = employeeRepository.getOne(id);
        System.out.println("employee = " + employee);
    }


    /**
     * Specification: 查询条件接口，需要提供自定义实现
     *
     * 有一个抽象方法：
     *  Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder)
     *      root: 查询的根对象（查询的任何属性都可以从根对象中获取）
     *      criteriaQuery: 顶层查询对象，自定义查询方式（一般不用）
     *      criteriaBuilder: 查询构造器，封装了很多的查询条件
     */
    @Test
    public void testFindOne(){
        /*
         * 1. 实现 Specification 接口（提供泛型，即查询对象的类型）
         * 2. 实现 toPredicate 方法（构造查询条件）
         * 3. 需要借助方法参数中的两个参数（
         *      root: 获取需要查询对象的属性
         *      criteriaBuilder: 构造查询条件，内部封装了很多查询条件（模糊匹配，精准匹配）
         *   ）
         */
        Specification<Employee> specification = new Specification<Employee>() {
            @Override
            public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                // 1.获取比较属性名称（是属性名称，不是字段名）
                Path<Object> name = root.get("name");

                // 2.构造查询条件
                // 第一个参数是需要比较的对象，Path对象，Path 接口继承自 Expression 接口
                // 第二个参数是当前需要比较的取值
                Predicate predicate = criteriaBuilder.equal(name, "Bobby1"); // 精准匹配（比较的属性和属性取值）
                return predicate;
            }
        };

        Optional<Employee> optional  = employeeRepository.findOne(specification);
        Employee employee = optional.orElse(new Employee());
        System.out.println("employee = " + employee);
    }
    
    
    /**
     * 多条件联合查询，where name like 'Bob%' and age = 20
     * root: 获取属性
     *       name, age
     * criteriaBuilder: 构造查询
     *       1.构造 name 的模糊匹配
     *       2.构造 age 的精确匹配
     *       3.将以上两个查询条件联合起来
     */
    @Test
    public void testFindAllSpec(){
        // 使用 Lambda 表达式
        List<Employee> employees = employeeRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
            Path name = root.get("name");
            Path age = root.get("age");

            /* equals 直接使用 path 对象进行匹配
             * gt, lt, ge, le, like 根据 path 对象指定比较的参数类型，然后再去比较
             */
            //Predicate p1 = criteriaBuilder.like(name, "Bob%"); // 模糊匹配
            Predicate p1 = criteriaBuilder.like(name.as(String.class), "Bob%"); // 模糊匹配
            Predicate p2 = criteriaBuilder.equal(age, 20); // 精准匹配

            // 使用 criteriaBuilder 将多个查询条件联合起来
            Predicate predicate = criteriaBuilder.and(p1, p2);

            return predicate;
        });

        employees.forEach(System.out::println);
    }

    /**
     * 排序
     * Sort。包括排序方式（升序或者降序），排序的属性
     */
    @Test
    public void testFindAllSort(){
        List<Employee> employees = employeeRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
            Path email = root.get("email");
            Predicate predicate = criteriaBuilder.like(email.as(String.class), "%@123.com");
            return predicate;
        }, new Sort(Sort.Direction.DESC, "age"));

        employees.forEach(System.out::println);
    }

    /**
     * 分页查询
     * findAll(Pageable)
     * findAll(Specification, Pageable)
     * Specification: 查询条件
     * Pageable: 分页参数。查询的页码，每页显示的条数
     *
     * 返回 Page 对象。Spring Data JPA 封装好的 pageBean 对象，包括数据列表，总条数
     */
    @Test
    public void testFindAllPaged(){
        Pageable pageable = PageRequest.of(0, 2); // PageRequest 是 Pageable 的实现类
        Page<Employee> page = employeeRepository.findAll(pageable);
        System.out.println("总条数 page.getTotalElements() = " + page.getTotalElements());
        System.out.println("总页数 page.getTotalPages() = " + page.getTotalPages());

        List<Employee> employees = page.getContent();
        employees.forEach(System.out::println);
    }

}
