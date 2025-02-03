package lt.mknyga.textbooks.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TextbookDTO {
    @JsonProperty("id")
    private String id;
    @JsonProperty("textbookId")
    private Integer textbookId;
    @JsonProperty("title")
    private String title;
    @JsonProperty("slug")
    private String slug;
    @JsonProperty("grade")
    private Integer grade;
    @JsonProperty("subject")
    private String subject;
    @JsonProperty("published")
    private Integer published;
    @JsonProperty("sections")
    private List<SectionDTO> sections;
    @JsonProperty("topics")
    private List<TopicListDTO> topics;

    // Getters
    public String getId() { return id; }
    public Integer getTextbookId() { return textbookId; }
    public String getTitle() { return title; }
    public String getSlug() { return slug; }
    public Integer getGrade() { return grade; }
    public Integer getPublished() { return published; }
    public String getSubject() { return subject; }
    public List<SectionDTO> getSections() { return sections; }
    public List<TopicListDTO> getTopics() { return topics; }

    // Setters
    public void setId(String id) { this.id = id; }
    public void setTextbookId(Integer textbookId) { this.textbookId = textbookId; }
    public void setTitle(String title) { this.title = title; }
    public void setSlug(String slug) { this.slug = slug; }
    public void setGrade(Integer grade) { this.grade = grade; }
    public void setPublished(Integer published) { this.published = published; }
    public void setSubject(String subject) { this.subject = subject; }
    public void setSections(List<SectionDTO> sections) { this.sections = sections; }
    public void setTopics(List<TopicListDTO> topics) { this.topics = topics; }
}
