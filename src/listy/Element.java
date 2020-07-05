package listy;

public class Element
{
    private int number;
    private Element next;
    public Element(int number)
    {
        this.number = number;
        next = null;
    }
    public Element(int number, Element next)
    {
        this.number = number;
        this.next = next;
    }

    public Element getNext() {
        return next;
    }

    public int getNumber() {
        return number;
    }

    public void insertAtTheEnd(int n)
    {
        if (next == null)
            next = new Element(n);
        else
            next.insertAtTheEnd(n);
    }
    public String toString()
    {
        if (next == null)
            return number + " }";
        else
            return number + ", " + next.toString();
    }

    public void removeLast() {
        if (next.next == null)
            next = null;
        else
            next.removeLast();
    }
    public boolean equals(Element lista)
    {
        if (number != lista.getNumber())
            return false;
        else if (next == null && lista.next == null)
            return true;
        else
            return next.equals(lista.next);
    }
}
