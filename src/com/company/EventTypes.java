package com.company;

public final class EventTypes {
    public static String chunk;

    static {
        chunk = "Chunk";
    }

    public static String sessionStart;

    static{
        sessionStart = "Session Start";
    }

    public static String sessionEnd;

    static {
        sessionEnd = "Session End";
    }

    public static String ping;

    static{
        ping = "ping";
    }
}
