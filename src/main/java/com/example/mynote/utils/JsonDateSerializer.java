package com.example.mynote.utils;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@JsonComponent
public class JsonDateSerializer {
    private static final String ISO_8601_DATE_FORMAT = "yyyy-MM-dd hh:mm:ss.sss";
    private static final SimpleDateFormat formatter = new SimpleDateFormat(ISO_8601_DATE_FORMAT);

    public static class Serializer extends JsonSerializer<Date>{

        @Override
        public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            try{
                if (date == null){
                    jsonGenerator.writeNull();
                }
                else{
                    jsonGenerator.writeString(formatter.format(date));
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static class Deserialize extends JsonDeserializer<Date>{

        @Override
        public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
            try{
                String dateAsString = jsonParser.getText();
                if(dateAsString == null){
                   return null;
                } else {
                    return formatter.parse(dateAsString);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }
    }

}
