package com.github.yildizmy.domain.entity;

import com.github.yildizmy.domain.enums.NotificationType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@Entity
public class Notification extends BaseEntity {

    @Column(nullable = false, length = 100)
    private String recipient;

    @Column(nullable = false, length = 200)
    private String subject;

    @Column(nullable = false, length = 1000)
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private NotificationType type;

    @Column(nullable = false)
    private Boolean sent = false;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notification that = (Notification) o;
        return Objects.equals(recipient, that.recipient) &&
                Objects.equals(subject, that.subject) &&
                Objects.equals(content, that.content) && type == that.type &&
                Objects.equals(sent, that.sent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recipient, subject, content, type, sent);
    }
}
