import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList<T> implements MyList<T> {

    static class Node<T> {
        T data;
        Node<T> next;

        Node(T data1, Node<T> next1){
            this.data = data1;
            this.next = next1;
        }
        Node(T data1){
            this.data = data1;
            this.next = null;
        }
    }
    private Node<T> head;
    private Node<T> tail;
    private int size = 0;

    private void checkDataIndex(int index) {  // checks for if the data index within the range of size
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Invalid Index: " + index + "or " +" Size: " + size);
    }
    private void isEmpty(){ // checks for if it is empty
        if(head==null){
            System.out.println("this list is empty");
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> current = head;  // starts at the head of the list

            @Override
            public boolean hasNext() { // checks if the list has the next data
                return current != null;
            }

            // moves the iterator forward one step and returns the current element
            @Override
            public T next() {
                if (!hasNext()) throw new NoSuchElementException("No more datas in the list");
                T item = current.data;  // returns retrieved the item
                current = current.next;  // move to the next node
                return item;
            }
        };
    }

    @Override
    public Object set(int index, T item){ // sets the input data with the given index
        checkDataIndex(index);
        Node<T> current = head;
        for(int i = 0; i< index; i++){
            current = current.next;
        }
        T result = current.data;
        current.data = item;
        return result;
    }

    @Override
    public void add(T item) { // adds new data as tail in the linked list
        Node<T> Node = new Node<>(item, null);
        if (head == null) {
            head = tail = Node;
        } else {
            tail.next = Node;
            tail = Node;
        }
        size++;
    }

    @Override
    public void add(int index, T item) {
        checkDataIndex(index);

        if (index == 0) {
            addFirst(item);
        } else if (index == size) {
            addLast(item);
        } else {
            Node<T> current = head;
            for (int i = 0; i < index - 1; i++) { // This new node ought to link to the node that our "current" node initially ranked next in the list.
                current = current.next;
            }
            Node<T> Node = new Node<>(item, current.next); // and now the next pointer from the "current" node points to the new node
            current.next = Node;
            size++;
        }
    }

    @Override
    public void addFirst(T item){  // add the first data to the list
        Node<T> Node = new Node<>(item, null);
        if( head == null){
            head = Node;
            return;
        }
        Node.next = head;
        head = Node;
    }

    @Override
    public void addLast(T item){ // add the last data to the list
        Node<T> Node = new Node<>(item, null);
        if( head == null){
            head = Node;
            return;
        }
        Node<T> current = head;
        while(current.next != null){
            current = current.next;
        }
        current.next = Node;
    }


    @Override
    public T get(int index){ // get the needed data by it is given index
        checkDataIndex(index);
        Node<T> current = head;
        for(int i = 0; i < index; i++){
            current = current.next;
        }
        return current.data;
    }

    @Override
    public T getFirst(){ // get the first data of the array
        isEmpty();
        return head.data;
    }

    @Override
    public T getLast(){ // get the last data of the array
        isEmpty();
        return tail.data;

    }

    @Override
    public void remove(int index){ // remove data by it is given index
        checkDataIndex(index);
        if (index == 0) {  // removes first data
            removeFirst();
            return;
        }
        if (index == size - 1) {  // removes last data
            removeLast();
            return;
        }
        Node<T> current = head;
        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }
        current.next = current.next.next;
        size--;
    }
    @Override
    public void removeFirst(){ // removes first data
        isEmpty();
        head=head.next;
    }

    @Override
    public void removeLast(){ // removes last data
        isEmpty();
        if (size == 1) {
            head = tail = null;
            size--;
            return;
        }
        Node<T> current = head;
        while(current.next != tail) {
            current = current.next;
        }
        current.next = null;
        tail = current;
        size--;

    }

    @Override
    public void sort() { // Sort by using swap
        if (head == null || head.next == null) {
            return; // the list is empty or has only one data, so no need to sort
        }

        for (int i = 0; i < size; i++) {
            Node<T> current = head;
            for (int j = 0; j < size - 1; j++) {
                Comparable<T> currentComparable = (Comparable<T>) current.data;
                if (currentComparable.compareTo(current.next.data) > 0) {
                    T temp = current.data;
                    current.data = current.next.data;
                    current.next.data = temp;
                }
                current = current.next; // moves to the next node
            }
        }
    }

    @Override
    public int indexOf(Object object) { // current index of object
        Node<T> current = head;
        for (int i = 0; current != null; i++, current = current.next) {
            if (object == current.data) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object object) {  // check out when the last time the need object is occurred in the list
        Node<T> current = head;
        int lastIndex = -1;
        for (int i = 0; current != null; current = current.next, i++) {
            if (object == current.data) {
                lastIndex = i;
            }
        }
        return lastIndex;
    }

    @Override
    public boolean exists(Object object) { // checks if any node contains the item that matches the given object based on equality
        Node<T> current = head;
        while (current != null) {
            if (object == null && current.data == null) {
                return true;
            } else if (object != null && object.equals(current.data)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public Object[] toArray() { //  convert the entire list into an array of Object datas
        Object[] array = new Object[size];
        int i = 0;
        Node<T> current = head;

        while (current != null) {
            array[i++] = current.data;
            current = current.next;
        }
        return array;
    }

    @Override
    public void clear(){ // clear the array
        head = tail = null;
        size = 0;
    }
    @Override
    public int size(){ //f ind out the size of the array
        return size;
    }
}