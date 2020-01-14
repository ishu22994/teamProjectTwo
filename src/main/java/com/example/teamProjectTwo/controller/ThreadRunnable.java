package com.example.teamProjectTwo.controller;

import com.example.teamProjectTwo.service.Impl.WritingServiceImpl;
import com.example.teamProjectTwo.service.MyFileHandler;
import org.springframework.beans.factory.annotation.Autowired;

public class ThreadRunnable implements Runnable {

    MyFileHandler myFileHandler;

    WritingServiceImpl writingServiceImpl;

    public ThreadRunnable(MyFileHandler myFileHandler, WritingServiceImpl writingService) {
        this.myFileHandler = myFileHandler;
        this.writingServiceImpl = writingService;
    }

    @Override
    public void run() {
        if(myFileHandler!= null){

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
}
