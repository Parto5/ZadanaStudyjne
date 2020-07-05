package watki.Zad4KolejkaBlokujaca;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Zad4 {
    public static class KonsumentProducent
    {
        private BlockingQueue<Integer> namagazynie = new LinkedBlockingQueue<>();
        private BlockingQueue<Integer> dokonsumpcji = new LinkedBlockingQueue<>();


        private boolean czyMogeProdukowac = true;

        public KonsumentProducent() throws InterruptedException {
            namagazynie.put(1000); //startowa wartosc na pierwszej kolejce
        }

        public void producent() throws InterruptedException
        {
            int produkty = namagazynie.take();
            Random gen = new Random();
            int nowy_produkt = gen.nextInt(1000) + 1;
            produkty = produkty + nowy_produkt;
            System.out.println("Producent wyprodukował : "+ nowy_produkt + ", więc mamy: " + produkty);
            dokonsumpcji.put(produkty);

        }
        public void konsument() throws InterruptedException
        {
            int produkty = dokonsumpcji.take();
            Random gen = new Random();
            int skonsumowany_produkt = gen.nextInt(1000) + 1;
            produkty = produkty - skonsumowany_produkt;
            if( produkty < 0) produkty = 0; // nie chcialem aby bylo ponizej zera
            System.out.println("Konsument skonsumował  : "+ skonsumowany_produkt + ", więc mamy: " + produkty);
            namagazynie.put(produkty);
        }

        public void kolejki()
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
    public static void main(String[] args) throws InterruptedException {
        KonsumentProducent kp = new KonsumentProducent();
        kp.kolejki();
    }
}
