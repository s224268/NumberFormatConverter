package Listeners;
import GUI.Gui;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class BinaryKeyListener implements KeyListener {
    Gui gui = Gui.getInstance();
    private static BinaryKeyListener binaryKeyListener;

    public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() == 9){
            e.getModifiersEx();
            Gui.jDecimalNumber.grabFocus();
            if (gui.getJBinaryNumber().length() == 0){
                gui.setJBinaryNumber("");
            }
        }
        gui.updateFromBinary();
    }

    @Override //These arent needed they just kinda need to be here
    public void keyPressed(KeyEvent e) {
    }
    @Override
    public void keyReleased(KeyEvent e) {
        gui.updateFromBinary();
    }

    public static BinaryKeyListener getInstance() {
        if (binaryKeyListener == null) {
            binaryKeyListener = new BinaryKeyListener();

        }
        return binaryKeyListener;
    }
}
