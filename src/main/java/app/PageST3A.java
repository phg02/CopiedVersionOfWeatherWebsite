package app;

import java.util.ArrayList;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import java.util.Map;
import java.util.HashMap;

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
public class PageST3A implements Handler {

    // URL of this page relative to http://localhost:7001/
    public static final String URL = "/page3A.html";

    public static final String TEMPLATE = "level3.html";

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

        String country = context.sessionAttribute("country2");
        String state = context.sessionAttribute("state2");
        String city = context.sessionAttribute("city2");
        String startYear = context.sessionAttribute("startyear2");
        String endYear = context.sessionAttribute("endyear2");

        System.out.println("country: " + country);
        System.out.println("state: " + state);
        System.out.println("city: " + city);
        System.out.println("startyear: " + startYear);
        System.out.println("endyear: " + endYear);

        //Get country selection
        JDBCConnection jdbc1 = new JDBCConnection();       
        ArrayList<String> countryselection = new ArrayList<String>();   
        ArrayList<info> infos1 = jdbc1.getallCountry();
        for (info country1 : infos1) {
            countryselection.add(country1.country);
        }  
        model.put("countryselection", countryselection);

        if (startYear == null || startYear.isEmpty()) {
            startYear = "0";
        }
        if (endYear == null || endYear.isEmpty()) {
            endYear = "0";
        }

        if (state == null || state.isEmpty()) {
            if (country == null) {
                model.put("title", new String("Add time period"));
            } else if (Integer.parseInt(startYear) > Integer.parseInt(endYear)) {
                model.put("title", new String("Error: Start year must be less than end year"));
            } else if (country != null && (startYear == null || startYear.isEmpty())
                    && (endYear == null || endYear.isEmpty())) {
                model.put("title", new String("Please select a year"));
            } else {
                model.put("title", new String("Compare temperature of " + country + ", " + city));
            }
            JDBCConnection jdbc2 = new JDBCConnection();
            ArrayList<info> List = jdbc2.CompareRegions(startYear, endYear);
            model.put("List", List);
        } else if (city == null || city.isEmpty()) {
            if (country == null) {
                model.put("title", new String("Add time period"));
            } else if (Integer.parseInt(startYear) > Integer.parseInt(endYear)) {
                model.put("title", new String("Error: Start year must be less than end year"));
            } else if (country != null && (startYear == null || startYear.isEmpty())
                    && (endYear == null || endYear.isEmpty())) {
                model.put("title", new String("Please select a year"));
            } else {
                model.put("title", new String("Compare temperature of " + country + ", " + state));
            }
            JDBCConnection jdbc3 = new JDBCConnection();
            ArrayList<info> List = jdbc3.CompareRegions(startYear, endYear);
            model.put("List", List);
        }

        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.render(TEMPLATE, model);
    }

}
