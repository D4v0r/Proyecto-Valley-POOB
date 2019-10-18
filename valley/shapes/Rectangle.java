package shapes;
import java.awt.*;
import java.lang.*;

/**
 * A rectangle that can be manipulated and that draws itself on a canvas.
 * 
 * @author  Michael Kolling and David J. Barnes (Modified)
 * @version 1.0  (15 July 2000)()
 */


 
public class Rectangle extends Shape{

    private int height;
    private int width;
    private double xPosition;
    private double yPosition;

    

    /**
     * Create a new rectangle at default position with default color.
     */
    public Rectangle(){
        super();
        height = 10;
        width = 50;
        xPosition = 1;
        yPosition = 1;
    }
 
    
    public int getWidth(){
        return width;
    }    
    
    public void setXposition(double x){
        xPosition=x;
    }
    
    public void setYposition(double y){
        yPosition=y;
    }
    
    public double getPositionX(){
        return xPosition;
    }
    
    public double getPositionY(){
        return yPosition;
    }
    
    /**
     * Make this rectangle visible. If it was already visible, do nothing.
     */
    public void makeVisible(){
        super.makeVisible();
    }
    
    /**
     * Make this rectangle invisible. If it was already invisible, do nothing.
     */
    public void makeInvisible(){
        super.makeInvisible();
    }
    
    /**
     * Move the rectangle a few pixels to the right.
     */
    public void moveRight(){
        super.moveRight();
    }

    /**
     * Move the rectangle a few pixels to the left.
     */
    public void moveLeft(){
        super.moveLeft();
    }

    /**
     * Move the rectangle a few pixels up.
     */
    public void moveUp(){
        super.moveUp();
    }

    /**
     * Move the rectangle a few pixels down.
     */
    public void moveDown(){
        super.moveDown();
    }

    /**
     * Move the rectangle horizontally.
     * @param distance the desired distance in pixels
     */
    public void moveHorizontal(double distance){
        erase();
        xPosition += distance;
        draw();
    }

    /**
     * Move the rectangle vertically.
     * @param distance the desired distance in pixels
     */
    public void moveVertical(double distance){
        erase();
        yPosition += distance;
        draw();
    }

    /**
     * Slowly move the rectangle horizontally.
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
     * Slowly move the rectangle vertically.
     * @param distance the desired distance in pixels
     */
    public void slowMoveVertical(int distance){
        int delta;

        if(distance < 0) {
            delta = -1;
            distance = -distance;
        } else {
            delta = 1;
        }

        for(int i = 0; i < distance; i++){
            yPosition += delta;
            draw();
        }
    }

    /**
     * Change the size to the new size
     * @param newHeight the new height in pixels. newHeight must be >=0.
     * @param newWidht the new width in pixels. newWidth must be >=0.
     */
    public void changeSize(int newHeight, int newWidth) {
        erase();
        height = newHeight;
        width = newWidth;
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
    
    // public void randomColor(){
        // String colors[]={"red","yellow","blue","black","brown","magenta","grey","fucsia"};
        // int indice = (int) Math.floor(Math.random() * colors.length);
        // color = colors[indice];
        // draw();
        
    // }

    /*
     * Draw the rectangle with current specifications on screen.
     */

    protected void draw() {
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.draw(this, color,
                new java.awt.Rectangle((int) xPosition,(int) yPosition, width, height));
            canvas.wait(10);
        }
    }

    /*
     * Erase the rectangle on screen.
     */
    protected void erase(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.erase(this);
        }
    }
}

