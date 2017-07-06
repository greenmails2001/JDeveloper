package com.vc.web.ejb.sas.entities;

import java.io.Serializable;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@NamedQueries({
  @NamedQuery(name = "HelpDesks.findAll", query = "select o from HelpDesks o"),
  @NamedQuery(name = "HelpDesks.findByCode", query = "select o from HelpDesks o where o.helpCode=:helpCode"),
  @NamedQuery(name = "HelpDesks.findAllRoot", query = "select o from HelpDesks o where o.helpDesks is null"),
  @NamedQuery(name = "HelpDesks.findAll.size", query = "select count(o) from HelpDesks o")
})
@Table(name = "HELP_DESKS")
@SequenceGenerator(name = "Hde_Seq", sequenceName = "Hde_Seq", 
                   allocationSize = 1)
public class HelpDesks implements Serializable {
    @Column(nullable = false)
    private String content;
    private String description;
    @Column(name="HELP_CODE", nullable = false)
    private String helpCode;
    @Id
    @Column(name="HELP_ID", nullable = false)
    @GeneratedValue(generator = "Hde_Seq")
    private Long helpId;
    @Column(nullable = false)
    private String title;
    @ManyToOne
    @JoinColumn(name = "HELP_PARENT_ID")
    private HelpDesks helpDesks;
    @OneToMany(mappedBy = "helpDesks")
    private List<HelpDesks> helpDesksList;
    @OneToMany(mappedBy = "helpDesks")
    private List<HelpDeskRelations> helpDeskRelationsList;
    @OneToMany(mappedBy = "helpDesks1")
    private List<HelpDeskRelations> helpDeskRelationsList1;

    public HelpDesks() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHelpCode() {
        return helpCode;
    }

    public void setHelpCode(String helpCode) {
        this.helpCode = helpCode;
    }

    public Long getHelpId() {
        return helpId;
    }

    public void setHelpId(Long helpId) {
        this.helpId = helpId;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public HelpDesks getHelpDesks() {
        return helpDesks;
    }

    public void setHelpDesks(HelpDesks helpDesks) {
        this.helpDesks = helpDesks;
    }

    public List<HelpDesks> getHelpDesksList() {
        return helpDesksList;
    }

    public void setHelpDesksList(List<HelpDesks> helpDesksList) {
        this.helpDesksList = helpDesksList;
    }

    public HelpDesks addHelpDesks(HelpDesks helpDesks) {
        getHelpDesksList().add(helpDesks);
        helpDesks.setHelpDesks(this);
        return helpDesks;
    }

    public HelpDesks removeHelpDesks(HelpDesks helpDesks) {
        getHelpDesksList().remove(helpDesks);
        helpDesks.setHelpDesks(null);
        return helpDesks;
    }

    public List<HelpDeskRelations> getHelpDeskRelationsList() {
        return helpDeskRelationsList;
    }

    public void setHelpDeskRelationsList(List<HelpDeskRelations> helpDeskRelationsList) {
        this.helpDeskRelationsList = helpDeskRelationsList;
    }

    public HelpDeskRelations addHelpDeskRelations(HelpDeskRelations helpDeskRelations) {
        getHelpDeskRelationsList().add(helpDeskRelations);
        helpDeskRelations.setHelpDesks(this);
        return helpDeskRelations;
    }

    public HelpDeskRelations removeHelpDeskRelations(HelpDeskRelations helpDeskRelations) {
        getHelpDeskRelationsList().remove(helpDeskRelations);
        helpDeskRelations.setHelpDesks(null);
        return helpDeskRelations;
    }

    public List<HelpDeskRelations> getHelpDeskRelationsList1() {
        return helpDeskRelationsList1;
    }

    public void setHelpDeskRelationsList1(List<HelpDeskRelations> helpDeskRelationsList1) {
        this.helpDeskRelationsList1 = helpDeskRelationsList1;
    }

    public HelpDeskRelations addHelpDeskRelations1(HelpDeskRelations helpDeskRelations) {
        getHelpDeskRelationsList1().add(helpDeskRelations);
        helpDeskRelations.setHelpDesks1(this);
        return helpDeskRelations;
    }

    public HelpDeskRelations removeHelpDeskRelations1(HelpDeskRelations helpDeskRelations) {
        getHelpDeskRelationsList1().remove(helpDeskRelations);
        helpDeskRelations.setHelpDesks1(null);
        return helpDeskRelations;
    }
}
