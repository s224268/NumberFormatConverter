package THINKYBITS;

public class MathMachine {
    public String getHexFromIntString(String integerString){
        int integer = Integer.parseInt(integerString);
        String hexstring = Integer.toHexString(integer);
        return hexstring;
    }
    public String getBinaryFromIntString(String integer) {
        int value = Integer.parseInt(integer);
        String binaryString = Integer.toBinaryString(value);
        return binaryString;
    }
    public String getIntFromHexString(String hex){
        int integer = Integer.parseInt(hex,16);
        String string = Integer.toString(integer);
        return string;
    }
    public String getIntFromBinaryString(String binary){
        int integer = Integer.parseInt(binary,2);
        String binaryString = Integer.toBinaryString(integer);
        return binaryString;
    }
}
