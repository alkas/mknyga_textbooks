package lt.mknyga.textbooks.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "topics")
public class Topic {
    @Id
    private String id;
    @Indexed(unique = true)
    private Integer topicId;
    @Indexed
    private Integer textbookId;
    @Indexed
    private Integer sectionId;
    private String achievements;
    private String competencies;
    private String criteria;
    private Integer lessons;
    private List<String> pages;
    private List<String> practice;
    private String tasks;
    private String title;

    // Default constructor
    public Topic() {}

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

    public String getCompetencies() {
        return competencies;
    }
    public String getAchievements() {
        return achievements;
    }
    public String getTasks() {
        return tasks;
    }
    public String getCriteria() {
        return criteria;
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

    public void setCompetencies(String competencies) {
        this.competencies = competencies;
    }
    public void setAchievements(String achievements) {
        this.achievements = achievements;
    }
    public void setTasks(String tasks) {
        this.tasks = tasks;
    }
    public void setCriteria(String criteria) {
        this.criteria = criteria;
    }
}
