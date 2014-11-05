/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sockets;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.net.*;

/**
 * Ejemplo que implementa un cliente de eco usando UDP.
 */
public class Sockets {

    /**
     * @param args the command line arguments
     */
    public static void main(String argv[]) {
        if (argv.length != 3) {
            System.err.println("Formato: ClienteUDP <maquina> <puerto> <mensaje>");
            System.exit(-1);
        }
        DatagramSocket sDatagram = null;
        try {
            // Creamos el socket no orientado a conexión
            // (en cualquier puerto libre)
            sDatagram = new DatagramSocket();
            // Establecemos un timeout de 30 segs
            sDatagram.setSoTimeout(30000);
            // Obtenemos la dirección IP del servidor
            // (recibida en el primer argumento por linea de comando)
            InetAddress dirServidor = InetAddress.getByName(argv[0]);
            // Obtenemos el puerto del servidor
            // (recibido en el segundo argumento por linea de comando)
            int puertoServidor = Integer.parseInt(argv[1]);
            // Obtenemos el mensaje
            // (tercer argumento de la linea de comando)
            String mensaje = argv[2];
            // Preparamos el datagrama que vamos a enviar y lo enviamos
            DatagramPacket dgramEnv= new DatagramPacket(mensaje.getBytes(),
                            mensaje.getBytes().length, dirServidor, puertoServidor);
            // Enviamos el datagrama
            sDatagram.send(dgramEnv);
            System.out.println("CLIENTE: Enviando "+ new String(dgramEnv.getData())
                    + " a "+ dgramEnv.getAddress().toString()+ ":"+ dgramEnv.getPort());
            // Preparamos el datagrama de recepción
            byte array[]= new byte[1024];
            DatagramPacket dgramRec= new DatagramPacket(array, array.length);
            // Recibimos el mensaje
            sDatagram.receive(dgramRec);
            System.out.println("CLIENTE: Recibido "+ new String(dgramRec.getData(), 0, dgramRec.getLength())
                    + " de "+ dgramRec.getAddress().toString()+ ":"+ dgramRec.getPort());
        } catch (SocketTimeoutException e) {
            System.err.println("30 segs sin recibir nada");
        } catch (Exception e) {
            System.err.println("Error: "+ e.getMessage());
        } finally {
        // Cerramos el socket para liberar la conexión
            sDatagram.close();
        }
    }
    
}
