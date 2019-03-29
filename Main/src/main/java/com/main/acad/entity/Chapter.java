package com.main.acad.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = {"id", "name", "subchapters", "referenceOnFile"})
@ToString

public class Chapter {
    private int id;
    private String name;
    private List<Chapter> subchapters;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Chapter> getSubchapters() {
        return subchapters;
    }

    public void setSubchapters(List<Chapter> subchapters) {
        this.subchapters = subchapters;
    }

    public String getReferenceOnFile() {
        return referenceOnFile;
    }

    public void setReferenceOnFile(String referenceOnFile) {
        this.referenceOnFile = referenceOnFile;
    }

    private String referenceOnFile;
}