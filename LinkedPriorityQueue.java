import java.util.EmptyStackException;

public class LinkedPriorityQueue<T extends Comparable<T> & Updatable>
{
    private Node firstNode;
    private Node lastNode;
    public int count = 0;

    public LinkedPriorityQueue()
    {
        firstNode = null;
        lastNode = null;
    }

    public void enqueue(T newEntry)
    {
        Node newNode = new Node(newEntry, null);
        if(firstNode == null)
        {
            firstNode = newNode;
            newNode.next = null;
            return;
        }

        int compare = newNode.data.compareTo(firstNode.data);
        if(compare <= 0)
        {
            // newNode goes in the first spot (negative number is returned if it's less than)
            newNode.next = firstNode;
            firstNode = newNode;
            return;
        }

        for(Node iterator = firstNode; iterator != null; iterator = iterator.next)
        {
            if(iterator.next == null)
            {
                iterator.next = newNode;
                return;
            }

            Node nextNode = iterator.next;
            if(nextNode.data.compareTo(newNode.data) > 0)
            {
                // newNode goes here
                iterator.next = newNode;
                newNode.next = nextNode;
                return;
            }
        }
    }


    public T dequeue()
    {
        if (isEmpty())
            throw new EmptyStackException();
        T data = firstNode.data;
        firstNode = firstNode.next;
        return data;
    }


    public T getFront()
    {
        if (isEmpty())
            throw new EmptyQueueException("Queue is Empty");
        return firstNode.getData();
    }

    public boolean isEmpty()
    {
        return firstNode == null;
    }


    public void clear()
    {
        firstNode = lastNode = null;
    }
    public int getCount() {
        for (Node iterator = firstNode; iterator != null; iterator = iterator.next) {
            count++;
        }
        return count;
    }

    public void updateValues() {
        for (Node iterator = firstNode; iterator != null; iterator = iterator.next) {
            iterator.data.update();
        }
    }

    private class Node
    {
        private T data;
        private Node next;

        private Node(T data, Node next)
        {
            this.data = data;
            this.next = next;
        }

        private T getData()
        {
            return data;
        }

        private Node getNextNode()
        {
            return next;
        }

        private void setNextNode(Node next)
        {
            this.next = next;
        }
    }
}