package app;

import java.util.ArrayList;

import helper.WorldTemperature;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JBDCConnection2 {
    // Name of database file (contained in database folder)
    public static final String DATABASE = "jdbc:sqlite:database/ContributorDB.db";

    /**
     * This creates a JDBC Object so we can keep talking to the database
     */
    public JBDCConnection2() {
        System.out.println("Created JDBC Connection Object");
    }

    public ArrayList<studentandpersonainfo> getSIDandName1(){
        ArrayList<studentandpersonainfo> namexSID = new ArrayList<studentandpersonainfo>();
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30); // set timeout to 30 sec.
            
            String query = "SELECT * FROM CONTRIBUTOR WHERE NAME LIKE '%Dao Thao Phuong%'";

            ResultSet results = statement.executeQuery(query);

            while (results.next()) {
                studentandpersonainfo info = new studentandpersonainfo();
                info.Name = results.getString("NAME");
                info.SID = results.getString("SID");

                namexSID.add(info);
                
            }
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

        return namexSID;
    }

    public ArrayList<studentandpersonainfo> getSIDandName2(){
        ArrayList<studentandpersonainfo> namexSID = new ArrayList<studentandpersonainfo>();
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30); // set timeout to 30 sec.
            
            String query = "SELECT * FROM CONTRIBUTOR WHERE NAME LIKE '%Pham Trung Nghia%'";

            ResultSet results = statement.executeQuery(query);

            while (results.next()) {
                studentandpersonainfo info = new studentandpersonainfo();
                info.Name = results.getString("NAME");
                info.SID = results.getString("SID");

                namexSID.add(info);
                
            }
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

        return namexSID;
    }

    public ArrayList<studentandpersonainfo> getSIDandName3(){
        ArrayList<studentandpersonainfo> namexSID = new ArrayList<studentandpersonainfo>();
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30); // set timeout to 30 sec.
            
            String query = "SELECT * FROM CONTRIBUTOR WHERE NAME LIKE '%Nguyen Minh Phuong%'";

            ResultSet results = statement.executeQuery(query);

            while (results.next()) {
                studentandpersonainfo info = new studentandpersonainfo();
                info.Name = results.getString("NAME");
                info.SID = results.getString("SID");

                namexSID.add(info);
                
            }
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

        return namexSID;
    }

    public ArrayList<studentandpersonainfo> getSIDandName4(){
        ArrayList<studentandpersonainfo> namexSID = new ArrayList<studentandpersonainfo>();
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30); // set timeout to 30 sec.
            
            String query = "SELECT * FROM CONTRIBUTOR WHERE NAME LIKE '%Nguyen Thi Hong Hanh%'";

            ResultSet results = statement.executeQuery(query);

            while (results.next()) {
                studentandpersonainfo info = new studentandpersonainfo();
                info.Name = results.getString("NAME");
                info.SID = results.getString("SID");

                namexSID.add(info);
                
            }
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

        return namexSID;
    }

    public ArrayList<studentandpersonainfo> getSIDandName5(){
        ArrayList<studentandpersonainfo> namexSID = new ArrayList<studentandpersonainfo>();
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30); // set timeout to 30 sec.
            
            String query = "SELECT * FROM CONTRIBUTOR WHERE NAME LIKE '%Bui Thai Son%'";

            ResultSet results = statement.executeQuery(query);

            while (results.next()) {
                studentandpersonainfo info = new studentandpersonainfo();
                info.Name = results.getString("NAME");
                info.SID = results.getString("SID");

                namexSID.add(info);
                
            }
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

        return namexSID;
    }

    public ArrayList<studentandpersonainfo> getPersona1(){
        ArrayList<studentandpersonainfo> getPersona = new ArrayList<studentandpersonainfo>();
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30); // set timeout to 30 sec.
            
            String query = "SELECT * FROM PERSONA WHERE NAME LIKE '%Emily Choo%'";

            ResultSet results = statement.executeQuery(query);

            while (results.next()) {
                studentandpersonainfo info = new studentandpersonainfo();
                info.Name = results.getString("NAME");
                info.age = results.getInt("AGE");
                info.background = results.getString("BACKGROUND");
                info.needsandgoals = results.getString("NEEDS_AND_GOALS");
                info.skillandexperience = results.getString("SKILL_AND_EXPERIENCE");

                getPersona.add(info);
                
            }
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

        return getPersona;
    }

    public ArrayList<studentandpersonainfo> getPersona2(){
        ArrayList<studentandpersonainfo> getPersona = new ArrayList<studentandpersonainfo>();
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30); // set timeout to 30 sec.
            
            String query = "SELECT * FROM PERSONA WHERE NAME LIKE '%Greta Wilson%'";

            ResultSet results = statement.executeQuery(query);

            while (results.next()) {
                studentandpersonainfo info = new studentandpersonainfo();
                info.Name = results.getString("NAME");
                info.age = results.getInt("AGE");
                info.background = results.getString("BACKGROUND");
                info.needsandgoals = results.getString("NEEDS_AND_GOALS");
                info.skillandexperience = results.getString("SKILL_AND_EXPERIENCE");

                getPersona.add(info);
                
            }
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

        return getPersona;
    }
    
    public ArrayList<studentandpersonainfo> getPersona3(){
        ArrayList<studentandpersonainfo> getPersona = new ArrayList<studentandpersonainfo>();
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30); // set timeout to 30 sec.
            
            String query = "SELECT * FROM PERSONA WHERE NAME LIKE '%John Hilbert%'";

            ResultSet results = statement.executeQuery(query);

            while (results.next()) {
                studentandpersonainfo info = new studentandpersonainfo();
                info.Name = results.getString("NAME");
                info.age = results.getInt("AGE");
                info.background = results.getString("BACKGROUND");
                info.needsandgoals = results.getString("NEEDS_AND_GOALS");
                info.skillandexperience = results.getString("SKILL_AND_EXPERIENCE");

                getPersona.add(info);
                
            }
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

        return getPersona;
    }
}

