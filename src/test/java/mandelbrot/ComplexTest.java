package mandelbrot;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.Random;
import java.util.concurrent.Callable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ComplexTest {
    private final Complex onePlusI = new Complex(1, 1);
    private final Complex minusI = new Complex(0, -1);
    private final Complex minusOne = new Complex(-1, 0);
    private final Complex oneMinusI = new Complex(1, -1);
    private final Complex twoI = new Complex(0, 2);
    private final Complex two = new Complex(2, 0);
    private final double real = -12;
    private final double imaginary = 10;


    @Test
    void testConstructor() {
        assertEquals(0., twoI.real, Helpers.EPSILON);
        assertEquals(2., twoI.imaginary, Helpers.EPSILON);
        assertEquals(1., oneMinusI.real, Helpers.EPSILON);
        assertEquals(-1., oneMinusI.imaginary, Helpers.EPSILON);
        assertEquals(2., two.real, Helpers.EPSILON);
        assertEquals(0., two.imaginary, Helpers.EPSILON);
    }

    @Test
    void testGetReal() {
        assertEquals(2., two.getReal(), Helpers.EPSILON);
        assertEquals(1., oneMinusI.getReal(), Helpers.EPSILON);
        assertEquals(-1., new Complex(-1, 1).getReal(), Helpers.EPSILON);
        assertEquals(real, new Complex(real, imaginary).getReal(), Helpers.EPSILON);
    }

    @Test
    void testGetImaginary() {
        assertEquals(2., twoI.getImaginary(), Helpers.EPSILON);
        assertEquals(1., new Complex(-1, 1).getImaginary(), Helpers.EPSILON);
        assertEquals(-1., oneMinusI.getImaginary(), Helpers.EPSILON);
        assertEquals(imaginary, new Complex(real, imaginary).getImaginary(), Helpers.EPSILON);
    }

    @Test
    void testOne() {
        assertEquals(1., Complex.ONE.getReal());
        assertEquals(0., Complex.ONE.getImaginary());
    }

    @Test
    void testI() {
        assertEquals(0, Complex.I.getReal());
        assertEquals(1, Complex.I.getImaginary());
    }

    @Test
    void testZero() {
        assertEquals(0, Complex.ZERO.getReal());
        assertEquals(0, Complex.ZERO.getImaginary());
    }

    @Test
    void testNegate() {
        assertEquals(minusOne, Complex.ONE.negate());
        assertEquals(Complex.I, minusI.negate());
        assertEquals(new Complex(-1, 1), oneMinusI.negate());
        assertEquals(new Complex(real, imaginary), new Complex(-real, -imaginary).negate());
    }

    @Test
    void testReciprocal() {
        assertEquals(Complex.ONE, Complex.ONE.reciprocal());
        assertEquals(Complex.I, minusI.reciprocal());
        assertEquals(new Complex(0.5, 0), two.reciprocal());
        assertEquals(new Complex(0.5, 0.5), oneMinusI.reciprocal());
    }

    @Test
    void testReciprocalOfZero() {
        assertThrows(ArithmeticException.class, () -> Complex.ZERO.reciprocal());
    }

    @Test
    void testSubstract() {
        assertEquals(minusOne, Complex.ZERO.subtract(Complex.ONE));
        assertEquals(oneMinusI, Complex.ONE.subtract(Complex.I));
        assertEquals(new Complex(real - 1, imaginary - 1),
                new Complex(real, imaginary).subtract(onePlusI));
    }

    @Test
    void testDivide() {
        assertEquals(onePlusI, onePlusI.divide(Complex.ONE));
        assertEquals(new Complex(0.5, 0), Complex.ONE.divide(two));
        assertEquals(minusI, oneMinusI.divide(onePlusI));
    }

    @Test
    void testDivideByZero() {
        assertThrows(ArithmeticException.class, () -> Complex.ONE.divide(Complex.ZERO));
    }

    @Test
    void testConjugate() {
        assertEquals(Complex.ZERO, Complex.ZERO.conjugate());
        assertEquals(Complex.ONE, Complex.ONE.conjugate());
        assertEquals(onePlusI, oneMinusI.conjugate());
        assertEquals(new Complex(real, -imaginary), new Complex(real, imaginary).conjugate());
    }

    @Test
    void testRotation() {
        assertEquals(Complex.I, Complex.rotation(Math.PI / 2));
        assertEquals(minusI, Complex.rotation(-Math.PI / 2));
        assertEquals(Complex.ONE, Complex.rotation(0));
        assertEquals(new Complex(Math.sqrt(2) / 2., Math.sqrt(2) / 2.),
                Complex.rotation(Math.PI / 4));
        assertEquals(new Complex(1. / 2., Math.sqrt(3) / 2.),
                Complex.rotation(Math.PI / 3));
    }

    @Test
    void testToString() {
        assertEquals("Complex{real=1.0, imaginary=-1.0}", oneMinusI.toString());
        assertEquals("Complex{real=" + real + ", imaginary=" + imaginary + "}", new Complex(real, imaginary).toString());
    }

    @Test
    void testHashCode() {
        Complex c1 = new Complex(real, imaginary);
        Complex c2 = new Complex(real, imaginary);
        assertEquals(c1.hashCode(), c2.hashCode());
    }

    @Test
    void testComplexReal() {

        Random random = new Random(0);
        double real;

        for (int testComplexNumber = 0; testComplexNumber < 10; testComplexNumber++) {

            real = random.nextDouble();
            Complex complex = new Complex(real, 0);
            assertEquals(real, complex.real);


        }


    }

    @Test
    void testAddComplex() {

        Random random = new Random(0);
        Complex[] complex = new Complex[2];

        for (int testNumber = 0; testNumber < 5; testNumber++) {

            for (int indexInTabular = 0; indexInTabular < 2; indexInTabular++) {


                complex[indexInTabular] = new Complex(random.nextDouble(), random.nextDouble());

            }

            assertEquals(new Complex(complex[0].real+complex[1].real,complex[0].imaginary+complex[1].imaginary), complex[0].add(complex[1]));

        }

    }

    @Test
    void testSubstractComplex() {


        Random random = new Random(0);
        Complex[] complex = new Complex[2];

        for (int testNumber = 0; testNumber < 5; testNumber++) {

            for (int indexInTabular = 0; indexInTabular < 2; indexInTabular++) {


                complex[indexInTabular] = new Complex(random.nextDouble(), random.nextDouble());

            }

            assertEquals(new Complex(complex[0].real-complex[1].real,complex[0].imaginary-complex[1].imaginary), complex[0].subtract(complex[1]));

        }


    }
    @Test
    void testMultiplyComplex(){


        Random random = new Random(0);
        Complex[] complex = new Complex[2];

        for (int testNumber = 0; testNumber < 5; testNumber++) {

            for (int indexInTabular = 0; indexInTabular < 2; indexInTabular++) {


                complex[indexInTabular] = new Complex(random.nextDouble(), random.nextDouble());

            }

            assertEquals(new Complex(complex[0].real*complex[1].real-(complex[0].imaginary*complex[1].imaginary),
                    complex[0].real*complex[1].imaginary+complex[0].imaginary*complex[1].real), complex[0].multiply(complex[1]));

        }


    }

    @Test
    void testSquaredModulus(){

        Random random = new Random(0);
        Complex complex ;

        for (int testNumber = 0; testNumber < 10; testNumber++) {

            complex = new Complex(random.nextDouble(),random.nextDouble());
            assertEquals(complex.real*complex.real+complex.imaginary*complex.imaginary,complex.squaredModulus());

        }


    }
    @Test
    void testModulus(){

        Random random = new Random(0);
        Complex complex ;

        for (int testNumber = 0; testNumber < 10; testNumber++) {

            complex = new Complex(random.nextDouble(),random.nextDouble());
            assertEquals(Math.sqrt(complex.real*complex.real+complex.imaginary*complex.imaginary),complex.modulus());

        }

    }

    @Test
    void testComplexPow(){

    Complex[] complex = new Complex[] { new Complex( 1 , 0),new Complex(1,0)};


    assertEquals(complex[0],(complex[0].pow(8)));
    assertEquals(complex[1],complex[1].pow(10));


    }

    @Test
    void testScale(){
        Random random = new Random(0);
        Complex complex;

        for (int testNumber = 0; testNumber < 10; testNumber++) {
            double lambda = random.nextDouble();

            complex = new Complex(random.nextDouble(),random.nextDouble());
            assertEquals(new Complex(complex.real * lambda, complex.imaginary * lambda), complex.scale(lambda));

        }

    }

    @Test
    void testComplexLikeObject(){

        Object[] object = new Object[] {"je vais tapper Christine demain xD",new Complex(2, 2),new Point(2,2)};

        boolean ok = true ;

        assertNotEquals(object[1].equals(object[0]),ok);
        assertEquals(object[1].equals(onePlusI.scale(2)),ok);
        assertNotEquals(object[2].equals(object[0]),ok);


    }

}