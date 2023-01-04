import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferStrategy;
import java.util.Scanner;

/*
 * Change to render off of Bar rather than bar.length
 * then merge should be good
 */
public class GUI extends Canvas {
    Bar b[] = new Bar[100];
    int WIDTH = 1000;
    int HEIGHT = 600;
    long t = System.currentTimeMillis();
    BufferStrategy bs;
    Graphics g;
    public GUI(int alg){
        Window w = new Window(1000, 600, this);
        // init bars
        while(!this.hasFocus()){

        }
        for(int i =0; i <100; i++){
            int r =(int)(Math.random()*400);
            b[i] = new Bar(i*10, 510-r, 8, r);
        }
        
        bs = this.getBufferStrategy();
        if(bs==null){
            this.createBufferStrategy(2);
            bs = this.getBufferStrategy();
        }
        g = bs.getDrawGraphics();

            render(); // render the bars fully randomized
            run(alg); // allow user to select sorting algorithm and run it
    }
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int alg = -1;
        while(!(alg == 0 || alg ==1 || alg ==2)){
            System.out.print("Enter 0 for bubble sort, 1 for selection sort,\nor 2 for merge sort: ");
            alg = Integer.parseInt(scanner.nextLine());
        }
        scanner.close();
        System.out.println(alg);
        new GUI(alg);
    }

    void render(){
        bs = this.getBufferStrategy();
        if(bs==null){
            this.createBufferStrategy(2);
            bs = this.getBufferStrategy();
        }
        g = bs.getDrawGraphics();

        g.setColor(Color.BLACK);
        g.clearRect(0, 0, WIDTH, HEIGHT);
        //g.drawString("Time Passed: " + (System.currentTimeMillis()-t)+ "ms", 10, 10);
        for(int i=0; i<b.length;i++){
            b[i].render(g);
            //g.fillRect(i*10, 510-b[i].getLength(), 8, b[i].getLength());
        }
        g.dispose();
        bs.show();
    }

    void run(int alg){
        // allow user to select sorting algorithm, add mouse pointer intg. later
        if(alg==1)
            selectionSort(b);
        else if(alg==0)
            bubbleSort(b);
        else if(alg==2){
            render();
            b = mergeSort(b);
        }
        // sorting algos working... still have to render mergeSort, probably use Bar.render()... move away from swapping length
    }

    void selectionSort(Bar[] a){
        for(int i=0; i<a.length;i++){
            int minIndex = findMinimum(a, i);
            if(minIndex != i){
                int temp = a[i].getLength();
                a[i].setLength(a[minIndex].getLength());
                a[minIndex].setLength(temp);
                // after 2 locations are swapped, re-render
                
                render();
            }
        }
    }

    int findMinimum(Bar[] a, int first){
        int minIndex = first;;
        for(int i = first+1; i<a.length; i++){
            if(a[i].compareTo(a[minIndex]) < 0){
                minIndex=i;
            }
        }
        return minIndex;
    }

    Bar[] bubbleSort(Bar[] a){
        t=System.currentTimeMillis();

        bs = this.getBufferStrategy();
        if(bs==null){
            this.createBufferStrategy(2);
            bs = this.getBufferStrategy();
        }
        
        g = bs.getDrawGraphics();

        for(int i=0;i<a.length;i++){
            for(int j=i; j<a.length;j++){
                if(a[i].compareTo(a[j]) > 0){
                    
                    int temp = a[i].getLength();
                    a[i].setLength(a[j].getLength());
                    a[i].render(g);
                    a[j].setLength(temp);
                    a[j].render(g);
                    g.clearRect(0, 0, 300, 100);
                    g.drawString("Time Passed: " + (System.currentTimeMillis()-t)+ "ms", 10, 10);
                    g.dispose(); 
                    g = bs.getDrawGraphics();   
                    bs.show();
                }
            }
        }
        //g.drawString("Time Passed: " + (System.currentTimeMillis()-t)+ "ms", 10, 10);

        g.dispose();
        bs.show();

        return(a);
    }

    Bar[] mergeSort(Bar[] a){
        

        if(a.length<=1)
            return a;
        Bar l[] = new Bar[1];
        Bar r[] = new Bar[a.length-1];
        l[0]=a[0];
        for(int i =1; i<a.length;i++){
            r[i-1]=a[i];
        }
        return merge(mergeSort(l),(mergeSort(r)));
    }

    Bar[] merge(Bar[] lBars, Bar[] rBars){
        Bar[] mrg = new Bar[lBars.length+rBars.length];
        int i=0, j =0;

        bs = this.getBufferStrategy();
        if(bs==null){
            this.createBufferStrategy(2);
            bs = this.getBufferStrategy();
        }
        g = bs.getDrawGraphics();
        

        while((i<lBars.length) && (j<rBars.length)){
            if(lBars[i].compareTo(rBars[j]) < 0){
                mrg[i+j] = lBars[i];
                //g.fillRect((i+j)*10, 510-mrg[i+j].getLength(), 8, mrg[i+j].getLength());
                mrg[i+j].render(((i+j)*10), g);
                i++;
            }
            else{
                mrg[i+j] = rBars[j];
                //g.fillRect((i+j)*10, 510-mrg[i+j].getLength(), 8, mrg[i+j].getLength());
                mrg[i+j].render(((i+j)*10), g);
                j++;
            }
            g.clearRect(0, 0, 300, 100);
            g.drawString("Time Passed: " + (System.currentTimeMillis()-t)+ "ms", 10, 10);
        }
        while(i<lBars.length){
            mrg[i+j]=lBars[i];
            //g.fillRect((i+j)*10, 510-mrg[i+j].getLength(), 8, mrg[i+j].getLength());
            mrg[i+j].render(((i+j)*10), g);
            g.clearRect(0, 0, 300, 100);
            g.drawString("Time Passed: " + (System.currentTimeMillis()-t)+ "ms", 10, 10);
            i++;
        }
        while(j<rBars.length){
            mrg[i+j]=rBars[j];
            //g.fillRect((i+j)*10, 510-mrg[i+j].getLength(), 8, mrg[i+j].getLength());
            mrg[i+j].render(((i+j)*10), g);
            g.clearRect(0, 0, 300, 100);
            g.drawString("Time Passed: " + (System.currentTimeMillis()-t)+ "ms", 10, 10);
            j++;
        }

        g.dispose();
        bs.show();
        return mrg;
    }

}
