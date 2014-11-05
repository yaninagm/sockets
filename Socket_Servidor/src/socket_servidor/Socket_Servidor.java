/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socket_servidor;

import java.io.DataInputStream;
import java.net.*;
import java.io.IOException;
/**
 *
 * @author ivan
 */
public class Socket_Servidor {
static final int portNumber=5500;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //Abro el socket del servidor
        ServerSocket myService=null;
        try{
            myService = new ServerSocket(portNumber);
        }catch(IOException e){
            System.out.println(e);
        }
        
        //Creo  un objeto socket para escuchar y aceptar al cliente.
        Socket clientSocket=null;
        try{
        clientSocket = myService.accept();
        }catch(IOException e){
            System.out.println(e);
        }
        
        // como creo un inputStream
        DataInputStream input;
        try{
            input = new DataInputStream(clientSocket.getInputStream());
        }catch(IOException e){
        }
        
    }
    
}
