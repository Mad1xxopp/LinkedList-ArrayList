public class Solve {
    public static void main(String[] args) {
        MyLinkedList<String> list = new MyLinkedList<>();
        list.add("calculus");
        list.add("discrete math");
        list.add("linear algebra");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}