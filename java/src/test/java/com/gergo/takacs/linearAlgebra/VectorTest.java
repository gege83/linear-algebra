package com.gergo.takacs.linearAlgebra;

import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class VectorTest {
    @Test(expected = EmptyCollectionNotPermitted.class)
    public void testConstructor() throws Exception {
        //given
        //when
        new Vector(Collections.emptyList());
        //then
    }

    @Test
    public void testAdd() throws Exception {
        //given
        Vector expected = new Vector(2d, 3d, 4d);
        Vector vector1 = new Vector(1d, 2d, 3d);
        Vector vector2 = new Vector(1d, 1d, 1d);
        //when
        Vector actual = vector1.add(vector2);
        //then
        assertEquals(expected, actual);
    }

    @Test
    public void testSub() throws Exception {
        //given
        Vector expected = new Vector(2d, 3d, 4d);
        Vector vector1 = new Vector(3d, 4d, 5d);
        Vector vector2 = new Vector(1d, 1d, 1d);
        //when
        Vector actual = vector1.sub(vector2);
        //then
        assertEquals(expected, actual);
    }

    @Test
    public void testScalarProduct() throws Exception {
        //given
        Vector expected = new Vector(2d, 3d, 4d);
        Vector vector = new Vector(1d, 1.5, 2d);
        //when
        Vector actual = vector.scalarProduct(2);
        //then
        assertEquals(expected, actual);
    }

    @Test
    public void testDotProduct() throws Exception {
        //given
        double expected = 18;
        Vector vector1 = new Vector(2d, 3d);
        Vector vector2 = new Vector(3d, 4d);
        //when
        double actual = vector1.dotProduct(vector2);
        //then
        assertEquals(expected, actual, 10e-10);
    }

    @Test
    public void testMagnitude() throws Exception {
        //given
        double expected = 5;
        Vector vector = new Vector(3d, 4d);
        //when
        double actual = vector.magnitude();
        //then
        assertEquals(expected, actual, 10e-10);
    }

    @Test
    public void testNormalize() throws Exception {
        //given
        Vector expected = new Vector(3 / 5., 4 / 5.);
        Vector vector = new Vector(3d, 4d);
        //when
        Vector actual = vector.normalize();
        //then
        assertEquals(expected, actual);
    }

    @Test
    public void testAngle() throws Exception {
        //given
        double expected = Math.PI / 2;
        Vector vector1 = new Vector(3d, 4d);
        Vector vector2 = new Vector(4d, -3d);
        //when
        double actual = vector1.angle(vector2);
        //then
        assertEquals(expected, actual, 10e-10);
    }

    @Test
    public void testAngleDeg() throws Exception {
        //given
        double expected = 90;
        Vector vector1 = new Vector(3d, 4d);
        Vector vector2 = new Vector(4d, -3d);
        //when
        double actual = vector1.angleDeg(vector2);
        //then
        assertEquals(expected, actual, 10e-10);
    }

    @Test
    public void testIsOrthogonal() throws Exception {
        //given
        Vector vector1 = new Vector(3d, 4d);
        Vector vector2 = new Vector(4d, -3d);
        //when
        boolean actual = vector1.isOrthogonal(vector2);
        //then
        assertTrue(actual);
    }

    @Test
    public void testIsParallelIfReverse() throws Exception {
        //given
        Vector vector1 = new Vector(3d, 4d);
        Vector vector2 = new Vector(-3d, -4d);
        //when
        boolean actual = vector1.isParallel(vector2);
        //then
        assertTrue(actual);
    }

    @Test
    public void testIsParallelIfSameDir() throws Exception {
        //given
        Vector vector1 = new Vector(3d, 4d);
        Vector vector2 = new Vector(6d, 8d);
        //when
        boolean actual = vector1.isParallel(vector2);
        //then
        assertTrue(actual);
    }

    @Test
    public void testProjectTo() throws Exception {
        //given
        Vector expected = new Vector(2, 0d);
        Vector vector1 = new Vector(2d, 20d);
        Vector vector2 = new Vector(3d, 0d);
        //when
        Vector actual = vector1.projectTo(vector2);
        //then
        assertEquals(expected, actual);
    }

    @Test
    public void testProjectToOrthogonal() throws Exception {
        //given
        Vector expected = new Vector(0, 20d);
        Vector vector1 = new Vector(2d, 20d);
        Vector vector2 = new Vector(3d, 0d);
        //when
        Vector actual = vector1.projectToOrthogonal(vector2);
        //then
        assertEquals(expected, actual);
    }

    @Test
    public void testCrossProductIf3D() throws Exception {
        //given
        Vector expected = new Vector(0, 0d, 20d);
        Vector vector1 = new Vector(5d, 0d, 0d);
        Vector vector2 = new Vector(0, 4d, 0d);
        //when
        Vector actual = vector1.crossProduct(vector2);
        //then
        assertEquals(expected, actual);
    }

    @Test
    public void testCrossProductIf2D() throws Exception {
        //given
        Vector expected = new Vector(0, 0d, 20d);
        Vector vector1 = new Vector(5d, 0d);
        Vector vector2 = new Vector(0, 4d);
        //when
        Vector actual = vector1.crossProduct(vector2);
        //then
        assertEquals(expected, actual);
    }

    @Test
    public void testParallelogramArea() throws Exception {
        //given
        double expected = 20d;
        Vector vector1 = new Vector(5d, 0d);
        Vector vector2 = new Vector(0, 4d);
        //when
        double actual = vector1.parallelogramArea(vector2);
        //then
        assertEquals(expected, actual, 10e-10);
    }

    @Test
    public void testTriangleArea() throws Exception {
        //given
        double expected = 10d;
        Vector vector1 = new Vector(5d, 0d);
        Vector vector2 = new Vector(0, 4d);
        //when
        double actual = vector1.triangleArea(vector2);
        //then
        assertEquals(expected, actual, 10e-10);
    }


}