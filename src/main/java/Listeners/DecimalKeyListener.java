package Listeners;
import GUI.Gui;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class DecimalKeyListener implements KeyListener {
    Gui gui = Gui.getInstance();
    private static DecimalKeyListener decimalKeyListener;

    public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() == 9){
            e.getModifiersEx();
            Gui.jHexNumber.grabFocus();
            if (gui.getJDecimalNumber().length() == 0){
                gui.setJDecimalNumber("");
            }
        }
        gui.updateFromDecimal();
    }

    @Override //These arent needed they just kinda need to be here
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        gui.updateFromDecimal();
    }

    public static DecimalKeyListener getInstance() {
        if (decimalKeyListener == null) {
            decimalKeyListener = new DecimalKeyListener();
        }
        return decimalKeyListener;
    }
}
