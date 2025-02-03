package lt.mknyga.textbooks.dto;

import java.util.List;

public class TopicListDTO {
    private String id;
    private Integer topicId;
    private Integer textbookId;
    private Integer sectionId;
    private String title;
    private Integer lessons;
    private List<String> pages;
    private List<String> practice;

    // Getters
    public String getId() {
        return id;
    }
    public Integer getTopicId() {
        return topicId;
    }
    public Integer getTextbookId() {
        return textbookId;
    }
    public Integer getSectionId() {
        return sectionId;
    }
    public String getTitle() {
        return title;
    }
    public Integer getLessons() {
        return lessons;
    }
    public List<String> getPages() {
        return pages;
    }
    public List<String> getPractice() {
        return practice;
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }
    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }
    public void setTextbookId(Integer textbookId) {
        this.textbookId = textbookId;
    }
    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setLessons(Integer lessons) {
        this.lessons = lessons;
    }
    public void setPages(List<String> pages) {
        this.pages = pages;
    }
    public void setPractice(List<String> practice) {
        this.practice = practice;
    }
}
