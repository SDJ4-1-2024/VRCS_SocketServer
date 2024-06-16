package org.example;

import model.Coordinates;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

class ClientHandler implements Runnable {
    private Socket clientSocket;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try (ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
             ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream())) {

            String request;
            while ((request = (String) in.readObject()) != null) {
                System.out.println("Received: " + request);
                String response = processRequest(request);
                out.writeObject(response);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private synchronized String processRequest(String request) {
        if (request.equals("getCoordinates")) {
            return new Coordinates().prepareRandomCoordinates().toString();
        } else if (request.equals("resetCoordinates")) {
            Coordinates coordinates = new Coordinates();
            return coordinates + " coordinates has been restarted";
        }
        return " getCoordinates or resetCoordinates for selected vehicle";
    }
}