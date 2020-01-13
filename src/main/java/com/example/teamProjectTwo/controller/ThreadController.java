package com.example.teamProjectTwo.controller;


import com.example.teamProjectTwo.service.Impl.CsvFileHandlerServiceImpl;
import com.example.teamProjectTwo.service.Impl.JsonFileHandlerServiceImpl;
import com.example.teamProjectTwo.service.Impl.WritingServiceImpl;
import com.example.teamProjectTwo.service.Impl.XmlFileHandlerServiceImpl;
import com.example.teamProjectTwo.service.MyFileHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/threadCall")
public class ThreadController extends Thread {

    MyFileHandler myFileHandler;
    boolean checkReadWrite;

    @Autowired
    WritingServiceImpl writingServiceImpl;

    ThreadController(String name, MyFileHandler myFileHandler,boolean checkReadWrite)
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
                if(Thread.currentThread().getName().equals("MongoWriteThread")){
                    writingServiceImpl.writeMongo();
                }else{
                    writingServiceImpl.writePostgres();
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @GetMapping
    public void threadCalling() throws InterruptedException{

        ThreadController Thread1 = new ThreadController("CSVWriteThread", new CsvFileHandlerServiceImpl(),true);
        Thread1.start();

        ThreadController Thread2 = new ThreadController("XMLWriteThread", new XmlFileHandlerServiceImpl() ,true);
        Thread2.start();

        ThreadController Thread3 = new ThreadController("JSONWriteThread",new JsonFileHandlerServiceImpl(),true);
        Thread3.start();

        Thread1.join();
        Thread2.join();
        Thread3.join();


        ThreadController Thread4 = new ThreadController("MongoWriteThread", null,false);
        Thread4.start();

        ThreadController Thread5 = new ThreadController("PostgresWriteThread",null,false);
        Thread5.start();



        Thread4.join();
        Thread5.join();

    }

}
