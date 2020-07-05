package listy;

import java.util.HashMap;
import java.util.Map;

public class Mapa {
    public static class FrequentNames
    {
        public HashMap<String, Integer> frequentNames;

        public FrequentNames()
        {
            frequentNames = new HashMap<String, Integer>();
        }
        public void show()
        {
            System.out.println(frequentNames);
        }
        public String choose(){
            String s = new String();
            int i = 0;
            for(Map.Entry<String, Integer> x : frequentNames.entrySet())
            {
                if (x.getValue() > i)
                {
                    s = x.getKey();
                    i = x.getValue();
                }
            }
            frequentNames.remove(s);
            return s;
        }
        public void put(String s, int i)
        {
            if (i >= 0)
            {
                frequentNames.put(s, i);
            }
            else
                System.out.println("Nie prawidlowa wartosc");
        }
    }
    public static void main(String[] args) {
        FrequentNames CzesteImiona = new FrequentNames();
        //CzesteImiona.frequentNames = new HashMap<String, Integer>();
        HashMap<String, Integer> frequentNames = CzesteImiona.frequentNames;

        CzesteImiona.put("Adam", 30);
        CzesteImiona.put("Ewa", 12);
        CzesteImiona.put("Paweł", 23);
        CzesteImiona.put("Barbara", 21);
        CzesteImiona.put("Tomasz", 40);
        CzesteImiona.put("Daniela", 5);
        CzesteImiona.show();
        System.out.println("");


        /*for(Map.Entry<String, Integer> x : frequentNames.entrySet())
            System.out.println(x);*/
        System.out.println("Wyjmowanie najczesciej wystepujacego imienia:");
        for(int i = 0; i < frequentNames.size(); )
        {
            String s = CzesteImiona.choose();
            System.out.println("Wyjete imie : " + s + " ");
            CzesteImiona.show();
        }

    }

}
