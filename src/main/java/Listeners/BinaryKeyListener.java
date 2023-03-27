package Listeners;
import GUI.Gui;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class BinaryKeyListener implements KeyListener {
    Gui gui = Gui.getInstance();
    private static BinaryKeyListener binaryKeyListener;

    public void keyTyped(KeyEvent e) {
        System.out.println("Binary has been pressed");
        gui.updateFromBinary(e.getKeyChar());
    }

    @Override //These arent needed they just kinda need to be here
    public void keyPressed(KeyEvent e) {
    }
    @Override
    public void keyReleased(KeyEvent e) {
    }


    public static BinaryKeyListener getInstance() {
        if (binaryKeyListener == null) {
            binaryKeyListener = new BinaryKeyListener();

        }
        return binaryKeyListener;
    }
}
