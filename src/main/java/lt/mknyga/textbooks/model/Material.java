package lt.mknyga.textbooks.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "materials")
public class Material {
    @Id
    private String id;
    @Indexed
    private Integer topicId;
    private String type;
    private String description;
    private String resource;

    // default constructor
    public Material() {}

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
