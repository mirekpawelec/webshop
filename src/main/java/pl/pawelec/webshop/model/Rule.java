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
import pl.pawelec.webshop.model.enum_.RuleStatus;

/**
 *
 * @author mirek
 */
@Entity
@Table(name = "rules")
public class Rule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rule_id", nullable = false)
    private Long ruleId;
    
    @Column(length = 250, nullable = false, unique = true)
    private String name;
    
    @Lob
    @Column(name = "content_rule", nullable = false)
    private String contentRule;
    
    @Column(length = 250)
    private String description;
    
    @Column(length = 2)
    private String status;
    
    @Convert(converter = TimestampToLocalDateTimeConverter.class)
    @Column(name = "lm_date")
    private LocalDateTime lastModyficationDate;
    
    @Convert(converter = TimestampToLocalDateTimeConverter.class)
    @Column(name = "c_date")
    private LocalDateTime createDate; 

    
    
    public Rule() {
        this.status = RuleStatus.OK.name();
        this.lastModyficationDate = LocalDateTime.now();
        this.createDate = LocalDateTime.now();
    }

    public Rule(String name, String contentRule) {
        this();
        this.name = name;
        this.contentRule = contentRule;
    }

    
    
    public boolean isNew(){
        return !Optional.ofNullable(ruleId).isPresent();
    }
    
    public Long getRuleId() {
        return ruleId;
    }

    public void setRuleId(Long ruleId) {
        this.ruleId = ruleId;
    }
    
    @NotEmpty(message = "{NotEmpty.Rule.name.validation}")
    @Size(max = 250, message = "{Size.Rule.name.validation}")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotEmpty(message = "{NotEmpty.Rule.contentRule.validation}")
    public String getContentRule() {
        return contentRule;
    }

    public void setContentRule(String contentRule) {
        this.contentRule = contentRule;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        hash = 53 * hash + Objects.hashCode(this.name);
        hash = 53 * hash + Objects.hashCode(this.contentRule);
        hash = 53 * hash + Objects.hashCode(this.description);
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
        final Rule other = (Rule) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.contentRule, other.contentRule)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        return true;
    }

    
    
    @Override
    public String toString() {
        return "Rule{"
                + "ruleId=" + ruleId 
                + ", name=" + name 
                + ", lengthContentRule=" + contentRule.length() 
                + ", lengthDescription=" + description.length() 
                + ", status=" + status 
                + ", lastModyficationDate=" + lastModyficationDate 
                + ", createDate=" + createDate 
                + '}';
    }    
}
