package com.company;

import java.awt.*;
import java.awt.event.*;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.*;
import javax.swing.border.Border;

public class Server extends JFrame implements ActionListener {


    JPanel p1;
    JTextField t1;
    JButton b1;
    static JLabel l4;
    static JTextArea a1;
    //Client c = new Client();

    static ServerSocket skt;
    static Socket s;
    static DataInputStream din;
    static DataOutputStream dout;


    Server() {

        p1 = new JPanel();
        p1.setLayout(null);
        p1.setBackground(new Color(7, 94, 84));
        p1.setBounds(0, 0, 350, 50);
        add(p1);

//      Adding the image icon to the app.
        ImageIcon img1 = new ImageIcon(ClassLoader.getSystemResource("com/company/icons/3.png"));
//      Getting the image and setting size to that.
        Image i1 = img1.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
//      Converting the resized image back to ImageIcon.
        ImageIcon img2 = new ImageIcon(i1);
        JLabel l1 = new JLabel(img2);
        l1.setBounds(5,13,25,25);
        p1.add(l1);

        l1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });

//      Adding the image icon to the app.
        ImageIcon img3 = new ImageIcon(ClassLoader.getSystemResource("com/company/icons/1.png"));
//      Getting the image and setting size to that.
        Image i2 = img3.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
//      Converting the resized image back to ImageIcon.
        ImageIcon img4 = new ImageIcon(i2);
        JLabel l2 = new JLabel(img4);
        l2.setBounds(40,10,30,30);
        p1.add(l2);

        JLabel l3 = new JLabel("Messi");
        l3.setFont(new Font("SAN_SERIF", Font.BOLD, 16));
        l3.setForeground(Color.WHITE);
        l3.setBounds(90, 7, 100, 20);
        p1.add(l3);

        l4 = new JLabel("Active Now");
        l4.setFont(new Font("SAN_SERIF", Font.PLAIN, 12));
        l4.setForeground(Color.WHITE);
        l4.setBounds(90, 25, 100, 20);
        p1.add(l4);


//      Adding the video icon to the app.
        ImageIcon img5 = new ImageIcon(ClassLoader.getSystemResource("com/company/icons/video.png"));
//      Getting the image and setting size to that.
        Image i3 = img5.getImage().getScaledInstance(25, 35, Image.SCALE_DEFAULT);
//      Converting the resized image back to ImageIcon.
        ImageIcon img6 = new ImageIcon(i3);
        JLabel l5 = new JLabel(img6);
        l5.setBounds(240,8,25,35);
        p1.add(l5);

//      Adding the phone icon to the app.
        ImageIcon img7 = new ImageIcon(ClassLoader.getSystemResource("com/company/icons/phone.png"));
//      Getting the image and setting size to that.
        Image i4 = img7.getImage().getScaledInstance(30, 35, Image.SCALE_DEFAULT);
//      Converting the resized image back to ImageIcon.
        ImageIcon img8 = new ImageIcon(i4);
        JLabel l6 = new JLabel(img8);
        l6.setBounds(280,8,30,35);
        p1.add(l6);

//      Adding the 3 icon to the app.
        ImageIcon img9 = new ImageIcon(ClassLoader.getSystemResource("com/company/icons/3icon.png"));
//      Getting the image and setting size to that.
        Image i5 = img9.getImage().getScaledInstance(13, 25, Image.SCALE_DEFAULT);
//      Converting the resized image back to ImageIcon.
        ImageIcon img10 = new ImageIcon(i5);
        JLabel l7 = new JLabel(img10);
        l7.setBounds(320,10,13,25);
        p1.add(l7);


//      Adding the text area to show both sender and receiver messages.
        a1 = new JTextArea();
        a1.setBounds(5, 55, 340, 400);
        a1.setFont(new Font("SAN_SERIF", Font.PLAIN, 15));
        a1.setEditable(false);
        a1.setLineWrap(true);
        a1.setWrapStyleWord(true);
        //a1.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        add(a1);

//      Adding the text field and button at the bottom.
        t1 = new JTextField();
        t1.setBounds(5, 460, 250, 30);
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        t1.setBorder(border);
        t1.setFont(new Font("SAN_SERIF", Font.PLAIN, 15));
        add(t1);

//        Adding a key listener for typing action.
        t1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                Client.changeStatus(true);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                Client.changeStatus(false);
            }
        });

//      Adding jbutton for send button.
        b1 = new JButton("Send");
        b1.setBounds(260, 460, 80, 30);
        b1.setBackground(new Color(7, 94, 84));
        b1.setFont(new Font("SAN_SERIF", Font.PLAIN, 14));
        b1.setForeground(Color.WHITE);
        b1.addActionListener(this);
        add(b1);

//      change background color
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setSize(350, 500);
        setLocation(150, 75);
//      use undecorated to remove maximise and close button on the top of app.
        setUndecorated(true);
        setVisible(true);
    }

//    Action performed when the send button is clicked.
    public void actionPerformed(ActionEvent ae) {
        try {
            if (!t1.getText().equals("")) {
                String out = t1.getText();
                a1.setText(a1.getText() + "\n\t\t" + out);
                dout.writeUTF(out);
                t1.setText("");
            }
        }
        catch (Exception e) {}
    }

    public static void main(String[] args) {
        new Server();
        String msg = "";
        try {
            skt = new ServerSocket(6001);
            s =   skt.accept();
            din = new DataInputStream(s.getInputStream());
            dout = new DataOutputStream(s.getOutputStream());
            msg = din.readUTF();
            a1.setText(a1.getText()+"\n"+msg);
        }
        catch(Exception e) {

        }
    }

    public static void changeStatus(boolean typing) {
        System.out.println("server"+typing);
//      Adding timer class for typing action.
        Timer t = new Timer(1, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!typing) {
                    l4.setText("Active now");
                }
            }
        });
        t.setInitialDelay(2000);
        if(typing) {
            l4.setText("typing...");
            t.stop();
        }
        else {
            if(!t.isRunning())
                t.start();
        }
    }
}
