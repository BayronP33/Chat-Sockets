/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chat.servidorchat;

import java.io.*;
import java.net.*;

public class ClienteHandler extends Thread {
    private Socket socket;
    private ServidorChat server;
    private BufferedReader in;
    private PrintWriter out;

    public ClienteHandler(Socket socket, ServidorChat server) {
        this.socket = socket;
        this.server = server;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch(IOException e) {
            server.appendLog("Error al crear streams: " + e.getMessage());
        }
    }

    public void sendMessage(String message) {
        out.println(message);
    }

    @Override
    public void run() {
        try {
            String message;
            while ((message = in.readLine()) != null) {
                String fullMessage = "Cliente: " + message;
                server.broadcast(fullMessage, this);
                server.appendLog(fullMessage);
            }
        } catch(IOException e) {
            server.appendLog("Error en comunicación con cliente: " + e.getMessage());
        } finally {
            try {
                socket.close();
            } catch(IOException e) {
                // Ignorar
            }
            server.removeClient(this);
        }
    }
}

