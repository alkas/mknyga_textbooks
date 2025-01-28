package lt.mknyga.textbooks.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "sections")
public class Section {
    @Id
    @Field("_id")
    private String id;
    @Field("id")
    private Integer sectionId;
    @Field("textbook_id")
    private Integer textbookId;
    private String title;

    // Default constructor
    public Section() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getSectionId() {
        return sectionId;
    }

    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
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
}
