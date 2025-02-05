package lt.mknyga.textbooks.controller;

import lt.mknyga.textbooks.dto.ApiResponse;
import lt.mknyga.textbooks.dto.TopicDTO;
import lt.mknyga.textbooks.dto.TopicListDTO;
import lt.mknyga.textbooks.model.Topic;
import lt.mknyga.textbooks.service.TopicService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TopicController {
    private final TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping("/sections/{sectionId}/topics")
    public ResponseEntity<ApiResponse<List<TopicListDTO>>> getTopicsBySection(
            @PathVariable Integer sectionId) {
        return ResponseEntity.ok(new ApiResponse<>(topicService.findBySectionId(sectionId)));
    }

    @GetMapping("/textbooks/{textbookId}/topics")
    public ResponseEntity<ApiResponse<List<TopicListDTO>>> getTopicsByTextbook(
            @PathVariable Integer textbookId) {
        return ResponseEntity.ok(new ApiResponse<>(topicService.findByTextbookId(textbookId)));
    }

    @GetMapping("/topics/{topicId}")
    public ResponseEntity<ApiResponse<TopicDTO>> getTopic(@PathVariable Integer topicId) {
        return ResponseEntity.ok(new ApiResponse<>(topicService.findByTopicId(topicId)));
    }

    @PostMapping("/topics")
    public ResponseEntity<ApiResponse<TopicDTO>> createTopic(@RequestBody Topic topic) {
        return new ResponseEntity<>(new ApiResponse<>(topicService.create(topic)), HttpStatus.CREATED);
    }

    @PutMapping("/topics/{id}")
    public ResponseEntity<ApiResponse<TopicDTO>> updateTopic(
            @PathVariable String id,
            @RequestBody Topic topic) {
        return ResponseEntity.ok(new ApiResponse<>(topicService.update(id, topic)));
    }

    @DeleteMapping("/topics/{id}")
    public ResponseEntity<Void> deleteTopic(@PathVariable String id) {
        topicService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
