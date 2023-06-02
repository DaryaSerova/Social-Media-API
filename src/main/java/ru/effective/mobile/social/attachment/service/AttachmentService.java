package ru.effective.mobile.social.attachment.service;

import org.springframework.web.multipart.MultipartFile;
import ru.effective.mobile.social.attachment.message.ResponseAttach;
import ru.effective.mobile.social.attachment.model.Attachment;

import java.io.IOException;
import java.util.List;

public interface AttachmentService {

    Attachment store(MultipartFile file, Long postId) throws IOException;

    List<ResponseAttach> getAllAttachments(Long id);
}
