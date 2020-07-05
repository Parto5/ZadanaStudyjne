package watki.zad6Filozofowie;

import java.util.Random;

public class Filozof5 {

    public static class Filozof implements Runnable
    {
        private Widelec lewyWidelec;
        private Widelec prawyWidelec;

        public Filozof(Widelec lewy, Widelec prawy)
        {
            this.lewyWidelec = lewy;
            this.prawyWidelec = prawy;
        }

        @Override
        public void run() {
            try{
                for ( ;; )
                {
                    Random gen = new Random();
                    System.out.println(Thread.currentThread().getName() + " śpi");
                    Thread.sleep(gen.nextInt(3500));
                    for (;;) {
                        if (lewyWidelec.isTaken || prawyWidelec.isTaken) {
                            Thread.sleep(gen.nextInt(3500));
                        } else {
                            lewyWidelec.isTaken = true;
                            prawyWidelec.isTaken = true;
                            System.out.println(Thread.currentThread().getName() + " je");
                            Thread.sleep(gen.nextInt(3500));
                            lewyWidelec.isTaken = false;
                            prawyWidelec.isTaken = false;
                            break;
                        }
                    }


                    System.out.println(Thread.currentThread().getName() + " zasnął");
                    Thread.sleep(gen.nextInt(3500));
                }
            }
            catch( InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public static class Widelec{
        public boolean isTaken;

        public Widelec()
        {
            isTaken = false;
        }
    }

    public static void main(String[] args) {
        Widelec[] widelce = new Widelec[5];
        Filozof[] filozofowie = new Filozof[5];

        for (int i = 0; i < widelce.length ; i++)
            widelce[i] = new Widelec();

        for (int i = 0; i < 5 ; i++)
        {
            int j = (i + 1);
            if (j == 5) j = 0;

            filozofowie[i] = new Filozof(widelce[i], widelce[j]);
            //System.out.println(i + " " + (i+1)%5);
        }
        for (int i = 0; i<5; i++)
        {
            Thread t = new Thread(filozofowie[i], "Filozof " + (i + 1));
            t.start();
        }
    }
}
