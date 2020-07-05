package listy;

public class Anchor
    {
        private Element first;
        private int NumberOfElements;

        public Anchor()
        {
            first = null;
            NumberOfElements = 0;
        }
        public Anchor(int n)
        {
            first = new Element(n);
            NumberOfElements++;
        }
        public void insertAtTheEnd(int n)
        {
            if (first == null) {
                first = new Element(n);
                NumberOfElements++;
            }
            else {
                first.insertAtTheEnd(n);
                NumberOfElements++;
            }
        }
        public void insertAtTheFront(int n)
        {
            if (first == null) {
                first = new Element(n);
                NumberOfElements++;
            }
            else {
                NumberOfElements++;
                first = new Element(n, first);
            }
        }
        public void removeFirst()
        {
            if (first == null)
                System.out.println("Lista juz jest pusta");
            else {
                if (first.getNext() == null)
                    first = null;
                else
                    first = first.getNext();
                NumberOfElements--;
            }
        }
        public void removeLast()
        {
            if (first == null)
                System.out.println("Lista juz jest pusta");
            else {
                if (first.getNext() == null)
                    first = null;
                else
                    first.removeLast();
                NumberOfElements--;
            }
        }
        public String toString()
        {
            if (first == null)
                return "Lista jest pusta " + NumberOfElements;
            else
                return "Lista zawiera " + NumberOfElements + " elementy: { " + first.toString();
        }
        public boolean equals(listy.Anchor lista)
        {
            if (NumberOfElements != lista.NumberOfElements)
                return false;
            else if (first == null && lista.first == null)
                return true;
            else
                return first.equals(lista.first);

        }
    }
