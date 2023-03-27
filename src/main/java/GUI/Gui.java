package GUI;

import Listeners.BinaryKeyListener;
import Listeners.DecimalKeyListener;
import Listeners.HexKeyListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URI;

public class Gui extends JFrame{

    DataFormatConverter dataFormatConverter = new DataFormatConverter();

    private static JTextArea jhexNumber;
    private static JTextArea jbinaryNumber;
    private static JTextArea jdecimalNumber;





    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    private int WIDTH = (int) (screenSize.getWidth()*0.3);
    private int HEIGHT = (int) (screenSize.getHeight()*0.4);
    private int BOXHEIGHT = (int) ((screenSize.getHeight())*0.05);
    private int BOXWIDTH = (int) (WIDTH*0.8);



    private static Gui gui;


    public static Gui getInstance(){
        if (gui == null){
            gui = new Gui();
        }
        return gui;
    }


    public void gui(){
        //this.smtp = smtp;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WIDTH, HEIGHT);
        this.setTitle("Anto's converter");
        this.setResizable(true);

        jhexNumber = new JTextArea("F");
        jhexNumber.setLineWrap(true);
        jhexNumber.setWrapStyleWord(true);
        jhexNumber.setPreferredSize(new Dimension(BOXWIDTH, BOXHEIGHT));

        jbinaryNumber = new JTextArea("1111");
        jbinaryNumber.setPreferredSize(new Dimension(BOXWIDTH, BOXHEIGHT));
        jbinaryNumber.setLineWrap(true);
        jbinaryNumber.setWrapStyleWord(true);

        jdecimalNumber = new JTextArea("15");
        jdecimalNumber.setPreferredSize(new Dimension(BOXWIDTH, BOXHEIGHT));
        jdecimalNumber.setLineWrap(true);
        jdecimalNumber.setWrapStyleWord(true);

        JLabel hexLabel = new JLabel("Hex value");
        JLabel binaryLabel = new JLabel("Binary value");
        JLabel decimalLabel = new JLabel("Decimal value");
        //JLabel supportMe = new JLabel();

        // Create a new JLabel with the hyperlink text
        JLabel supportMe = new JLabel("<html><a href='paypal.me/AntonHelsgaun'>Buy me a cup of coffee on PayPal</a></html>");




        jdecimalNumber.addFocusListener(new FocusListener() {
            public synchronized void focusGained(FocusEvent e) {
                jdecimalNumber.addKeyListener(DecimalKeyListener.getInstance());
            }
            public synchronized void focusLost(FocusEvent e) {
                System.out.println("Focus off decimal");
            }
        });
        jbinaryNumber.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                jbinaryNumber.addKeyListener(BinaryKeyListener.getInstance());
            }
            public void focusLost(FocusEvent e) {
                System.out.println("Focus off binary");
            }
        });
        jhexNumber.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                jhexNumber.addKeyListener(HexKeyListener.getInstance());
            }
            public void focusLost(FocusEvent e) {
                System.out.println("Focus off hex");
            }
        });

        supportMe.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                // Open the hyperlink in the user's default web browser
                try {
                    Desktop.getDesktop().browse(new URI("https://www.paypal.me/AntonHelsgaun"));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            public void mouseEntered(MouseEvent e) {
                // Change the cursor to a hand cursor when the mouse enters the label
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            public void mouseExited(MouseEvent e) {
                // Change the cursor back to the default cursor when the mouse exits the label
                setCursor(Cursor.getDefaultCursor());
            }
        });


        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(hexLabel)
                                .addComponent(jhexNumber, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(binaryLabel)
                                .addComponent(jbinaryNumber, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(decimalLabel)
                                .addComponent(jdecimalNumber, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(supportMe, GroupLayout.Alignment.CENTER)
                        )
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(hexLabel)
                                .addComponent(jhexNumber, 12, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(binaryLabel)
                                .addComponent(jbinaryNumber, 12, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(decimalLabel)
                                .addComponent(jdecimalNumber, 12, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addComponent(supportMe,12,GroupLayout.DEFAULT_SIZE,14)
                        )


        ;
        this.setMinimumSize(new Dimension(250,210));

        this.add(panel);
        this.setVisible(true);

    }
    public void setJbinaryNumberWidth(int width){
        jbinaryNumber.setBounds(new Rectangle(width,50));
    }
    public void setJHexNumberWidth(int width){
        jhexNumber.setBounds(new Rectangle(width,50));
    }
    public void setJDecimalNumberWidth(int width){
        jdecimalNumber.setBounds(new Rectangle(width,50));
    }



    public String getJhexNumber() {
        return jhexNumber.getText();
    }

    public void setJHexNumber(String text) {
        jhexNumber.setText(text);
    }

    public String getJbinaryNumber() {
        return jbinaryNumber.getText();
    }

    public void setJBinaryNumber(String text) {
        jbinaryNumber.setText(text);
    }

    public String getJdecimalNumber() {
        if (jdecimalNumber == null) {
            return "";}
        else {
            return jdecimalNumber.getText();
        }

    }

    public void setJDecimalNumber(String text) {
        jdecimalNumber.setText(text);
    }

    public void updateFromDecimal() {
        dataFormatConverter.updateFromDecimal();
        }

    public void updateFromBinary(){
        DataFormatConverter.updateFromBinary();
    }
    public void updateFromHex() {
        DataFormatConverter.updateFromHex();
    }

    public void setJHexNumber(Color color){
        jhexNumber.setForeground(color);
    }
    public void setJBinaryNumber(Color color){
        jbinaryNumber.setForeground(color);
    }
    public void setJDecimalNumber(Color color){
        jdecimalNumber.setForeground(color);
    }
}
