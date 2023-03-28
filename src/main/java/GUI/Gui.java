package GUI;

import Listeners.BinaryKeyListener;
import Listeners.DecimalKeyListener;
import Listeners.HexKeyListener;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.net.URI;

public class Gui extends JFrame{

    DataFormatConverter dataFormatConverter = new DataFormatConverter();

    private static RoundedTextArea jHexNumber;
    private static JTextArea jBinaryNumber;
    private static JTextArea jDecimalNumber;





    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();


    private int WIDTH = round(screenSize.getWidth()*0.2,12);
    private int HEIGHT = round(400,12);
    private int BOXHEIGHT = round(screenSize.getHeight()*0.05 ,12);
    private int BOXWIDTH = round(WIDTH*0.8,12);

    int round(double value, int nearest) {
        return (int) Math.round(value / nearest) * nearest;
    }


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
        this.setTitle("Anto's number format converter");
        this.setResizable(true);

        //jHexNumber = new JTextArea("F");
        jHexNumber = new RoundedTextArea(40);
        JScrollPane scrollPane = new JScrollPane(jHexNumber);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        jHexNumber.setFont(new Font("Roboto", Font.BOLD, 12));
        Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        jHexNumber.setBorder(padding);

        jBinaryNumber = new JTextArea("1111");
        jBinaryNumber.setLineWrap(true);
        jBinaryNumber.setWrapStyleWord(true);
        jBinaryNumber.setFont(new Font("Roboto", Font.BOLD, 12));


        jDecimalNumber = new JTextArea("15");
        jDecimalNumber.setLineWrap(true);
        jDecimalNumber.setWrapStyleWord(true);
        jDecimalNumber.setFont(new Font("Roboto", Font.BOLD, 12));

        JLabel hexLabel = new JLabel("Hex value");
        hexLabel.setFont(new Font("Roboto", Font.BOLD, 14));
        JLabel binaryLabel = new JLabel("Binary value");
        binaryLabel.setFont(new Font("Roboto", Font.BOLD, 14));
        JLabel decimalLabel = new JLabel("Decimal value");
        decimalLabel.setFont(new Font("Roboto", Font.BOLD, 14));

        JLabel supportMe = new JLabel("<html><a href='https://www.paypal.com/donate/?hosted_button_id=TRJXNGCDENSYL'>Donate to me on PayPal</a></html>");


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
                    Desktop.getDesktop().browse(new URI("https://www.paypal.com/donate/?hosted_button_id=TRJXNGCDENSYL"));
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

        JPanel panel = new JPanel(new FlowLayout());
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
        this.setMinimumSize(new Dimension(250,220));

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
