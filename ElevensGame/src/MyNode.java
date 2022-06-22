public class MyNode<T> {

    private T data;
    private MyNode<T> next;

    //create node that contains data and points to the next node
    public MyNode(T dataValue) {
        data = dataValue;
        next = null;
    }

    //shows data of node
    public T getData() {
        return data;
    }
    //places data in node
    public void setData(T dataValue) {
        data = dataValue;
    }
    //finds the next node
    public MyNode<T> getNext() {
        return next;
    }
    //places next node in current node
    public void setNext(MyNode<T> nextNode) {
        next = nextNode;
    }

    //tests the node methods
    public static void main(String[] args) {
        //store integers as data in a node
        MyNode<Integer> node1 = new MyNode<Integer>(1);
        MyNode<Integer> node2 = new MyNode<Integer>(2);
        MyNode<Integer> node3 = new MyNode<Integer>(3);

        //store strings as data in a node
        MyNode<String> node4 = new MyNode<String>("5 of Hearts");
        MyNode<String> node5 = new MyNode<String>("7 of Clubs");

        //point a node to its next node
        node1.setNext(node2);
        node2.setNext(node3);
        node4.setNext(node5);

        //print out data in node and then the next node after it
        System.out.println("Node 1 is: " + node1.getData());
        System.out.println("Node 2 is: " + node1.getNext().getData());
        System.out.println("Node 3 is: " + node1.getNext().getNext().getData());
        System.out.println("\nThis card is: " + node4.getData());
        System.out.println("This card is: " + node4.getNext().getData());
    }
}

