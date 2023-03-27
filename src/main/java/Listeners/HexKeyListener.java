package Listeners;

import GUI.Gui;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class HexKeyListener implements KeyListener {
    private static HexKeyListener hexKeyListener;

    @Override //These arent needed they just kinda need to be here
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        Gui gui = Gui.getInstance();
        char c = e.getKeyChar();
        System.out.println("Keychar " + c);
        System.out.println("Entire hex " + gui.getJhexNumber());
        System.out.println("Hex has been pressed");
        gui.updateFromHex(c);
    }

    public void keyTyped(KeyEvent e) {

    }


    public static HexKeyListener getInstance() {
        if (hexKeyListener == null) {
            hexKeyListener = new HexKeyListener();

        }
        return hexKeyListener;
    }
}
