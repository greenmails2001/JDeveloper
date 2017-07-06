package com.vc.web.ejb.sas.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({
  @NamedQuery(name = "SecurityQuestions.findAll", query = "select o from SecurityQuestions o"),
  @NamedQuery(name = "SecurityQuestions.findAll.size", query = "select count(o) from SecurityQuestions o")
})
@Table(name = "SECURITY_QUESTIONS")
public class SecurityQuestions implements Serializable {
    @Column(nullable = false)
    private String question;
    @Id
    @Column(name="SQU_ID", nullable = false)
    private Long squId;

    public SecurityQuestions() {
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Long getSquId() {
        return squId;
    }

    public void setSquId(Long squId) {
        this.squId = squId;
    }
}
