package org.example.pruebafx;

import java.io.File;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.PriorityQueue;

public class LinkedList {

    private static class Node {
        SongData data;
        Node next = null;
        Node previous = null;

        Node(SongData data) {

            this.data = data;
        }
    }

    private Node head; // head
    private Node tail;
    private Node current; // current

    public void insert(SongData data) {
        data.setId(generateRandomID());
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.previous = tail;
            tail.next = newNode;
            tail = newNode;
        }
    }
    public SongData find(int index) {
        Node current = head;
        int currentNumber = 0;
        while (current != null && currentNumber < index) {
            current = current.next;
            currentNumber++;
        }
        if (current == null && head != null) {
            return head.data;
        } else if (current != null) {
            return current.data;
        }
        return null;
    }
    public SongData get(int index) {
        SongData results = find(index);
        return results;
    }
    //Resive un id y le da like a la cancion con dicho id
    public void addLike(String id) {
        SongData node = findByID(id);
        if (node != null) {
            node.addLike();
        }
    }
    //Recibe un id y le da dislike a la cancion con dicho id
    public void addDislike(String id) {
        SongData node = findByID(id);
        if (node != null) {
            node.addDislike();
        }
    }

    //Funcion que busca el id autogenerado de la cancion implementada
    public SongData findByID(String id) {
        Node current = head;
        while (current != null) {
            if (current.data.getId().equals(id)) {
                return current.data;
            }
            current = current.next;
        }
        return null;
    }

    public int size() {
        int sizeCount = 0;
        Node current = head;
        while (current != null) {
            sizeCount++;
            current = current.next;

        }
        return sizeCount;

    }

    //Este metodo genera un id completamente aleatorio a cada cancion de la lista
    private String generateRandomID() {
        UUID randomUUID = UUID.randomUUID();
        return randomUUID.toString();
    }

    // Método para obtener un nodo aleatorio
    private SongData getRandomNode() {
        int size = size();
        int randomIndex = (int) (Math.random() * size);
        return find(randomIndex);
    }

    // Función para generar una lista aleatoria de 3 elementos
    public LinkedList generateRandomList() {
        LinkedList randomList = new LinkedList();
        Set<SongData> selectedNodes = new HashSet<>(); // Conjunto para almacenar nodos seleccionados
        int maxAttempts = 100; // Número máximo de intentos para evitar un bucle infinito
        int attempts = 0;

        while (randomList.size() <= 9 && attempts < maxAttempts) {
            SongData randomNode = getRandomNode();
            if (randomNode != null && !selectedNodes.contains(randomNode)) {
                randomList.insert(randomNode);
                selectedNodes.add(randomNode);
            }
            attempts++;
        }

        return randomList;
    }
    /*Metodo para eliminar una cancion(dato importante la elimina solo para esa ejecucion y
    no de la carpeta
     */
    public void removeAtIndex(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size());
        }

        Node current = head;
        Node previous = null;

        if (index == 0) {
            head = current.next;
            if (head != null) {
                head.previous = null;
            }
        } else {
            for (int i = 0; i < index; i++) {
                previous = current;
                current = current.next;
            }
            previous.next = current.next;
            if (current.next != null) {
                current.next.previous = previous;
            }
        }
    }
    public void clear() {
        head = null;
        tail = null;
        current = null;
    }
    public void addAll(LinkedList otherList) {
        if (otherList != null) {
            Node otherCurrent = otherList.head;
            while (otherCurrent != null) {
                this.insert(otherCurrent.data);
                otherCurrent = otherCurrent.next;
            }
        }
    }
    public boolean isEmpty() {
        return head == null;
    }

    public void sortByLikesAndDislikes() {
        PriorityQueue<SongData> priorityQueue = new PriorityQueue<>();
        Node current = head;

        // Extraer los datos de cada nodo y agregarlos a la PriorityQueue
        while (current != null) {
            priorityQueue.add(current.data);
            current = current.next;
        }

        // Reconstruir la lista enlazada con los elementos ordenados de la PriorityQueue
        clear(); // Limpia la lista actual para reconstruirla
        while (!priorityQueue.isEmpty()) {
            SongData data = priorityQueue.poll();
            insert(data); // Asume que insert() es un método que inserta al final de la lista
        }
    }
}

