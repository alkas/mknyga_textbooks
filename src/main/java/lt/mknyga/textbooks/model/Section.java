package lt.mknyga.textbooks.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "sections")
public class Section {
    @Id
    private String id;
    @Indexed(unique = true)
    private Integer sectionId;
    @Indexed
    private Integer textbookId;
    private Integer bookNo;
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
    public void setSectionId(Integer sectionId) { this.sectionId = sectionId; }

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

    public Integer getBookNo() { return bookNo; }
    public void setBookNo(Integer bookNo) { this.bookNo = bookNo; }






}
