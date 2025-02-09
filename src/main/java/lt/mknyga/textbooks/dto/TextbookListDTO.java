package lt.mknyga.textbooks.dto;

import java.util.List;

public class TextbookListDTO {
    private String id;
    private Integer textbookId;
    private String title;
    private String slug;
    private Integer grade;
    private String subject;
    private Integer published;

    // Default constructoe
    public TextbookListDTO() {}

    // Getters
    public String getId() { return id; }
    public Integer getTextbookId() { return textbookId; }
    public String getTitle() { return title; }
    public String getSlug() { return slug; }
    public Integer getGrade() { return grade; }
    public Integer getPublished() { return published; }
    public String getSubject() { return subject; }

    // Setters
    public void setId(String id) { this.id = id; }
    public void setTextbookId(Integer textbookId) { this.textbookId = textbookId; }
    public void setTitle(String title) { this.title = title; }
    public void setSlug(String slug) { this.slug = slug; }
    public void setGrade(Integer grade) { this.grade = grade; }
    public void setPublished(Integer published) { this.published = published; }
    public void setSubject(String subject) { this.subject = subject; }

}
