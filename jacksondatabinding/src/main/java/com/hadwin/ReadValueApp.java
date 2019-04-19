package com.hadwin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hadwin.models.Patient;

import java.io.File;
import java.io.IOException;

public class ReadValueApp {

        public  static void main(String args[]) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                Patient patient = mapper.readValue(new File("writePatient.JSON"), Patient.class);
                System.out.println(patient.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
}
