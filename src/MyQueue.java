public class MyQueue<T> {
    private MyLinkedList<T> list = new MyLinkedList<>();

    public T addqueue(T item) { // adds data to the end of the list
        list.addLast(item);
        return item;
    }


    public T deletequeue(){ // removes the first data of the list, which is the front of the queue
        T removingItem = peek();
        list.removeFirst();
        return removingItem;
    }


    public T peek() { // gets the first data of the list, which is the front of the queue
        if (isEmpty()) {
            return null; // or throw exception
        }
        return list.getFirst();
    }

    public boolean isEmpty() {
        return list.size() == 0;
    }
    public int size() {
        return list.size();
    }
}