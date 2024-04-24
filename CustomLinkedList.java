import java.util.Arrays;
import java.util.Objects;

public class CustomLinkedList {

    private Node[] buckets = new Node[16];
    private Node head;
    private Node tail;
    private Node prev ;
    private Node last ;


    private int size;

    private class Node{
        Integer key;
        String value;
        Node next;

        public Node(Integer key, String value) {
            this.key = key;
            this.value = value;
        }

        public Node(Integer key, String value, Node next, Node prev , Node last) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }


    public String add(Integer key, String value) {
        if (size >= buckets.length * 0.75) {
            resize();
        }

        int index = index(key, buckets.length);

        Node newNode = new Node(key, value);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = tail.next;
        }

        Node bucket = buckets[index];
        if (bucket == null) {
            buckets[index] = newNode;
            size++;
            return null;
        }

        while (bucket.next != null) {
            if (bucket.key.equals(key)) {
                String old = bucket.value;
                bucket.value = value;
                return old;
            }
            bucket = bucket.next;
        }

        if (bucket.key.equals(key)) {
            String old = bucket.value;
            bucket.value = value;
            return old;
        }
        bucket.next = new Node(key, value);
        size++;

        return null;
    }

    private void resize() {
        int length = buckets.length * 2;
        Node[] temp = new Node[length];

        for (Node bucket : buckets) {
            while (Objects.nonNull(bucket)) {
                int index = index(bucket.key, length);
                Node node = temp[index];

                if (node == null) {
                    temp[index] = new Node(bucket.key, bucket.value);
                    bucket = bucket.next;
                    continue;
                }

                while (node.next != null) {
                    node = node.next;
                }
                node.next = new Node(bucket.key, bucket.value);
                bucket = bucket.next;
            }
        }
        buckets = temp;
    }

    public String get(Integer key) {
        int index = index(key, buckets.length);
        Node bucket = buckets[index];

        while (bucket != null) {
            if (bucket.key.equals(key)) {
                return bucket.value;
            }
            bucket = bucket.next;
        }
        return null;
    }

    public int index(Integer key, int length) {
        return key.hashCode() % length;
    }

    public void remove(int index) {
        if(index > size){
            throw  new IndexOutOfBoundsException("Wrong input");
        }
        if(Objects.equals(index,0)){
            head = head.next;
            return;
        }
        prev = null ;
        head.next = null ;
        if(head.next == null){
            last = null;
        }else {
            head.next = prev ;
            size--;
            return ;
        }
    }


    @Override
    public String toString() {
        return "CustomLinkedList{" +
                "buckets=" + Arrays.toString(buckets) +
                ", head=" + head +
                ", tail=" + tail +
                ", size=" + size +
                '}';
    }
}

