package lt.mknyga.textbooks.dto;

import java.util.List;

public class TopicListDTO {
    private String id;
    private String title;
    private Integer orderNo;
    private List<String> pages;
    private List<String> practice;
    private Integer lessons;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public List<String> getPages() {
        return pages;
    }

    public void setPages(List<String> pages) {
        this.pages = pages;
    }

    public List<String> getPractice() {
        return practice;
    }

    public void setPractice(List<String> practice) {
        this.practice = practice;
    }

    public Integer getLessons() {
        return lessons;
    }

    public void setLessons(Integer lessons) {
        this.lessons = lessons;
    }
}
