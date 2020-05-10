package ru.itis.chat.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "chat_messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "user_id")
    private User owner;

    @ManyToOne
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "chat_id")
    private Chat chat;

    @Column(name = "creation_time")
    private LocalDateTime creationTime;

    @Column(name = "content")
    private String content;
}
