package com.example.teamProjectTwo.service.Impl;

import com.example.teamProjectTwo.dto.EmployeeDTO;
import com.example.teamProjectTwo.service.MyFileHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class CsvFileHandlerServiceImpl implements MyFileHandler {


    @Autowired
    private KafkaController kafkaController;

    @Override
    public  void read() {
        String csvFile = "/Users/namanbhatt/Downloads/employee.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                String[] c = line.split(cvsSplitBy);
                double experience = new Double(c[3]);
                Date date = new SimpleDateFormat("MM/dd/yy").parse(c[2]);
                EmployeeDTO employeeDTO = new EmployeeDTO();
                employeeDTO.setFirstName(c[0]);
                employeeDTO.setLastName(c[1]);
                employeeDTO.setDateOfBirth(date);
                employeeDTO.setExperience(experience);

                kafkaController.post(employeeDTO);
            }
            //send employeeDto to kafka

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public int columnsInCSV(){
        return 5;
    }

}
