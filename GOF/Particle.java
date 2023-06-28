package GOF;

import java.awt.*;

public class Particle
{
    int x;
    int y;

    public Particle(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    void draw(Graphics g)
    {
        g.fillRect(x,y,10,10);
    }

    @Override
    public int hashCode()
    {
        return x+y;
    }

    @Override
    public boolean equals(Object obj)
    {
        return ((Particle) obj).x == x && ((Particle) obj).y == y;
    }
}
