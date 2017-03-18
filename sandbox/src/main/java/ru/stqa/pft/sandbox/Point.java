package ru.stqa.pft.sandbox;

/**
 * Created by luk on 2017-03-18.
 */
public class Point {
    double x;
    double y;
    Point(int x,int y){
        this.x=x;
        this.y=y;
    }
    public static double distance(Point p1, Point p2){
        //A=(x1, y1),B=(x2, y2)   distance=|AB|=sqrt((x2−x1)(x2-x1)+(y2−y1)(y2-y1))

        return Math.sqrt((p2.x-p1.x)*(p2.x-p1.x)+(p2.y-p1.y)*(p2.y-p1.y));

    }
public static void main(String[] arg){
        Point p1=new Point(5,5);
        Point p2= new Point (4,4);
    System.out.println("Distance between "+p1.x+","+p1.y+" and "+p2.x+","+p2.y+" = "+distance(p1,p2));

}

}
