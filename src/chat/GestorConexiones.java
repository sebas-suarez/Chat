
package chat;

import java.util.ArrayList;

public class GestorConexiones {
    private static GestorConexiones singleton = new GestorConexiones();
    
    public static GestorConexiones getInstance(){
    
        return singleton;
    }
    
    public ArrayList<Conexion>conexiones = new ArrayList<Conexion>();
    public void enviarTrama(int codigo, String trama){
            for(Conexion ms: conexiones){
             ms.enviarTrama(codigo,trama);
        }
    }
    
    public void conectaNuevo(Conexion nuevo){
            for(Conexion conexion : conexiones){
              nuevo.enviarTrama(1, conexion.getNick());
            }
             conexiones.add(nuevo);
    }
    
    public void desconecta(Conexion eliminar){
    
        int posicion = -1;
        for(int n = 0; n < conexiones.size();n++){
          if(conexiones.get(n) == eliminar){
            posicion = n;
          }
        }
      if(posicion != -1){
        for(int n =0; n < conexiones.size(); n++){
          if(n != posicion){
            conexiones.get(n).enviarTrama(3,"" + posicion);
          }
        }
        conexiones.remove(posicion);
      }    
    }
}
