package com.example.mynote.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Period {
    private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd hh:mm:ss.sss";

    private Date begin;
    private Date end;

    public Boolean checkIsInPeriod(Date date)
    {
        return date.after(begin) && date.before((end));
    }

    public void setBegin(String begin) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(DEFAULT_DATE_FORMAT);

        this.begin = formatter.parse(begin);
    }

    public void setBegin(Date begin){
        this.begin = begin;
    }

    public void setEnd(String end) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(DEFAULT_DATE_FORMAT);

        this.end = formatter.parse(end);
    }

    public void setEnd(Date end){
        this.end = end;
    }
}
