import java.io.*; 
import java.net.*;
import java.util.*;
import java.text.*;
import java.lang.*;
public class FTPServer{

    //Connection port and  server socket
    private static final int CONN_PORT = 7171;
    private static ServerSocket serverSocket = null;
    public static void main(String args[]) throws IOException{

        Database database = new Database();

        //Create server socket on port 7171
        try{
            serverSocket = new ServerSocket(CONN_PORT);
        }
        catch(IOException ex){
            System.out.println("Cannot start central server");
            System.exit(1);
        }

        //Continuously listen for client connections
        int clientNumber = 0;
        do{            
            Socket clientSocket = serverSocket.accept();

            //Create and start a new thread for each client with the client socket and number
            ClientHandler clientHandler = new ClientHandler(clientSocket, clientNumber);
            clientHandler.start();
        }while(true);
    }
}

class ClientHandler extends Thread{
    
    //Control connection socket 
    static final String EOF = "EOF";
    private Socket connectionSocket; 
    private BufferedReader inFromClient;
    private DataOutputStream outToClient;

    //Creates client
    public ClientHandler(Socket connectionSocket, int clientNumber){
        this.connectionSocket = connectionSocket;
        
        //Get control input from client
        try{
            inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            outToClient = new DataOutputStream(connectionSocket.getOutputStream());
        }
        catch(IOException ex){
            System.out.println("Error creating I/O streams");
        }
    }

    //The logic for the client-server connection
    public void run(){
        boolean cont = true;
        do{
            try{

                //Get control message
                String[] tokens = inFromClient.readLine().split("\\s");
                if(tokens.length == 1){
                    if(tokens[0].equals("list")){
                        for(String info : database){
                            outToClient.writeBytes(info);
                            outToClient.writeBytes(EOF + '\n');
                        }



                    }
                    else if(tokens[0].equals("quit")){




                    }
                }
                else if(tokens.length == 3){
                    if(tokens[0].equals("connect") || tokens[0].equals("start")){
                        String ipAddress = tokens[1];
                        int port = Integer.parseInt(tokens[2]);
                        if(tokens[0].equals("connect")){

                        }
                        else{

                        }
                    }
                }
            }
            catch(IOException ex){

            }

                   
                    
        }while(cont);

        //Close control connection
        try{
            System.out.println("Client " + clientNumber + " disconnected");
            connectionSocket.close();
        }
        catch(IOException ex){
            System.out.println("Unable to disconnect: (IO EX: " + ex + ")");
        }
    }
}

class Database{
    static ArrayList<String> hosts;
    public Database(){
        hosts = new ArrayList<String>();
    }
    public static void addUser(String info){
        host.add(userInfo);
    }
}