package Listeners;

import GUI.Gui;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class HexKeyListener implements KeyListener {
    private static HexKeyListener hexKeyListener;
    Gui gui = Gui.getInstance();

    @Override //These arent needed they just kinda need to be here
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        gui.updateFromHex();
    }

    public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() == 9){
            e.getModifiersEx();
            Gui.jBinaryNumber.grabFocus();
            if (gui.getJHexNumber().length() == 0){
                gui.setJHexNumber("");
            }
        }
        gui.updateFromHex();
    }


    public static HexKeyListener getInstance() {
        if (hexKeyListener == null) {
            hexKeyListener = new HexKeyListener();

        }
        return hexKeyListener;
    }
}
