package ch16;
/*
Write a method called stutter that doubles the size of a list by replacing every integer in the
list with two of that integer. For example, suppose a variable list stores the values
[1, 8, 19, 4, 17], after a call of list.stutter(), it should store [1, 1, 8, 8, 19, 19, 4, 4, 17, 17].
 */
public class ch16_9 {
    public static void main(String[] args) {
        LinkedIntList liste = new LinkedIntList();
        liste.add(1);
        liste.add(18);
        liste.add(39);

        liste.stutter();
        System.out.println(liste);
    }
    /*
    public void stutter(){

        if (front == null){
            return;
        }

        ListNode current = front;

        //Laver en ny node efter hvert nye tal (fra den originalel iste) med samme data fra det forrige tal
        //og får derefter current til at springe den nye node over ("current = current.next.next;"),
        //så vi ikke ender ud i et infinite loop.
        while (current != null){
            current.next = new ListNode(current.data,current.next);
            current = current.next.next;
        }
    }
     */
}
