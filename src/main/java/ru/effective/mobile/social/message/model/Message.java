package ru.effective.mobile.social.message.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sender", nullable = false)
    private String senderId;

    @Column(name = "recipient", nullable = false)
    private String recipientId;

    @Column(name = "content", nullable = false)
    @Length(max = 3000)
    private String content;

    @Column(name = "date")
    private LocalDateTime date;

    @PrePersist
    protected void onCreate() {
        date = LocalDateTime.now();
    }

}
