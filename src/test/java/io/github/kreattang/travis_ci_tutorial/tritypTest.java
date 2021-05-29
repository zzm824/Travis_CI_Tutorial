package io.github.kreattang.travis_ci_tutorial;

import org.junit.*;

import static org.junit.Assert.assertEquals;

public class tritypTest {
    trityp tri=new trityp();

    //    Triang = 1 if triangle is scalene
    //    Triang = 2 if triangle is isosceles
    //    Triang = 3 if triangle is equilaterala
    //    Triang = 4 if not a triangle

    @Test
    public void testTriang_4(){
        assertEquals(1,tri.Triang(3,6,5));
        assertEquals(2,tri.Triang(5,5,7));
        assertEquals(3,tri.Triang(5,5,5));
        assertEquals(4,tri.Triang(1,2,7));
        assertEquals(4,tri.Triang(2,2,4));
        assertEquals(4,tri.Triang(2,2,5));

    }
}