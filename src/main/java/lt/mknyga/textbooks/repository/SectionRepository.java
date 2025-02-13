package lt.mknyga.textbooks.repository;

import lt.mknyga.textbooks.model.Section;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SectionRepository extends MongoRepository<Section, String> {
    List<Section> findAllByTextbookId(Integer textbookId);
    Optional<Section> findBySectionId(Integer sectionId);
    boolean existsBySectionId(Integer sectionId);
}
