package lt.mknyga.textbooks.repository;

import lt.mknyga.textbooks.model.Textbook;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TextbookRepository extends MongoRepository<Textbook, String> {
    List<Textbook> findByGradeAndSubject(Integer grade, String subject);
    Optional<Textbook> findByTextbookId(Integer textbookId);
    List<Textbook> findByGrade(Integer grade);
    List<Textbook> findBySubject(String subject);
    List<Textbook> findBySlug(String slug);
    boolean existsByTextbookId(Integer textbookId);
    boolean existsById(String id);
}
