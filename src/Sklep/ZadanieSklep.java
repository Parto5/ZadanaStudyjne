package Sklep;

import java.util.ArrayList;

//

public class ZadanieSklep {

    //static jest tylko dlatego, bo w jednym pliku to zrobiłem i chciałem sprawdzić czy działa w main
    public static class Towar{
        public String name;

        public Towar(String name){
            this.name = name;
        }
    }

    //static jest tylko dlatego, bo w jednym pliku to zrobiłem i chciałem sprawdzić czy działa w main
    public static class Regal{
        private ArrayList<Towar> towary;

        public Regal(){
            towary = new ArrayList<>();
        }

        boolean isEmpty()
        {
            return towary.size() == 0;
        }

        public void add(Towar t)
        {
            towary.add(t);
        }
        public Regal duplicate(Towar t)
        {
            Regal duplicate = new Regal();
            for(Towar i : towary)
            {
                if(i.name.equals(t.name))
                {
                    duplicate.add(i);
                }
            }
            return duplicate;
        }


        //zignoruj to, to tylko użyłem aby sprawdzic czy dziala
        @Override
        public String toString() {
            String s = "Regal(";
            for(Towar t : towary)
            {
                s = s + " " + t.name +" ";
            }
            s = s + ')';
            return s;
        }
    }

    public static class Sklep{
        private ArrayList<Regal> regaly;

        public Sklep() {
            regaly = new ArrayList<>();
        }
        public void add(Regal r)
        {
            try {
                if (r.isEmpty())
                    throw new Exception(); //internet sugerował IllegalStateExceptio, ogólny też przejdzie
                else
                    regaly.add(r);
            }
            catch (Exception e) {
                //e.printStackTrace();
                System.out.println("Pusty regal - nie wolno takich dodawac!");
            }
        }

        @Override
        public String toString() {
            String s = "Sklep{";
            for(Regal r : regaly)
            {
                s = s + r.toString();
            }
            s = s + '}';
            return s;
        }
    }

    //testowanie czy dziala - na tescie tego nie dawaj :P
    public static void main(String[] args) {
        Sklep s = new Sklep();
        Regal r1 = new Regal();
        Regal r2 = new Regal();

        r1.add(new Towar("Chleb"));
        r1.add(new Towar("Piwo"));
        r1.add(new Towar("Chleb"));

        s.add(r1);
        s.add(r2); //pusty nie doda bo wyjatek pusty
        s.add(r1);
        System.out.println(s.toString());
        r2 = r1.duplicate(new Towar("Chleb"));
        s.add(r2);
        System.out.println(s.toString());

    }
}
