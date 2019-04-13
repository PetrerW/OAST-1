package com.company;

public class Log {
    public static boolean  printInfo = true;
    public static boolean  printError = true;
    public static boolean  printWarn = true;
    public static boolean  printDebug = true;

    public static void info(String text){
        if(printInfo)
            java.lang.System.out.println("[INFO] " + text);
    }
    public static void error(String text){
        if(printError)
            java.lang.System.out.println("[ERROR] " + text);
    }
    public static void warn(String text){
        if(printWarn)
            java.lang.System.out.println("[WARN] " + text);
    }
    public static void debug(String text){
        if(printDebug)
            java.lang.System.out.println("[WARN] " + text);
    }
}
