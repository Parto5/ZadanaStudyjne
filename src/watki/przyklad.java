package watki;

import java.util.concurrent.Semaphore;
/*
class MyThread extends Thread{
    @Override
    public void run() {
        System.out.println("hello from my thread");
    }
}*/

public class przyklad {
}
/*
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello World: " + Thread.currentThread());
                final Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("hello: " + Thread.currentThread());
                        try {
                            Thread.sleep(10 * 1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
                thread.start();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(thread.getState());
            }
        });
        t.getState(); // NEW
        t.start(); // RUNNABLE -> harmonogramowanie do działania / bądź już działa
        Thread.sleep(1000 * 3);
        //BLOCKED
        //WAITING
        //TIME_WAITING
        System.out.println(t.getState()); //TERMINATED
        new MyThread().start();
*//*
        final Semaphore a = new Semaphore(1); //--> semafor binarny
        final Semaphore b =  new Semaphore(0);
        Thread first = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i < 1000; i++) {
                    try {
                        a.acquire();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("1: "+i);
                    b.release();
                }
            }
        });

        Thread second = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i < 1000; i++) {
                    try {
                        b.acquire();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("2: "+i);
                    a.release();
                }
            }
        });

        first.start();
        second.start();*/