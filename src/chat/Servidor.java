package chat;

import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Servidor extends Thread {

    private int port;
    private JFrame ventana;

    public Servidor(JFrame ventana, int port) {
        this.port = port;
        this.ventana = ventana;
    }

    public void run() {

        ServerSocket serverSocket = null;

        try {

            serverSocket = new ServerSocket(port);
            while (true) {
                Socket socket = serverSocket.accept();
                GestorConexiones.getInstance().conectaNuevo(new Conexion(socket));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(ventana, "Error al abrir el puerto. Posiblemente ya este en uso.");
        }
        try {
            serverSocket.close();

        } catch (Exception e) {
        }
    }
}
