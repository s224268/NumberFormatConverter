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

    private static JTextArea jHexNumber;
    private static JTextArea jBinaryNumber;
    private static JTextArea jDecimalNumber;





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
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WIDTH, HEIGHT);
        this.setTitle("Anto's converter");
        this.setResizable(true);

        jHexNumber = new JTextArea("F");
        jHexNumber.setLineWrap(true);
        jHexNumber.setWrapStyleWord(true);
        jHexNumber.setPreferredSize(new Dimension(BOXWIDTH, BOXHEIGHT));

        jBinaryNumber = new JTextArea("1111");
        jBinaryNumber.setPreferredSize(new Dimension(BOXWIDTH, BOXHEIGHT));
        jBinaryNumber.setLineWrap(true);
        jBinaryNumber.setWrapStyleWord(true);

        jDecimalNumber = new JTextArea("15");
        jDecimalNumber.setPreferredSize(new Dimension(BOXWIDTH, BOXHEIGHT));
        jDecimalNumber.setLineWrap(true);
        jDecimalNumber.setWrapStyleWord(true);

        JLabel hexLabel = new JLabel("Hex value");
        JLabel binaryLabel = new JLabel("Binary value");
        JLabel decimalLabel = new JLabel("Decimal value");

        JLabel supportMe = new JLabel("<html><a href='paypal.me/AntonHelsgaun'>Buy me a cup of coffee on PayPal</a></html>");




        jDecimalNumber.addFocusListener(new FocusListener() {
            public synchronized void focusGained(FocusEvent e) {
                jDecimalNumber.addKeyListener(DecimalKeyListener.getInstance());
            }
            public synchronized void focusLost(FocusEvent e) {
                System.out.println("Focus off decimal");
            }
        });
        jBinaryNumber.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                jBinaryNumber.addKeyListener(BinaryKeyListener.getInstance());
            }
            public void focusLost(FocusEvent e) {
                System.out.println("Focus off binary");
            }
        });
        jHexNumber.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                jHexNumber.addKeyListener(HexKeyListener.getInstance());
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
                                .addComponent(jHexNumber, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(binaryLabel)
                                .addComponent(jBinaryNumber, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(decimalLabel)
                                .addComponent(jDecimalNumber, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(supportMe, GroupLayout.Alignment.CENTER)
                        )
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(hexLabel)
                                .addComponent(jHexNumber, 12, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(binaryLabel)
                                .addComponent(jBinaryNumber, 12, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(decimalLabel)
                                .addComponent(jDecimalNumber, 12, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addComponent(supportMe,12,GroupLayout.DEFAULT_SIZE,14)
                        )


        ;
        this.setMinimumSize(new Dimension(250,210));

        this.add(panel);
        this.setVisible(true);

    }

    public String getJHexNumber() {
        return jHexNumber.getText();
    }

    public void setJHexNumber(String text) {
        jHexNumber.setText(text);
    }

    public String getJBinaryNumber() {
        return jBinaryNumber.getText();
    }

    public void setJBinaryNumber(String text) {
        jBinaryNumber.setText(text);
    }

    public String getJDecimalNumber() {
        if (jDecimalNumber == null) {
            return "";}
        else {
            return jDecimalNumber.getText();
        }

    }

    public void setJDecimalNumber(String text) {
        jDecimalNumber.setText(text);
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
        jHexNumber.setForeground(color);
    }
    public void setJBinaryNumber(Color color){
        jBinaryNumber.setForeground(color);
    }
    public void setJDecimalNumber(Color color){
        jDecimalNumber.setForeground(color);
    }
}
