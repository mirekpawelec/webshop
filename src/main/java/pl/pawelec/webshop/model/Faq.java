/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.model;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
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
import pl.pawelec.webshop.model.enum_.FaqStatus;

/**
 *
 * @author mirek
 */
@Entity
@Table(name = "faq")
public class Faq {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "faq_id", nullable = false)
    private Long faqId;
    
    @Column(length = 250, nullable = false, unique = true)
    private String question;
    
    @Lob
    @Column(nullable = false)
    private String answer;
    
    @Column(length = 2)
    private String status;
    
    @Convert(converter = TimestampToLocalDateTimeConverter.class)
    @Column(name = "lm_date")
    private LocalDateTime lastModyficationDate;
    
    @Convert(converter = TimestampToLocalDateTimeConverter.class)
    @Column(name = "c_date")
    private LocalDateTime createDate;

    
    
    public Faq() {
        this.status = FaqStatus.OK.name();
        this.lastModyficationDate = LocalDateTime.now();
        this.createDate = LocalDateTime.now();
    }

    public Faq(String question, String answer) {
        this();
        this.question = question;
        this.answer = answer;
    }

    
    
    public boolean isNew(){
        return !Optional.ofNullable(faqId).isPresent();
    }
        
    public Long getFaqId() {
        return faqId;
    }

    public void setFaqId(Long faqId) {
        this.faqId = faqId;
    }

    @NotEmpty(message = "{NotEmpty.Faq.question.validation}")
    @Size(max = 250, message = "{Size.Faq.question.validation}")
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @NotEmpty(message = "{NotEmpty.Faq.answer.validation}")
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getLastModyficationDate() {
        return lastModyficationDate;
    }

    public void setLastModyficationDate(LocalDateTime lastModyficationDate) {
        this.lastModyficationDate = lastModyficationDate;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.question);
        hash = 11 * hash + Objects.hashCode(this.answer);
        hash = 11 * hash + Objects.hashCode(this.status);
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
        final Faq other = (Faq) obj;
        if (!Objects.equals(this.question, other.question)) {
            return false;
        }
        if (!Objects.equals(this.answer, other.answer)) {
            return false;
        }
        if (!Objects.equals(this.status, other.status)) {
            return false;
        }
        return true;
    }

    
    
    @Override
    public String toString() {
        return "faq{" 
                + "faqId=" + faqId 
                + ", question=" + question 
                + ", answer=" + answer 
                + ", status=" + status 
                + ", lastModyficationDate=" + lastModyficationDate 
                + ", createDate=" + createDate 
                + '}';
    }
}
