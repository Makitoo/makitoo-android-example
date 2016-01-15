package com.moonpi.swiftnotes;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by josselin on 11/12/15.
 */
public class Crash {

    public static void crashNull() {
        String test = null;
        int i = Integer.getInteger(test);
        System.out.println(i);
    }

    public static void crashNotFound() throws IOException {
        File file = new File("notfound.txt");
        BufferedInputStream buff = new BufferedInputStream(new FileInputStream(file));
        int line = buff.read();
        System.out.println(line);
    }

    public static void crashBadOperation() {
        int i = 100 / 0;
        System.out.println(i);
    }

    public static void crashIterator() {
        Map<String, Double> map = new HashMap();
        Iterator it = map.entrySet().iterator();
        String line = it.next().toString();
        System.out.println(line);
    }

    public static void crashLongLoop() {
        for(int i=0; i< 1000000; i++){
            ++i;
        }
    }

    public static void crashArrayIndexOutOfBound() {
        ArrayList al = new ArrayList();
        al.add("");
        String test = al.get(10000).toString();
        System.out.println(test);
    }
}
