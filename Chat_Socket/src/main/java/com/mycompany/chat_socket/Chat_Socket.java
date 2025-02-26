/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.chat_socket;

public class Chat_Socket {

    public static void main(String[] args) {
        Servidor server = new Servidor();
        server.setVisible(true);

        Cliente cliente = new Cliente();
        cliente.setVisible(true);
    }
}

