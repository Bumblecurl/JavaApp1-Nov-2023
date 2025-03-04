/*-------------------------------------------------------------
	FILE		: NumberUtil.java
	AUTHOR		: JavaApp1-Nov-2023 Group
	Last UPDATE	: 9th December 2023
	
	Utility class for numeric operations
	
	Copyleft (c) 1993 C and System Programmers Association
	All Right Free
-------------------------------------------------------------*/
package org.csystem.util.numeric;

import java.math.BigInteger;

import static java.lang.Math.*;

public final class NumberUtil {
    private static final String [] ONES;
    private static final String [] TENS;
    private static final BigInteger THREE = BigInteger.valueOf(3);
    private static final BigInteger FIVE = BigInteger.valueOf(5);
    private static final BigInteger SEVEN = BigInteger.valueOf(7);
    private static final BigInteger ELEVEN = BigInteger.valueOf(11);
    private static final BigInteger NINE = BigInteger.valueOf(9);

    static {
        ONES = new String[]{"", "bir", "iki", "üç", "dört", "beş", "altı", "yedi", "sekiz", "dokuz"};
        TENS = new String[]{"", "on", "yirmi", "otuz", "kırk", "elli", "altmış", "yetmiş", "seksen", "doksan"};
    }

    private NumberUtil()
    {
    }

    private static int [] digits(long val, int n)
    {
        val = Math.abs(val);
        var result = new int[val == 0 ? 1 : (int)(Math.log10(val) / n) + 1];
        var powOfTen = (int)Math.pow(10, n);

        for (var i = result.length - 1; i >= 0; result[i] = (int)(val % powOfTen), val /= powOfTen, --i)
            ;

        return result;
    }

    private static String numberToText3DigitsTR(int a, int b, int c)
    {
        var sb = new StringBuilder();

        if (a != 0) {
            if (a != 1)
                sb.append(ONES[a]);

            sb.append("yüz");
        }

        if (b != 0)
            sb.append(TENS[b]);

        if (c != 0)
            sb.append(ONES[c]);

        return sb.toString();
    }

    private static String numberToText3DigitsTR(int val)
    {
        return val == 0 ? "sıfır" : (val < 0 ? "eksi" : "") + numberToText3DigitsTR(Math.abs(val / 100), Math.abs(val / 10 % 10), Math.abs(val % 10));
    }

    public static boolean areFriends(int a, int b)
    {
        return sumFactors(a) == b  && sumFactors(b) == a;
    }

    public static int calculateDigitalRoot(int val)
    {
        var root = abs(val);

        while ((root = sumDigits(root)) > 9)
            ;

        return root;
    }

    public static int countDigits(long val)
    {
        return val != 0 ? (int)log10(abs(val)) + 1 : 1;
    }

    public static int [] digits(long val)
    {
        return digits(val, 1);
    }

    public static int [] digitsInThrees(long val)
    {
        return digits(val, 3);
    }

    public static int [] digitsInTwos(long val)
    {
        return digits(val, 2);
    }

    public static long digitsFactorialSum(int n)
    {
        var total = 0L;

        while (n != 0) {
            total += factorial(n % 10);
            n /= 10;
        }

        return total;
    }

    public static long factorial(int n)
    {
        var result = 1L;

        for (var i = 2L; i <= n; ++i)
            result *= i;

        return result;
    }

    public static BigInteger factorialBigInteger(int n)
    {
        var result = BigInteger.ONE;
        var nVal = BigInteger.valueOf(n);

        for (var i = BigInteger.TWO; i.compareTo(nVal) <= 0; i = i.add(BigInteger.ONE))
            result = result.multiply(i);

        return result;
    }

    public static int fibonacciNumber(int n)
    {
        if (n <= 2)
            return n - 1;

        var prev1 = 1;
        var prev2 = 0;
        var val = prev1 + prev2;

        for (var i = 3; i < n; ++i) {
            prev2 = prev1;
            prev1 = val;
            val = prev1 + prev2;
        }

        return val;
    }

