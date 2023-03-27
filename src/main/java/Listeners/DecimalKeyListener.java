package Listeners;
import GUI.Gui;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class DecimalKeyListener implements KeyListener {
    Gui gui = Gui.getInstance();
    private static DecimalKeyListener decimalKeyListener;

    public void keyTyped(KeyEvent e) {
    }

    @Override //These arent needed they just kinda need to be here
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("Decimal has been pressed");
        gui.updateFromDecimal(e.getKeyChar());
    }


    public static DecimalKeyListener getInstance() {
        if (decimalKeyListener == null) {
            decimalKeyListener = new DecimalKeyListener();

        }
        return decimalKeyListener;
    }
}
