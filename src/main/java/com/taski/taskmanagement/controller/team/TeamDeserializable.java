package com.taski.taskmanagement.controller.team;

import com.taski.taskmanagement.entity.Team;
import com.taski.taskmanagement.exception.EntityNotFoundException;
import com.taski.taskmanagement.repo.TeamRepository;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.springframework.util.StringUtils;

import java.io.IOException;

public class TeamDeserializable extends StdDeserializer<Team> {
    private final TeamRepository teamRepository;

    public TeamDeserializable(TeamRepository teamRepository) {
        super(Team.class);
        this.teamRepository = teamRepository;
    }

    @Override
    public Team deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {

        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String teamDocumentId = node.get("id").asText();
        if(StringUtils.hasText(teamDocumentId)){
            return teamRepository.findById(teamDocumentId)
                    .orElseThrow(() -> new EntityNotFoundException(Team.class));
        }
        return null;
    }
}
