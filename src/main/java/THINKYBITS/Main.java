package THINKYBITS;
import GUI.Gui;

public class Main {
    public static void main(String[] args) {
        run();
    }

    public static synchronized void run(){
        Gui gui = Gui.getInstance();
        gui.gui();
    }

}
