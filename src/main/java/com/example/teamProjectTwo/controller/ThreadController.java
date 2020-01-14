package com.example.teamProjectTwo.controller;


import com.example.teamProjectTwo.listener.KafkaConsumer;
import com.example.teamProjectTwo.service.Impl.CsvFileHandlerServiceImpl;
import com.example.teamProjectTwo.service.Impl.JsonFileHandlerServiceImpl;
import com.example.teamProjectTwo.service.Impl.KafkaController;
import com.example.teamProjectTwo.service.Impl.XmlFileHandlerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/threadCall")
public class ThreadController {

    @Autowired
    private CsvFileHandlerServiceImpl csvFileHandlerService;

    @Autowired
    private XmlFileHandlerServiceImpl xmlFileHandlerService;

    @Autowired
    private  JsonFileHandlerServiceImpl jsonFileHandlerService;

    @Autowired
    KafkaConsumer kafkaConsumer;


    @GetMapping("/")
    public void threadCalling() throws InterruptedException{

        

        Thread thread1 = new Thread(new ThreadRunnable(csvFileHandlerService));
        thread1.setName("CSVWriteThread");
        thread1.start();
        Thread thread2 = new Thread(new ThreadRunnable(xmlFileHandlerService));
        thread2.setName("XMLWriteThread");
        thread2.start();
        Thread thread3 = new Thread(new ThreadRunnable(jsonFileHandlerService));
        thread3.setName("JSONWriteThread");
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();



    /*    ThreadController Thread4 = new ThreadController("MongoWriteThread", null);
        Thread4.start();

        ThreadController Thread5 = new ThreadController("PostgresWriteThread",null);
        Thread5.start();



        Thread4.join();
        Thread5.join();
*/
    }

}
