package ru.effective.mobile.social.attachment.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.effective.mobile.social.attachment.model.Attachment;
import ru.effective.mobile.social.attachment.repository.AttachmentRepository;

import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class AttachmentPersistServiceImpl implements AttachmentPersistService {

    private final AttachmentRepository attachmentRepository;

    @Override
    public Attachment store(Attachment attachment) {

        return attachmentRepository.save(attachment);
    }

    @Override
    public Stream<Attachment> getAllAttachments(Long id) {
        return attachmentRepository.findAttachmentByPostId(id).stream();
    }
}
