package p02Objects.e01DefaultValues;

/**
 *   Members and static members of a class are guaranteed to receive
 *   a default value (false, 0, or null) if was not initialized explicitly.
 *   Local variables remains uninitialized. Using an uninitialized variable
 *   results in compile-time error.
 */
class Main {

    public static void main(String[] args) {
        DataOnly dataOnly = new DataOnly();
        System.out.println(dataOnly);
        System.out.println("-----------------------------");
        System.out.println(StaticOnly.values());
        f();
    }

    static void f() {
        int x;
        //System.out.println(x); // compile-time error: variable x might not have been initialized
        A anA;
        //System.out.println(anA); // compile-time error: variable anA might not have been initialized
    }
}

class A {
}

class DataOnly {

    boolean aBoolean;
    char aChar;
    byte aByte;
    short aShort;
    int anInt;
    long aLong;
    float aFloat;
    double aDouble;
    A anA;

    @Override
    public String toString() {
        return "aBoolean: " + aBoolean + "\n" +
                "aChar: " + aChar + "\n" +
                "aByte: " + aByte + "\n" +
                "aShort: " + aShort + "\n" +
                "anInt: " + anInt + "\n" +
                "aLong: " + aLong + "\n" +
                "aFloat: " + aFloat + "\n" +
                "aDouble: " + aDouble + "\n" +
                "anA: " + anA;
    }
}

class StaticOnly {

    static boolean aBoolean;
    static char aChar;
    static byte aByte;
    static short aShort;
    static int anInt;
    static long aLong;
    static float aFloat;
    static double aDouble;
    static A anA;

    static String values() {
        return "aBoolean: " + aBoolean + "\n" +
                "aChar: " + aChar + "\n" +
                "aByte: " + aByte + "\n" +
                "aShort: " + aShort + "\n" +
                "anInt: " + anInt + "\n" +
                "aLong: " + aLong + "\n" +
                "aFloat: " + aFloat + "\n" +
                "aDouble: " + aDouble + "\n" +
                "anA: " + anA;
    }
}
