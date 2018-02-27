package ch16;

import java.util.*;

public class LinkedIntList {
    private ListNode front;

    public LinkedIntList(){
        front = null;
    }

    public int size(){
        int count = 0;
        ListNode current = front;
        while (current != null){
            current = current.next;
            count++;
        }
        return count;
    }
    // adder bagerst i listen
    public void add(int value){
        if (front == null){
            front = new ListNode(value);
        } else {
            ListNode current = front;
            while (current.next != null){
                current = current.next;
            }
            current.next = new ListNode(value);
        }
    }
    // adder på et givet index
    public void add(int index, int value){
        if (index == 0){
            front = new ListNode(value, front);
        } else {
            ListNode current = nodeAt(index - 1);
            current.next = new ListNode(value, current.next);
        }
    }

    public void remove(int index){
        if (index == 0){
            front = front.next;
        } else {
            ListNode current = nodeAt(index - 1);
            current.next = current.next.next;
        }
    }

    public int indexOf(int value){
        int index = 0;
        ListNode current = front;
        while (current != null){
            if(current.data == value){
                return index;
            }
            index++;
            current = current.next;
        }
        return -1;
    }

    public int get(int index){
        return nodeAt(index).data;
    }

    private ListNode nodeAt(int index){
        ListNode current = front;
        for (int i = 0; i < index; i++){
            current = current.next;
        }
        return current;
    }

    @Override
    public String toString() {
        if (front == null){
            return "[]";
        } else {
            String result = "[" + front.data;
            ListNode current = front.next;
            while(current != null){
                result += ", " + current.data;
                current = current.next;
            }
            result += "]";
            return result;
        }
    }
    //ch16_opg1
    public void set (int index, int value){
        nodeAt(index).data = value;
    }

    //ch16_opg2
    public int biggest(){

        // sætter metoden til at smide en exception hvis listen er tom.
        if (front == null) {
            throw new NoSuchElementException();
        } else {

            ListNode current = front;
            int max = front.data;

            //simpelt loop som tjekker om den næste nodes data er større end den nuværende største (som er gemt i min).
            while (current.next != null){
                if (current.data > max) {
                    max = current.data;
                }
                current = current.next;
            }
            return max;
        }
    }

    //ch16_opg3
    public ListNode firstToLast (){

        ListNode current = front;

        // Sørger for at der kun sker noget hvis der er 1+ elementer.
        if (front != null){
            // hopper ned i bagenden af alle nodesne.
            while (current.next != null){
                current = current.next;
            }
            // indsætter value fra noden i front til bagerste node.
            current.next = new ListNode(front.data);
            // sletter første node ved at delinke den.
            // SKAL ske i denne rækkefølge da første node ikke kan linkes til bagenden hvis den først er blevet delinked.
            front = front.next;
        }
        return current;
    }

    //ch16_opg4
    public int lastIndexOf(int value){

        ListNode current = front;
        int index = -1;
        int counter = 0;

        // Looper så længe at nuværende node indeholder data.
        // Starter aldrig loopet hvis fronten er null (dvs. en tom liste), og returnerer -1
        // hvis indtastet value ikke forekommer i listen (eller hvis listen er tom).
        while (current != null){
            if (current.data == value){
                index = counter;
            }
            counter++;
            current = current.next;
        }
        return index;
    }

    //ch16_opg5
    public void countDuplicates () {

        //laver en sum variabel til at holde antallet af duplicates
        int sum = 0;
        ListNode current = front;

        if (front == null) {
            System.out.println(sum);
        }

        //while loop som tæller duplicates ved at sammenligne den nuværende node.data med den næste nodes data.
        //Dette virker kun fordi alle duplicates (hvis der er nogen) kommer i rækkefølge.
        while (current.next != null) {
            if (current.data == current.next.data) {
                sum++;
            }
            current = current.next;
        }
        System.out.println(sum);

    }

    //ch16_opg7
    public int deleteBack() {

        ListNode current = front;
        int deletedNodeData = 0;
        int lastIndex = 0;

        //Smider en exception hvis listen er tom.
        if (front == null) {
            throw new NoSuchElementException();
        }
        //Hopper ned i bagenden af alle nodesne og laver samtidig en tæller til at finde frem til den
        //sidste nodes index (for senere at kunne fjerne den).
        while (current.next != null) {
            current = current.next;
            lastIndex++;
        }
        //Sætter hermed min variabel til at indeholde dataen fra sidste node
        deletedNodeData = current.data;
        //Sletter derefter sidste node
        remove(lastIndex);
        return deletedNodeData;
    }

    //ch16_opg8
    //Valgte at lade metoden tage en liste som paramter for at gøre koden lettere at læse.
    public void switchPairs (LinkedIntList list){

        //Laver en midlertidig liste som fyldes op med dataen i ønsket rækkefølge
        //(hvert par switched).
        LinkedIntList tempList = new LinkedIntList();
        //Switcher hvert par ved brug af et forloop hvorunder i%2==0 bliver sat som en condition for et if-statement.
        //Hvis i%2==0 tilføjes i+1 og derefter i, hvilket får de to styks data til at skifte plads.
        //Grunden til at i%2==0 er sat som condition, er at den tilføjer 2 tal hver anden gang, hvilket
        //gør at listen forbliver samme længde og at tallene bliver switched som efterspurgt i opgaven.
        for (int i = 0; i < list.size()-1; i++){
            if (i % 2 == 0){
                tempList.add(list.get(i+1));
                tempList.add(list.get(i));
            }
        }
        //Tester om den givne liste er af ujævn længde -
        //hvis den er ujævn, indsættes sidste nodes data bagi.
        if (list.size() % 2 != 0){
            tempList.add(list.get(list.size()-1));
        }

        System.out.println(tempList);
    }

    //ch16_opg9
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

    //ch16_opg15
    public boolean notEquals(LinkedIntList liste2){

        ListNode node1 = front;
        ListNode node2 = liste2.front;

        while (node1 != null || node2 != null){

            //Føler at der er en bedre møde at skrive nedenstående 2 linjer på, men kunne ikke lige
            //umiddelbart se hvordan.
            //Hvis de to lister ikke er lige lange kan de ikke være ens, derfor returner true.
            if (size() != liste2.size()){
                return true;
            }
            //Hvis de to noder på samme index i hver deres liste ikke indeholder samme data,
            //kan listerne ikke være ens, derfor return true.
            if (node1.data != node2.data){
                return true;
            }
            //Cycler igennem hver liste
            node1 = node1.next;
            node2 = node2.next;
        }
        return false;

    }

    //ch16_17
}
