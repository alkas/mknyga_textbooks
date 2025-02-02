package lt.mknyga.textbooks.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "sections")
public class Section {
    @Id
    private String id;
    @Field("section_id")
    private Integer sectionId;
    @Field("textbook_id")
    private Integer textbookId;
    @Field("book_no")
    private Integer bookNo;
    private String title;

    // Default constructor
    public Section() {}

    // Getters
    public String getId() {
        return id;
    }
    public Integer getSectionId() {
        return sectionId;
    }
    public Integer getTextbookId() {
        return textbookId;
    }
    public String getTitle() {
        return title;
    }
    public Integer getBookNo() { return bookNo; }

    //Setters
    public void setId(String id) {
        this.id = id;
    }
    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
    }
    public void setTextbookId(Integer textbookId) {
        this.textbookId = textbookId;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setBookNo(Integer bookNo) { this.bookNo = bookNo; }
}
