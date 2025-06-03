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
public class PageWorld implements Handler {

    // URL of this page relative to http://localhost:7001/
    public static final String URL = "/pageworld.html";

    public static final String TEMPLATE = "worldview.html";

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        Map<String, Object> model = new HashMap<String, Object>();

        // Get year selection
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<Integer> yearselection = new ArrayList<Integer>();
        ArrayList<info> infos = jdbc.getallYear();

        for (info time : infos) {
            yearselection.add(time.year);
        }
        model.put("yearselection", yearselection);

        // get year from form
        String startYear = context.sessionAttribute("start");
        String endYear = context.sessionAttribute("end");

        System.out.println("Start year: " + startYear);
        System.out.println("End year: " + endYear);

        if (startYear == null || endYear == null || startYear.isEmpty() || endYear.isEmpty()) {
            model.put("title", new String("Add time period"));
        } else if (Integer.valueOf(startYear) > Integer.valueOf(endYear)) {
            model.put("title", new String("Error: The start year must be smaller than the end year!"));
        } else {
            model.put("title", new String("Compare data from " + startYear + " to " + endYear));
            JDBCConnection jdbc1 = new JDBCConnection();
            ArrayList<info> List = jdbc1.CompareRegions2(startYear, endYear);
            model.put("List", List);
        }

        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.render(TEMPLATE, model);
    }

}
