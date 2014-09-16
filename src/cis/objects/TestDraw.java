package cis.objects;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class TestDraw extends Applet
   implements MouseListener, MouseMotionListener {

   int width, height;
   int mx, my;  // the mouse coordinates
   boolean isButtonPressed = false;

   public void init() {
      width = getSize().width;
      height = getSize().height;
      setBackground( Color.black );

      mx = width/2;
      my = height/2;

      addMouseListener( this );
      addMouseMotionListener( this );
   }

   public void mouseEntered( MouseEvent e ) {
      // called when the pointer enters the applet's rectangular area
   }
   public void mouseExited( MouseEvent e ) {
      // called when the pointer leaves the applet's rectangular area
   }
   public void mouseClicked( MouseEvent e ) {
      // called after a press and release of a mouse button
      // with no motion in between
      // (If the user presses, drags, and then releases, there will be
      // no click event generated.)
   }
   public void mousePressed( MouseEvent e ) {  // called after a button is pressed down
      isButtonPressed = true;
      setBackground( Color.gray );
      repaint();
      // "Consume" the event so it won't be processed in the
      // default manner by the source which generated it.
      e.consume();
   }
   public void mouseReleased( MouseEvent e ) {  // called after a button is released
      isButtonPressed = false;
      setBackground( Color.black );
      repaint();
      e.consume();
   }
   public void mouseMoved( MouseEvent e ) {  // called during motion when no buttons are down
      mx = e.getX();
      my = e.getY();
      showStatus( "Mouse at (" + mx + "," + my + ")" );
      repaint();
      e.consume();
   }
   public void mouseDragged( MouseEvent e ) {  // called during motion with buttons down
      mx = e.getX();
      my = e.getY();
      showStatus( "Mouse at (" + mx + "," + my + ")" );
      repaint();
      e.consume();
   }

   public void paint( Graphics g ) {
      if ( isButtonPressed ) {
         g.setColor( Color.black );
      }
      else {
         g.setColor( Color.gray );
      }
      g.fillRect( mx-20, my-20, 40, 40 );
   }
}