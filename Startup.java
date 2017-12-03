import javax.swing.*;
import javafx.event.ActionEvent;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.*;
import java.awt.event.*;

public class Startup extends JFrame implements ActionListener{
    
    private JButton startGameButton, joinGameButton, goButton;
    private JLabel ipAddressLabel, portNumberLabel;
    private JTextField ipAddress, portNumber;
    private JPanel mainPanel;
    private Font font;
    private boolean newGame;
    
    public Startup(){
        font = new Font("Monospaced", Font.PLAIN, 26);
        mainPanel = new JPanel(new GridBagLayout());
        startGameButton = new JButton("Start a new game");
        joinGameButton = new JButton("Join a game");
        goButton = new JButton("Go!");
        ipAddressLabel = new JLabel();
        portNumberLabel = new JLabel();
        ipAddress = new JTextField();
        portNumber = new JTextField();
        
        goButton.setVisible(false);
        ipAddressLabel.setVisible(false);
        portNumberLabel.setVisible(false);
        ipAddress.setVisible(false);
        portNumber.setVisible(false);
        
        startGameButton.addActionListener(this);
        joinGameButton.addActionListener(this);
        goButton.addActionListener(this);
        
        //Add components to main panel
        mainPanel.add(startGameButton, getGBC(0, 0, 4, 1, true, false));
        mainPanel.add(joinGameButton, getGBC(4, 0, 4, 1, true, false));
        mainPanel.add(ipAddressLabel, getGBC(0, 1, 4, 1, true, false));
        mainPanel.add(ipAddress, getGBC(4, 1, 4, 1, true, false));
        mainPanel.add(portNumberLabel, getGBC(0, 2, 4, 1, true, false));
        mainPanel.add(portNumber, getGBC(4, 2, 4, 1, true, false));
        mainPanel.add(goButton,getGBC(0, 3, 8, 1, true, false));
        
        //Set fonts
        startGameButton.setFont(font);
        joinGameButton.setFont(font);
        ipAddressLabel.setFont(font);
        ipAddress.setFont(font);
        portNumberLabel.setFont(font);
        portNumber.setFont(font);
        goButton.setFont(font);

        frameSetup();
    }
    
    private void frameSetup(){
        this.setTitle("Hangman Startup");
        this.add(mainPanel);
        this.pack();
        this.setSize(900,700);
        this.getContentPane();   
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    //Creates GridBagConstraints 
    private GridBagConstraints getGBC(int x, int y, int w, int h, boolean fillHor, boolean fillVer){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = w;
        gbc.gridheight = h;
        if(fillHor){
            gbc.fill = GridBagConstraints.HORIZONTAL;
        }
        if(fillVer){
            gbc.fill = GridBagConstraints.VERTICAL;
        }

        //Margin around each component
        gbc.insets = new Insets(5, 5, 5, 5);
        return gbc;
    }

    public void actionPerformed(java.awt.event.ActionEvent e){
        
        //Start new game
        if(e.getSource() == startGameButton){
            ipAddressLabel.setVisible(false);
            ipAddress.setVisible(false);
            portNumberLabel.setVisible(true);
            portNumber.setVisible(true);
            goButton.setVisible(true);
            portNumberLabel.setText("Listening port number: ");
            newGame = true;
        }

        //Join existing game
        else if(e.getSource() == joinGameButton){
            ipAddressLabel.setVisible(true);
            ipAddress.setVisible(true);
            portNumberLabel.setVisible(true);
            portNumber.setVisible(true);
            goButton.setVisible(true);
            portNumberLabel.setText("Server port number: ");
            ipAddressLabel.setText("Server IP Address: ");
            newGame = false;
        }

        else if(e.getSource() == goButton){
            int serverPort = Integer.parseInt(portNumber.getText());
            if(newGame){
                new HostController(serverPort);
                this.dispose();
            }
            else{
                String serverIpAddress = ipAddress.getText();
                new ConnectController(serverIpAddress, serverPort);
                this.dispose();
            }
        }
    }

    public static void main(String[] args){
        new Startup();
    }
}