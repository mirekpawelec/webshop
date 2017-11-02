/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.model;

import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;
import pl.pawelec.webshop.converter.TimestampToLocalDateTimeConverter;
import pl.pawelec.webshop.model.enum_.MessageStatus;

/**
 *
 * @author mirek
 */
@Entity
@Table(name = "messages")
public class ClientMessage {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id", nullable = false)
    private Long messageId;
    
    @Column(nullable = false, length = 50)
    private String name;
    
    @Column(nullable = false, length = 50)
    private String email;
    
    @Column(length = 50)
    private String subject;
    
    @Lob
    @Column(nullable = false, length = 250)
    private String content;

    @Column(length = 2)
    private String status;
    
    @Convert(converter = TimestampToLocalDateTimeConverter.class)
    @Column(name = "c_date")
    private LocalDateTime createDate;

    
    
    public ClientMessage() {
        this.status = MessageStatus.NE.name();
        this.createDate = LocalDateTime.now();
    }

    public ClientMessage(String name, String email, String subject, String content) {
        this();
        this.name = name;
        this.email = email;
        this.subject = subject;
        this.content = content;
    }

    
    
    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    @NotEmpty(message = "{NotEmpty.ClientMessage.name.validation}")
    @Size(max = 50, message = "{Size.ClientMessage.name.validation}")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotEmpty(message = "{NotEmpty.ClientMessage.email.validation}")
    @Size(max = 50, message = "{Size.ClientMessage.email.validation}")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @NotEmpty(message = "{NotEmpty.ClientMessage.content.validation}")
    @Size(max = 250, message = "{Size.ClientMessage.content.validation}")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public boolean isNew(){
        return name==null && email==null && subject==null && content==null;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.name);
        hash = 53 * hash + Objects.hashCode(this.email);
        hash = 53 * hash + Objects.hashCode(this.subject);
        hash = 53 * hash + Objects.hashCode(this.content);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ClientMessage other = (ClientMessage) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.subject, other.subject)) {
            return false;
        }
        if (!Objects.equals(this.content, other.content)) {
            return false;
        }
        return true;
    }

    
    
    @Override
    public String toString() {
        return "ClientMessage{" 
                + "messageId=" + messageId 
                + ", name=" + name 
                + ", email=" + email 
                + ", subject=" + subject 
                + ", content=" + content 
                + ", status=" + status 
                + ", createDate=" + createDate 
                + '}';
    }
    
}
