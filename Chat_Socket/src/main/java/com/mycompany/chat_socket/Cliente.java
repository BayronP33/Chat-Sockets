/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.chat_socket;

import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JOptionPane;

public class Cliente extends javax.swing.JFrame {

    public Cliente() {
        initComponents();
        this.setVisible(true);

    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        Area_Enviar_Cliente = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        Area_Recibir_Cliente = new javax.swing.JTextArea();
        Enviar_Cliente = new javax.swing.JButton();
        Recibir_Cliente = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Area_Enviar_Cliente.setColumns(20);
        Area_Enviar_Cliente.setRows(5);
        jScrollPane1.setViewportView(Area_Enviar_Cliente);

        Area_Recibir_Cliente.setEditable(false);
        Area_Recibir_Cliente.setColumns(20);
        Area_Recibir_Cliente.setRows(5);
        jScrollPane2.setViewportView(Area_Recibir_Cliente);

        Enviar_Cliente.setText("Enviar");
        Enviar_Cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Enviar_ClienteActionPerformed(evt);
            }
        });

        Recibir_Cliente.setText("Recibir");
        Recibir_Cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Recibir_ClienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(266, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(Recibir_Cliente)
                        .addGap(251, 251, 251))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(Enviar_Cliente)
                        .addGap(252, 252, 252))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Enviar_Cliente)
                .addGap(16, 16, 16)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Recibir_Cliente)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Enviar_ClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Enviar_ClienteActionPerformed
    try {
        Socket socket = new Socket("127.0.0.1", 5000); // Conectar con el servidor en el puerto 5000
        PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
        
        String mensaje = Area_Enviar_Cliente.getText();
        salida.println(mensaje); // Enviar mensaje al servidor
        
        socket.close(); // Cerrar conexión después de enviar
        Area_Enviar_Cliente.setText(""); // Limpiar el área de texto
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error al enviar el mensaje: " + e.getMessage());
    }  // **← Aquí faltaba cerrar la llave correctamente**
    }//GEN-LAST:event_Enviar_ClienteActionPerformed

    private void Recibir_ClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Recibir_ClienteActionPerformed
        try {
            ServerSocket serverSocket = new ServerSocket(6000); // Escuchar en el puerto 6000
            Socket socket = serverSocket.accept();
        
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String mensaje = entrada.readLine();
        
            Area_Recibir_Cliente.setText(mensaje); // Mostrar mensaje recibido en el JTextArea
        
            socket.close();
            serverSocket.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al recibir el mensaje: " + e.getMessage());
        }
    }//GEN-LAST:event_Recibir_ClienteActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Cliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea Area_Enviar_Cliente;
    private javax.swing.JTextArea Area_Recibir_Cliente;
    private javax.swing.JButton Enviar_Cliente;
    private javax.swing.JButton Recibir_Cliente;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
