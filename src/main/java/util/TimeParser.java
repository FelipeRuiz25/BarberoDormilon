package util;

import org.apache.commons.lang3.StringUtils;

public class TimeParser {

    public static String secondsToString(int seconds){
        if (seconds < 0) throw new IllegalArgumentException();
        int minutes = (int) Math.floor(seconds / 60.0);
        seconds-= minutes*60;
        return minutes + ":" + StringUtils.leftPad(seconds+"",2, "0");
    }
}
