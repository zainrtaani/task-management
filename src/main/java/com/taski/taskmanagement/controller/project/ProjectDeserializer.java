package com.taski.taskmanagement.controller.project;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.taski.taskmanagement.entity.Project;
import com.taski.taskmanagement.entity.Status;
import com.taski.taskmanagement.exception.EntityNotFoundException;
import com.taski.taskmanagement.repo.ProjectRepository;
import com.taski.taskmanagement.repo.StatusRepository;
import org.springframework.util.StringUtils;

import java.io.IOException;

public class ProjectDeserializer extends StdDeserializer<Project> {
    private final ProjectRepository projectRepository;

    public ProjectDeserializer(ProjectRepository projectRepository) {
        super(Project.class);
        this.projectRepository = projectRepository;
    }

    @Override
    public Project deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {

        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String projectId = node.get("id").asText();
        if(StringUtils.hasText(projectId)){
            return projectRepository.findById(projectId)
                    .orElseThrow(() -> new EntityNotFoundException(Project.class));
        }
        return null;
    }
}
