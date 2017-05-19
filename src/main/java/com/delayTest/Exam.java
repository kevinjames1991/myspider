package com.delayTest;
import java.util.Random;  
import java.util.concurrent.DelayQueue;  
import java.util.concurrent.ExecutorService;  
import java.util.concurrent.Executors;  
public class Exam {    
    static final int STUDENT_SIZE = 5;    
    public static void main(String[] args) throws Exception {    
        Random r = new Random();    
        DelayQueue<Student> students = new DelayQueue<Student>();    
        for(int i = 0; i < STUDENT_SIZE; i++){    
//        	Thread.sleep(1000);
            students.put(new Student("学生" + ( i + 1), 3000 + (100-i)*10));    
        }    
        System.out.println("put finished");
        ExecutorService exec = Executors.newCachedThreadPool();    
        students.put(new EndExam(12000,exec));//1200为考试结束时间    
        exec.execute(new Teacher(students));    
            
    }    
    
}     