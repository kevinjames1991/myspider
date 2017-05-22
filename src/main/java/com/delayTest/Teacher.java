package com.delayTest;

import java.util.concurrent.DelayQueue;  
import java.util.concurrent.ExecutorService;  
class Teacher implements Runnable{    
    private DelayQueue<Student> students;    
        
    public Teacher(DelayQueue<Student> students) {    
        super();    
        this.students = students;    
    }    
    
    
    @Override    
    public void run() {    
        try {    
            System.out.println("考试开始……");    
            while (!Thread.interrupted()) {    
                students.take().run();    
            }    
            System.out.println("考试结束……");    
        } catch (InterruptedException e) {    
            e.printStackTrace();    
        }    
    
    }    
        
}    