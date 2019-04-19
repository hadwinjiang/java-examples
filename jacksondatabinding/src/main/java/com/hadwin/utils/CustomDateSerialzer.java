package com.hadwin.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomDateSerialzer extends StdSerializer<Date> {

    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd hh:mm");

    protected CustomDateSerialzer() {
        this(null);
    }

    protected CustomDateSerialzer(Class t) {
        super(t);
    }

    public void serialize(Date value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeString(formatter.format(value) + "(Chicago local time)");
    }
}
