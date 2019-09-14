package com.bravo.demo.springbootdemo2.repo;

import com.bravo.demo.springbootdemo2.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Bobby
 * @since 2019/9/14 14:06
 */
public interface AttachmentRepository extends JpaRepository<Attachment, Long> {
}
