import java.awt.Graphics;
import java.awt.Color;
import java.awt.Canvas;
import java.awt.image.BufferStrategy;

public class Bar implements Comparable{
    private int x, y, width, length;

    public Bar(int xPos, int yPos, int w, int l){
        x=xPos;
        y=yPos;
        width=w;
        length=l;
    }
    public Bar(){
        
    }

    public void render(Graphics g){
        
        g.clearRect(x, 50, width, 510);
        g.fillRect(x, y, width, length);
        
    }

    public void render (int xPos, Graphics g){
        g.clearRect(xPos, 50, width, 510);
        g.fillRect(xPos, 510-length, width, length);
    }

    public int getLength(){
        return length;
    }

    public void setLength(int l){
        length = l;
        y = 510-l;
    }
    public int compareTo(Object bar){
        return length - ((Bar)bar).getLength();
    }

    public void swap(Bar b){
        int temp =length;
        length = b.getLength();
        b.setLength(temp);
        return;
    }
}
