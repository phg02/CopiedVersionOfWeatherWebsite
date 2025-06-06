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
public class PageST2B implements Handler {

    // URL of this page relative to http://localhost:7001/
    public static final String URL = "/country.html";
    public static final String TEMPLATE = "lv2Country.html";

    @Override
    public void handle(Context context) throws Exception {
        // Create a hash map to hold the data we want to pass to our template
        Map<String, Object> model = new HashMap<String, Object>();

        // add year selection
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<Integer> yearselection = new ArrayList<Integer>();
        ArrayList<info> infos = jdbc.getallYear();
        for (info time : infos) {
            yearselection.add(time.year);
        }
        model.put("yearselection", yearselection);

        model.put("sortby", new String("Ascending"));
        model.put("sortby1", new String("Ascending"));
        model.put("sortby2", new String("Ascending"));
        model.put("sortby3", new String("Ascending"));
        model.put("sortby4", new String("Ascending"));

        // add country selection
        JDBCConnection jdbc1 = new JDBCConnection();
        ArrayList<String> countryselection = new ArrayList<String>();
        ArrayList<info> infos1 = jdbc1.getallCountry();
        for (info time1 : infos1) {
            countryselection.add(time1.country);
        }
        model.put("countryselection", countryselection);

        // read value from form
        String startYear = context.sessionAttribute("startyear1");
        String endYear = context.sessionAttribute("endyear1");
        String country = context.sessionAttribute("country");
        if (startYear == null || startYear.isEmpty()) {
            startYear = "0";
        }
        if (endYear == null || endYear.isEmpty()) {
            endYear = "0";
        }

        // return country name
        if (country == null || country.isEmpty()) {
            model.put("country", new String("Country View"));
        } else if (Integer.parseInt(startYear) > Integer.parseInt(endYear)) {
            model.put("country", new String("Error: Start year must be less than end year"));
        } else if (Integer.parseInt(startYear) == 0 || Integer.parseInt(endYear) == 0) {
            model.put("country", new String("Please enter a year"));
        } else {
            model.put("country", new String("Viewing " + country + " (From " + startYear + " to " + endYear + ")"));
            // return info for country
            JDBCConnection jbdc2 = new JDBCConnection();
            ArrayList<info> infos2 = jbdc2.lvl2Bquery(startYear, endYear, country);
            model.put("answer", infos2);

            JDBCConnection jbdc3 = new JDBCConnection();
            ArrayList<info> infos3 = jbdc3.readCountryInfo(startYear, country);
            model.put("answer1", infos3);

            JDBCConnection jbdc4 = new JDBCConnection();
            ArrayList<info> infos4 = jbdc4.readCountryInfo(endYear, country);
            model.put("answer2", infos4);

            JDBCConnection jbdc5 = new JDBCConnection();
            double percentTemp = jbdc5.percentTempChange(startYear, endYear, country);
            if (percentTemp > 0) {
                model.put("percentTemp", new String("+" + percentTemp));
            } else {
                model.put("percentTemp", percentTemp);
            }

            if (country == null) {
                model.put("percentPop", new String("0.0"));
            } else {
                JDBCConnection jbdc6 = new JDBCConnection();
                double results6 = jbdc6.getPopulation(startYear, country);
                JDBCConnection jbdc7 = new JDBCConnection();
                double results7 = jbdc7.getPopulation(endYear, country);
                double percentPop = ((results7 - results6) / results6) * 100;
                if (percentPop > 0) {
                    model.put("percentPop", new String("+" + percentPop));
                } else {
                    model.put("percentPop", percentPop);
                }
                System.out.println(percentPop);
            }
            JDBCConnection jbdc8 = new JDBCConnection();
            double percentMinTempChange = jbdc8.percentMinTempChange(startYear, endYear, country);
            if (percentMinTempChange > 0) {
                model.put("percentMinTemp", new String("+" + percentMinTempChange));
            } else {
                model.put("percentMinTemp", percentMinTempChange);
            }

            JDBCConnection jbdc9 = new JDBCConnection();
            double percentMaxTempChange = jbdc9.percentMaxTempChange(startYear, endYear, country);
            if (percentMaxTempChange > 0) {
                model.put("percentMaxTemp", new String("+" + percentMaxTempChange));
            } else {
                model.put("percentMaxTemp", percentMaxTempChange);
            }
            String sort = context.sessionAttribute("sort");
            if (sort == null || sort.isEmpty()) {
                model.put("sortby", new String("Ascending"));
            } else if (sort.equals("Ascending")) {
                model.put("sortby", new String("Ascending"));
            } else if (sort.equals("Descending")) {
                model.put("sortby", new String("Descending"));
            } else {
                model.put("sortby", new String("Ascending"));
            }

            if (sort == null || sort.isEmpty()) {
                JDBCConnection jbdc11 = new JDBCConnection();
                ArrayList<info> infos11 = jbdc11.lvl2AVGTempRankingASC(startYear, endYear, country);
                model.put("answer3", infos11);
            } else if (sort.equals("Ascending")) {
                JDBCConnection jbdc11 = new JDBCConnection();
                ArrayList<info> infos11 = jbdc11.lvl2AVGTempRankingASC(startYear, endYear, country);
                model.put("answer3", infos11);
            } else if (sort.equals("Descending")) {
                JDBCConnection jbdc11 = new JDBCConnection();
                ArrayList<info> infos11 = jbdc11.lvl2AVGTempRankingDESC(startYear, endYear, country);
                model.put("answer3", infos11);
            } else {
                JDBCConnection jbdc11 = new JDBCConnection();
                ArrayList<info> infos11 = jbdc11.lvl2AVGTempRankingASC(startYear, endYear, country);
                model.put("answer3", infos11);
            }
            String sort1 = context.sessionAttribute("sort1");

            if (sort1 == null || sort1.isEmpty()) {
                model.put("sortby1", new String("Ascending"));
            } else if (sort1.equals("Ascending")) {
                model.put("sortby1", new String("Ascending"));
            } else if (sort1.equals("Descending")) {
                model.put("sortby1", new String("Descending"));
            } else {
                model.put("sortby1", new String("Ascending"));
            }
            if (sort1 == null || sort1.isEmpty()) {
                JDBCConnection jbdc12 = new JDBCConnection();
                ArrayList<info> infos12 = jbdc12.lvl2PopulationRankingASC(startYear, endYear, country);
                model.put("answer4", infos12);
            } else if (sort1.equals("Ascending")) {
                JDBCConnection jbdc12 = new JDBCConnection();
                ArrayList<info> infos11 = jbdc12.lvl2PopulationRankingASC(startYear, endYear, country);
                model.put("answer4", infos11);
            } else if (sort1.equals("Descending")) {
                JDBCConnection jbdc12 = new JDBCConnection();
                ArrayList<info> infos11 = jbdc12.lvl2PopulationRankingDESC(startYear, endYear, country);
                model.put("answer4", infos11);
            } else {
                JDBCConnection jbdc12 = new JDBCConnection();
                ArrayList<info> infos11 = jbdc12.lvl2PopulationRankingDESC(startYear, endYear, country);
                model.put("answer4", infos11);
            }

            String sort2 = context.sessionAttribute("sort2");
            if (sort2 == null || sort2.isEmpty()) {
                model.put("sortby2", new String("Ascending"));
            } else if (sort2.equals("Ascending")) {
                model.put("sortby2", new String("Ascending"));
            } else if (sort2.equals("Descending")) {
                model.put("sortby2", new String("Descending"));
            } else {
                model.put("sortby2", new String("Ascending"));
            }

            if (sort2 == null || sort2.isEmpty()) {
                JDBCConnection jbdc12a = new JDBCConnection();
                ArrayList<info> infos12a = jbdc12a.lvl2MinTempRankingASC(startYear, endYear, country);
                model.put("answer5", infos12a);
            } else if (sort2.equals("Ascending")) {
                JDBCConnection jbdc12a = new JDBCConnection();
                ArrayList<info> infos12a = jbdc12a.lvl2MinTempRankingASC(startYear, endYear, country);
                model.put("answer5", infos12a);
            } else if (sort2.equals("Descending")) {
                JDBCConnection jbdc12a = new JDBCConnection();
                ArrayList<info> infos12a = jbdc12a.lvl2MinTempRankingDESC(startYear, endYear, country);
                model.put("answer5", infos12a);
            } else {
                JDBCConnection jbdc12a = new JDBCConnection();
                ArrayList<info> infos12a = jbdc12a.lvl2MinTempRankingDESC(startYear, endYear, country);
                model.put("answer5", infos12a);
            }

            String sort3 = context.sessionAttribute("sort3");
            if (sort3 == null || sort3.isEmpty()) {
                model.put("sortby4", new String("Ascending"));
            } else if (sort3.equals("Ascending")) {
                model.put("sortby4", new String("Ascending"));
            } else if (sort3.equals("Descending")) {
                model.put("sortby4", new String("Descending"));
            } else {
                model.put("sortby4", new String("Ascending"));
            }

            if (sort3 == null || sort3.isEmpty()) {
                JDBCConnection jdbc14 = new JDBCConnection();
                ArrayList<info> infos14 = jdbc14.lvl2MaxTempRankingASC(startYear, endYear, country);
                model.put("answer6", infos14);
            } else if (sort3.equals("Ascending")) {
                JDBCConnection jdbc14 = new JDBCConnection();
                ArrayList<info> infos14 = jdbc14.lvl2MaxTempRankingASC(startYear, endYear, country);
                model.put("answer6", infos14);
            } else if (sort3.equals("Descending")) {
                JDBCConnection jdbc14 = new JDBCConnection();
                ArrayList<info> infos14 = jdbc14.lvl2MaxTempRankingDESC(startYear, endYear, country);
                model.put("answer6", infos14);
            } else {
                JDBCConnection jdbc14 = new JDBCConnection();
                ArrayList<info> infos14 = jdbc14.lvl2MaxTempRankingDESC(startYear, endYear, country);
                model.put("answer6", infos14);
            }
        }

        context.render(TEMPLATE, model);

    }
}
