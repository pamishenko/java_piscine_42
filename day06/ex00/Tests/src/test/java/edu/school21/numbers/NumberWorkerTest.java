package edu.school21.numbers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import static edu.school21.numbers.NumberWorker.myAbs;
import static org.junit.jupiter.api.Assertions.*;


class NumberWorkerTest
{

    @ParameterizedTest
    @ValueSource(ints = {2, 3, 5, 7, 11, 13, 17, 71, 73, 79, 83, 89, 97, 2147483647})
    public void isPrimeForPrimes(int prime) throws Exception {
        assertTrue(new NumberWorker().isPrime(prime));
    }


    @ParameterizedTest
    @ValueSource(ints = {6, 8, 9, 34, 35, 36, 38, 39, 40, 42, 44, 45, 49, 50, 51, 52, 54, 55, 62, 63})
    public void isPrimeForNotPrimes(int notPrime) throws Exception {
        assertFalse(new NumberWorker().isPrime(notPrime));
    }

    @ParameterizedTest
    @ValueSource(ints = {6, -8, -9, 34, -35, -36, -38, -39, 40, 42, 44, -45, 49, 50, -51, -52, 54, 55, -62, 63})
    public void isMyAbs(int number) throws Exception {
        assertTrue(new NumberWorker().myAbs(number) == Math.abs(number));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv", delimiter = '\t')
    public void isSumCorrect(int input, int expected) throws Exception {
        assertEquals(expected, new NumberWorker().digitSum(input));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/sqrtdata.csv", delimiter = ' ')
    public void isSqrtCorrect(int input, int expected) throws Exception {
        assertEquals(expected, new NumberWorker().mySqrt(input));
    }

    @Test
    public void testNegativeSum() throws Exception	{
        assertEquals(-10, new NumberWorker().digitSum(-154));
    }

    @ParameterizedTest
    @ValueSource(doubles = {-8.1, -9.0,-45.09, 490.4, 50.0, -510.0, -52.0, 54.0, 55.0, -62.0, 63})
    public void testmyAbs(double number) {
        assertTrue(myAbs(number) > 0);
    }

}
