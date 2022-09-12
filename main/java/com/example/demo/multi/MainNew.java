package com.example.demo.multi;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MainNew {

    // visibility + atomicity = consistency
    // ordering + visibility = consistency


    // monitor
    // starvation
    // happens before!

    // lock concurrent util
    // concurrent collection
    // reentrant lock -> solve starvation
    // readwritelock
    // spring take a look

    static AtomicInteger counter = new AtomicInteger(0);

    static Queue<String> queue = new LinkedList<>();

    static Producer producer = new Producer();
    static Producer producer1 = new Producer();
    static Producer producer2 = new Producer();
    static Producer producer3 = new Producer();
    static Producer producer4 = new Producer();
    static Consumer consumer = new Consumer();
    static Consumer consumer1 = new Consumer();

    static ReadWriteLock lock = new ReentrantReadWriteLock();
//    final static Condition lessThan10 = lock.newCondition();
//    final static Condition isEmpty = lock.newCondition();

    public static void main(String[] args) throws InterruptedException {


        ReentrantLock lock = new ReentrantLock();




        long currentTime = System.currentTimeMillis();

        producer.setName("producer0");
        producer1.setName("producer1");
        producer2.setName("producer1");
        producer3.setName("producer1");
        producer4.setName("producer1");


        consumer.setName("consumer");
        consumer1.setName("consumer1");

        producer.start();
        producer1.start();

        consumer.start();
        consumer1.start();

        producer.join();
        producer1.join();
        producer2.join();
        producer3.join();
        producer4.join();
        consumer.join();
        consumer1.join();

        System.out.println("KEEEEEEEEEEE");
        System.out.println((System.currentTimeMillis() - currentTime) /1000F + " seconds");
        System.out.println("");
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
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }

    static void addValue() throws InterruptedException {
        for (int i = 0; i < 100_000; i++) {
            try {
                lock.writeLock().lock();
                counter.incrementAndGet();
//                System.out.println("less than 10 I produce: " + Thread.currentThread().getName());
                queue.add(Thread.currentThread().getName() + " " + System.nanoTime());
            } finally {
                lock.writeLock().unlock();
            }
        }
    }

    static void readValue()   {
        for (int i = 0; i < 100_000; i++) {

            try {
                lock.readLock().lock();
                counter.incrementAndGet();
                queue.poll();
//                System.out.println(Thread.currentThread().getName() + " reading: " + queue.poll() + " " + System.nanoTime());
            } finally {
                lock.readLock().unlock();
            }

        }
    }

}
