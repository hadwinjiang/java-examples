package com.hadwin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.hadwin.models.Patient;

import java.io.File;
import java.io.IOException;

public class AppMain {
    public  static void main(String args[]){
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        Patient patient = new Patient();
        patient.setFirstName("Jean-Marc");
        patient.setLastName("Julien");
        patient.setDateOfBirth("1991-02-03");
        patient.setAddress("555 Main Street");
        patient.setCity("Chicago");
        patient.setState("IL");
        patient.setZip("54321");
        patient.setPhone("555-123-9876");

        try {
            mapper.writeValue(new File("writePatient.JSON"), patient);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
