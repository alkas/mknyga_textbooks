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
    private List<MaterialDTO> materials;
    private List<String> competencies;
    private List<String> achievements;
    private List<String> tasks;
    private List<String> criteria;
    private TextbookDTO textbook;
    private SectionDTO section;

    public static class MaterialDTO {
        private String type;
        private String desc;
        private List<String> resources;

        // Getters
        public String getType() {
            return type;
        }
        public String getDesc() {
            return desc;
        }
        public List<String> getResources() {
            return resources;
        }

        // Setters
        public void setType(String type) {
            this.type = type;
        }
        public void setDesc(String desc) {
            this.desc = desc;
        }
        public void setResources(List<String> resources) {
            this.resources = resources;
        }
    }

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
    public List<MaterialDTO> getMaterials() {
        return materials;
    }
    public List<String> getCompetencies() {
        return competencies;
    }
    public List<String> getAchievements() {
        return achievements;
    }
    public List<String> getTasks() {
        return tasks;
    }
    public List<String> getCriteria() {
        return criteria;
    }
    public TextbookDTO getTextbook() { return textbook; }
    public SectionDTO getSection() { return section; }

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
    public void setMaterials(List<MaterialDTO> materials) {
        this.materials = materials;
    }
    public void setCompetencies(List<String> competencies) { this.competencies = competencies; }
    public void setAchievements(List<String> achievements) {
        this.achievements = achievements;
    }
    public void setTasks(List<String> tasks) {
        this.tasks = tasks;
    }
    public void setCriteria(List<String> criteria) {
        this.criteria = criteria;
    }
    public void setTextbook(TextbookDTO textbook) { this.textbook = textbook; }
    public void setSection(SectionDTO section) { this.section = section; }
}
