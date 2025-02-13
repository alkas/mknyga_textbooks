package lt.mknyga.textbooks.dto;


public class MaterialDTO {
    private String id;
    private Integer topicId;
    private String type;
    private String description;
    private String resource;

    // Default constructor
    public MaterialDTO() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public Integer getTopicId() { return topicId; }
    public void setTopicId(Integer topicId) { this.topicId = topicId; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getResource() { return resource; }
    public void setResource(String resource) { this.resource = resource; }
}
