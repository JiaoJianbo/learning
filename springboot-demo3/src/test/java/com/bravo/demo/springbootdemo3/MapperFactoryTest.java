package com.bravo.demo.springbootdemo3;

import com.bravo.demo.springbootdemo3.entity.Person;
import lombok.Data;
import ma.glasnost.orika.MapperFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * To test Orika
 *
 * @author Bobby
 * @since 2019/11/24 21:30
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MapperFactoryTest {
    @Autowired
    private MapperFactory mapperFactory;

    @Test
    public void testCopyBean(){
        Person person = new Person();
        person.setFirstName("Jim");
        person.setLastName("Green");

        mapperFactory.classMap(Person.class, Student.class)
                .field("firstName", "name") //不一样的字段映射
                .byDefault()
                .register();

        Student student = mapperFactory.getMapperFacade().map(person, Student.class);
        System.out.println("student = " + student);
        Assert.assertEquals("Copy 出错。", person.getFirstName(), student.getName());
    }

}

@Data
class Student {
    private String name;
    private Integer age;
}