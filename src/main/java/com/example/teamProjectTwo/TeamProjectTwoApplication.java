package com.example.teamProjectTwo;

import com.example.teamProjectTwo.service.Impl.CsvFileHandlerServiceImpl;
import com.example.teamProjectTwo.service.Impl.JsonFileHandlerServiceImpl;
import com.example.teamProjectTwo.service.Impl.XmlFileHandlerServiceImpl;
import com.example.teamProjectTwo.service.MyFileHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TeamProjectTwoApplication {

	public static void main(String[] args) throws Exception{
		SpringApplication.run(TeamProjectTwoApplication.class, args);

	}

}
