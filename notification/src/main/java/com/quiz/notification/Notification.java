package com.quiz.notification;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Data
public class Notification {

    @Id
    private String id;
    private String notification;
    private Integer reciverId;
    private Boolean read;

    @JsonFormat(pattern = "dd.MM.yyyy / HH:mm:ss", timezone = "Europe/Belgrade")
    private LocalDateTime date;
}
