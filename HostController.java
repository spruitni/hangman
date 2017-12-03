import java.io.*;
import java.net.*;

public class HostController extends Controller{
    
    //Connection port and  server socket
    private Model model;
    private int port;
    private ServerSocket serverSocket = null;
    private DataOutputStream dos;
    private BufferedReader br;

    public HostController(int port){
        super(true);
        this.port = port;
        try{
            serverSocket = new ServerSocket(port);
            Socket clientSocket = serverSocket.accept();
            System.out.println("Connection created");
            dos = new DataOutputStream(clientSocket.getOutputStream());
            br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        }
        catch(IOException ex){
            System.out.println("Cannot Setup server");
            System.exit(1);
        }
        this.control(dos);
        this.listen(br);
    }

    public static void main(String[] args){
        HostController hostController = new HostController(7171);
    }
}