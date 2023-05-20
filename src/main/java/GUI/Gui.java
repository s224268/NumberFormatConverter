package GUI;

import Listeners.BinaryKeyListener;
import Listeners.DecimalKeyListener;
import Listeners.HexKeyListener;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.net.URI;

public class Gui extends JFrame{
    DataFormatConverter dataFormatConverter = new DataFormatConverter();

    public static RoundedTextArea jHexNumber;
    public static RoundedTextArea jBinaryNumber;
    public static RoundedTextArea jDecimalNumber;

    JPanel panel;

    public boolean darkMode = true;


    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final int WIDTH = round(screenSize.getWidth()*0.2,12);
    private final int HEIGHT = round(400,12);
    private final int BOXHEIGHT = round(screenSize.getHeight()*0.05 ,12);
    private final int BOXWIDTH = round(WIDTH*0.8,12);
    private static final int radius = 6;

    Dimension preferredSize = new Dimension(BOXWIDTH,BOXHEIGHT);

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
    final Font titleFont = new Font("Roboto", Font.BOLD, 14);
    JLabel supportMe = new JLabel("Support me on PayPal");
    JCheckBox darkModeBox = new JCheckBox("Dark mode");

    JLabel decimalLabel;
    JLabel binaryLabel;
    JLabel hexLabel;


    public static Gui getInstance(){
        if (gui == null){
            gui = new Gui();
        }
        return gui;
    }

    static Border padding = BorderFactory.createEmptyBorder(radius,radius,radius,radius);

    public static Color getTextColor(){
        return textColor;
    }

    private void updateColor(RoundedTextArea thingy){
        thingy.setForeground(textColor);
        thingy.setBackground(primaryColor);
        thingy.setCaretColor(textColor);
    }

    public void gui(){

        errorColor = Color.decode("#BF0000");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WIDTH, HEIGHT);
        this.setTitle("Anto's number format converter");
        this.setResizable(true);
        this.setMinimumSize(new Dimension(250,255));
        darkModeBox.setSelected(true);


        jHexNumber = new RoundedTextArea(radius);
        jHexNumber.setFont(primaryFont);
        jHexNumber.setBorder(padding);
        jHexNumber.setPreferredSize(preferredSize);
        jHexNumber.setOpaque(true);

        jBinaryNumber = new RoundedTextArea(radius);
        jBinaryNumber.setFont(primaryFont);
        jBinaryNumber.setBorder(padding);
        jBinaryNumber.setPreferredSize(preferredSize);
        jBinaryNumber.setOpaque(true);

        jDecimalNumber = getjDecimalNumber();
        jDecimalNumber.setFont(primaryFont);
        jDecimalNumber.setBorder(padding);
        jDecimalNumber.setPreferredSize(preferredSize);
        jDecimalNumber.setOpaque(true);


        hexLabel = new JLabel("Hex value");
        hexLabel.setFont(titleFont);
        hexLabel.setForeground(textColor);
        binaryLabel = new JLabel("Binary value");
        binaryLabel.setFont(titleFont);
        binaryLabel.setForeground(textColor);
        decimalLabel = new JLabel("Decimal value");
        decimalLabel.setFont(titleFont);
        decimalLabel.setForeground(textColor);


        jDecimalNumber.addFocusListener(new FocusListener() {
            public synchronized void focusGained(FocusEvent e) {
                jDecimalNumber.addKeyListener(DecimalKeyListener.getInstance());
                jDecimalNumber.setFont(highlightFont);
                jDecimalNumber.setForeground(highlightColor);
                //jDecimalNumber.setOpaque(true); //Setting to and from opaque is a super sketch way to make the field change color when you focus it
            }
            public synchronized void focusLost(FocusEvent e) {
                System.out.println("Focus off decimal");
                jDecimalNumber.setFont(primaryFont);
                jDecimalNumber.setForeground(textColor);
                //jDecimalNumber.setOpaque(false);
                //TODO: ADD GRAB FOCUS WHEN pressing tab
                //jDecimalNumber.grabFocus();
            }
        });
        jBinaryNumber.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                jBinaryNumber.addKeyListener(BinaryKeyListener.getInstance());
                jBinaryNumber.setFont(highlightFont);
                jBinaryNumber.setForeground(highlightColor);
                //jBinaryNumber.setOpaque(true);
            }
            public void focusLost(FocusEvent e) {
                System.out.println("Focus off binary");
                jBinaryNumber.setFont(primaryFont);
                jBinaryNumber.setForeground(textColor);
                //jBinaryNumber.setOpaque(false);
            }
        });
        jHexNumber.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                jHexNumber.addKeyListener(HexKeyListener.getInstance());
                jHexNumber.setFont(highlightFont);
                jHexNumber.setForeground(highlightColor);
                //jHexNumber.setOpaque(true);
            }
            public void focusLost(FocusEvent e) {
                System.out.println("Focus off hex");
                jHexNumber.setFont(primaryFont);
                jHexNumber.setForeground(textColor);
                //jHexNumber.setOpaque(false);

            }
        });

        darkModeBox.addActionListener(e -> {
            darkMode = darkModeBox.isSelected();
            switchColors();
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
        panel = new JPanel(new FlowLayout());
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
                                .addComponent(darkModeBox,GroupLayout.Alignment.LEADING)
                                .addComponent(supportMe, GroupLayout.Alignment.TRAILING)

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

                                .addGroup(layout.createBaselineGroup(true,false)
                                        .addComponent(darkModeBox,12,GroupLayout.DEFAULT_SIZE,14)
                                        .addComponent(supportMe,12,GroupLayout.DEFAULT_SIZE,14)
                                )
                        )
        ;

        this.add(panel);
        switchColors();
        this.setVisible(true);

    }

    private void switchColors() {

        if (darkMode){
            primaryColor = Color.decode("#2D2F31");
            secondaryColor = Color.decode("#1F1F1F");
            highlightColor = Color.decode("#E3E3E3");
            textColor = Color.decode("#E3E3E3");
        } else {
            primaryColor = Color.decode("#DEE3E7");
            secondaryColor = Color.decode("#FAFAFA");
            highlightColor = Color.decode("#37474F");
            textColor = Color.decode("#222222");
        }

        updateColor(jHexNumber);
        updateColor(jBinaryNumber);
        updateColor(jDecimalNumber);

        supportMe.setForeground(highlightColor);
        darkModeBox.setForeground(textColor);
        darkModeBox.setBackground(secondaryColor);

        panel.setBackground(secondaryColor);

        hexLabel.setForeground(textColor);
        binaryLabel.setForeground(textColor);
        decimalLabel.setForeground(textColor);
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

    public void updateJHexNumberTextColor(){
        jHexNumber.setForeground(textColor);
    }
    public void updateJBinaryNumberTextColor(){
        jBinaryNumber.setForeground(textColor);
    }
    public void updateJDecimalNumberTextColor(){
        jDecimalNumber.setForeground(textColor);
    }
}
