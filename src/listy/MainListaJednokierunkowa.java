package listy;


public class MainListaJednokierunkowa {
    public static void fillUp(Anchor lista, int[] tab)
    {
        for (int i = 0; i<tab.length; i++)
            lista.insertAtTheEnd(tab[i]);
    }
    public static void main(String[] args) {
        Anchor lista = new Anchor(6);
        System.out.println("Testowanie dodawania i odejmowania elementow do listy:");
        lista.insertAtTheEnd(1);
        lista.insertAtTheEnd(3);
        System.out.println(lista);
        lista.removeFirst();
        lista.insertAtTheFront(8);
        lista.insertAtTheFront(5);
        lista.insertAtTheFront(9);
        System.out.println(lista);
        lista.removeLast();
        System.out.println(lista);


        System.out.println("Stworzenie dodatkowej tabeli z tymi samymi elementami i porównanie:");
        Anchor lista2 = new Anchor();
        { int[] tab = {9, 5, 8, 1}; fillUp(lista2, tab);} //klamra aby tab nie zostało w pamięci
        System.out.println(lista2);

        System.out.println(lista.equals(lista2));

        System.out.println("Zmiana jednej z tabel i ponowne porównanie:");
        lista2.insertAtTheEnd(5);
        System.out.println(lista2.equals(lista));
    }
}
