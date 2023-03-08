package THINKYBITS;

public class MathMachine {
    public String getHexFromIntString(String integerString){
        long integer = Long.parseLong(integerString,2);
        String hexstring = Long.toHexString(integer);
        return hexstring;
    }

}
