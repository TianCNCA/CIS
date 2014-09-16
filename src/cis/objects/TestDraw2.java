package cis.objects;

import java.awt.Canvas;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

  public class TestDraw2 {
  Frame f = new Frame();

public void disp() {
    f.setBounds(100, 100, 200, 200);
    MosL ml = new MosL();
    Can c = new Can();
    f.add(c);
    c.addMouseListener(ml);
    c.addMouseMotionListener(ml);
    f.setVisible(true);
}

public static void main(String[] args) {
	TestDraw2 d = new TestDraw2();
    d.disp();
}

public class MosL extends MouseAdapter {
    int sx = 0;
    int sy = 0;
    boolean onDrag = false;

    @Override
    public void mouseDragged(MouseEvent e) {
        if (onDrag) {
            int x = e.getX();
            int y = e.getY();

            Canvas comp = (Canvas) e.getSource();
            Graphics g = comp.getGraphics();
                            // comp.repaint(); << for cleaning up the intermediate lines : doesnt work :(
            g.drawLine(sx, sy, x, y);
            return;
        }
        onDrag = true;
        sx = e.getX();
        sy = e.getY();

        System.out.println("Draggg");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("Pressed");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("Released");
        if (onDrag)
            onDrag = false;
    }
}

public class Can extends Canvas {
    @Override
    public void paint(Graphics g) {

    }
}
}