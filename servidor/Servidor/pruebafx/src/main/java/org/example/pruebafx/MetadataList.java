package org.example.pruebafx;

public class MetadataList {
    public class MetadataNode {
        String data;
        MetadataNode next;

        // Constructor to create a new node
        MetadataNode(String data) {
            this.data = data;
            next = null;
        }
    }

    public MetadataNode head; // Head of the list

    // Method to insert a new node
    public void insert(String data) {
        MetadataNode newMetadataNode = new MetadataNode(data);
        if (head == null) {
            head = newMetadataNode;
        } else {
            MetadataNode temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newMetadataNode;
        }
    }

    // Method to display the linked list
    public void display() {
        MetadataNode temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }
}
