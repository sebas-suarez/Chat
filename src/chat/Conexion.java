package chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Conexion extends Thread {

    private Socket s;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;
    private String nick;

    public Conexion(Socket s) {

        try {

            this.s = s;
            inputStream = new DataInputStream(s.getInputStream());
            outputStream = new DataOutputStream(s.getOutputStream());
            start();

        } catch (Exception e) {
        }
    }

    public String getNick() {

        return nick;
    }

    public void run() {

        while (true) {
            try {

                int codigo = inputStream.readInt();
                String trama = inputStream.readUTF();
                switch (codigo) {

                    case 1:
                        nick = trama;
                        GestorConexiones.getInstance().enviarTrama(codigo, trama);
                        break;

                    case 2:
                        trama = "<" + nick + "> - " + trama;
                        GestorConexiones.getInstance().enviarTrama(codigo, trama);
                        break;

                    case 3:
                        GestorConexiones.getInstance().desconecta(this);
                        break;
                }
            } catch (Exception e) {
            }
        }
    }
    
    public void enviarTrama(int codigo, String trama){
    
        try{
        
            outputStream.writeInt(codigo);
            outputStream.writeUTF(trama);

        }catch(Exception e){
       }
    }

}
