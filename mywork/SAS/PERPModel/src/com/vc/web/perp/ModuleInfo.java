package com.vc.web.perp;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

public class ModuleInfo implements Serializable {
    private Long moduleId;
    private Long moduleIdChildOf;
    private String moduleName;
    private String moduleLabel;
    private String moduleDescription;
    private Long gusId;

    private List<ModuleInfo> childModules = new ArrayList<ModuleInfo>();

    public ModuleInfo() {

    }

    public ModuleInfo(Long moduleId, Long moduleIdChildOf, String moduleName, 
                      String moduleLabel, String moduleDescription, 
                      Long gusId) {
        this.moduleId = moduleId;
        this.moduleName = moduleName;
        this.moduleLabel = moduleLabel;
        this.moduleIdChildOf = moduleIdChildOf;
        this.moduleDescription = moduleDescription;
        this.gusId = gusId;
    }

    public void setModuleId(Long moduleId) {
        this.moduleId = moduleId;
    }

    public Long getModuleId() {
        return moduleId;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleLabel(String moduleLabel) {
        this.moduleLabel = moduleLabel;
    }

    public String getModuleLabel() {
        return moduleLabel;
    }

    public void setModuleIdChildOf(Long moduleIdChildOf) {
        this.moduleIdChildOf = moduleIdChildOf;
    }

    public Long getModuleIdChildOf() {
        return moduleIdChildOf;
    }

    public void setModuleDescription(String moduleDescription) {
        this.moduleDescription = moduleDescription;
    }

    public String getModuleDescription() {
        return moduleDescription;
    }

    public List<ModuleInfo> getChildModules() {
        return this.childModules;
    }

    public void addChildModule(ModuleInfo moduleInfo) {
        this.childModules.add(moduleInfo);
    }

    public void setGusId(Long gusId) {
        this.gusId = gusId;
    }

    public Long getGusId() {
        return gusId;
    }
}