    public static int getDigitsPowSum(int val)
    {
        var n = countDigits(val);
        var result = 0;

        while (val != 0) {
            result += (int)pow(val % 10, n);
            val /= 10;
        }

        return result;
    }

    public static long getPrime(int n)
    {
        var count = 0;
        var val = 2;

        while (true) {
            if (isPrime(val))
                ++count;

            if (count == n)
                break;

            ++val;
        }

        return val;
    }

    public static int hardyRamanujanCount(int n)
    {
        var count = 0;

        EXIT_LOOP:
        for (var a = 1; a * a * a < n; ++a)
            for (var b = a + 1; a * a * a + b * b * b <= n; ++b)
                if (a * a * a + b * b * b == n) {
                    if (++count == 2)
                        break EXIT_LOOP;

                    ++a;
                }

        return count;
    }

    public static int indexOfPrime(long val)
    {
        var i = 1;
        var a = 2L;

        while (true) {
            if (a == val)
                return i;

            if (isPrime(a))
                ++i;

            ++a;
        }
    }

    public static boolean isArmstrong(int val)
    {
        return val >= 0 && getDigitsPowSum(val) == val;
    }

    public static boolean isDecimalHarshad(int val)
    {
        return val > 0 && val % sumDigits(val) == 0;
    }

    public static boolean isFactorian(int n)
    {
        return n > 0 && digitsFactorialSum(n) == n;
    }

    public static boolean isHardyRamanujan(int n)
    {
        return n > 0 && hardyRamanujanCount(n) == 2;
    }

    public static boolean isPerfect(int val)
    {
        return sumFactors(val) == val;
    }

    public static boolean isPrime(long val)
    {
        if (val <= 1)
            return false;

        if (val % 2 == 0)
            return val == 2;

        if (val % 3 == 0)
            return val == 3;

        if (val % 5 == 0)
            return val == 5;

        if (val % 7 == 0)
            return val == 7;

        for (var i = 11L; i * i <= val; i += 2)
            if (val % i == 0)
                return false;

        return true;
    }

    public static boolean isPrime(BigInteger val)
    {
        if (val.compareTo(BigInteger.ONE) <= 0)
            return false;

        if (val.remainder(BigInteger.TWO).equals(BigInteger.ZERO))
            return val.equals(BigInteger.TWO);

        if (val.remainder(THREE).equals(BigInteger.ZERO))
            return val.equals(THREE);

        if (val.remainder(FIVE).equals(BigInteger.ZERO))
            return val.equals(FIVE);

        if (val.remainder(SEVEN).equals(BigInteger.ZERO))
            return val.equals(SEVEN);

        for (var i = ELEVEN; i.multiply(i).compareTo(val) <= 0; i = i.add(BigInteger.TWO))
             if (val.remainder(i).equals(BigInteger.ZERO))
                 return false;

        return true;
    }

    public static boolean isPrimeX(long val)
    {
        boolean result;

        for (var sum = val; (result = isPrime(sum)) && sum > 9; sum = sumDigits(sum))
            ;

        return result;
    }

    /**
     *
     * @author Hüseyin Mercimek
     */
    public static boolean isPrimeX(BigInteger val)
    {
        boolean result ;

        for(var sum = val; ( result = isPrime(sum)) && sum.compareTo(NINE) > 0; sum = sumDigits(sum))
            ;
        return result;
    }


    public static boolean isSuperPrime(long val)
    {
        return isPrime(val) && isPrime(indexOfPrime(val));
    }

    /**
     *
     * @author Berkay Yılmaz
     */
    public static boolean isSuperPrime(BigInteger val)
    {
        return isPrime(val) && isPrime(indexOfPrime(val));
    }

