package ru.effective.mobile.social.attachment.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "attachment")
public class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "post_id")
    private Long postId;

    @Lob
    @Column(name = "data")
    private byte[] data;

    public Attachment() {
    }

    public Attachment(String name, String type, Long postId, byte[] data) {
        this.name = name;
        this.type = type;
        this.postId = postId;
        this.data = data;
    }

}
