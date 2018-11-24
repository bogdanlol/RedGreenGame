package proiectparcredgreen;

import java.awt.Color;
import java.awt.Frame;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;

class Square extends JPanel {
    JLabel label = new JLabel((Icon)null);

    public Square() {
        setBackground(Color.black);
        add(label);
    }

    public void setIcon(Icon icon) {
        label.setIcon(icon);
    }
}