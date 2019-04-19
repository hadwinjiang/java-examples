package com.hadwin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hadwin.models.Patient;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatApp {
    public static void main(String args[]) {

        ObjectMapper mapper = new ObjectMapper();

        try {
            Patient patient = mapper.readValue(new File("writePatient.JSON"), Patient.class);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd");
            Date date = df.parse(patient.getDateOfBirth());

            mapper.setDateFormat(df);
            System.out.println("patient birthday: ");
            System.out.println(mapper.writeValueAsString(date));

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }
}
