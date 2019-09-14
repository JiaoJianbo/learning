package com.bravo.demo.springbootdemo2.repo;

import com.bravo.demo.springbootdemo2.entity.Attachment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Bobby
 * @since 2019/9/14 17:19
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AttachmentRepositoryTest {
    @Autowired
    private AttachmentRepository attachmentRepository;

    @Test
    public void testSave() {
        Attachment attachment = new Attachment();
        attachment.setName("Attach1");
        // createDate 和 modifyDate 都是非空的，但是这里并没有赋值，仍然可以正确执行
        // 说明 @CreatedDate 和 @LastModifiedDate 为这两个字段自动生成了值
        attachmentRepository.save(attachment);
    }
}
