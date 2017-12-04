import java.io.*;

public class GVGameHub{

    /*
     * Start the game hub with a title, and instructions on how to begin.
     * It starts by asking for a game choice.
     */
    public GVGameHub(){
        boolean cont = true;
        System.out.println("****** Welcome to the GV Game Hub *******");
        System.out.println("2-player games available: Chess | Hangman");
        System.out.println("*****************************************");
        do{
            String message = getUserInput("Would you like to play Chess(c) or Hangman(h)? ");
            if(message.equals("CHESS") || message.equals("C")){
                System.out.println("Sorry, currently unavailable");
                //setUp("CHESS");
                //cont = false;
            }
            else if(message.equals("HANGMAN") || message.equals("H")){
                setUp("HANGMAN");
                cont = false;
            }
            else if(message.equals("quit")){
                System.out.println("Goodbye...");
                cont = false;
            }
        }while(cont);
    }

    /*
     * Returns the user input given a prompt from the command line
     * prompt: Prompt to send to the console
     */
    public String getUserInput(String prompt){
        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
        System.out.print(prompt);
        try{
            return userInput.readLine().trim().toUpperCase();
        }
        catch(IOException ex){
            return  "";
        }
    }

    /*
     * Sets up the game based on whether the client wants to be a client or server. 
     * Will start the game of Chess or Hangman based on user's initial choice above.
     */
    public void setUp(String game){
        boolean cont = true;
        do{
            String hostOrClient = getUserInput("Would you like to be a server(s) or client(c)? ");
            if(hostOrClient.equals("SERVER") || hostOrClient.equals("S")){
                int port = Integer.parseInt(getUserInput("Enter your port number: "));
                if(game.equals("CHESS")){
                    //TODO
                }
                else if(game.equals("HANGMAN")){
                    new HostController(port);
                }
                cont = false;
            }
            else if(hostOrClient.equals("CLIENT") || hostOrClient.equals("C")){
                int port = Integer.parseInt(getUserInput("Enter host port number: "));
                String ipAddress = getUserInput("Enter host IP address: ");
                if(game.equals("CHESS")){
                    //TODO
                }
                else if(game.equals("HANGMAN")){
                    new ConnectController(ipAddress, port);
                }
                cont = false;            
            }
        }while(cont);
    }

    /*
     * Start the game hub
     */
    public static void main(String[] args){
        new GVGameHub();
    }
}