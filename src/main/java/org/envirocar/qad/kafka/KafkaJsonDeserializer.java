package org.envirocar.qad.kafka;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Objects;

public class KafkaJsonDeserializer<T> implements KafkaDeserializer<T> {

    private static final Logger LOG = LoggerFactory.getLogger(KafkaJsonDeserializer.class);
    private final Class<T> type;
    private final ObjectMapper objectMapper;

    public KafkaJsonDeserializer(Class<T> type, ObjectMapper objectMapper) {
        this.type = Objects.requireNonNull(type);
        this.objectMapper = Objects.requireNonNull(objectMapper);
    }

    @Override
    public T deserialize(String s, byte[] bytes) {
        try {
            return this.objectMapper.readValue(bytes, this.type);
        } catch (IOException e) {
            LOG.error("Error reading " + this.type, e);
        }
        return null;
    }
}
