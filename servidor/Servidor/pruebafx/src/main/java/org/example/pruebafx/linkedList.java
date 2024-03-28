package org.example.pruebafx;

import java.io.File;
import java.util.UUID;

class Node {
    File data;
    Node next;
    Node previous;

    String id;

    Integer likes;

    Integer dislikes;

    // Constructor to create a new node
    Node(File data, String id) {
        this.data = data;
        this.id = id;
        next = null;
        previous = null;
        likes = 0;
        dislikes = 0;;
    }
}

public class linkedList {
    Node head; // head
    Node tail;
    Node current; // current

    //metodo de insertar
    public void insert(File data) {
        String randomID = generateRandomID();
        Node newNode = new Node(data, randomID);
        //System.out.println("Generated id ::: " + randomID);

        if (head == null) {
            head = newNode;
            tail = newNode;
            System.out.println("seteando la cabeza como el nuevo nodo");
        } else {
            newNode.previous = tail;
            tail.next = newNode;
            tail = newNode;
        }
    }

    public Node find(int index) {
        Node current = head;
        int currentNumber = 0;
        while (current != null && currentNumber < index) {
            current = current.next;
            currentNumber ++;
        }
        if (current == null && head != null) {
            return head;
        } else if (current != null) {
            return current;
        }
        return null;
    }
    //falta aqui
    public File get(int index) {
        Node results = find(index);
        if (results != null) {
            return results.data;
        }
        return null;
    }

    public void addLike(int index) {
        Node results = find(index);
        if (results != null) {
            results.likes += 1;
        }
    }

    public void addDislike(int index) {
        Node results = find(index);
        if (results != null) {
            results.dislikes += 1;
        }
    }


    public int size() {
        int sizeCount = 0;
        Node current = head;
        while (current != null) {
            sizeCount ++;
            current = current.next;

        }
        return sizeCount;

    }

    private String generateRandomID() {
        UUID randomUUID = UUID.randomUUID();
        return randomUUID.toString();
    }

    // Método para obtener un nodo aleatorio
    public Node getRandomNode() {
        int size = size();
        int randomIndex = (int) (Math.random() * size);
        return find(randomIndex);
    }

    // Función para generar una lista aleatoria de 3 elementos
    public linkedList generateRandomList() {
        linkedList randomList = new linkedList();
        for (int i = 0; i < 3; i++) {
            Node randomNode = getRandomNode();
            randomList.insert(randomNode.data);
        }
        return randomList;
    }

}

