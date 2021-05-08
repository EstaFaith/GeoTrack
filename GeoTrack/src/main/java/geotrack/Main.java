package geotrack;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;

public class Main extends JFrame {
    private static final long serialVersionUID = -4593051884611417379L;
    private JPanel panelLogin;

    static File programRootDir = new File("c:\\GeoCord\\");
    static File guestFile = new File("c:\\GeoCord\\guest.txt");

    User user1;
    static Main main;

    //Launch the application
    public static void main(String[] args) throws Exception {
        main = new Main();

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                main.setLocationRelativeTo(null);
                main.setVisible(true);
                main.setTitle("Mark My Vehicle");
            }
        });
    }
    //Create the frame
    public Main() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 527, 150);
        panelLogin = new JPanel();
        panelLogin.setBackground(SystemColor.controlHighlight);
        panelLogin.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(panelLogin);

        JLabel lblLoginAsGuest = new JLabel("Hi! This program is used to mark your vehicle on map (based on your current location)");
        lblLoginAsGuest.setBounds(200, 185, 150, 150);
        lblLoginAsGuest.setForeground(UIManager.getColor("Menu.acceleratorForeground"));
        lblLoginAsGuest.setBackground(UIManager.getColor("Menu.acceleratorForeground"));
        panelLogin.add(lblLoginAsGuest);

        JButton btnGuest = new JButton("Go!");
        btnGuest.addActionListener(new ActionListener() {

            //Action for Guest button
            public void actionPerformed(ActionEvent e) {
                if (!programRootDir.exists()) {
                    try {
                        programRootDir.mkdir();
                        guestFile.createNewFile();
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, "Creation of guest file error...");
                    }
                }
                try {
                    user1 = new User(guestFile);
                    main.dispose();
                    main.setVisible(false);
                    Menu mm = new Menu(user1);
                    mm.setLocationRelativeTo(null);
                    mm.setVisible(true);
                } catch(Exception ex) {
                    JOptionPane.showMessageDialog(null, "No guest file exists on this computer\nPlease consult the administrator of the system.");
                }
            }
        });
        panelLogin.add(btnGuest);
    }
}
