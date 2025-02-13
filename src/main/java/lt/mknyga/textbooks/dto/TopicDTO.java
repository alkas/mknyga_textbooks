package lt.mknyga.textbooks.dto;

import java.util.List;

public class TopicDTO {
    private String id;
    private Integer topicId;
    private Integer textbookId;
    private Integer sectionId;
    private String title;
    private Integer lessons;
    private List<String> pages;
    private List<String> practice;
    private String competencies;
    private String achievements;
    private String tasks;
    private String criteria;
    private TextbookDTO textbook;
    private SectionDTO section;
    private List<MaterialDTO> materials;

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
    public Integer getLessons() { return lessons; }
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
    public TextbookDTO getTextbook() { return textbook; }
    public SectionDTO getSection() { return section; }
    public List<MaterialDTO> getMaterials() { return materials; }

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
    public void setCompetencies(String competencies) { this.competencies = competencies; }
    public void setAchievements(String achievements) {
        this.achievements = achievements;
    }
    public void setTasks(String tasks) {
        this.tasks = tasks;
    }
    public void setCriteria(String criteria) {
        this.criteria = criteria;
    }
    public void setTextbook(TextbookDTO textbook) { this.textbook = textbook; }
    public void setSection(SectionDTO section) { this.section = section; }
    public void setMaterials(List<MaterialDTO> materials) { this.materials = materials; }
}
