package GUI;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class CustomKeyListener implements KeyListener {

        public void keyTyped(KeyEvent e){
            System.out.println("key has been pressed");
        }
        @Override //These arent needed they just kinda need to be here
        public void keyPressed(KeyEvent e) {
        }
        @Override
        public void keyReleased(KeyEvent e) {
        }
    private static CustomKeyListener customKeyListener;
        //Constructor


    public static CustomKeyListener getInstance(){
        if (customKeyListener == null){
            customKeyListener = new CustomKeyListener();
        }
        return customKeyListener;
    }
}


