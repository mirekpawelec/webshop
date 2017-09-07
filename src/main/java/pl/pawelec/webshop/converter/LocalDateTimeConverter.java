/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.converter;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 *
 * @author mirek
 */
@Converter(autoApply = true)
public class LocalDateTimeConverter implements AttributeConverter<LocalDateTime, Timestamp>{

    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime attribute) {
        return attribute==null? null: Timestamp.valueOf(attribute); //Timestamp.from( attribute.toInstant(ZoneOffset.ofHours(0)) );
        //return new Date ( attribute.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli() );
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp dbData) {
        return dbData==null ? null : dbData.toLocalDateTime(); //LocalDateTime.ofInstant( dbData.toInstant(), ZoneOffset.ofHours(0) );
    }

    
}
