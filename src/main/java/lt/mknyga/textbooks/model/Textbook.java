package lt.mknyga.textbooks.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "textbooks")
public class Textbook {
    @Id
    @Field("_id")
    private String id;
    @Field("id")
    private Integer textbookId;
    private String title;
    private String slug;
    private Integer grade;
    private Integer published;
    private String subject;

    public Textbook() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getTextbookId() {
        return textbookId;
    }

    public void setTextbookId(Integer textbookId) {
        this.textbookId = textbookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getPublished() {
        return published;
    }

    public void setPublished(Integer published) {
        this.published = published;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
