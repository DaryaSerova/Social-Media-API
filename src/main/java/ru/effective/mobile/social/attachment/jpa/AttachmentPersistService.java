package ru.effective.mobile.social.attachment.jpa;

import ru.effective.mobile.social.attachment.model.Attachment;

import java.util.stream.Stream;

public interface AttachmentPersistService {

    Attachment store(Attachment attachment);

    Stream<Attachment> getAllAttachments(Long id);
}
