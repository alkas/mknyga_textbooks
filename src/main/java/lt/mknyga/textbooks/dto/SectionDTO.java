package lt.mknyga.textbooks.dto;

public class SectionDTO {
    private String id;
    private Integer sectionId;
    private Integer textbookId;
    private Integer bookNo;
    private String title;

    // Getters
    public String getId() {
        return id;
    }
    public Integer getSectionId() { return sectionId; }
    public Integer getTextbookId() {
        return textbookId;
    }
    public Integer getBookNo() { return bookNo; }
    public String getTitle() {
        return title;
    }


    // Setters
    public void setId(String id) {
        this.id = id;
    }
    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
    }
    public void setTextbookId(Integer textbookId) {
        this.textbookId = textbookId;
    }
    public void setBookNo(Integer bookNo) { this.bookNo = bookNo; }
    public void setTitle(String title) { this.title = title; }
}
