package com.example.teamProjectTwo;

import com.example.teamProjectTwo.service.Impl.CsvFileHandlerServiceImpl;
import com.example.teamProjectTwo.service.Impl.JsonFileHandlerServiceImpl;
import com.example.teamProjectTwo.service.Impl.XmlFileHandlerServiceImpl;
import com.example.teamProjectTwo.service.MyFileHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TeamProjectTwoApplication extends Thread{

	MyFileHandler myFileHandler;
	boolean checkReadWrite;

	TeamProjectTwoApplication(String name, MyFileHandler myFileHandler,boolean checkReadWrite)
	{
		super(name);
		this.myFileHandler = myFileHandler;
		this.checkReadWrite = checkReadWrite;
	}
	public void run()
	{

		if(checkReadWrite == true){
			try{
				myFileHandler.read();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			try{
				myFileHandler.write();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}



	}


	public static void main(String[] args) throws Exception{
		SpringApplication.run(TeamProjectTwoApplication.class, args);
		TeamProjectTwoApplication Thread1 = new TeamProjectTwoApplication("CSVWriteThread", new CsvFileHandlerServiceImpl(),true);
		Thread1.start();

		TeamProjectTwoApplication Thread2 = new TeamProjectTwoApplication("XMLWriteThread", new XmlFileHandlerServiceImpl() ,true);
		Thread2.start();

		TeamProjectTwoApplication Thread3 = new TeamProjectTwoApplication("JSONWriteThread",new JsonFileHandlerServiceImpl(),true);
		Thread3.start();

		Thread1.join();
		Thread2.join();
		Thread3.join();


		TeamProjectTwoApplication Thread4 = new TeamProjectTwoApplication("CSVWriteThread", new CsvFileHandlerServiceImpl(),false);
		Thread4.start();

		TeamProjectTwoApplication Thread5 = new TeamProjectTwoApplication("XMLWriteThread",new XmlFileHandlerServiceImpl(),false);
		Thread5.start();

		TeamProjectTwoApplication Thread6 = new TeamProjectTwoApplication("JSONWriteThread",new JsonFileHandlerServiceImpl(),false);
		Thread6.start();

		Thread4.join();
		Thread5.join();
		Thread6.join();

	}

}