    public static BigInteger indexOfPrime(BigInteger val)
    {
        var i = BigInteger.ONE;
        var a = BigInteger.TWO;

        while (true) {
            if (a.equals(val))
                return i;
            if (isPrime(a))
                i = i.add(BigInteger.ONE);

            a = a.add(BigInteger.ONE);
        }
    }
    public static int mid(int a, int b, int c)
    {
        if (a <= b && b <= c || c <= b && b <= a)
            return b;

        if (b <= a && a <= c || c <= a && a <= b)
            return a;

        return c;
    }

    /**
     *
     * @author Bera Bozgöz
     */
    public static BigInteger mid(BigInteger a, BigInteger b, BigInteger c)
    {
        if (a.compareTo(b) <= 0 && b.compareTo(c) <= 0 || c.compareTo(b) <= 0 && b.compareTo(a) <= 0)
            return b;

        if (b.compareTo(a) <= 0 && a.compareTo(c) <= 0 || c.compareTo(a) <= 0 && a.compareTo(b) <= 0)
            return a;

        return c;
    }

    public static int nextClosestFibonacciNumber(int val)
    {
        if (val < 0)
            return 0;

        var prev1 = 1;
        var prev2 = 0;
        int next;

        while (true) {
            next = prev1 + prev2;

            if (val < next)
                return next;

            prev2 = prev1;
            prev1 = next;
        }
    }

    public static int nextClosestPrimeFibonacciNumber(int val)
    {
        if (val < 0)
            return 0;

        var prev1 = 1;
        var prev2 = 0;
        int next;

        while (true) {
            next = prev1 + prev2;

            if (val < next && isPrime(next))
                return next;

            prev2 = prev1;
            prev1 = next;
        }
    }

    public static int nextTotalOfTwoClosestPrimeFibonacciNumber(int val)
    {
        if (val < 0)
            return 1;

        int result;

        return (result = nextClosestPrimeFibonacciNumber(val)) + nextClosestPrimeFibonacciNumber(result);
    }

    public static String numberToTextTR(long val)
    {
        var str = numberToText3DigitsTR((int)val);

        //...

        return str;
    }

    public static int reverse(int val)
    {
        var result = 0;

        while (val != 0) {
            result = result * 10 + val % 10;
            val /= 10;
        }

        return result;
    }

    /**
     *
     * @author Yaşar Uğur Güleç
     */
    public static BigInteger reverse(BigInteger val)
    {
        var result = BigInteger.ZERO;

        while (val.compareTo(BigInteger.ZERO) > 0) {
            result = result.multiply(BigInteger.TEN).add(val.remainder(BigInteger.TEN));
            val = val.divide(BigInteger.TEN);
        }

        return result;
    }

    public static int sumDigits(long val)
    {
        var total = 0;

        while (val != 0) {
            total += (int)(val % 10);
            val /= 10;
        }

        return abs(total);
    }

    /**
     *
     * @author Hüseyin Mercimek
     */
    public static BigInteger sumDigits(BigInteger val)
    {
        var total = BigInteger.ZERO;

        while(!val.equals(BigInteger.ZERO)) {
            total = total.add(val.remainder(BigInteger.TEN));
            val = val.divide(BigInteger.TEN);
        }
        return total;
    }

    /**
     *
     * @author Buğrahan Kısa
     */

    public static BigInteger sumFactors(BigInteger val)
    {
        var result = BigInteger.ONE;
        var sqrtVal = val.sqrt();

        for (var i = BigInteger.TWO; i.compareTo(sqrtVal) <= 0; i = i.add(BigInteger.TWO)) {
            if (val.remainder(i).compareTo(BigInteger.ZERO) == 0) {
                result = result.add(i);

                BigInteger divideResult = val.divide(i);
                if (i.compareTo(divideResult) != 0)
                    result = result.add(divideResult);
            }
        }

        return result;
    }
    public static int sumFactors(long val)
    {
        var result = 1;
        var sqrtVal = (long)sqrt(val);

        for (var i = 2L; i <= sqrtVal; ++i)
            if (val % i == 0)
                result += (int)((i == val / i) ? i : (i + val / i));

        return result;
    }
}
