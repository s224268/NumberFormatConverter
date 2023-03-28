package GUI;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class RoundedTextArea extends JTextArea {
    private int radius;
    static Graphics2D g2;
    static Graphics g;

    public RoundedTextArea(int radius) {
        this.radius = radius;
        setOpaque(false);
        setLineWrap(true);
        setWrapStyleWord(true);
    }

    @Override
    protected void paintComponent(Graphics g) { //Used in initial construction and for constructing/defining g and g2
        RoundedTextArea.g = g;
        g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.CYAN);
        g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
        super.paintComponent(g);
        g2.dispose();
    }

    protected void paintComponent(Color color) { //Used to change colors later?
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(color);
        g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
        super.paintComponent(g);
        g2.dispose();
    }



    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Gui.errorColor);
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
        g2.dispose();
    }
}
