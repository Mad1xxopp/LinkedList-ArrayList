import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyArrayList<T> implements MyList<T> {
    private int size = 0;
    private static final int DEFAULT_CAPACITY = 10;
    private T[] arr;

    public MyArrayList() { // Creating MyArraylist
        arr = (T[]) new Object[DEFAULT_CAPACITY];
    }
    public MyArrayList(int capacity) { // Creating MyArraylist
        if (capacity < 0)
            throw new IllegalArgumentException("Illegal Capacity: " + capacity);
        arr = (T[]) new Object[capacity];
        size = 0;
    }

    @Override
    public int size() { // returns the size of the array
        return size;
    }

    public void checkForCapacity(){ // checks for if the memory size is okay so the memory allocated worked well, if not it extends the size of the array
        if(size == arr.length){
            T[] newArray = (T[]) new Object[size * 2 + 1]; //  + 1 to handle zero initial capacity
            for(int i = 0; i<size; i++){
                newArray[i] = (T) arr[i];
            }
            arr = newArray;
        }
    }

    @Override
    public void add(T item){ // adds the data on top
        checkForCapacity();
        arr[size] = item;
        size++;
    }

    @Override
    public void addFirst(T item){ // initial data of the array by moving each data to the right, and adding the data to the first data at index 0 at the conclusion
        checkForCapacity();
        for( int i = size; i > 0; i--){
            arr[i] = arr[i-1];
        }
        arr[0] = item;
        size++;


    }
    @Override
    public void addLast(T item){ // method add()
        checkForCapacity();
        arr[size] = item;
        size++;

    }
    private void throwException(int index){  // throws exception if there any problem with the memory
        if (index > size || index < 0)
            throw new IndexOutOfBoundsException("Invalid index: " + index);
    }

    private void checkEmpty() { // checks empty
        if (size == 0) {
            throw new NoSuchElementException("List is empty.");
        }
    }

    @Override

    public void add(int index, T item){ // add data by it is given index
        throwException(index);
        checkForCapacity();
        for(int i = size; i>index; i--){
            arr[i] = arr[i-1];
        }
        arr[index] = item;
        size++;
    }

    @Override
    public void remove(int index){ // remove data by it is given index
        throwException(index);
        for( int i = index; i<size-1; i++){
            arr[i] = arr[i+1];
        }
        arr[size - 1] = null; // prevents the memory leak
        size--;
    }


    @Override
    public void removeFirst() { // removes first data
        checkEmpty();
        for (int i = 1; i < size; i++) {
            arr[i - 1] = arr[i];
        }
        arr[--size] = null;
    }

    @Override
    public void removeLast() { // removes last data
        checkEmpty();
        arr[--size] = null;
    }


    @Override
    public T get(int index){ // get need data by using index
        throwException(index);
        return (T) arr[index];
    }

    @Override
    public T getFirst(){ // get first data by using index
        checkEmpty();
        return (T) arr[0];
    }

    @Override
    public T getLast(){ // get last data by using index
        checkEmpty();
        return (T) arr[size-1];
    }


    @Override
    public void clear(){ // clear the array
        for( int i = 0; i < size; i++){
            arr[i] = null;
        }
        size = 0;
    }

    @Override
    public Object[] toArray(){  //  convert the entire list into an array of Object datas
        Object[] result = new Object[size];
        for( int i = 0; i< size; i++){
            result[i] = arr[i];
        }
        return result;
    }

    @Override
    public Iterator<T> iterator() { // using iterator by importing it from import java.util.Iterator;
        return new Iterator<T>() {
            private int indx = 0;
            @Override
            public boolean hasNext() { // checks for if the array has the next data
                return indx < size;
            }

            @Override
            public T next() { // add the data as the next one and the iterator moves forward one step
                if (!hasNext()) throw new NoSuchElementException();
                return (T) arr[indx++];
            }
        };
    }

    @Override
    public Object set(int index, T item){ // sets the input data with the given index
        throwException(index);
        return arr[index] = item;
    }
    @Override
    public boolean exists(Object object) { // checks whether a specific data object is exist in the list
        for (int i = 0; i < size; i++) {
            if (arr[i] == null && object == null) {
                return true;  // Both are null
            } else if (arr[i] != null && arr[i].equals(object)) {
                return true;  // not null and equal
            }
        }
        return false;
    }

    @Override
    public void sort() {  // Sort array
        T temp;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size - 1; j++) {
                if (((Comparable<T>) arr[j]).compareTo(arr[j + 1]) > 0) { // it changes the place of datas if true. It is true when array[j] > array[j+1]
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    @Override
    public int indexOf(Object object){ // returns the index of the first occurrence of the indicated data in this array, or -1 if this list does not exist
        if( object == null){
            for (int i = 0; i < size; i++)
            {
                if (arr[i] == null)
                    return i;
            }
        }else
        {
            for (int i = 0; i < size; i++)
            {
                if(object.equals(arr[i]))
                    return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object object) { // last index of array
        if (object == null) {
            for (int i = size - 1; i >= 0; i--) {
                if (arr[i] == null) {
                    return i;  // return index of null data
                }
            }
        } else {
            for (int i = size - 1; i >= 0; i--) {
                if (object.equals(arr[i])) {
                    return i;  // return index of matching data
                }
            }
        }
        return -1;  // return -1 if no match is found
    }






}