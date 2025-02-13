package lt.mknyga.textbooks.util;

import lt.mknyga.textbooks.dto.*;
import lt.mknyga.textbooks.model.Material;
import lt.mknyga.textbooks.model.Section;
import lt.mknyga.textbooks.model.Textbook;
import lt.mknyga.textbooks.model.Topic;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DTOConverter {

    public TextbookDTO convertToTextbookDTO(Textbook textbook, List<Section> sections, List<Topic> topics) {
        TextbookDTO textbookDTO = new TextbookDTO();
        textbookDTO.setId(textbook.getId());
        textbookDTO.setTextbookId(textbook.getTextbookId());
        textbookDTO.setTitle(textbook.getTitle());
        textbookDTO.setSlug(textbook.getSlug());
        textbookDTO.setGrade(textbook.getGrade());
        textbookDTO.setSubject(textbook.getSubject());
        textbookDTO.setPublished(textbook.getPublished());

        if (sections != null) {
            textbookDTO.setSections(sections.stream()
                    .map(this::convertToSectionDTO)
                    .collect(Collectors.toList()));
        }
        if (topics != null) {
            textbookDTO.setTopics(topics.stream()
                    .map(this::convertToTopicListDTO)
                    .collect(Collectors.toList()));
        }
        return textbookDTO;
    }

    public TextbookListDTO convertToTextbookListDTO(Textbook textbook) {
        TextbookListDTO textbookListDTO = new TextbookListDTO();
        textbookListDTO.setId(textbook.getId());
        textbookListDTO.setTextbookId(textbook.getTextbookId());
        textbookListDTO.setTitle(textbook.getTitle());
        textbookListDTO.setSlug(textbook.getSlug());
        textbookListDTO.setGrade(textbook.getGrade());
        textbookListDTO.setPublished(textbook.getPublished());
        textbookListDTO.setSubject(textbook.getSubject());
        return textbookListDTO;
    }

    public SectionDTO convertToSectionDTO(Section section) {
        SectionDTO sectionDTO = new SectionDTO();
        sectionDTO.setId(section.getId());
        sectionDTO.setTitle(section.getTitle());
        sectionDTO.setSectionId(section.getSectionId());
        sectionDTO.setBookNo(section.getBookNo());
        sectionDTO.setTextbookId(section.getTextbookId());
        return sectionDTO;
    }

    public TopicDTO convertToTopicDTO(Topic topic, List<Material> materials, Section section, Textbook textbook) {
        TopicDTO topicDTO = new TopicDTO();
        topicDTO.setId(topic.getId());
        topicDTO.setTopicId(topic.getTopicId());
        topicDTO.setTextbookId(topic.getTextbookId());
        topicDTO.setSectionId(topic.getSectionId());
        topicDTO.setTitle(topic.getTitle());
        topicDTO.setPages(topic.getPages());
        topicDTO.setPractice(topic.getPractice());
        topicDTO.setLessons(topic.getLessons());
        topicDTO.setAchievements(topic.getAchievements());
        topicDTO.setCompetencies(topic.getCompetencies());
        topicDTO.setCriteria(topic.getCriteria());
        topicDTO.setTasks(topic.getTasks());

        if (materials != null) {
            topicDTO.setMaterials(materials.stream()
                    .map(this::convertToMaterialDTO)
                    .collect(Collectors.toList()));
        }

        if (textbook != null) {
            topicDTO.setTextbook(convertToTextbookDTO(textbook, null, null));
        }
        if (section != null) {
            topicDTO.setSection(convertToSectionDTO(section));
        }
        return topicDTO;
    }

    public TopicListDTO convertToTopicListDTO(Topic topic) {
        TopicListDTO toipcListDto = new TopicListDTO();
        toipcListDto.setId(topic.getId());
        toipcListDto.setTopicId(topic.getTopicId());
        toipcListDto.setTextbookId(topic.getTextbookId());
        toipcListDto.setSectionId(topic.getSectionId());
        toipcListDto.setTitle(topic.getTitle());
        toipcListDto.setLessons(topic.getLessons());
        toipcListDto.setPages(topic.getPages());
        toipcListDto.setPractice(topic.getPractice());
        return toipcListDto;
    }

    public MaterialDTO convertToMaterialDTO(Material material) {
        MaterialDTO materialDTO = new MaterialDTO();
        materialDTO.setId(material.getId());
        materialDTO.setTopicId(material.getTopicId());
        materialDTO.setType(material.getType());
        materialDTO.setResource(material.getResource());
        materialDTO.setDescription(material.getDescription());
        return materialDTO;
    }
}
