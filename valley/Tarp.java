import java.lang.*;
import shapes.*;
import java.util.*;
import java.awt.geom.*;
import javax.swing.JOptionPane;
import java.awt.Toolkit;
/**
 * Write a description of class Tarp here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tarp implements Comparable <Tarp>
{
    private int[] lowerEnd;
    private int[] higherEnd;
    private ArrayList<Puncture> punctures;
    private boolean isVisible;
    private String color;
    private Line line;
    private Valley valley;
    

    /**
     * Constructor for objects of class Tarp
     */
    public Tarp(int[] lowerEnd, int[] higherEnd){
        isVisible=false;
        color="black";
        this.lowerEnd = lowerEnd;
        this.higherEnd = higherEnd;
        punctures= new ArrayList<>();
        valley = Valley.getValley();
        int limitHeight = valley.getHeight();
        double [] point1 = { (double) lowerEnd[0], (double) (limitHeight - lowerEnd[1])};
        double [] point2 = { (double) higherEnd[0], (double) (limitHeight - higherEnd[1])};
        line = new Line(point1, point2);
        line.changeColor(color);
        belongingTo(this.lowerEnd[0], this.higherEnd[0]);
    }
    
    public void belongingTo(int x1, int x2){
        ArrayList<Vineyard> vineyards = valley.getVineyards();
        for(Vineyard v: vineyards){
            int xi = v.getPosition();
            int xf = v.getPosition() + v.getWidth();
            if ((xi == x1 && xf == x2) || (xi == x2 && xf == x1)){
                String newColor = v.getColor();
                changeColor(newColor);
            }
        }
    } 
    
    public int getHeightValley(){
        return valley.getHeight();
    }
    
    /**
     * retorna las posiciones iniciales 
     */
    public int[] getLowerEnd(){
        return lowerEnd;
    }
    
    /**
     * retorna las posiciones finales
     */
    public int[] getHigherEnd(){
        return higherEnd;
    }
    
    /**
     * retorna los huecos que tenga
     */
    public ArrayList<Puncture> getPunctures(){
        return punctures;
    }
    
    public void invisiblePuncture(){
        punctures.get(0).makeInvisible();
    }
    
    public void clearPunctures(){
        punctures.clear();
    }
    
    public void addPunctures(Puncture p){
        punctures.add(p);
    }
    
    public boolean getisVisible(){
        return isVisible;
    }
    
    public Line getLine(){
        return line;
    }
    
    /**
     * verifica si colisiono con un hueco
     */
    public boolean collisionPuncture(int x){
        boolean rta=false;
        for (Puncture p: punctures){
            if (x==p.getXPosition()-1 || x==p.getXPosition()+1){
                rta=true;
            }
        }
        return rta;
    }
    
    /**
     * Agrega un hueco en una lona
     * @param tarp es la ubicacion de la lona en la lista, x la posicion de la misma
     */
    public void makePuncture(int x){
        double y = rectFunction((double) x);
        double limitHeight = (double) valley.getHeight();
        Puncture p = new Puncture(x , limitHeight - y);
        punctures.add(p);
        p.open();
        if(isVisible){
            p.makeVisible();
        }
    }
    
    /**
     * tapa un hueco 
     * @param int x la posicion a tapar
     */
    public void patchPuncture(int x){
        if(punctures.size() > 0){
            int position = 0;
            for (int i = 0; i < punctures.size(); i++){
                if(punctures.get(i).getXPosition() == (double) x ){
                    position = i;
                }
            }
            if(position == -1){
                if(isVisible){
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(null, "Ese Hueco no existe");
                }
            } else {
                punctures.get(position).close();
                punctures.remove(position);
            }
        }
    }
    
    /**
     * calcula el valor Y de la ecuacion de la lona dado un punto x
     * @param double x, el valor en x
     */
    public double rectFunction(double x){
        double[] point = {(double) lowerEnd[0], (double) higherEnd[1]};
        double m = (double) (higherEnd[1]-lowerEnd[1])/ (higherEnd[0]-lowerEnd[0]);
        double b = lowerEnd[1]-(m*lowerEnd[0]);
        return ( (m * x) + b);
    }
    
    /**
     * borra una lona
     */
    public void remove(){
        punctures = null;
        line = null;        
        makeInvisible();
    }
    
    /**
     * hace visible la lona si es posible
     */
    public void makeVisible(){
        isVisible=true;
        for(Puncture p: punctures){
                p.makeVisible();
        }
        line.makeVisible();
    }
    
    /**
     * hace invisible la lona si es posible
     */
    public void makeInvisible(){
        isVisible=false;
        line.makeInvisible();
        for(Puncture p: punctures){
                p.makeInvisible();
        }
    }
    
    /**
     * cambia el color de la lona
     * @param String color, el color a cambiar
     */
    public void changeColor(String color){
        this.color = color;
        line.changeColor(color);
    }
    
    @Override 
    public int compareTo(Tarp otherTarp){
        int x1 = otherTarp.getLowerEnd()[0];
        int x2 = otherTarp.getHigherEnd()[0];
        if (x1 < lowerEnd[0] || x2 < higherEnd[0]){
            return 0;
        }else if(x1 == lowerEnd[0] && x2 == higherEnd[0]){
            return 1;
        }else {
            return -1;
        }
    }

}
