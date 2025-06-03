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
public class pagesubB implements Handler {

    // URL of this page relative to http://localhost:7001/
    public static final String URL = "/p.html";

    public static final String TEMPLATE = "subtaskb.html";

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        Map<String, Object> model = new HashMap<String, Object>();

        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<Integer> yearselection = new ArrayList<Integer>();
        ArrayList<info> infos = jdbc.getallYear();

        for (info time : infos) {
            yearselection.add(time.year);
        }
        model.put("yearselection", yearselection);

        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        // COUNTRY SELECTION
        JDBCConnection jdbc1 = new JDBCConnection();
        ArrayList<String> countryselection = new ArrayList<String>();
        ArrayList<info> infos1 = jdbc1.getallCountry();

        for (info time1 : infos1) {
            countryselection.add(time1.country);
        }
        model.put("countryselection", countryselection);

        String country = context.sessionAttribute("country");
        String state = context.sessionAttribute("state");
        String city = context.sessionAttribute("city");
        String startyear = context.sessionAttribute("startyear");
        String endyear = context.sessionAttribute("endyear");

        System.out.println(country);
        System.out.println(state);
        System.out.println(city);
        System.out.println(startyear);
        System.out.println(endyear);

        if (country != null && (state.isEmpty() || state == null) && (city.isEmpty() || state == null)
                && startyear != null && endyear != null) {
            System.out.println("country");
            model.put("region", new String(country));
            JDBCConnection jdbc2 = new JDBCConnection();
            ArrayList<info> scinfo = jdbc2.CountrySimilar(startyear, endyear, country);
            model.put("scinfo", scinfo);

            JDBCConnection jdbc3 = new JDBCConnection();
            String avgTemp = "";
            String pop = "";
            for (info result : scinfo) {
                avgTemp = Double.toString(result.AVGtemp);
                pop = Long.toString(result.population);
            }
            ArrayList<info> scinfo1 = jdbc3.CountryMatch(avgTemp, pop);
            model.put("result", scinfo1);

        } else if (country != null && (!state.isEmpty() || state != null) && (city.isEmpty() || city == null)
                && startyear != null && endyear != null) {
            System.out.println("state");
            model.put("region", new String(country + ", " + state));
            JDBCConnection jdbc2 = new JDBCConnection();
            ArrayList<info> scinfo = jdbc2.StateSimilar(startyear, endyear, country, state);
            model.put("scinfo", scinfo);

            JDBCConnection jdbc3 = new JDBCConnection();
            String avgTemp = "";

            for (info result : scinfo) {
                avgTemp = Double.toString(result.AVGtemp);
            }
            ArrayList<info> scinfo1 = jdbc3.StateMatch(avgTemp);
            model.put("result", scinfo1);

        } else if (country != null && (state.isEmpty() || state == null) && (!city.isEmpty() || city != null)
                && startyear != null && endyear != null) {
            System.out.println("city");
            model.put("region", new String(country + ", " + city));
            JDBCConnection jdbc2 = new JDBCConnection();
            ArrayList<info> scinfo = jdbc2.CitySimilar(startyear, endyear, country, city);
            model.put("scinfo", scinfo);

            JDBCConnection jdbc3 = new JDBCConnection();
            String avgTemp = "";

            for (info result : scinfo) {
                avgTemp = Double.toString(result.AVGtemp);
            }
            ArrayList<info> scinfo1 = jdbc3.CityMatch(avgTemp);
            model.put("result", scinfo1);
        } else {
            System.out.println("Error");
            model.put("region", new String("Error"));
        }
        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.render(TEMPLATE, model);
    }

}
