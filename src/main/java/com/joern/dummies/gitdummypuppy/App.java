package com.joern.dummies.gitdummypuppy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

/**
 * Created by jsattler on 27.04.2017.
 */
public class App {

    private static final Logger l = LoggerFactory.getLogger(App.class);

    public static void main(String... args){

        new App().printProps();
    }


    public void printProps(){


        Properties prop = new Properties();
        InputStream input = null;

        try {

            String filename = "puppyprops\\config.properties";
            input = getClass().getClassLoader().getResourceAsStream(filename);
            if (input == null) {
                l.error("Sorry, unable to find " + filename);
                return;
            }

            prop.load(input);

            Enumeration<?> e = prop.propertyNames();
            while (e.hasMoreElements()) {
                String key = (String) e.nextElement();
                String value = prop.getProperty(key);
                l.debug("Key : " + key + ", Value : " + value);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    l.error("Failed to read props.", e);
                }
            }
        }

    }
}