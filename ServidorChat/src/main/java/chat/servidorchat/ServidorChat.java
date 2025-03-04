/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package chat.servidorchat;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class ServidorChat extends JFrame {
    private JTextArea txtLog;
    private JTextField txtMessage;
    private JButton btnSend;
    private ServerSocket serverSocket;
    private ArrayList<ClienteHandler> clients;
    private final int PORT = 12349;

    public ServidorChat() {
        super("Servidor de Chat");
        clients = new ArrayList<>();
        initComponents();
        startServer();
    }

    private void initComponents() {
        txtLog = new JTextArea();
        txtLog.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(txtLog);

        txtMessage = new JTextField();
        btnSend = new JButton("Enviar");

        btnSend.addActionListener(e -> sendServerMessage());
        txtMessage.addActionListener(e -> sendServerMessage());

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(txtMessage, BorderLayout.CENTER);
        panel.add(btnSend, BorderLayout.EAST);

        this.setLayout(new BorderLayout());
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(panel, BorderLayout.SOUTH);
        this.setSize(400, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private void sendServerMessage() {
        String message = txtMessage.getText().trim();
        if (!message.isEmpty()) {
            appendLog("Yo: " + message);
            String broadcastMessage = "Servidor: " + message;
            broadcast(broadcastMessage, null);
            txtMessage.setText("");
        }
    }

    private void startServer() {
        new Thread(() -> {
            try {
                serverSocket = new ServerSocket(PORT);
                appendLog("Servidor iniciado en el puerto " + PORT);
                while (true) {
                    Socket socket = serverSocket.accept();
                    appendLog("Cliente conectado: " + socket.getInetAddress().getHostAddress());
                    ClienteHandler clientHandler = new ClienteHandler(socket, this);
                    clients.add(clientHandler);
                    clientHandler.start();
                }
            } catch (IOException e) {
                appendLog("Error en el servidor: " + e.getMessage());
            }
        }).start();
    }

    public synchronized void broadcast(String message, ClienteHandler excludeClient) {
        for (ClienteHandler client : clients) {
            if (client != excludeClient) {
                client.sendMessage(message);
            }
        }
    }

    public void appendLog(String message) {
        SwingUtilities.invokeLater(() -> txtLog.append(message + "\n"));
    }

    public void removeClient(ClienteHandler client) {
        clients.remove(client);
        appendLog("Cliente desconectado.");
    }

    public static void main(String[] args) {
        new ServidorChat();
    }
}

