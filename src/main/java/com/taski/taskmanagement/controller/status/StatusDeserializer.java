package com.taski.taskmanagement.controller.status;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.taski.taskmanagement.entity.Status;
import com.taski.taskmanagement.entity.Team;
import com.taski.taskmanagement.entity.user.User;
import com.taski.taskmanagement.exception.EntityNotFoundException;
import com.taski.taskmanagement.repo.StatusRepository;
import com.taski.taskmanagement.repo.UserRepository;
import org.springframework.util.StringUtils;

import java.io.IOException;

public class StatusDeserializer extends StdDeserializer<Status> {
    private final StatusRepository statusRepository;

    public StatusDeserializer(StatusRepository statusRepository) {
        super(Status.class);
        this.statusRepository = statusRepository;
    }

    @Override
    public Status deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {

        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String statusId = node.get("id").asText();
        if(StringUtils.hasText(statusId)){
            return statusRepository.findById(statusId)
                    .orElseThrow(() -> new EntityNotFoundException(Status.class));
        }
        return null;
    }
}
