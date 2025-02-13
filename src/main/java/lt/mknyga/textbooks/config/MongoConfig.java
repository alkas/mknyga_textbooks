package lt.mknyga.textbooks.config;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.data.mongodb.core.index.IndexDefinition;
import org.springframework.data.mongodb.core.index.IndexOperations;


@Configuration
public class MongoConfig {
    private final MongoTemplate mongoTemplate;

    public MongoConfig(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @PostConstruct
    public void initIndexes() {
        IndexOperations indexTextbooksOps = mongoTemplate.indexOps("textbooks");
        IndexDefinition indexTextbooksDefinition = new Index().on("textbookId", Sort.Direction.ASC).unique();
        indexTextbooksOps.ensureIndex(indexTextbooksDefinition);

        IndexOperations indexSectionsOps = mongoTemplate.indexOps("sections");
        IndexDefinition indexSectionsDefinition = new Index().on("sectionId", Sort.Direction.ASC).unique();
        indexSectionsOps.ensureIndex(indexSectionsDefinition);

        IndexOperations indexTopicsOps = mongoTemplate.indexOps("topics");
        IndexDefinition indexTopicsDefinition = new Index().on("topicId", Sort.Direction.ASC).unique();
        indexTopicsOps.ensureIndex(indexTopicsDefinition);
    }
}
