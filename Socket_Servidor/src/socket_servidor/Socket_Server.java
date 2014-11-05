/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socket_servidor;

import java.io.*;
import java.net.*;

/**
 *
 * @author ivan
 */
public class Socket_Server {

    static final int portNumber = 25;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

// declaration section:
// declare a server socket and a client socket for the server
// declare an input and an output stream
        ServerSocket Server = null;
        String line;
        DataInputStream is;
        PrintStream os;
        Socket clientSocket = null;
// Try to open a server socket on port 9999
// Note that we can't choose a port less than 1023 if we are not
// privileged users (root)
        try {
            Server = new ServerSocket(portNumber);
        } catch (IOException e) {
            System.out.println(e);
        }
// Create a socket object from the ServerSocket to listen and accept 
// connections.
// Open input and output streams
        try {
            clientSocket = Server.accept();
            is = new DataInputStream(clientSocket.getInputStream());
            os = new PrintStream(clientSocket.getOutputStream());
// As long as we receive data, echo that data back to the client.
            while (true) {
                line = is.readLine();
                os.println(line);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

}
