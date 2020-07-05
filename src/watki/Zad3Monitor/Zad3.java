package watki.Zad3Monitor;

import java.util.Random;

public class Zad3 {
    public static class KonsumentProducent
    {
        private static int produkty;

        private boolean czyMogeProdukowac = true;

        public KonsumentProducent()
        {
            produkty = 1000;
        }

        public synchronized void producent() throws InterruptedException
        {
            while(!czyMogeProdukowac)
                wait();
            Random gen = new Random();
            int nowy_produkt = gen.nextInt(1000) + 1;
            KonsumentProducent.produkty = KonsumentProducent.produkty + nowy_produkt;
            System.out.println("Producent wyprodukował : "+ nowy_produkt + ", więc mamy: " + KonsumentProducent.produkty);
            czyMogeProdukowac = false;
            notifyAll();
        }
        public synchronized void konsument() throws InterruptedException
        {
            while(czyMogeProdukowac)
                wait();
            Random gen = new Random();
            int skonsumowany_produkt = gen.nextInt(1000) + 1;
            KonsumentProducent.produkty = KonsumentProducent.produkty - skonsumowany_produkt;
            if( KonsumentProducent.produkty < 0) KonsumentProducent.produkty = 0; // nie chcialem aby bylo ponizej zera
            System.out.println("Konsument skonsumował  : "+ skonsumowany_produkt + ", więc mamy: " + KonsumentProducent.produkty);
            czyMogeProdukowac = true;
            notifyAll();
        }

        public void monitor()
        {
            Thread watekKonsumenta = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (;;) {
                        try {
                            konsument();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            Thread watekProducenta = new Thread(() -> {
                for (;;) {
                    try {
                        producent();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            watekKonsumenta.start();
            watekProducenta.start();
        }
    }
    public static void main(String[] args) {
        KonsumentProducent kp = new KonsumentProducent();
        kp.monitor();
    }
}
