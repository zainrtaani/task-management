package com.taski.taskmanagement.controller.user;

import com.taski.taskmanagement.entity.Team;
import com.taski.taskmanagement.entity.user.User;
import com.taski.taskmanagement.exception.EntityNotFoundException;
import com.taski.taskmanagement.repo.UserRepository;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.springframework.util.StringUtils;

import java.io.IOException;

public class UserDeserializer extends StdDeserializer<User> {
    private final UserRepository userRepository;

    public UserDeserializer(UserRepository userRepository) {
        super(User.class);
        this.userRepository = userRepository;
    }

    @Override
    public User deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {

        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String userId = node.get("id").asText();
        if(StringUtils.hasText(userId)){
            return userRepository.findById(userId)
                    .orElseThrow(() -> new EntityNotFoundException(User.class));
        }
        return null;
    }
}
