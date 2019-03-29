package com.main.acad.dao;

import com.main.acad.entity.Chapter;

import java.io.FileReader;
import java.util.List;

public interface ChapterDao {

    void addChapter(String name);

    void updateChapter(Chapter chapter);

    void removeChapter(int id);

    FileReader getInformstioAboutChildren(String name);

    List<Chapter> getlistChapters();

    List<Chapter> getlistChildren(int id);

    boolean createNewChildChapter(String chapterName, String nameFile, String context, String nameSubChapters);

    List<Chapter> getListAllSubChapters();

    boolean deleteSubChapter(String nameSubChapter);

    boolean updateSubChapter(String chapterName, String newTextFile);

    List<Chapter> getListSimilarChapter(String chapterSimilar);

}
