
package chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import javax.swing.JOptionPane;

public class Cliente extends Thread{
  private int port;
  private String url;
  private Socket s;
  private boolean conectado;
  ClienteForm ventana;
  private String nick;
  
  public Cliente(int port, String url, String nick, ClienteForm ventana){
    this.port = port;
    this.url = url;
    this.ventana = ventana;
    this.nick = nick;
  }
  
  public void run(){
  
    try{
      s = new Socket(url,port);
      DataInputStream inputStream = new DataInputStream(s.getInputStream());
      enviarTrama(1, nick);
      conectado = true;
      while(conectado){
        int codigo = inputStream.readInt();
        String trama = inputStream.readUTF();
        switch(codigo){
            case 1:
                ventana.nuevaPersona(trama);
                break;
            
            case 2:
                ventana.mensajeRecibido(trama);
                break;
             
            case 3:
                try{
                  int posicion = Integer.parseInt(trama);
                  ventana.borrarPersona(posicion);
                  
                }catch(Exception e2){
               }
               break; 
        }
      }
      
    }catch(Exception e){
      JOptionPane.showMessageDialog(ventana,"No se pudo establecer la conexion");
    }    
  }
  
  public void enviarMensaje(String mensaje){
    enviarTrama(2, mensaje);
  }
  
  public void enviarTrama(int codigo, String trama){
    try{
      DataOutputStream outputStream = new DataOutputStream(s.getOutputStream());
      outputStream.writeInt(codigo);
      outputStream.writeUTF(trama);
    }catch(Exception e){
      JOptionPane.showMessageDialog(ventana, "No se pudo enviar el mensaje");
    }
  }
  
}
