package com.jobtracker.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "job-tracker.storage")
public class StorageProperties {
    private String resumeDir;
    private String noteDir;
    private String generalNoteDir;

    public String getResumeDir() {
        return resumeDir;
    }

    public void setResumeDir(String resumeDir) {
        this.resumeDir = resumeDir;
    }

    public String getNoteDir() {
        return noteDir;
    }

    public void setNoteDir(String noteDir) {
        this.noteDir = noteDir;
    }

    public String getGeneralNoteDir() {
        return generalNoteDir;
    }

    public void setGeneralNoteDir(String generalNoteDir) {
        this.generalNoteDir = generalNoteDir;
    }
}
