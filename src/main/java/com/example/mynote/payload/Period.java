package com.example.mynote.payload;

import lombok.Data;

import java.util.Date;

@Data
public class Period {
    private Date begin;
    private Date end;

    public Boolean checkIsInPeriod(Date date)
    {
        return date.before(begin) && date.after((end));
    }
}
