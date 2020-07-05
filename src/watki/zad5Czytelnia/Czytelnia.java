package watki.zad5Czytelnia;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Czytelnia {

    public static class Biblioteka {
        private final int liczbaCzytelnikow;
        private final int liczbaPisarzy;
        private int czytajacy = 0;
        private int piszacy = 0;
        BlockingQueue<Integer> ksiazki = new LinkedBlockingQueue<>();
        BlockingQueue<Integer> rekopisma = new LinkedBlockingQueue<>();

        public Biblioteka(int czytelnicy, int pisarze) {
            czytajacy = 0;
            piszacy = 0;
            if (pisarze == czytelnicy) pisarze--;
            liczbaCzytelnikow = czytelnicy;
            liczbaPisarzy = pisarze;
            otworzBiblioteke(liczbaPisarzy);
        }

        public void otworzBiblioteke(int dlaKogo) {
            rekopisma.clear();
            ksiazki.clear();
            Random gen = new Random();
            if (dlaKogo == liczbaCzytelnikow) {
                System.out.println("Biblioteka szykowana dla czytelnikow");
                rekopisma.clear();
                for (int i = 0; i < liczbaCzytelnikow; i++) {
                    ksiazki.add(gen.nextInt(100));
                }
            } else {
                ksiazki.clear();
                System.out.println("Biblioteka szykowana dla pisarzy");
                for (int i = 0; i < liczbaPisarzy; i++) {
                    rekopisma.add(gen.nextInt(100));
                }
            }
        }

        public void pisz() throws InterruptedException {

            int pismo = rekopisma.take();
            Random time = new Random();
            piszacy++;
            System.out.println("W bibliotece pisarzy " + piszacy + " ,pisarz pisze pismo nr " + pismo);
            Thread.sleep(time.nextInt(100));
            rekopisma.put(pismo);
            piszacy--;
            if (piszacy == 0) {
                otworzBiblioteke(liczbaCzytelnikow);
            }
            Thread.sleep(time.nextInt(5000));
        }

        public void czytaj() throws InterruptedException {
            int ksiazka = ksiazki.take();
            Random time = new Random();
            czytajacy++;
            System.out.println("W bibliotece czytajacych " + czytajacy + " ,czytelnik czyta pismo nr " + ksiazka);
            Thread.sleep(time.nextInt(100));
            ksiazki.put(ksiazka);
            czytajacy--;
            if (czytajacy == 0) {
                otworzBiblioteke(liczbaPisarzy);
            }
            Thread.sleep(time.nextInt(5000));
        }

        public void uzytkownicy() {
            Thread[] watekPisarza = new Thread[liczbaPisarzy];
            Thread[] watekCzytelnika = new Thread[liczbaCzytelnikow];
            for (int i = 0; i < watekPisarza.length; i++) {
                watekPisarza[i] = new Thread(() -> {
                    for (; ; ) {
                        try {
                            pisz();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }

            for (int i = 0; i < watekCzytelnika.length; i++) {
                watekCzytelnika[i] = new Thread(() -> {
                    for (; ; ) {
                        try {
                            czytaj();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
            for (int i = 0; i < watekPisarza.length; i++) {
                watekPisarza[i].start();
            }

            for (int i = 0; i < watekCzytelnika.length; i++) {
                watekCzytelnika[i].start();
            }
        }
    }

    public static void main(String[] args) {
        Biblioteka biblioteka = new Biblioteka(12,6);
        biblioteka.uzytkownicy();
        //System.out.println("Hello World"); //obowiazkowy kod :)
    }
}