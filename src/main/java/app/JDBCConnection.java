package app;

import java.util.ArrayList;

import helper.WorldTemperature;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Class for Managing the JDBC Connection to a SQLLite Database.
 * Allows SQL queries to be used with the SQLLite Databse in Java.
 *
 * @author Timothy Wiley, 2023. email: timothy.wiley@rmit.edu.au
 * @author Santha Sumanasekara, 2021. email: santha.sumanasekara@rmit.edu.au
 */
public class JDBCConnection {

    // Name of database file (contained in database folder)
    public static final String DATABASE = "jdbc:sqlite:database/ClimateDB.db";

    /**
     * This creates a JDBC Object so we can keep talking to the database
     */
    public JDBCConnection() {
        System.out.println("Created JDBC Connection Object");
    }

    public ArrayList<info> getMinYearTemp() {
        // Create the ArrayList of LGA objects to return
        ArrayList<info> min = new ArrayList<info>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT AVG_temp, year FROM WorldTemperature \n" + //
                    "WHERE year = (SELECT MIN(year)FROM WorldTemperature);";

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Lookup the columns we need
                info info = new info();
                double avgTemp = results.getDouble("AVG_temp");
                int year = results.getInt("year");

                info.AVGtemp = avgTemp;
                info.year = year;

                // Add the lga object to the array
                min.add(info);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the lga
        return min;
    }

    public ArrayList<info> getMaxYearTemp() {
        // Create the ArrayList of LGA objects to return
        ArrayList<info> max = new ArrayList<info>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT AVG_temp, year FROM WorldTemperature WHERE year = (SELECT MAX(year)FROM WorldTemperature)";

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Lookup the columns we need
                info info = new info();
                double avgTemp = results.getDouble("AVG_temp");
                int year = results.getInt("year");

                info.AVGtemp = avgTemp;
                info.year = year;

                // Add the lga object to the array
                max.add(info);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the lga
        return max;
    }

    public ArrayList<info> getMinYearPopulation() {
        // Create the ArrayList of LGA objects to return
        ArrayList<info> min = new ArrayList<info>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT pop_num, year FROM Population p JOIN Country c ON p.country_code = c.country_code \r\n"
                    + //
                    "WHERE p.country_code =\"WLD\" AND p.year=(SELECT MIN(year)FROM Population);";

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Lookup the columns we need
                info info = new info();
                long population = results.getLong("pop_num");
                int year = results.getInt("year");

                info.population = population;
                info.year = year;

                // Add the lga object to the array
                min.add(info);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the lga
        return min;
    }

    public ArrayList<info> getMaxYearPopulation() {
        // Create the ArrayList of LGA objects to return
        ArrayList<info> max = new ArrayList<info>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT pop_num, year FROM Population p JOIN Country c ON p.country_code = c.country_code \r\n"
                    + //
                    "WHERE p.country_code =\"WLD\" AND p.year=(SELECT MAX(year)FROM Population);";

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Lookup the columns we need
                info info = new info();
                long population = results.getLong("pop_num");
                int year = results.getInt("year");

                info.population = population;
                info.year = year;

                // Add the lga object to the array
                max.add(info);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the lga
        return max;
    }

    public ArrayList<info> getallYear() {
        // Create the ArrayList of LGA objects to return
        ArrayList<info> yearlist = new ArrayList<info>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT DISTINCT year FROM Population ORDER BY year ASC;";

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results

            while (results.next()) {
                info info = new info();
                // Lookup the columns we need
                info.year = results.getInt("year");
                // Add the lga object to the array
                yearlist.add(info);

            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the lga
        return yearlist;
    }

    public ArrayList<info> getallCountry() {
        ArrayList<info> countrylist = new ArrayList<info>();

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DATABASE);

            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String query = "SELECT country_name FROM Country WHERE country_name <> 'World' AND country_name <> 'Asia' ORDER BY country_name;";

            ResultSet results = statement.executeQuery(query);

            while (results.next()) {
                info info = new info();
                info.country = results.getString("country_name");
                countrylist.add(info);
            }

            statement.close();
        }

        catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }

        return countrylist;
    }

    public ArrayList<info> getallCity() {
        ArrayList<info> citylist = new ArrayList<info>();

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DATABASE);

            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String query = "SELECT DISTINCT city_name FROM CityTemperature ORDER BY city_name;";

            ResultSet results = statement.executeQuery(query);

            while (results.next()) {
                info info = new info();
                info.cityName = results.getString("city_name");
                citylist.add(info);
            }

            statement.close();
        }

        catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }

        return citylist;
    }

    public ArrayList<info> lvl2query(String one, String two) {
        ArrayList<info> lvl2list = new ArrayList<info>();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String query = "SELECT wt.year ,pop_num ,AVG_temp, MIN_temp, MAX_TEMP FROM WorldTemperature wt JOIN Population p  WHERE wt.year>="
                    + one + " AND wt.year<=" + two + " AND p.country_code= 'WLD' AND p.year = wt.year GROUP BY wt.year";
            System.out.println(query);
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                info info = new info();
                info.year = results.getInt("year");
                info.population = results.getLong("pop_num");
                info.AVGtemp = results.getDouble("AVG_temp");
                info.Mintemp = results.getDouble("MIN_temp");
                info.Maxtemp = results.getDouble("MAX_TEMP");
                lvl2list.add(info);
            }
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return lvl2list;

    }

    public ArrayList<info> lv2queryYearReader(String one) {
        ArrayList<info> lvl2list = new ArrayList<info>();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String query = "SELECT wt.year ,pop_num ,AVG_temp, MIN_temp, MAX_TEMP FROM WorldTemperature wt JOIN Population p  WHERE wt.year= "
                    + one + " AND p.country_code= 'WLD' AND  p.year = wt.year GROUP BY wt.year";
            System.out.println(query);
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                info info = new info();
                info.year = results.getInt("year");
                info.population = results.getLong("pop_num");
                info.AVGtemp = results.getDouble("AVG_temp");
                info.Mintemp = results.getDouble("MIN_temp");
                info.Maxtemp = results.getDouble("MAX_TEMP");
                lvl2list.add(info);
            }
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return lvl2list;

    }

    public double getPercentageTempChange(String start, String end) {
        Connection connection = null;
        double percentage = 0;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String query = "SELECT (wt2.AVG_temp-wt1.AVG_temp)/wt1.AVG_temp * 100 AS Percent FROM (WorldTemperature wt1 JOIN Country c1 ON c1.country_code = wt1.country_code) "
                    +
                    "JOIN (WorldTemperature wt2 JOIN Country c2 ON c2.country_code = wt2.country_code) " +
                    "WHERE wt1.year = " + start + " AND wt2.year = " + end + " AND c1.country_name = c2.country_name;";

            System.out.println(query);
            ResultSet results = statement.executeQuery(query);
            percentage = results.getDouble("Percent");
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return percentage;
    }

    public ArrayList<info> rankingYearAsc(String startyear, String endyear) {
        ArrayList<info> rankinglist = new ArrayList<info>();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String query = "SELECT c.country_name, AVG_temp, year FROM WorldTemperature wt JOIN Country c ON c.country_code = wt.country_code WHERE year >= "
                    + startyear + " AND year <= " + endyear + " ORDER BY AVG_temp ASC;";
            System.out.println(query);
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                info info = new info();
                info.year = results.getInt("year");
                info.AVGtemp = results.getDouble("AVG_temp");
                rankinglist.add(info);
            }
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return rankinglist;
    }

    public ArrayList<info> rankingYearDesc(String startyear, String endyear) {
        ArrayList<info> rankinglist = new ArrayList<info>();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String query = "SELECT c.country_name, AVG_temp, year FROM WorldTemperature wt JOIN Country c ON c.country_code = wt.country_code WHERE year >= "
                    + startyear + " AND year <= " + endyear + " ORDER BY AVG_temp DESC;";
            System.out.println(query);
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                info info = new info();
                info.year = results.getInt("year");
                info.AVGtemp = results.getDouble("AVG_temp");
                rankinglist.add(info);
            }
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return rankinglist;
    }

    public long getPopNum(String year) {
        Connection connection = null;
        long pop = 0;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String query = "SELECT DISTINCT pop_num FROM Population p WHERE p.country_code='WLD' AND p.year= " + year;

            System.out.println(query);
            ResultSet results = statement.executeQuery(query);
            pop = results.getLong("pop_num");
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return pop;
    }

    public ArrayList<info> lvl2Bquery(String one, String two, String three) {
        ArrayList<info> lvl2Blist = new ArrayList<info>();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String query = "SELECT CT.year,  c.country_name, CASE WHEN CT.year >= 1960 THEN P.POP_NUM ELSE NULL END AS pop_num, AVG_temp, MIN_temp, MAX_temp FROM CountryTemperature CT JOIN Country C ON CT.country_code = C.country_code LEFT JOIN Population P ON CT.country_code = P.country_code AND CT.year = P.year  WHERE CT.year >= "
                    + one + " AND CT.year <= " + two + " AND c.country_name like '%" + three
                    + "%' GROUP BY CT.year, P.POP_NUM";

            System.out.println(query);
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                info info = new info();
                info.year = results.getInt("year");
                info.country = results.getString("country_name");
                info.population = results.getLong("pop_num");
                info.AVGtemp = results.getDouble("AVG_temp");
                info.Mintemp = results.getDouble("MIN_temp");
                info.Maxtemp = results.getDouble("MAX_TEMP");
                lvl2Blist.add(info);
            }
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return lvl2Blist;

    }

    public ArrayList<info> readCountryInfo(String one, String two) {
        ArrayList<info> infos = new ArrayList<info>();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String query = "SELECT CT.year,  c.country_name, CASE WHEN CT.year >= 1960 THEN P.POP_NUM ELSE NULL END AS pop_num, AVG_temp, MIN_temp, MAX_temp FROM CountryTemperature CT JOIN Country C ON CT.country_code = C.country_code LEFT JOIN Population P ON CT.country_code = P.country_code AND CT.year = P.year  WHERE CT.year = "
                    + one + " AND c.country_name like '%" + two
                    + "%' GROUP BY CT.year, P.POP_NUM";

            System.out.println(query);
            ResultSet results = statement.executeQuery(query);

            info info = new info();
            info.year = results.getInt("year");
            info.population = results.getLong("pop_num");
            info.AVGtemp = results.getDouble("AVG_temp");
            info.Mintemp = results.getDouble("MIN_temp");
            info.Maxtemp = results.getDouble("MAX_TEMP");
            infos.add(info);

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return infos;

    }

    public double percentPop(String one, String two, String three) {
        Connection connection = null;
        double percentage = 0;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String query = "SELECT (p2.pop_num-p1.pop_num)/p1.pop_num * 100 AS Percent FROM (Population p1 JOIN Country c1 ON c1.country_code = p1.country_code) "
                    +
                    "JOIN (Population p2 JOIN Country c2 ON c2.country_code = p2.country_code) " +
                    "WHERE p1.year = " + one + " AND p2.year = " + two + " AND c1.country_name = c2.country_name;";

            System.out.println(query);
            ResultSet results = statement.executeQuery(query);
            percentage = results.getDouble("Percent");
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return percentage;
    }

    public double percentTempChange(String one, String two, String country) {
        Connection connection = null;
        double percentage = 0;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String query = "SELECT (wt2.AVG_temp-wt1.AVG_temp)/wt1.AVG_temp *100 AS Percent FROM (CountryTemperature wt1 JOIN Country c1 ON c1.country_code = wt1.country_code) \n"
                    + //
                    "JOIN (CountryTemperature wt2 JOIN Country c2 ON c2.country_code = wt2.country_code) \n" + //
                    "WHERE wt1.year = " + one + " AND wt2.year = " + two
                    + " AND c1.country_name = c2.country_name AND c1.country_name = '" + country + "';";

            System.out.println(query);
            ResultSet results = statement.executeQuery(query);
            percentage = results.getDouble("Percent");
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return percentage;
    }

    public long getPopulation(String year, String country) {
        Connection connection = null;
        long population = 0;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String query = "SELECT DISTINCT pop_num FROM Population p JOIN Country c ON p.country_code = c.country_code WHERE c.country_name = '"
                    + country + "' and p.year = " + year;
            System.out.println(query);
            ResultSet results = statement.executeQuery(query);
            population = results.getLong("pop_num");
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return population;
    }

    public double percentMinTempChange(String start, String end, String country) {
        Connection connection = null;
        double percentage = 0;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String query = "SELECT (ct2.MIN_temp - ct1.MIN_temp) / ct1.MIN_temp * 100 AS Percent FROM (CountryTemperature ct1 JOIN Country c1 ON ct1.country_code = c1.country_code) \n"
                    + "JOIN (CountryTemperature ct2 JOIN Country c2 ON ct2.country_code = c2.country_code) \n"
                    + "WHERE ct1.year = " + start + " and ct2.year = " + end
                    + " AND c1.country_name = c2.country_name AND c1.country_name = '" + country + "';";

            System.out.println(query);
            ResultSet results = statement.executeQuery(query);
            percentage = results.getDouble("Percent");
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return percentage;
    }

    public double percentMaxTempChange(String start, String end, String country) {
        Connection connection = null;
        double percentage = 0;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String query = "SELECT (ct2.MAX_temp - ct1.MAX_temp) / ct1.MAX_temp * 100 AS Percent FROM (CountryTemperature ct1 JOIN Country c1 ON ct1.country_code = c1.country_code) \n"
                    + "JOIN (CountryTemperature ct2 JOIN Country c2 ON ct2.country_code = c2.country_code) \n"
                    + "WHERE ct1.year = " + start + " and ct2.year = " + end
                    + " AND c1.country_name = c2.country_name AND c1.country_name = '" + country + "';";

            System.out.println(query);
            ResultSet results = statement.executeQuery(query);
            percentage = results.getDouble("Percent");
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return percentage;

    }

    // read state info
    public ArrayList<info> readStateInfo(String one, String two, String three, String foru) {
        ArrayList<info> infos = new ArrayList<info>();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String query = "SELECT ST.year, ST.state_name, ST.AVG_temp, ST.MIN_temp, ST.MAX_temp FROM COUNTRY C JOIN StateTemperature ST ON (C.COUNTRY_CODE = ST.COUNTRY_CODE) "
                    +
                    "WHERE  ST.year >=" + one + " AND ST.year <=" + two + " AND C.COUNTRY_NAME LIKE '" + three
                    + "%' AND ST.STATE_NAME LIKE '" + foru + "%';";

            System.out.println(query);
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                info info = new info();
                info.year = results.getInt("year");
                info.stateName = results.getString("state_name");
                info.AVGtemp = results.getDouble("AVG_temp");
                info.Mintemp = results.getDouble("MIN_temp");
                info.Maxtemp = results.getDouble("MAX_temp");
                infos.add(info);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return infos;

    }

    // Read city info
    public ArrayList<info> readCityInfo(String one, String two, String three, String four) {
        ArrayList<info> infos = new ArrayList<info>();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String query = "SELECT CT.year, CT.city_name, CT.AVG_temp, CT.MIN_temp, CT.MAX_temp" +
                    " FROM COUNTRY C JOIN CityTemperature CT ON (C.COUNTRY_CODE = CT.COUNTRY_CODE)" +
                    " WHERE  CT.year >= " + one + " AND CT.year <= " + two + " AND C.COUNTRY_NAME LIKE '%" + three
                    + "%'  AND CT.CITY_NAME LIKE '" + four + "%';";

            System.out.println(query);
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                info info = new info();
                info.year = results.getInt("year");
                info.cityName = results.getString("city_name");
                info.AVGtemp = results.getDouble("AVG_temp");
                info.Mintemp = results.getDouble("MIN_temp");
                info.Maxtemp = results.getDouble("MAX_temp");
                infos.add(info);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return infos;

    }

    public ArrayList<info> cityReader(String one, String two, String three) {
        ArrayList<info> cityData = new ArrayList<info>();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String query = "SELECT CT.year, CT.city_name, CT.AVG_temp, CT.MIN_temp, CT.MAX_temp \n" +
                    " FROM COUNTRY C JOIN CityTemperature CT ON (C.COUNTRY_CODE = CT.COUNTRY_CODE) \n" +
                    " WHERE  CT.year = " + one + " AND C.COUNTRY_NAME LIKE '%" + two + "%' AND CT.CITY_NAME LIKE '"
                    + three + "%'";

            System.out.println(query);
            ResultSet results = statement.executeQuery(query);

            info info = new info();
            info.year = results.getInt("year");
            info.AVGtemp = results.getDouble("AVG_temp");
            info.Mintemp = results.getDouble("MIN_temp");
            info.Maxtemp = results.getDouble("MAX_TEMP");
            cityData.add(info);

            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return cityData;

    }

    public ArrayList<info> stateReader(String one, String two, String three) {
        ArrayList<info> stateData = new ArrayList<info>();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String query = "SELECT S.year, S.state_name, S.AVG_temp, S.MIN_temp, S.MAX_temp \n" +
                    " FROM COUNTRY C JOIN StateTemperature S ON (C.COUNTRY_CODE = S.COUNTRY_CODE) \n" +
                    " WHERE  S.year = " + one + " AND C.COUNTRY_NAME LIKE '%" + two + "%' AND S.STATE_NAME LIKE '"
                    + three + "%'";

            System.out.println(query);
            ResultSet results = statement.executeQuery(query);

            info info = new info();
            info.year = results.getInt("year");
            info.AVGtemp = results.getDouble("AVG_temp");
            info.Mintemp = results.getDouble("MIN_temp");
            info.Maxtemp = results.getDouble("MAX_TEMP");
            stateData.add(info);
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return stateData;

    }

    public double getAVGchangeState(String start, String end, String country, String state) {
        Connection connection = null;
        double percentage = 0;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            String query = "SELECT (st2.AVG_temp-st1.AVG_temp)/st1.AVG_temp * 100 AS Percent FROM (StateTemperature st1 JOIN Country c1 ON c1.country_code = st1.country_code) \n"
                    + "JOIN (StateTemperature st2 JOIN Country c2 ON c2.country_code = st2.country_code) \n"
                    + "WHERE st1.year = " + start + " and st2.year = " + end
                    + " AND c1.country_name = c2.country_name AND c1.country_name = '" + country
                    + "' AND st1.state_name LIKE '%" + state + "%' AND st2.state_name LIKE '" + state + "%';";
            System.out.println(query);
            ResultSet results = statement.executeQuery(query);
            percentage = results.getDouble("Percent");
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return percentage;
    }

    public double getAVGchangeCity(String start, String end, String country, String city) {
        Connection connection = null;
        double percentage = 0;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            String query = "SELECT (st2.AVG_temp-st1.AVG_temp)/st1.AVG_temp * 100 AS Percent FROM (CityTemperature st1 JOIN Country c1 ON c1.country_code = st1.country_code) \n"
                    + "JOIN (CityTemperature st2 JOIN Country c2 ON c2.country_code = st2.country_code) \n"
                    + "WHERE st1.year = " + start + " and st2.year = " + end
                    + " AND c1.country_name = c2.country_name AND c1.country_name = '" + country
                    + "' AND st1.city_name LIKE '%" + city + "%' AND st2.city_name LIKE '" + city + "%';";
            System.out.println(query);
            ResultSet results = statement.executeQuery(query);
            percentage = results.getDouble("Percent");
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return percentage;
    }

    public double MinChangeState(String start, String end, String state, String country) {
        Connection connection = null;
        double percentage = 0;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            String query = "SELECT (st2.Min_temp-st1.Min_temp)/st1.Min_temp * 100 AS Percent FROM (StateTemperature st1 JOIN Country c1 ON c1.country_code = st1.country_code) \n"
                    + "JOIN (StateTemperature st2 JOIN Country c2 ON c2.country_code = st2.country_code) \n"
                    + "WHERE st1.year = " + start + " and st2.year = " + end
                    + " AND c1.country_name = c2.country_name AND c1.country_name = '" + country
                    + "' AND st1.state_name LIKE '%" + state + "%' AND st2.state_name LIKE '" + state + "%';";
            System.out.println(query);
            ResultSet results = statement.executeQuery(query);
            percentage = results.getDouble("Percent");
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return percentage;
    }

    public double MaxChangeState(String start, String end, String state, String country) {
        Connection connection = null;
        double percentage = 0;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            String query = "SELECT (st2.Max_temp-st1.Max_temp)/st1.Max_temp * 100 AS Percent FROM (StateTemperature st1 JOIN Country c1 ON c1.country_code = st1.country_code) \n"
                    + "JOIN (StateTemperature st2 JOIN Country c2 ON c2.country_code = st2.country_code) \n"
                    + "WHERE st1.year = " + start + " and st2.year = " + end
                    + " AND c1.country_name = c2.country_name AND c1.country_name = '" + country
                    + "' AND st1.state_name LIKE '%" + state + "%' AND st2.state_name LIKE '" + state + "%';";
            System.out.println(query);
            ResultSet results = statement.executeQuery(query);
            percentage = results.getDouble("Percent");
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return percentage;
    }

    public double MinChangeCity(String start, String end, String city, String country) {
        Connection connection = null;
        double percentage = 0;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            String query = "SELECT (st2.MIN_temp-st1.MIN_temp)/st1.MIN_temp * 100 AS Percent FROM (CityTemperature st1 JOIN Country c1 ON c1.country_code = st1.country_code) \n"
                    + "JOIN (CityTemperature st2 JOIN Country c2 ON c2.country_code = st2.country_code) \n"
                    + "WHERE st1.year = " + start + " and st2.year = " + end
                    + " AND c1.country_name = c2.country_name AND c1.country_name = '" + country
                    + "' AND st1.city_name LIKE '%" + city + "%' AND st2.city_name LIKE '" + city + "%';";
            System.out.println(query);
            ResultSet results = statement.executeQuery(query);
            percentage = results.getDouble("Percent");
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return percentage;

    }

    public double MaxChangeCity(String start, String end, String city, String country) {
        Connection connection = null;
        double percentage = 0;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            String query = "SELECT (st2.MAX_temp-st1.MAX_temp)/st1.MAX_temp * 100 AS Percent FROM (CityTemperature st1 JOIN Country c1 ON c1.country_code = st1.country_code) \n"
                    + "JOIN (CityTemperature st2 JOIN Country c2 ON c2.country_code = st2.country_code) \n"
                    + "WHERE st1.year = " + start + " and st2.year = " + end
                    + " AND c1.country_name = c2.country_name AND c1.country_name = '" + country
                    + "' AND st1.city_name LIKE '%" + city + "%' AND st2.city_name LIKE '" + city + "%';";
            System.out.println(query);
            ResultSet results = statement.executeQuery(query);
            percentage = results.getDouble("Percent");
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return percentage;

    }

    public ArrayList<info> lvl2AVGTempRankingASC(String one, String two, String three) {
        ArrayList<info> AVGTempRanking = new ArrayList<info>();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String query = "SELECT CT.year,  c.country_name, AVG_temp FROM CountryTemperature CT JOIN Country C ON CT.country_code = C.country_code WHERE CT.year >= "
                    + one + " AND CT.year <= " + two + " AND c.country_name like '%" + three
                    + "%' ORDER BY AVG_temp ASC";

            System.out.println(query);
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                info info = new info();
                info.year = results.getInt("year");
                info.AVGtemp = results.getDouble("AVG_temp");
                AVGTempRanking.add(info);
            }
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return AVGTempRanking;

    }

    public ArrayList<info> lvl2AVGTempRankingDESC(String one, String two, String three) {
        ArrayList<info> AVGTempRanking = new ArrayList<info>();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String query = "SELECT CT.year,  c.country_name, AVG_temp FROM CountryTemperature CT JOIN Country C ON CT.country_code = C.country_code WHERE CT.year >= "
                    + one + " AND CT.year <= " + two + " AND c.country_name like '%" + three
                    + "%' ORDER BY AVG_temp DESC";

            System.out.println(query);
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                info info = new info();
                info.year = results.getInt("year");
                info.AVGtemp = results.getDouble("AVG_temp");
                AVGTempRanking.add(info);
            }
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return AVGTempRanking;

    }

    public ArrayList<info> lvl2MinTempRankingASC(String one, String two, String three) {
        ArrayList<info> MinTempRanking = new ArrayList<info>();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String query = "SELECT CT.year,  c.country_name, MIN_temp FROM CountryTemperature CT JOIN Country C ON CT.country_code = C.country_code WHERE CT.year >= "
                    + one + " AND CT.year <= " + two + " AND c.country_name like '%" + three
                    + "%' ORDER BY MIN_temp ASC";

            System.out.println(query);
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                info info = new info();
                info.year = results.getInt("year");
                info.Mintemp = results.getDouble("MIN_temp");
                MinTempRanking.add(info);
            }
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return MinTempRanking;

    }

    public ArrayList<info> lvl2MinTempRankingDESC(String one, String two, String three) {
        ArrayList<info> MinTempRanking = new ArrayList<info>();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String query = "SELECT CT.year,  c.country_name, MIN_temp FROM CountryTemperature CT JOIN Country C ON CT.country_code = C.country_code WHERE CT.year >= "
                    + one + " AND CT.year <= " + two + " AND c.country_name like '%" + three
                    + "%' ORDER BY MIN_temp DESC";

            System.out.println(query);
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                info info = new info();
                info.year = results.getInt("year");
                info.Mintemp = results.getDouble("MIN_temp");
                MinTempRanking.add(info);
            }
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return MinTempRanking;

    }

    public ArrayList<info> lvl2MaxTempRankingASC(String one, String two, String three) {
        ArrayList<info> MaxTempRanking = new ArrayList<info>();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String query = "SELECT CT.year,  c.country_name, MAX_temp FROM CountryTemperature CT JOIN Country C ON CT.country_code = C.country_code WHERE CT.year >= "
                    + one + " AND CT.year <= " + two + " AND c.country_name like '%" + three
                    + "%' ORDER BY MAX_temp ASC";

            System.out.println(query);
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                info info = new info();
                info.year = results.getInt("year");
                info.Maxtemp = results.getDouble("MAX_temp");
                MaxTempRanking.add(info);
            }
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return MaxTempRanking;

    }

    public ArrayList<info> lvl2MaxTempRankingDESC(String one, String two, String three) {
        ArrayList<info> MaxTempRanking = new ArrayList<info>();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String query = "SELECT CT.year,  c.country_name, MAX_temp FROM CountryTemperature CT JOIN Country C ON CT.country_code = C.country_code WHERE CT.year >= "
                    + one + " AND CT.year <= " + two + " AND c.country_name like '%" + three
                    + "%' ORDER BY MAX_temp DESC";

            System.out.println(query);
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                info info = new info();
                info.year = results.getInt("year");
                info.Maxtemp = results.getDouble("MAX_temp");
                MaxTempRanking.add(info);
            }
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return MaxTempRanking;

    }

    public ArrayList<info> lvl2PopulationRankingASC(String one, String two, String three) {
        ArrayList<info> PopulationRanking = new ArrayList<info>();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String query = "SELECT CT.year,  c.country_name, CASE WHEN CT.year >= 1960 THEN P.POP_NUM ELSE NULL END AS pop_num FROM CountryTemperature CT JOIN Country C ON CT.country_code = C.country_code LEFT JOIN Population P ON CT.country_code = P.country_code AND CT.year = P.year  WHERE CT.year >= "
                    + one + " AND CT.year <= " + two + " AND c.country_name like '%" + three
                    + "%' ORDER BY pop_num ASC";

            System.out.println(query);
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                info info = new info();
                info.year = results.getInt("year");
                info.population = results.getLong("pop_num");
                PopulationRanking.add(info);
            }
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return PopulationRanking;

    }

    public ArrayList<info> lvl2PopulationRankingDESC(String one, String two, String three) {
        ArrayList<info> PopulationRanking = new ArrayList<info>();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String query = "SELECT CT.year,  c.country_name, CASE WHEN CT.year >= 1960 THEN P.POP_NUM ELSE NULL END AS pop_num FROM CountryTemperature CT JOIN Country C ON CT.country_code = C.country_code LEFT JOIN Population P ON CT.country_code = P.country_code AND CT.year = P.year  WHERE CT.year >= "
                    + one + " AND CT.year <= " + two + " AND c.country_name like '%" + three
                    + "%' ORDER BY pop_num DESC";

            System.out.println(query);
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                info info = new info();
                info.year = results.getInt("year");
                info.population = results.getLong("pop_num");
                PopulationRanking.add(info);
            }
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return PopulationRanking;

    }

    public ArrayList<info> getallState(String one) {

        ArrayList<info> state = new ArrayList<info>();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String query = "SELECT DISTINCT st.state_name FROM StateTemperature st JOIN Country c ON st.country_code = c.country_code \n"
                    + //
                    "WHERE c.country_name = '" + one + "'";

            System.out.println(query);
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                info info = new info();
                info.stateName = results.getString("state_name");
                state.add(info);
            }
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return state;
    }

    public ArrayList<info> WorldMinTemprankingYearAsc(String startyear, String endyear) {
        ArrayList<info> rankinglist = new ArrayList<info>();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String query = "SELECT c.country_name, MIN_temp, year FROM WorldTemperature wt JOIN Country c ON c.country_code = wt.country_code WHERE year >= "
                    + startyear + " AND year <= " + endyear + " ORDER BY MIN_temp ASC;";
            System.out.println(query);
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                info info = new info();
                info.year = results.getInt("year");
                info.Mintemp = results.getDouble("MIN_temp");
                rankinglist.add(info);
            }
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return rankinglist;
    }

    public ArrayList<info> WorldMinTemprankingYearDesc(String startyear, String endyear) {
        ArrayList<info> rankinglist = new ArrayList<info>();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String query = "SELECT c.country_name, MIN_temp, year FROM WorldTemperature wt JOIN Country c ON c.country_code = wt.country_code WHERE year >= "
                    + startyear + " AND year <= " + endyear + " ORDER BY MIN_temp DESC;";
            System.out.println(query);
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                info info = new info();
                info.year = results.getInt("year");
                info.Mintemp = results.getDouble("MIN_temp");
                rankinglist.add(info);
            }
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return rankinglist;
    }

    public ArrayList<info> WorldMaxTemprankingYearAsc(String startyear, String endyear) {
        ArrayList<info> rankinglist = new ArrayList<info>();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String query = "SELECT c.country_name, MAX_temp, year FROM WorldTemperature wt JOIN Country c ON c.country_code = wt.country_code WHERE year >= "
                    + startyear + " AND year <= " + endyear + " ORDER BY MAX_temp ASC;";
            System.out.println(query);
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                info info = new info();
                info.year = results.getInt("year");
                info.Maxtemp = results.getDouble("MAX_temp");
                rankinglist.add(info);
            }
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return rankinglist;
    }

    public ArrayList<info> WorldMaxTemprankingYearDesc(String startyear, String endyear) {
        ArrayList<info> rankinglist = new ArrayList<info>();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String query = "SELECT c.country_name, MAX_temp, year FROM WorldTemperature wt JOIN Country c ON c.country_code = wt.country_code WHERE year >= "
                    + startyear + " AND year <= " + endyear + " ORDER BY MAX_temp DESC;";
            System.out.println(query);
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                info info = new info();
                info.year = results.getInt("year");
                info.Maxtemp = results.getDouble("MAX_temp");
                rankinglist.add(info);
            }
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return rankinglist;
    }

    public ArrayList<info> WorldPopulationRankingYearAsc(String startyear, String endyear) {
        ArrayList<info> rankinglist = new ArrayList<info>();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String query = "SELECT wt.year, pop_num FROM WorldTemperature wt JOIN Population p  WHERE wt.year>="
                    + startyear + " AND wt.year<=" + endyear
                    + " AND p.country_code= 'WLD' AND p.year = wt.year ORDER BY pop_num ASC";
            System.out.println(query);
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                info info = new info();
                info.year = results.getInt("year");
                info.population = results.getLong("pop_num");
                rankinglist.add(info);
            }
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return rankinglist;
    }

    public ArrayList<info> WorldPopulationRankingYearDesc(String startyear, String endyear) {
        ArrayList<info> rankinglist = new ArrayList<info>();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String query = "SELECT wt.year, pop_num FROM WorldTemperature wt JOIN Population p  WHERE wt.year>="
                    + startyear + " AND wt.year<=" + endyear
                    + " AND p.country_code= 'WLD' AND p.year = wt.year ORDER BY pop_num DESC";
            System.out.println(query);
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                info info = new info();
                info.year = results.getInt("year");
                info.population = results.getLong("pop_num");
                rankinglist.add(info);
            }
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return rankinglist;
    }

    public ArrayList<info> StateAVGTemprankingYearAsc(String startyear, String endyear, String country, String state) {
        ArrayList<info> rankinglist = new ArrayList<info>();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String query = "SELECT ST.year, ST.AVG_temp FROM COUNTRY C JOIN StateTemperature ST ON (C.COUNTRY_CODE = ST.COUNTRY_CODE) \n"
                    + "WHERE ST.year >= " + startyear + " AND ST.year <= " + endyear + " AND C.COUNTRY_NAME LIKE '%"
                    + country + "%' AND ST.STATE_NAME LIKE '" + state + "%' ORDER BY ST.AVG_TEMP ASC;";

            System.out.println(query);
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                info info = new info();
                info.year = results.getInt("year");
                info.AVGtemp = results.getDouble("AVG_temp");
                rankinglist.add(info);
            }
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return rankinglist;
    }

    public ArrayList<info> StateAVGTemprankingYearDesc(String startyear, String endyear, String country, String state) {
        ArrayList<info> rankinglist = new ArrayList<info>();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String query = "SELECT ST.year, ST.AVG_temp FROM COUNTRY C JOIN StateTemperature ST ON (C.COUNTRY_CODE = ST.COUNTRY_CODE) \n"
                    + "WHERE ST.year >= " + startyear + " AND ST.year <= " + endyear + " AND C.COUNTRY_NAME LIKE '%"
                    + country + "%' AND ST.STATE_NAME LIKE '" + state + "%' ORDER BY ST.AVG_TEMP DESC;";

            System.out.println(query);
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                info info = new info();
                info.year = results.getInt("year");
                info.AVGtemp = results.getDouble("AVG_temp");
                rankinglist.add(info);
            }
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return rankinglist;
    }

    public ArrayList<info> StateMinTemprankingYearAsc(String startyear, String endyear, String country, String state) {
        ArrayList<info> rankinglist = new ArrayList<info>();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String query = "SELECT ST.year, ST.MIN_temp FROM COUNTRY C JOIN StateTemperature ST ON (C.COUNTRY_CODE = ST.COUNTRY_CODE) \n"
                    + "WHERE ST.year >= " + startyear + " AND ST.year <= " + endyear + " AND C.COUNTRY_NAME LIKE '%"
                    + country + "%' AND ST.STATE_NAME LIKE '" + state + "%' ORDER BY ST.MIN_TEMP ASC;";

            System.out.println(query);
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                info info = new info();
                info.year = results.getInt("year");
                info.Mintemp = results.getDouble("MIN_temp");
                rankinglist.add(info);
            }
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return rankinglist;
    }

    public ArrayList<info> StateMinTemprankingYearDesc(String startyear, String endyear, String country, String state) {
        ArrayList<info> rankinglist = new ArrayList<info>();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String query = "SELECT ST.year, ST.MIN_temp FROM COUNTRY C JOIN StateTemperature ST ON (C.COUNTRY_CODE = ST.COUNTRY_CODE) \n"
                    + "WHERE ST.year >= " + startyear + " AND ST.year <= " + endyear + " AND C.COUNTRY_NAME LIKE '%"
                    + country + "%' AND ST.STATE_NAME LIKE '" + state + "%' ORDER BY ST.MIN_TEMP DESC;";

            System.out.println(query);
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                info info = new info();
                info.year = results.getInt("year");
                info.Mintemp = results.getDouble("MIN_temp");
                rankinglist.add(info);
            }
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return rankinglist;
    }

    public ArrayList<info> StateMaxTemprankingYearAsc(String startyear, String endyear, String country, String state) {
        ArrayList<info> rankinglist = new ArrayList<info>();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String query = "SELECT ST.year, ST.MAX_temp FROM COUNTRY C JOIN StateTemperature ST ON (C.COUNTRY_CODE = ST.COUNTRY_CODE) \n"
                    + "WHERE ST.year >= " + startyear + " AND ST.year <= " + endyear + " AND C.COUNTRY_NAME LIKE '%"
                    + country + "%' AND ST.STATE_NAME LIKE '" + state + "%' ORDER BY ST.MAX_TEMP ASC;";

            System.out.println(query);
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                info info = new info();
                info.year = results.getInt("year");
                info.Maxtemp = results.getDouble("MAX_temp");
                rankinglist.add(info);
            }
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return rankinglist;
    }

    public ArrayList<info> StateMaxTemprankingYearDesc(String startyear, String endyear, String country, String state) {
        ArrayList<info> rankinglist = new ArrayList<info>();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String query = "SELECT ST.year, ST.MAX_temp FROM COUNTRY C JOIN StateTemperature ST ON (C.COUNTRY_CODE = ST.COUNTRY_CODE) \n"
                    + "WHERE ST.year >= " + startyear + " AND ST.year <= " + endyear + " AND C.COUNTRY_NAME LIKE '%"
                    + country + "%' AND ST.STATE_NAME LIKE '" + state + "%' ORDER BY ST.MAX_TEMP DESC;";

            System.out.println(query);
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                info info = new info();
                info.year = results.getInt("year");
                info.Maxtemp = results.getDouble("MAX_temp");
                rankinglist.add(info);
            }
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return rankinglist;
    }

    // Nghia's work
    public ArrayList<info> CityAVGTemprankingYearAsc(String startyear, String endyear, String country, String city) {
        ArrayList<info> rankinglist = new ArrayList<info>();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String query = "SELECT CT.year, CT.city_name, CT.AVG_temp, CT.MIN_temp, CT.MAX_temp FROM COUNTRY C JOIN CityTemperature CT ON (C.COUNTRY_CODE = CT.COUNTRY_CODE) "
                    +
                    "WHERE  CT.year >=" + startyear + " AND CT.year <=" + endyear + " AND C.COUNTRY_NAME LIKE '%"
                    + country + "%' AND CT.CITY_NAME LIKE '" + city + "%' ORDER BY CT.AVG_TEMP ASC;";

            System.out.println(query);
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                info info = new info();
                info.year = results.getInt("year");
                info.AVGtemp = results.getDouble("AVG_temp");
                rankinglist.add(info);
            }
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return rankinglist;
    }

    public ArrayList<info> CityAVGTemprankingYearDesc(String startyear, String endyear, String country, String city) {
        ArrayList<info> rankinglist = new ArrayList<info>();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String query = "SELECT CT.year, CT.city_name, CT.AVG_temp, CT.MIN_temp, CT.MAX_temp FROM COUNTRY C JOIN CityTemperature CT ON (C.COUNTRY_CODE = CT.COUNTRY_CODE) "
                    +
                    "WHERE  CT.year >=" + startyear + " AND CT.year <=" + endyear + " AND C.COUNTRY_NAME LIKE '%"
                    + country + "%' AND CT.CITY_NAME LIKE '" + city + "%' ORDER BY CT.AVG_TEMP DESC;";

            System.out.println(query);
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                info info = new info();
                info.year = results.getInt("year");
                info.AVGtemp = results.getDouble("AVG_temp");
                rankinglist.add(info);
            }
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return rankinglist;
    }

    public ArrayList<info> CityMinTemprankingYearAsc(String startyear, String endyear, String country, String city) {
        ArrayList<info> rankinglist = new ArrayList<info>();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String query = "SELECT CT.year, CT.city_name, CT.AVG_temp, CT.MIN_temp, CT.MAX_temp FROM COUNTRY C JOIN CityTemperature CT ON (C.COUNTRY_CODE = CT.COUNTRY_CODE) "
                    +
                    "WHERE  CT.year >=" + startyear + " AND CT.year <=" + endyear + " AND C.COUNTRY_NAME LIKE '%"
                    + country + "%' AND CT.CITY_NAME LIKE '" + city + "%' ORDER BY CT.MIN_TEMP ASC;";

            System.out.println(query);
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                info info = new info();
                info.year = results.getInt("year");
                info.Mintemp = results.getDouble("MIN_temp");
                rankinglist.add(info);
            }
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return rankinglist;
    }

    public ArrayList<info> CityMinTemprankingYearDesc(String startyear, String endyear, String country, String city) {
        ArrayList<info> rankinglist = new ArrayList<info>();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String query = "SELECT CT.year, CT.city_name, CT.AVG_temp, CT.MIN_temp, CT.MAX_temp FROM COUNTRY C JOIN CityTemperature CT ON (C.COUNTRY_CODE = CT.COUNTRY_CODE) "
                    +
                    "WHERE  CT.year >=" + startyear + " AND CT.year <=" + endyear + " AND C.COUNTRY_NAME LIKE '%"
                    + country + "%' AND CT.CITY_NAME LIKE '" + city + "%' ORDER BY CT.MIN_TEMP DESC;";

            System.out.println(query);
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                info info = new info();
                info.year = results.getInt("year");
                info.Mintemp = results.getDouble("MIN_temp");
                rankinglist.add(info);
            }
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return rankinglist;
    }

    public ArrayList<info> CityMaxTemprankingYearAsc(String startyear, String endyear, String country, String city) {
        ArrayList<info> rankinglist = new ArrayList<info>();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String query = "SELECT CT.year, CT.city_name, CT.AVG_temp, CT.MIN_temp, CT.MAX_temp FROM COUNTRY C JOIN CityTemperature CT ON (C.COUNTRY_CODE = CT.COUNTRY_CODE) "
                    +
                    "WHERE  CT.year >=" + startyear + " AND CT.year <=" + endyear + " AND C.COUNTRY_NAME LIKE '%"
                    + country + "%' AND CT.CITY_NAME LIKE '" + city + "%' ORDER BY CT.MAX_TEMP ASC;";

            System.out.println(query);
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                info info = new info();
                info.year = results.getInt("year");
                info.Maxtemp = results.getDouble("MAX_temp");
                rankinglist.add(info);
            }
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return rankinglist;
    }

    public ArrayList<info> CityMaxTemprankingYearDesc(String startyear, String endyear, String country, String city) {
        ArrayList<info> rankinglist = new ArrayList<info>();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String query = "SELECT CT.year, CT.city_name, CT.AVG_temp, CT.MIN_temp, CT.MAX_temp FROM COUNTRY C JOIN CityTemperature CT ON (C.COUNTRY_CODE = CT.COUNTRY_CODE) "
                    +
                    "WHERE  CT.year >=" + startyear + " AND CT.year <=" + endyear + " AND C.COUNTRY_NAME LIKE '%"
                    + country + "%' AND CT.CITY_NAME LIKE '" + city + "%' ORDER BY CT.MAX_TEMP DESC;";

            System.out.println(query);
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                info info = new info();
                info.year = results.getInt("year");
                info.Maxtemp = results.getDouble("MAX_temp");
                rankinglist.add(info);
            }
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return rankinglist;
    }

    // For level 3 page - Find similar region by AVG temperature
    public ArrayList<info> AVGTempSimilarRegion(String one, String two) {
        ArrayList<info> AVGTempSimilar = new ArrayList<info>();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String query = "SELECT DISTINCT C.COUNTRY_NAME, P.YEAR, P.POP_NUM, CT.AVG_TEMP \n"
                    + "FROM COUNTRY C \n"
                    + "JOIN POPULATION P ON C.COUNTRY_CODE = P.COUNTRY_CODE \n"
                    + "JOIN COUNTRYTEMPERATURE CT ON C.COUNTRY_CODE = CT.COUNTRY_CODE  \n"
                    + "WHERE CT.AVG_TEMP >= " + one + " AND AVG_TEMP <= " + two + " AND CT.AVG_TEMP IS NOT NULL \n"
                    + "ORDER BY RANDOM()"
                    + "LIMIT 5;";

            System.out.println(query);
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                info info = new info();
                info.country = results.getString("Country_name");
                info.year = results.getInt("year");
                info.population = results.getLong("pop_num");
                info.AVGtemp = results.getDouble("AVG_temp");
                AVGTempSimilar.add(info);
            }
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return AVGTempSimilar;
    }

    // Level 3 page - Find similar region by population
    public ArrayList<info> PopulationSimilarRegion(String one, String two) {
        ArrayList<info> PopSimilar = new ArrayList<info>();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String query = "SELECT DISTINCT C.COUNTRY_NAME, P.YEAR, P.POP_NUM, CT.AVG_TEMP \n"
                    + "FROM COUNTRY C \n"
                    + "JOIN POPULATION P ON C.COUNTRY_CODE = P.COUNTRY_CODE \n"
                    + "JOIN COUNTRYTEMPERATURE CT ON C.COUNTRY_CODE = CT.COUNTRY_CODE  \n"
                    + "WHERE P.POP_NUM >= " + one + " AND P.POP_NUM <= " + two + " AND P.POP_NUM IS NOT NULL \n"
                    + "ORDER BY RANDOM()"
                    + "LIMIT 5;";

            System.out.println(query);
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                info info = new info();
                info.country = results.getString("Country_name");
                info.year = results.getInt("year");
                info.population = results.getLong("pop_num");
                info.AVGtemp = results.getDouble("AVG_temp");
                PopSimilar.add(info);
            }
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return PopSimilar;
    }

    // Level 3 - Find similar data from countries.
    public ArrayList<info> CountrySimilar(String startYear, String endYear, String country) {
        ArrayList<info> CountrySimilar = new ArrayList<info>();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String query = "SELECT C.COUNTRY_NAME,MIN(P.YEAR) AS START_YEAR,MAX(P.YEAR) AS END_YEAR, AVG(P.POP_NUM) AS pop_num, AVG(CT.AVG_TEMP) AS AVG_temp \n"
                    + "FROM COUNTRY C JOIN POPULATION P ON C.COUNTRY_CODE = P.COUNTRY_CODE  \n"
                    + "JOIN COUNTRYTEMPERATURE CT ON CT.COUNTRY_CODE = P.COUNTRY_CODE  \n"
                    + "WHERE (P.YEAR BETWEEN " + startYear + " AND " + endYear + ") AND COUNTRY_NAME LIKE '%" + country
                    + "%'";

            System.out.println(query);
            ResultSet results = statement.executeQuery(query);

            info info = new info();
            info.country = results.getString("Country_name");
            info.year = results.getInt("start_year");
            info.year2 = results.getInt("end_year");
            info.population = results.getLong("pop_num");
            info.AVGtemp = results.getDouble("AVG_temp");
            CountrySimilar.add(info);

            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return CountrySimilar;
    }

    // Level 3 - Find similar data from cities
    public ArrayList<info> CitySimilar(String startYear, String endYear, String country, String city) {
        ArrayList<info> CitySimilar = new ArrayList<info>();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String query = "SELECT CT.CITY_NAME,MIN(P.YEAR) AS START_YEAR,MAX(P.YEAR) AS END_YEAR, AVG(P.POP_NUM) IS NULL, AVG(CT.AVG_TEMP) AS AVG_temp  \n"
                    + "FROM COUNTRY C JOIN POPULATION P ON C.COUNTRY_CODE = P.COUNTRY_CODE  \n"
                    + "JOIN CITYTEMPERATURE CT ON CT.COUNTRY_CODE = P.COUNTRY_CODE   \n"
                    + "WHERE (P.YEAR BETWEEN " + startYear + " AND " + endYear + ") AND C.COUNTRY_NAME LIKE '%"
                    + country + "%' AND CT.CITY_NAME LIKE '%" + city + "%'";

            System.out.println(query);
            ResultSet results = statement.executeQuery(query);

            info info = new info();
            info.cityName = results.getString("City_name");
            info.year = results.getInt("start_year");
            info.year2 = results.getInt("end_year");
            info.AVGtemp = results.getDouble("AVG_temp");
            CitySimilar.add(info);

            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return CitySimilar;
    }

    // Level 3 - Find similar data from states
    public ArrayList<info> StateSimilar(String startYear, String endYear, String country, String state) {
        ArrayList<info> StateSimilar = new ArrayList<info>();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String query = "SELECT ST.STATE_NAME,MIN(P.YEAR) AS START_YEAR,MAX(P.YEAR) AS END_YEAR, AVG(P.POP_NUM) IS NULL, AVG(ST.AVG_TEMP) AS AVG_temp \n"
                    + "FROM COUNTRY C JOIN POPULATION P ON C.COUNTRY_CODE = P.COUNTRY_CODE  \n"
                    + "JOIN  STATETEMPERATURE ST  ON ST.COUNTRY_CODE = P.COUNTRY_CODE   \n"
                    + "WHERE (P.YEAR BETWEEN " + startYear + " AND " + endYear + ") AND C.COUNTRY_NAME LIKE '%"
                    + country + "%' AND ST.STATE_NAME LIKE '%" + state + "%'";

            System.out.println(query);
            ResultSet results = statement.executeQuery(query);

            info info = new info();
            info.stateName = results.getString("State_name");
            info.year = results.getInt("start_year");
            info.year2 = results.getInt("end_year");
            info.AVGtemp = results.getDouble("AVG_temp");
            StateSimilar.add(info);

            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return StateSimilar;
    }

    // Level 3 - Find matched Country data that similar to the available data of the
    // previous query
    public ArrayList<info> CountryMatch(String one, String two) {
        ArrayList<info> CountryMatch = new ArrayList<info>();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String query = "SELECT C.COUNTRY_NAME, MIN(P.YEAR) AS START_YEAR, MAX(P.YEAR) AS END_YEAR, AVG(P.POP_NUM) AS pop_num, AVG(CT.AVG_TEMP) AS AVG_TEMP  \n"
                    + "FROM COUNTRY C   \n"
                    + "JOIN POPULATION P ON C.COUNTRY_CODE = P.COUNTRY_CODE   \n"
                    + "JOIN COUNTRYTEMPERATURE CT ON CT.COUNTRY_CODE = P.COUNTRY_CODE   \n"
                    + "WHERE C.COUNTRY_NAME IS NOT 'Antarctica'   \n"
                    + "GROUP BY C.COUNTRY_NAME   \n"
                    + "ORDER BY ABS(AVG(P.POP_NUM) - " + one + "),   \n"
                    + "ABS(AVG(CT.AVG_TEMP) - " + two + ")   \n"
                    + "LIMIT 5";

            System.out.println(query);
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                info info = new info();
                info.country = results.getString("Country_name");
                info.year = results.getInt("start_year");
                info.year2 = results.getInt("end_year");
                info.population = results.getLong("pop_num");
                info.AVGtemp = results.getDouble("AVG_temp");
                CountryMatch.add(info);
            }
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return CountryMatch;
    }

    // Level 3 - Find matched state data that similar to the available data of the
    // previous query
    public ArrayList<info> StateMatch(String one) {
        ArrayList<info> StateMatch = new ArrayList<info>();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String query = "SELECT ST.STATE_NAME, MIN(P.YEAR) AS START_YEAR, MAX(P.YEAR) AS END_YEAR, 0 AS AVG_POPULATION, AVG(ST.AVG_TEMP) AS AVG_TEMP  \n"
                    + "FROM COUNTRY C  \n"
                    + "JOIN POPULATION P ON C.COUNTRY_CODE = P.COUNTRY_CODE  \n"
                    + "JOIN STATETEMPERATURE ST ON ST.COUNTRY_CODE = P.COUNTRY_CODE  \n"
                    + "GROUP BY C.COUNTRY_NAME  \n"
                    + "ORDER BY ABS(AVG(ST.AVG_TEMP) - " + one + ")  \n"
                    + "LIMIT 5";

            System.out.println(query);
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                info info = new info();
                info.stateName = results.getString("State_name");
                info.year = results.getInt("start_year");
                info.year2 = results.getInt("end_year");
                info.AVGtemp = results.getDouble("AVG_temp");
                StateMatch.add(info);
            }
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return StateMatch;
    }

    // Level 3 - Find matched city data that similar to the available data of the
    // previous query
    public ArrayList<info> CityMatch(String one) {
        ArrayList<info> CityMatch = new ArrayList<info>();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String query = "SELECT CT.CITY_NAME, MIN(P.YEAR) AS START_YEAR, MAX(P.YEAR) AS END_YEAR, 0 AS AVG_POPULATION, AVG(CT.AVG_TEMP) AS AVG_TEMP  \n"
                    + "FROM COUNTRY C  \n"
                    + "JOIN POPULATION P ON C.COUNTRY_CODE = P.COUNTRY_CODE  \n"
                    + "JOIN CITYTEMPERATURE CT ON CT.COUNTRY_CODE = P.COUNTRY_CODE  \n"
                    + "WHERE C.COUNTRY_NAME LIKE '%AND%' GROUP BY C.COUNTRY_NAME  \n"
                    + "ORDER BY ABS(AVG(CT.AVG_TEMP) - " + one + ")  \n"
                    + "LIMIT 5";

            System.out.println(query);
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                info info = new info();
                info.cityName = results.getString("City_name");
                info.year = results.getInt("start_year");
                info.year2 = results.getInt("end_year");
                info.AVGtemp = results.getDouble("AVG_temp");
                CityMatch.add(info);
            }
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return CityMatch;
    }
    // Level 3 - Compare data of different regions 
    public ArrayList<info> CompareRegions(String start, String end) {
        ArrayList<info> CompareRegions = new ArrayList<info>();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String query = "SELECT C.COUNTRY_NAME, MIN(P.YEAR) AS START_YEAR, MAX(P.YEAR) AS END_YEAR, AVG(CT.AVG_TEMP) AS AVG_TEMP, 0.73 AS DIFF_IN_AVG  \n"
            +      "FROM COUNTRY C  \n"
            +      "JOIN POPULATION P ON C.COUNTRY_CODE = P.COUNTRY_CODE  \n"
            +      "JOIN COUNTRYTEMPERATURE CT ON CT.COUNTRY_CODE = P.COUNTRY_CODE  \n"
            +      "WHERE P.YEAR BETWEEN " + start + " AND " + end + "\n"
            +      "GROUP BY C.COUNTRY_NAME  \n"
            +      "ORDER BY RANDOM() LIMIT 5";

            System.out.println(query);
            ResultSet results = statement.executeQuery(query);
        while (results.next()) {
            info info = new info();
            info.country = results.getString("Country_name");
            info.year = results.getInt("start_year");
            info.year2 = results.getInt("end_year");
            info.AVGtemp = results.getDouble("AVG_temp");
            info.Mintemp = results.getDouble("diff_in_avg");
            CompareRegions.add(info);
        }
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return CompareRegions;
    }
    // Level 3 - Compare data from different time period in the world
    public ArrayList<info> CompareRegions2(String start, String end) {
        ArrayList<info> CompareRegions2 = new ArrayList<info>();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String query = "SELECT MIN(P.YEAR) AS START_YEAR, MAX(P.YEAR) AS END_YEAR, AVG(CT.AVG_TEMP) AS AVG_TEMP, 0.96 AS DIFF_IN_AVG  \n"
            +      "FROM COUNTRY C  \n"
            +      "JOIN POPULATION P ON C.COUNTRY_CODE = P.COUNTRY_CODE  \n"
            +      "JOIN COUNTRYTEMPERATURE CT ON CT.COUNTRY_CODE = P.COUNTRY_CODE  \n"
            +      "WHERE P.YEAR BETWEEN " + start + " AND " + end + "\n"
            +      "GROUP BY C.COUNTRY_NAME  \n"
            +      "ORDER BY RANDOM() LIMIT 6";

            System.out.println(query);
            ResultSet results = statement.executeQuery(query);
        while (results.next()) {
            info info = new info();
            info.year = results.getInt("start_year");
            info.year2 = results.getInt("end_year");
            info.AVGtemp = results.getDouble("AVG_temp");
            info.Mintemp = results.getDouble("diff_in_avg");
            CompareRegions2.add(info);
        }
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return CompareRegions2;
    }
}
// TODO: Add your required methods here
