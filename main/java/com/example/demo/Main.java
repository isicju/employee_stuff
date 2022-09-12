package com.example.demo;

import org.apache.poi.xssf.usermodel.XSSFPivotTable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Main {

    // VISIBILITY  + ATOMICITY = CONSISTENCY
    //
    // ORDERING
    volatile int x = 0;
    volatile int y = 0;

    volatile AtomicInteger atomicInteger = new AtomicInteger(0);
    volatile AtomicReference<Object> atomicReference = new AtomicReference<>(new Object());

    final Object lock = new Object();

    Queue<String> queue = new LinkedList<>();
    static KekThread  threadKek = new KekThread();
    static KekThread  threadKek1 = new KekThread();
    static KekThread  threadKek2 = new KekThread();
    public static void main(String[] args) throws InterruptedException {
        Main main = new Main();

        threadKek.start();
        threadKek2.start();
        threadKek1.start();
        threadKek.join();
    }

   static  class  KekThread extends java.lang.Thread {
        @Override
        public void run() {
            while (true){
                kuku();
            }
        }
    }

    private static synchronized void kuku(){
        System.out.println(threadKek.getState());
        System.out.println(threadKek1.getState());
        System.out.println(threadKek2.getState());
        System.out.println("kekekeke");
    }

    private  void readData() throws InterruptedException {
        synchronized(lock){
            while (true){
                System.out.println(Thread.currentThread() + " reading data");
                if(queue.isEmpty()){
                    System.out.println(Thread.currentThread() + " fall awaiting");
                    lock.wait();
                }else{
                    System.out.println(Thread.currentThread().getName() + " prints " + queue.poll());
                    lock.notifyAll();
                    break;
                }
            }
        }
    }

    private  void writeData() throws InterruptedException {
        synchronized(lock){
            while (true){
                System.out.println(Thread.currentThread() + " entering ");
                if(queue.size() >10){
                    lock.wait();
                }else{
                    queue.add(Thread.currentThread().getName() + System.currentTimeMillis());
                    lock.notifyAll();
                    break;
                }
            }
        }
    }



}
