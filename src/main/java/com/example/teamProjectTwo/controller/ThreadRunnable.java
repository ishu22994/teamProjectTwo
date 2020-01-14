package com.example.teamProjectTwo.controller;

import com.example.teamProjectTwo.service.MyFileHandler;

public class ThreadRunnable implements Runnable {

    MyFileHandler myFileHandler;

    public ThreadRunnable(MyFileHandler myFileHandler) {
        this.myFileHandler = myFileHandler;
        //this.writingServiceImpl = writingService;
    }

    @Override
    public void run() {
        if (myFileHandler != null) {

            try {
                myFileHandler.read();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
