package app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import io.javalin.http.Context;
import io.javalin.http.Handler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Example Index HTML class using Javalin
 * <p>
 * Generate a static HTML page using Javalin
 * by writing the raw HTML into a Java String object
 *
 * @author Timothy Wiley, 2023. email: timothy.wiley@rmit.edu.au
 * @author Santha Sumanasekara, 2021. email: santha.sumanasekara@rmit.edu.au
 */
public class PageST3B implements Handler {

    // URL of this page relative to http://localhost:7001/
    public static final String URL = "/page3B.html";

    public static final String TEMPLATE = "subtaska.html";

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        Map<String, Object> model = new HashMap<String, Object>();

        String maxPop = context.sessionAttribute("maxpop");
        String minPop = context.sessionAttribute("minpop");
        String minTemp = context.sessionAttribute("mintemp");
        String maxTemp = context.sessionAttribute("maxtemp");

        System.out.println("maxpop: " + maxPop);
        System.out.println("minpop: " + minPop);
        System.out.println("mintemp: " + minTemp);
        System.out.println("maxtemp: " + maxTemp);

        if (maxPop == null && minPop == null && minTemp == null && maxTemp == null) {
            model.put("title", "Country with a selected range of data");
            System.out.println("not working");

        } else if (minPop != null && !minPop.isEmpty() && maxPop != null && !maxPop.isEmpty()
                && minTemp.isEmpty() && maxTemp.isEmpty()) {
            model.put("title", "Country with similar population range");
            if (Integer.valueOf(minPop) > Integer.valueOf(maxPop)) {
                model.put("title", "Error");
                System.out.println("con error");
            } else {
                model.put("title", "Country with a selected range of data");
                System.out.println("not working");
                JDBCConnection jdbc1 = new JDBCConnection();
                ArrayList<info> List = jdbc1.PopulationSimilarRegion(minPop, maxPop);
                model.put("output", List);
                System.out.println("working");
            }
            maxPop = null;
            minPop = null;
            minTemp = null;
            maxTemp = null;
        } else if (minTemp != null && maxTemp != null && !minTemp.isEmpty() && !maxTemp.isEmpty()
                && maxPop.isEmpty() && maxPop.isEmpty()) {
            model.put("title", "Country with similar temperature");
            if (Integer.valueOf(minTemp) > Integer.valueOf(maxTemp)) {
                model.put("title", "Error");
                System.out.println("con error");
            } else {
                model.put("title", "Country with a selected range of data");
                System.out.println("not working");
                JDBCConnection jdbc1 = new JDBCConnection();
                ArrayList<info> List = jdbc1.AVGTempSimilarRegion(minTemp, maxTemp);
                model.put("output", List);
                System.out.println("working");
            }

        } else {
            model.put("title", "Error");
            System.out.println("error big con");
        }
        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.render(TEMPLATE, model);
    }

}