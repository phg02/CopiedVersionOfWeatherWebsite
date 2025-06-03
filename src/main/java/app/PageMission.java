package app;

import java.util.ArrayList;

import io.javalin.http.Context;
import io.javalin.http.Handler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.Map;
import java.util.HashMap;

/**
 * Example Index HTML class using Javalin
 * <p>
 * Generate a static HTML page using Javalin
 * by writing the raw HTML into a Java String object
 *
 * @author Timothy Wiley, 2023. email: timothy.wiley@rmit.edu.au
 * @author Santha Sumanasekara, 2021. email: santha.sumanasekara@rmit.edu.au
 */
public class PageMission implements Handler {

    // URL of this page relative to http://localhost:7001/
    public static final String URL = "/mission.html";

    public static final String TEMPLATE = "about_us.html";

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        Map<String, Object> model = new HashMap<String, Object>();
        //Student 1
        JBDCConnection2 jbdc = new JBDCConnection2();
        ArrayList<studentandpersonainfo> namexSID1 = jbdc.getSIDandName1();
        model.put("StudentID1", namexSID1);
        //Student 2
        JBDCConnection2 jbdc2 = new JBDCConnection2();
        ArrayList<studentandpersonainfo> namexSID2 = jbdc2.getSIDandName2();
        model.put("StudentID2", namexSID2);
        //Student 3
        JBDCConnection2 jbdc3 = new JBDCConnection2();
        ArrayList<studentandpersonainfo> namexSID3 = jbdc3.getSIDandName3();
        model.put("StudentID3", namexSID3);
        //Student 4 
        JBDCConnection2 jbdc4 = new JBDCConnection2();
        ArrayList<studentandpersonainfo> namexSID4 = jbdc4.getSIDandName4();
        model.put("StudentID4", namexSID4);
        //Student 5
        JBDCConnection2 jbdc5 = new JBDCConnection2();
        ArrayList<studentandpersonainfo> namexSID5 = jbdc5.getSIDandName5();
        model.put("StudentID5", namexSID5);
        

        //Persona 1
        JBDCConnection2 jbdc1 = new JBDCConnection2();
        ArrayList<studentandpersonainfo> Persona1 = jbdc1.getPersona1();
        model.put("Persona1", Persona1);
        
        //Persona 2 
        JBDCConnection2 jbdc6 = new JBDCConnection2();
        ArrayList<studentandpersonainfo> Persona2 = jbdc6.getPersona2();
        model.put("Persona2", Persona2);

        //Persona 3 
        JBDCConnection2 jbdc7 = new JBDCConnection2();
        ArrayList<studentandpersonainfo> Persona3 = jbdc7.getPersona3();
        model.put("Persona3", Persona3);


        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.render(TEMPLATE, model);
    }

    

}
