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
    private static RoundedTextArea jBinaryNumber;
    private static RoundedTextArea jDecimalNumber;





    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();


    private final int WIDTH = round(screenSize.getWidth()*0.2,12);
    private final int HEIGHT = round(400,12);
    private final int BOXHEIGHT = round(screenSize.getHeight()*0.05 ,12);
    private final int BOXWIDTH = round(WIDTH*0.8,12);
    private static final int radius = 18;

    Dimension prefferedSize = new Dimension(BOXWIDTH,BOXHEIGHT);

    int round(double value, int nearest) {
        return (int) Math.round(value / nearest) * nearest;
    }


    private static Gui gui;

    static Color primaryColor;
    static Color secondaryColor;
    static Color highlightColor;
    static Color errorColor;
    static Color textColor;

    final Font primaryFont = new Font("Roboto", Font.PLAIN, 14);
    final Font highlightFont = new Font("Roboto", Font.BOLD, 14);

    final Font titleFont = new Font("Roboto", Font.PLAIN, 16);


    public static Gui getInstance(){
        if (gui == null){
            gui = new Gui();
        }
        return gui;
    }

    static Border padding = BorderFactory.createEmptyBorder(radius,radius,radius,radius);


    public void gui(){

        primaryColor = Color.decode("#FAFAFA");
        secondaryColor = Color.decode("#DEE3E7");
        highlightColor = Color.decode("#37474F");
        textColor = Color.decode("#222222");
        errorColor = Color.red;

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WIDTH, HEIGHT);
        this.setTitle("Anto's number format converter");
        this.setResizable(true);
        this.setMinimumSize(new Dimension(250,255));


        jHexNumber = new RoundedTextArea(radius);
        jHexNumber.setFont(primaryFont);
        jHexNumber.setBorder(padding);
        jHexNumber.setForeground(textColor);
        jHexNumber.setBackground(primaryColor);
        jHexNumber.setPreferredSize(prefferedSize);

        jBinaryNumber = new RoundedTextArea(radius);
        jBinaryNumber.setFont(primaryFont);
        jBinaryNumber.setBorder(padding);
        jBinaryNumber.setForeground(textColor);
        jBinaryNumber.setBackground(secondaryColor);
        jBinaryNumber.setPreferredSize(prefferedSize);

        jDecimalNumber = getjDecimalNumber();
        jDecimalNumber.setFont(primaryFont);
        jDecimalNumber.setBorder(padding);
        jDecimalNumber.setForeground(textColor);
        jDecimalNumber.setBackground(primaryColor);
        jDecimalNumber.setPreferredSize(prefferedSize);

        JLabel hexLabel = new JLabel("Hex value");
        hexLabel.setFont(titleFont);
        hexLabel.setForeground(textColor);
        JLabel binaryLabel = new JLabel("Binary value");
        binaryLabel.setFont(titleFont);
        binaryLabel.setForeground(textColor);
        JLabel decimalLabel = new JLabel("Decimal value");
        decimalLabel.setFont(titleFont);
        decimalLabel.setForeground(textColor);

        JLabel supportMe = new JLabel("<html><a href='https://www.paypal.com/donate/?hosted_button_id=TRJXNGCDENSYL'>Donate to me on PayPal</a></html>");


        jDecimalNumber.addFocusListener(new FocusListener() {
            public synchronized void focusGained(FocusEvent e) {
                jDecimalNumber.addKeyListener(DecimalKeyListener.getInstance());
                jDecimalNumber.setFont(highlightFont);
                jDecimalNumber.setForeground(highlightColor);
                jDecimalNumber.setOpaque(true); //Setting to and from opaque is a super sketch way to make the field change color when you focus it

            }
            public synchronized void focusLost(FocusEvent e) {
                System.out.println("Focus off decimal");
                jDecimalNumber.setFont(primaryFont);
                jDecimalNumber.setForeground(textColor);
                jDecimalNumber.setOpaque(false);
            }
        });
        jBinaryNumber.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                jBinaryNumber.addKeyListener(BinaryKeyListener.getInstance());
                jBinaryNumber.setFont(highlightFont);
                jBinaryNumber.setForeground(highlightColor);
                jBinaryNumber.setOpaque(true);
            }
            public void focusLost(FocusEvent e) {
                System.out.println("Focus off binary");
                jBinaryNumber.setFont(primaryFont);
                jBinaryNumber.setForeground(textColor);
                jBinaryNumber.setOpaque(false);
            }
        });
        jHexNumber.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                jHexNumber.addKeyListener(HexKeyListener.getInstance());
                jHexNumber.setFont(highlightFont);
                jHexNumber.setForeground(highlightColor);
                jHexNumber.setOpaque(true);
            }
            public void focusLost(FocusEvent e) {
                System.out.println("Focus off hex");
                jHexNumber.setFont(primaryFont);
                jHexNumber.setForeground(textColor);
                jHexNumber.setOpaque(false);

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
        layout.setAutoCreateGaps(false);
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
                                .addGap(14)
                                .addComponent(binaryLabel)
                                .addComponent(jBinaryNumber, 12, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(14)
                                .addComponent(decimalLabel)
                                .addComponent(jDecimalNumber, 12, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(14)
                                .addComponent(supportMe,12,GroupLayout.DEFAULT_SIZE,14)
                        )


        ;

        panel.setBackground(primaryColor);

        this.add(panel);
        this.setVisible(true);

    }

    private RoundedTextArea getjDecimalNumber() {
        return new RoundedTextArea(radius);
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
