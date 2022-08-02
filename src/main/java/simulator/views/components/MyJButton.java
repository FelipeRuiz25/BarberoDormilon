package simulator.views.components;

import javax.swing.*;
import java.awt.event.ActionListener;

public class MyJButton extends JButton {

    public MyJButton(ActionListener listener, String actionCommand, String text) {
        super(text);
        this.addActionListener(listener);
        this.setActionCommand(actionCommand);
    }

}
