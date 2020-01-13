package com.example.teamProjectTwo.service.Impl;
import com.example.teamProjectTwo.dto.EmployeeDTO;
import com.example.teamProjectTwo.entity.EmployeePostgres;
import com.example.teamProjectTwo.repository.EmployeePostgresRepository;
import com.example.teamProjectTwo.service.EmployeeServicePostgres;
import com.example.teamProjectTwo.service.MyFileHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

    @Service
    public class XmlFileHandlerServiceImpl implements MyFileHandler,EmployeeServicePostgres {

        @Autowired
        EmployeePostgresRepository employeePostgresRepository;

        EmployeePostgres employeePostgres;

        @Override
        public void read() {
            try {
                //hgvvj
                File file = new File("/Users/ishitshah/Downloads/employee.xml");
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = null;
                db = dbf.newDocumentBuilder();
                Document doc = db.parse(file);
                doc.getDocumentElement().normalize();
                NodeList nodeList = doc.getElementsByTagName("employee");
                for (int iterator = 0; iterator < nodeList.getLength(); iterator++) {
                    Node node = nodeList.item(iterator);
                    Element element = (Element) node;
                    String firstName = element.getElementsByTagName("firstName").item(0).getTextContent();
                    String lastName = element.getElementsByTagName("lastName").item(0).getTextContent();
                    Date date = new SimpleDateFormat("MM/dd/yy").parse(element.getElementsByTagName("dateOfBirth").item(0).getTextContent());
                    double experience = new Double(element.getElementsByTagName("experience").item(0).getTextContent());
                    EmployeeDTO employee = new EmployeeDTO();
                    employee.setFirstName(firstName);
                    employee.setLastName(lastName);
                    employee.setDateOfBirth(date);
                    employee.setExperience(experience);
                }
                //send to kafka
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        @Override
        public  void write(){


            //fetch from kafka


        }


        @Override
        public void addPostgres(EmployeePostgres employeePostgres){
            employeePostgresRepository.save(employeePostgres);
        }

    }

