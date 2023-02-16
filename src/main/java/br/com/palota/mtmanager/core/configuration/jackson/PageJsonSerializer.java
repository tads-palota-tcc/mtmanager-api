package br.com.palota.mtmanager.core.configuration.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.data.domain.Page;

import java.io.IOException;

@JsonComponent
public class PageJsonSerializer extends JsonSerializer<Page<?>> {

    @Override
    public void serialize(Page<?> value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeObjectField("content", value.getContent());
        gen.writeObjectField("size", value.getSize());
        gen.writeObjectField("totalElements", value.getTotalElements());
        gen.writeObjectField("totalPages", value.getTotalPages());
        gen.writeObjectField("number", value.getNumber());
        gen.writeObjectField("last", value.isLast());
        gen.writeObjectField("first", value.isFirst());
        gen.writeEndObject();
    }
}
