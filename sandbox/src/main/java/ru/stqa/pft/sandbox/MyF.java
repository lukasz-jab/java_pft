package ru.stqa.pft.sandbox;

public class MyF {
    public static void main(String[] args) {

        Square k = new Square(3);
        System.out.println("Pole kwadratu o boku " + k.l + " = " + k.area());

        Rectangle p = new Rectangle(Math.random(), Math.random());
        System.out.println("Pole prostokąta o boku " + p.a + " na " + p.b + " = " + p.area());

        Point p1=new Point(5,5);
        Point p2=new Point(4,4);
        System.out.println("Distance between "+p1.x+","+p1.y+" and "+p2.x+","+p2.y+" = "+distance(p1,p2));


    }

    public static double distance(Point p1, Point p2){
        //A=(x1, y1),B=(x2, y2)   distance=|AB|=sqrt((x2−x1)(x2-x1)+(y2−y1)(y2-y1))

        return Math.sqrt((p1.x-p2.x)*(p1.x-p2.x)+(p1.y-p2.y)*(p1.y-p2.y));

    }


}