package com.programming;

import java.util.ArrayList;

public class HashTableOperation<K, V> {
    MyMapNode head;
    MyMapNode tail;
    private final int numOfBuckets;
    ArrayList<MyMapNode<K, V>> myBucketArray;

    public HashTableOperation() {
        this.numOfBuckets = 10;
        this.myBucketArray = new ArrayList<>();
        // Creating empty linked list.
        for (int i = 0; i < numOfBuckets; i++)
            this.myBucketArray.add(null);
    }


    public V get(K word) {
        int index = this.getBucketIndex(word);
        if (this.myBucketArray.get(index) == null)
            return null;
        MyMapNode<K, V> myNode = searchNode(word);
        return (myNode == null) ? null : myNode.getValue();
    }


    public MyMapNode<K, V> searchNode(K word) {
        MyMapNode<K, V> currentNode = head;
        int position = 0;
        while (currentNode != null) {
            position++;
            if (currentNode.getKey().equals(word)) {
                return currentNode;
            }
            currentNode = currentNode.getNext();
        }
        return currentNode;
    }


    public void add(K key, V value) {
        int index = this.getBucketIndex(key);
        MyMapNode<K, V> myNode = this.myBucketArray.get(index);
        if (myNode == null) {
            myNode = new MyMapNode<>(key, value);
            this.myBucketArray.set(index, myNode);
        }
        myNode = searchNode(key);
        if (myNode == null) {
            myNode = new MyMapNode<>(key, value);
            this.append(myNode);
        } else
            myNode.setValue(value);

    }


    private void append(MyMapNode<K, V> myNode) {
        if (this.head == null)
            this.head = myNode;
        if (this.tail == null)
            this.tail = myNode;
        else {
            this.tail.setNext(myNode);
            this.tail = myNode;
        }
    }


    public int getBucketIndex(K word) {
        int hashCode = Math.abs(word.hashCode());
        int index = hashCode % numOfBuckets;
        return index;
    }

    @Override
    public String toString() {
        return "MyLinkedListNodes{" + head + '}';
    }

}
