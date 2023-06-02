package ru.effective.mobile.social.attachment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.effective.mobile.social.attachment.model.Attachment;

import java.util.Optional;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, Long> {

    Optional<Attachment> findAttachmentByPostId(Long id);

}
