package lt.mknyga.textbooks.repository;

import lt.mknyga.textbooks.model.Material;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaterialRepository extends MongoRepository<Material, String> {
    List<Material> findAllByTopicId(Integer topicId);
    boolean existsByTopicId(Integer topicId);
}
