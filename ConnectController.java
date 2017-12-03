import java.io.*;
import java.net.*;

public class ConnectController extends Controller{
    
    private Socket socket;
    private String ipAddress;
    private int port;
    private DataOutputStream dos;
    private BufferedReader br;

    public ConnectController(String ipAddress, int port){
        super(false);
        this.ipAddress = ipAddress;
        this.port = port;
        try{
            socket = new Socket(ipAddress, port);
            System.out.println("Connection created");
            dos = new DataOutputStream(socket.getOutputStream());
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }
        catch(IOException ex){
            System.out.println("Cannot Setup server");
            System.exit(1);
        }
        this.control(dos);
        this.listen(br);
    }

    public static void main(String[] args){
        ConnectController connectController = new ConnectController("127.0.0.1", 7171);
    }
}