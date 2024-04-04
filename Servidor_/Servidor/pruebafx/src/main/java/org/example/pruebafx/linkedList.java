package org.example.pruebafx;

import java.io.File;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

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
            currentNumber ++;
        }
        if (current == null && head != null) {
            return head.data;
        } else if (current != null) {
            return current.data;
        }
        return null;
    }

    public File get(int index) {
        Node results = find(index);
        if (results != null) {
            return results.data;
        }
        return null;
    }

    //Resive un id y le da like a la cancion con dicho id
    public void addLike(String id) {
        Node node = findByID(id);
        if (node != null) {
            node.data.addLike();
        }
    }

    //Resive un id y le da dislike a la cancion con dicho id
    public void addDislike(String id) {
        Node node = findByID(id);
        if (node != null) {
            node.data.addDislike();
        }
    }

    //Funcion que busca el id autogenerado de la cancion implementada
    public SongData findByID(String id) {
        Node current = head;
        while (current != null) {
            if (current.id.equals(id)) {
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
            sizeCount ++;
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
    private Node getRandomNode() {
        int size = size();
        int randomIndex = (int) (Math.random() * size);
        return find(randomIndex);
    }

    // Función para generar una lista aleatoria de 3 elementos
    public LinkedList generateRandomList() {
        LinkedList randomList = new LinkedList();
        Set<Node> selectedNodes = new HashSet<>(); // Conjunto para almacenar nodos seleccionados
        int maxAttempts = 100; // Número máximo de intentos para evitar un bucle infinito
        int attempts = 0;

        while (randomList.size() <= 2 && attempts < maxAttempts) {
            Node randomNode = getRandomNode();
            if (randomNode != null && !selectedNodes.contains(randomNode)) {
                randomList.insert(randomNode.data);
                selectedNodes.add(randomNode);
            }
            attempts++;
        }

        return randomList;
    }

    //Metodo para ordenar la lista aleatoria que manda el Community Player
    public void sortByLikesAndDislikes() {
        Node current = head;
        Node next = null;
        Node temp = null;
        int size = size();

        for (int i = 0; i < size - 1; i++) {
            next = current.next;
            while (next != null) {
                // Comparar los likes y dislikes de los nodos actual y siguiente
                if (current.likes - current.dislikes < next.likes - next.dislikes) {
                    // Intercambiar los nodos si el nodo actual tiene menos likes que el siguiente
                    temp = next.next;
                    next.next = current;
                    current.next = temp;

                    // Si el nodo actual es el head, actualizar el head
                    if (current == head) {
                        head = next;
                    }
                }
                next = next.next;
            }
            current = current.next;
        }
    }

    //Metodo para eliminar una cancion(dato importante la elimina solo para esa ejecucion y no de la carpeta)
    public void removeAtIndex(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size());
        }

        Node current = head;
        Node previous = null;

        // Si el índice es 0, el nodo a eliminar es el nodo cabeza
        if (index == 0) {
            head = current.next;
            if (head != null) {
                head.previous = null;
            }
        } else {
            // Busca el nodo en la posición dada
            for (int i = 0; i < index; i++) {
                previous = current;
                current = current.next;
            }
            // Actualiza los punteros para eliminar el nodo
            previous.next = current.next;
            if (current.next != null) {
                current.next.previous = previous;
            }
        }
        current.next = null;
        current.previous = null;
    }


    public void print() {
        StringBuilder jsonOutput = new StringBuilder();
        jsonOutput.append("{\n");
        jsonOutput.append("  \"playlist\": [\n");

        Node currentNode = head;
        while (currentNode != null) {
            var current = currentNode.data;
            jsonOutput.append("    {\n")
                .append("      \"id\": \"" + current.getId() + "\",\n")
                .append("      \"likes\": " + current.getLikes() + ",\n")
                .append("      \"dislikes\": " + current.getDislikes())
                .append("    }");

            if (currentNode.next != null) {
                jsonOutput.append(",");
            }

            jsonOutput.append("\n");
            currentNode = currentNode.next;
        }
        jsonOutput.append("  ]\n");
        jsonOutput.append("}\n");

        // Imprimir la cadena JSON resultante
        System.out.println(jsonOutput.toString());
    }
}