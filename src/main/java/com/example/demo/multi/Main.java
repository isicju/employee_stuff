package com.example.demo.multi;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Main {


    static class SomeObject {
        int value = 10;

        public SomeObject() {

        }

    }

    static Queue<String> queue = new LinkedList<>();
    static Producer producer = new Producer();
    static Producer producer1 = new Producer();
    static Consumer consumer = new Consumer();
    static Consumer consumer1 = new Consumer();


    private static AtomicInteger atomicInteger = new AtomicInteger(10);
    private static AtomicReference<Object> atomicObject = new AtomicReference<>(10);


    private Map<String, String> map = new HashMap<>();

    static ReentrantLock lock = new ReentrantLock(true);
    final static Condition lessThan10  = lock.newCondition();
    final static Condition isEmpty = lock.newCondition();

    static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private List<String> list = new CopyOnWriteArrayList<>();


    public static void main(String[] args) {

        producer.setName("producerHighPriority");
        producer1.setName("producer1");
        consumer.setName("consumer");
        consumer1.setName("consumer1");

        producer.start();
        producer1.start();

        consumer.start();
        consumer1.start();

    }

    static void addValueLock() {
        try {
            readWriteLock.writeLock().lock();

        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    static void readValueReentrant() throws InterruptedException {
        try {
            readWriteLock.readLock().lock();

        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    static class Producer extends Thread {
        Producer() {
            super(() -> {
                try {
                    addValue();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    static class Consumer extends Thread {
        Consumer() {
            super(() -> {
                try {
                    readValue();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    static void addValue() throws InterruptedException {
        while (true) {
            try{
                lock.lock();
                if (queue.size() >= 10) {
                    System.out.println("more than 10 I fell asleep: " + Thread.currentThread().getName());
                    lessThan10.await();
                } else {
                    isEmpty.signalAll();
                    System.out.println("less than 10 I produce: " + Thread.currentThread().getName());
                    queue.add(Thread.currentThread().getName() + " " + System.nanoTime());
                }

            }finally {
                lock.unlock();
            }
        }
    }

    static void readValue() throws InterruptedException {
        while (true) {
            try{
                lock.lock();
                if (queue.isEmpty()) {
                    isEmpty.await();
                } else {
                    System.out.println(Thread.currentThread().getName() + " reading: " + queue.poll() + " " + System.nanoTime());
                    if(queue.size() < 10){
                        lessThan10.signalAll();
                    }
                }
            }finally {
                lock.unlock();
            }
        }
    }

}
