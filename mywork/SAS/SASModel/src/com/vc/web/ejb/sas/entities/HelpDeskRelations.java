package com.vc.web.ejb.sas.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({
  @NamedQuery(name = "HelpDeskRelations.findAll", query = "select o from HelpDeskRelations o"),
  @NamedQuery(name = "HelpDeskRelations.findAll.size", query = "select count(o) from HelpDeskRelations o")
})
@Table(name = "HELP_DESK_RELATIONS")
public class HelpDeskRelations implements Serializable {
    private String description;
    @Id
    @Column(name="HDR_ID", nullable = false)
    private Long hdrId;
    @ManyToOne
    @JoinColumn(name = "HELP_RELATION_ID")
    private HelpDesks helpDesks;
    @ManyToOne
    @JoinColumn(name = "HELP_ID")
    private HelpDesks helpDesks1;

    public HelpDeskRelations() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getHdrId() {
        return hdrId;
    }

    public void setHdrId(Long hdrId) {
        this.hdrId = hdrId;
    }


    public HelpDesks getHelpDesks() {
        return helpDesks;
    }

    public void setHelpDesks(HelpDesks helpDesks) {
        this.helpDesks = helpDesks;
    }

    public HelpDesks getHelpDesks1() {
        return helpDesks1;
    }

    public void setHelpDesks1(HelpDesks helpDesks1) {
        this.helpDesks1 = helpDesks1;
    }
}
