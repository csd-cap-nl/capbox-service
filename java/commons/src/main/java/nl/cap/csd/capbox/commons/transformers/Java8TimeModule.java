package nl.cap.csd.capbox.commons.transformers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * This {@link com.fasterxml.jackson.databind.Module} add serializers for a cleaner java 8 time POJO conversion to JSON
 * strings.
 *
 * The basic format is: {year:####,month:##,day:##,hour:##,minute:##,second:##}, where the fields will be omitted if
 * they are not part of the time POJO.
 */
public class Java8TimeModule extends SimpleModule {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern(
            "'{year:'yyyy',month:'M',day:'d'}'");

    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern(
            "'{hour:'H',minute:'m',second:'s'}'");

    private static final DateTimeFormatter DATETIME_FORMAT = DateTimeFormatter.ofPattern(
            "'{year:'yyyy',month:'M',day:'d',hour:'H',minute:'m',second:'s'}'");

    public Java8TimeModule() {
        super();
        addSerializer(LocalDate.class, new JsonSerializer<LocalDate>() {
            @Override
            public void serialize(final LocalDate value, final JsonGenerator gen, final SerializerProvider serializers)
                    throws IOException {
                gen.writeString(DATE_FORMAT.format(value));
            }
        });
        addSerializer(LocalTime.class, new JsonSerializer<LocalTime>() {
            @Override
            public void serialize(final LocalTime value, final JsonGenerator gen, final SerializerProvider serializers)
                    throws IOException {
                gen.writeString(TIME_FORMAT.format(value));
            }
        });
        addSerializer(LocalDateTime.class, new JsonSerializer<LocalDateTime>() {
            @Override
            public void serialize(final LocalDateTime value, final JsonGenerator gen,
                                  final SerializerProvider serializers)
                    throws IOException {
                gen.writeString(DATETIME_FORMAT.format(value));
            }
        });
    }

}
