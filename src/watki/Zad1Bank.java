package watki;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Zad1Bank {
    public static class KonsumentProducent
    {
        final Semaphore produkuj = new Semaphore(1); //--> semafor binarny
        final Semaphore konsumuj =  new Semaphore(0);
        private Konsument konsument;
        private Producent producent;
        private static int produkty;

        public KonsumentProducent()
        {
            konsument = new Konsument(produkuj,konsumuj);
            producent = new Producent(produkuj,konsumuj);
            produkty = 1000;
        }
    }
    public static class Producent
    {
        final Semaphore produkuj;
        final Semaphore konsumuj;
        Random gen = new Random();
        Thread watek = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i =0; i<100; i++) {
                    int nowy_produkt = gen.nextInt(1000) + 1;
                    try {
                        produkuj.acquire();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    KonsumentProducent.produkty = KonsumentProducent.produkty + nowy_produkt;
                    System.out.println("Producent wyprodukował : "+ nowy_produkt + ", więc mamy: " + KonsumentProducent.produkty);
                    konsumuj.release();
                }
            }
        });
        public Producent(Semaphore a, Semaphore b)
        {
            this.produkuj = a;
            this.konsumuj = b;
        }
    }
    public static class Konsument
    {
        final Semaphore a;
        final Semaphore b;
        Random gen = new Random();
        Thread watek = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i =0; i<100; i++) {
                    int skonsumowany_produkt = gen.nextInt(1000) + 1;
                    //System.out.println(skonsumowany_produkt);
                    try {
                        b.acquire();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    KonsumentProducent.produkty = KonsumentProducent.produkty - skonsumowany_produkt;
                    if( KonsumentProducent.produkty < 0) KonsumentProducent.produkty = 0;
                    System.out.println("Konsument skonsumował  : "+ skonsumowany_produkt + ", więc mamy: " + KonsumentProducent.produkty);
                    a.release();
                }
            }
        });
        public Konsument(Semaphore a, Semaphore b)
        {
            this.a = a;
            this.b = b;
        }
    }
    public static void main(String[] args) throws InterruptedException {
        KonsumentProducent kp = new KonsumentProducent();
        kp.konsument.watek.start();
        kp.producent.watek.start();
    }
}
