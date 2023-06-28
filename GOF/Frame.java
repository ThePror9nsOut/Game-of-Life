package GOF;

import javax.swing.*;

public class Frame extends JFrame
{
    public Frame()
    {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000,800);
        setResizable(false);

        Handler handler = new Handler();
        add(handler);
        addMouseListener(handler);
        addKeyListener(handler);
        setVisible(true);
    }
}
