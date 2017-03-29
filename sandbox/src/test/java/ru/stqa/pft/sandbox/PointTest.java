package ru.stqa.pft.sandbox;


import org.testng.Assert;
import org.testng.annotations.Test;

import static ru.stqa.pft.sandbox.Point.distance;

/**
 * Created by luk on 2017-03-29.
 */
public class PointTest {

Point p1=new Point(5,5);
Point p2=new Point(4,4);
Point p3=new Point(1,1);
Point p4=new Point(1,1);

@Test
    public void testPoint(){

    Assert.assertTrue(distance(p1,p2)==1.4142135623730951);
    Assert.assertTrue(distance(p3,p4)==0);
}


}
