package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by luk on 2017-03-26.
 */


public class SquareTest {

    @Test
    public void testArea(){
        Square s = new Square(5);
        s.area();
        Assert.assertEquals(s.area(),25.0);

    }




}
