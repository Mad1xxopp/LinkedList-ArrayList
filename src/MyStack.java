public class MyStack<T>{

    private MyArrayList<T> list = new MyArrayList<>();

    public T push(T item) { // push or add new data
        list.add(item);
        return item;
    }

    public T peek(){ // Get Front data
        if (empty()) {
            return null; // or throw exception
        }
        return list.get(0);
    }

    public T pop(){ // Removes the head of the linked list
        if (empty()) {
            return null; // or throw exception
        }
        T removingItem = peek();
        list.removeFirst();
        return removingItem;
    }

    public boolean empty(){ // Returns whether the stack is empty
        return list.size() == 0;
    }
    public int size() { // Returns the size of the stack
        return list.size();
    }
}