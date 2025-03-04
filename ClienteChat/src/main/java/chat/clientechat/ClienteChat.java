/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package chat.clientechat;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;

public class ClienteChat extends JFrame {
    private JTextArea chatArea;
    private JTextField txtMessage;
    private JButton btnSend;

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    private final String SERVER_IP = "127.0.0.1"; 
    private final int SERVER_PORT = 12349;

    public ClienteChat() {
        super("Cliente de Chat");
        initComponents();
        connectToServer();
        startListening();
    }

    private void initComponents() {
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(chatArea);

        txtMessage = new JTextField();
        btnSend = new JButton("Enviar");

        btnSend.addActionListener(e -> sendMessage());
        txtMessage.addActionListener(e -> sendMessage());

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

    private void connectToServer() {
        try {
            socket = new Socket(SERVER_IP, SERVER_PORT);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            appendChat("Conectado al servidor.");
        } catch (IOException e) {
            appendChat("Error al conectar al servidor: " + e.getMessage());
        }
    }

    private void startListening() {
        new Thread(() -> {
            try {
                String message;
                while ((message = in.readLine()) != null) {
                    appendChat(message);
                }
            } catch (IOException e) {
                appendChat("Error de conexión: " + e.getMessage());
            }
        }).start();
    }

    private void sendMessage() {
        String message = txtMessage.getText().trim();
        if (!message.isEmpty()) {
            appendChat("Yo: " + message);
            out.println(message);
            txtMessage.setText("");
        }
    }

    private void appendChat(String message) {
        SwingUtilities.invokeLater(() -> {
            chatArea.append(message + "\n");
        });
    }

    public static void main(String[] args) {
        new ClienteChat();
    }
}
