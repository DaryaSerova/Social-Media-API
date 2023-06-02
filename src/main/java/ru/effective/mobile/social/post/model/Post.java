package ru.effective.mobile.social.post.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import ru.effective.mobile.social.attachment.model.Attachment;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    @Length(min = 1, max = 50)
    private String title;

    @Column(name = "description", nullable = false)
    @Length(max = 3000)
    private String description;

    @Column(name = "author", nullable = false)
    @Length(max = 20)
    private String author;

    @Column(name = "date_create")
    private LocalDateTime dateCreate;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY, mappedBy = "postId")
    private List<Attachment> attachments;

    @PrePersist
    protected void onCreate() {
        dateCreate = LocalDateTime.now();
    }


}
