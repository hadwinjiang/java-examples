package com.hadwin.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hadwin.utils.CustomDateSerialzer;

import java.util.Date;

public class Event {

    public String name;

//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-mm-dd hh:mm")
//    public Date birthDate;

    @JsonSerialize(using = CustomDateSerialzer.class)
    public Date birthDate;

    public Event(String name, Date date) {
        this.name = name;
        this.birthDate = date;
    }
}
