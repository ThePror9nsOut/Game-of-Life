package GOF;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Handler extends JPanel implements MouseListener, ActionListener, KeyListener
{
    int[] mousePosition;
    boolean play = false;
    public static int border = 1000-10;
    public static int floor = 800-10;
    public static Set<Particle> particles = new HashSet<>();

    Handler()
    {
        Timer updater = new Timer(30,this);
        updater.start();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        repaint();
    }

    public void paint(Graphics g)
    {
        super.paint(g);
        setBackground(Color.black);
        g.setColor(Color.white);

        mousePosition = new int[] {MouseInfo.getPointerInfo().getLocation().x-this.getLocationOnScreen().x,
                MouseInfo.getPointerInfo().getLocation().y-this.getLocationOnScreen().y};
        g.drawRect(mousePosition[0]-mousePosition[0]%10,mousePosition[1]-mousePosition[1]%10,10,10);

        if(play)
        {
            Set<Particle> toAdd = new HashSet<>();
            Set<Particle> toRemove = new HashSet<>();
            for(int y = 0; y < floor; y+=10)
            {
                for(int x = 0; x < border; x+=10)
                {
                    int neighbours = 0;

                    if(particles.contains(new Particle(x-10,y-10))) {neighbours++;}
                    if(particles.contains(new Particle(x,y-10))) {neighbours++;}
                    if(particles.contains(new Particle(x+10,y-10))) {neighbours++;}
                    if(particles.contains(new Particle(x+10,y))) {neighbours++;}
                    if(particles.contains(new Particle(x+10,y+10))) {neighbours++;}
                    if(particles.contains(new Particle(x,y+10))) {neighbours++;}
                    if(particles.contains(new Particle(x-10,y+10))) {neighbours++;}
                    if(particles.contains(new Particle(x-10,y))) {neighbours++;}

                    if(neighbours == 3) {toAdd.add(new Particle(x,y));}
                    if(neighbours < 2 || neighbours > 3) {toRemove.add(new Particle(x,y));}
                }
            }

            particles.addAll(toAdd);
            particles.removeAll(toRemove);
        }

        for(Particle particle : particles)
        {
            particle.draw(g);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
        mousePosition = new int[] {MouseInfo.getPointerInfo().getLocation().x-this.getLocationOnScreen().x,
                MouseInfo.getPointerInfo().getLocation().y-this.getLocationOnScreen().y};
        int[] targetPos =  new int[] {mousePosition[0]-mousePosition[0]%10,mousePosition[1]-mousePosition[1]%10};

        if(e.getButton() == 1)
        {
            particles.add(new Particle(targetPos[0], targetPos[1]));
        }
        else if(e.getButton() == 3)
        {
            particles.remove(new Particle(targetPos[0], targetPos[1]));
        }
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        if(e.getKeyCode() == KeyEvent.VK_SPACE)
        {
            play = !play;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}
