/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.converter;

import java.time.LocalDate;
import java.sql.Date;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 *
 * @author mirek
 */
@Converter(autoApply = true)
public class DateToLocalDateConverter implements AttributeConverter<LocalDate, Date>{

    @Override
    public Date convertToDatabaseColumn(LocalDate localDate) {
        return localDate==null? null : Date.valueOf(localDate);
    }

    @Override
    public LocalDate convertToEntityAttribute(Date dbData) {
        return dbData==null? null : dbData.toLocalDate();
    }
    
}
