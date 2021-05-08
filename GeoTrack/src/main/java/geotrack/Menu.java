package geotrack;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.imageio.ImageIO;
import javax.swing.*;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONException;

import java.io.*;

public class Menu extends JFrame {
    private static final long serialVersionUID = -5174642765310724600L;
    public final static String url = "https://freegeoip.app/json/";

    public Menu(final User user1) throws Exception {

        //MainMenu JFrame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(SystemColor.controlHighlight);
        getContentPane().setLayout(null);
        setBounds(100, 100, 825, 560);

        JPanel panelSearch = new JPanel();
        panelSearch.setBounds(12, 10, 785, 500);
        panelSearch.setLayout(null);

        //Event Button for searching by address
        JButton btnAddress = new JButton("Input The Location");
        btnAddress.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnAddress.setBounds(300, 70, 175, 40);
        panelSearch.add(btnAddress);

        btnAddress.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String json;
                try{
                    json = httpRequest();
                } catch (IOException ex){
                    System.out.println("Request Error!");
                    return;
                }

                Location location;
                try{
                    location = Parser.parseJSONtoLocation(json);
                } catch (JSONException ex){
                    System.out.println("JSON failed!");
                    return;
                }

                System.out.print(location.toString());
                try {
                    useBrowserToShowMap(location.text());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        //This is the event button to log out. This causes the system to terminate the program, also causing the user to log out.
        JButton btnLogOutAndExit = new JButton("Exit");


        btnLogOutAndExit.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnLogOutAndExit.setBounds(313, 120, 150, 20);
        panelSearch.add(btnLogOutAndExit);

        btnLogOutAndExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Goodbye!");
                System.exit(0);
            }
        });

        //This label is the title for the main search page
        JLabel lblSearchTitle = new JLabel();
        lblSearchTitle.setFont(new Font("Tahoma", Font.BOLD, 28));
        lblSearchTitle.setBounds(130, 10, 525, 35);
        lblSearchTitle.setText("Find your location simply and fast !");
        panelSearch.add(lblSearchTitle);

        try {
            Image sat_image = ImageIO.read(getClass().getResource("../logo.png"));
            sat_image = sat_image.getScaledInstance(600, 300, java.awt.Image.SCALE_SMOOTH);
            ImageIcon sat_imageIcon = new ImageIcon(sat_image);
            JLabel lblNewLabel = new JLabel(sat_imageIcon);
            lblNewLabel.setBounds(0, 80, 800, 500);
            panelSearch.add(lblNewLabel);

        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null,ex);
        }

        this.getContentPane().add(panelSearch);
    }

    /**
     * Сделать запросик за json с текущим местоположением.
     * @return json с текущим местоположением.
     * @throws IOException при ошибке в ходе запроса.
     */
    private static String httpRequest() throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient();

        try (
                Response response = okHttpClient
                        .newCall(new Request.Builder()
                                .url(url).build()).execute()) {
            return response.body().string();
        }
        finally {
            okHttpClient.connectionPool().evictAll();
        }
    }

    //This will method not work unless the user has Firefox
    public void useBrowserToShowMap(String text) throws Exception {
        String[] argsToRun = {"C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe", "-new-window", "https://www.google.co.uk/maps/place/" + text};
        new ProcessBuilder(argsToRun).start();
    }

}
