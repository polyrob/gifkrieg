package com.gifkrieg.data.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;

/**
 * Created by robbie on 4/6/17.
 */
@Converter(autoApply = true)
public class TimeAttributeConverter implements AttributeConverter<Long, Timestamp> {

    @Override
    public Timestamp convertToDatabaseColumn(Long epoch) {
        return (epoch == null ? null : Timestamp.from(Instant.ofEpochSecond(epoch)));
    }

    @Override
    public Long convertToEntityAttribute(Timestamp sqlTimestamp) {
        return (sqlTimestamp == null ? null : sqlTimestamp.getTime());
    }
}