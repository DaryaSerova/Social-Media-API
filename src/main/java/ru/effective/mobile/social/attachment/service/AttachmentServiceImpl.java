package ru.effective.mobile.social.attachment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.effective.mobile.social.attachment.jpa.AttachmentPersistService;
import ru.effective.mobile.social.attachment.message.ResponseAttach;
import ru.effective.mobile.social.attachment.model.Attachment;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AttachmentServiceImpl implements AttachmentService {

    private final AttachmentPersistService attachmentPersistService;

    @Override
    public Attachment store(MultipartFile file, Long postId) throws IOException {

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        Attachment attachment = new Attachment(fileName, file.getContentType(), postId, file.getBytes());

        return attachmentPersistService.store(attachment);
    }


    @Override
    public List<ResponseAttach> getAllAttachments(Long id) {

        List<ResponseAttach> attachments = attachmentPersistService.getAllAttachments(id).map(attachment -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/attachment/")
                    .path(attachment.getId().toString())
                    .toUriString();

            return new ResponseAttach(
                    attachment.getName(),
                    fileDownloadUri,
                    attachment.getType(),
                    attachment.getId(),
                    attachment.getData().length);
        }).collect(Collectors.toList());

        return attachments;
    }
}
