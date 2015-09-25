package com.furknyavuz.test.algorithm;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Abstract Solver Class
 * - Template execute method
 * Created by furkan on 06.09.2015.
 */
public abstract class AbstractSolver implements Solver {

    abstract String solve() throws Exception;

    public String execute() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");

        System.out.println("Starting Time : " + sdf.format(Calendar.getInstance().getTime()));

        String result = solve();

        System.out.println("Ending Time   : " + sdf.format(Calendar.getInstance().getTime()));
        return result;
    }
}
