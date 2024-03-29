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
            node.likes += 1;
        }
    }
    //Resive un id y le da dislike a la cancion con dicho id
    public void addDislike(String id) {
        Node node = findByID(id);
        if (node != null) {
            node.dislikes += 1;
        }
    }

    //Funcion que busca el id autogenerado de la cancion implementada
    public Node findByID(String id) {
        Node current = head;
        while (current != null) {
            if (current.id.equals(id)) {
                return current;
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

        Node current = head;
        while (current != null) {
            jsonOutput.append("    {\n");
            jsonOutput.append("      \"id\": \"" + current.id + "\",\n");
            jsonOutput.append("      \"likes\": " + current.likes + ",\n");
            jsonOutput.append("      \"dislikes\": " + current.dislikes + ",\n");
            // Asume que el objeto File se convierte a una cadena para la impresión JSON
            jsonOutput.append("      \"data\": \"" + current.data.toString() + "\"\n");
            jsonOutput.append("    }");

            if (current.next != null) {
                jsonOutput.append(",");
            }

            jsonOutput.append("\n");
            current = current.next;
        }

        jsonOutput.append("  ]\n");
        jsonOutput.append("}\n");

        // Imprimir la cadena JSON resultante
        System.out.println(jsonOutput.toString());
    }


}

