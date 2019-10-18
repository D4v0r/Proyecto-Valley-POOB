package shapes;

import java.awt.*;
import java.awt.geom.*;

/**
 * A circle that can be manipulated and that draws itself on a canvas.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 1.0.  (15 July 2000) 
 */

public class Circle extends Shape{

    public static double PI=3.1416;
    
    private int diameter;
    private double xPosition;
    private double yPosition;
    
    /**
     * Create a new circle at default position with default color.
     */
    public Circle(){
        super();
        diameter = 30;
        xPosition = 0;
        yPosition = 0;
    }
    
    public void setXposition(double x){
        xPosition=x;
    }
    
    public void setYPosition(double y){
        yPosition=y;
    }
    
    /**
     * Make this circle visible. If it was already visible, do nothing.
     */
    public void makeVisible(){
        super.makeVisible();
    }
    
    /**
     * Make this circle invisible. If it was already invisible, do nothing.
     */
    public void makeInvisible(){
        super.makeInvisible();
    }
    
    /**
     * Move the circle a few pixels to the right.
     */
    public void moveRight(){
        super.moveRight();
    }

    /**
     * Move the circle a few pixels to the left.
     */
    public void moveLeft(){
        super.moveLeft();
    }

    /**
     * Move the circle a few pixels up.
     */
    public void moveUp(){
        super.moveUp();
    }

    /**
     * Move the circle a few pixels down.
     */
    public void moveDown(){
        super.moveDown();
    }

    /**
     * Move the circle horizontally.
     * @param distance the desired distance in pixels
     */
    public void moveHorizontal(double distance){
        erase();
        xPosition += (int) distance;
        draw();
    }

    /**
     * Move the circle vertically.
     * @param distance the desired distance in pixels
     */
    public void moveVertical(double distance){
        erase();
        yPosition += (int) distance;
        draw();
    }

    /**
     * Slowly move the circle horizontally.
     * @param distance the desired distance in pixels
     */
    public void slowMoveHorizontal(int distance){
        int delta;

        if(distance < 0) {
            delta = -1;
            distance = -distance;
        } else {
            delta = 1;
        }

        for(int i = 0; i < distance; i++){
            xPosition += delta;
            draw();
        }
    }

    /**
     * Slowly move the circle vertically
     * @param distance the desired distance in pixels
     */
    public void slowMoveVertical(int distance){
        int delta;

        if(distance < 0) {
            delta = -1;
            distance = -distance;
        }else {
            delta = 1;
        }

        for(int i = 0; i < distance; i++){
            yPosition += delta;
            draw();
        }
    }

    /**
     * Change the size.
     * @param newDiameter the new size (in pixels). Size must be >=0.
     */
    public void changeSize(int newDiameter){
        erase();
        diameter = newDiameter;
        draw();
    }

    /**
     * Change the color. 
     * @param color the new color. Valid colors are "red", "yellow", "blue", "green",
     * "magenta" and "black".
     */
    public void changeColor(String newColor){
        super.changeColor(newColor);
    }

    /*
     * Draw the circle with current specifications on screen.
     */
    protected void draw(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.draw(this, color, 
                new Ellipse2D.Double(xPosition, yPosition, 
                diameter, diameter));
            canvas.wait(10);
        }
    }

    /*
     * Erase the circle on screen.
     */
    protected void erase(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.erase(this);
        }
    }

}
