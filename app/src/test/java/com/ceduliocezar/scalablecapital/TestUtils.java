package com.ceduliocezar.scalablecapital;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Utils class for testing.
 */
public class TestUtils {

    public static String readContentFromResources(Class clazz, String fileName) throws IOException {

        InputStream stream = clazz.getClassLoader().getResourceAsStream(fileName);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));

        String inputLine;

        StringBuilder stringBuilder = new StringBuilder();
        while ((inputLine = bufferedReader.readLine()) != null) {
            stringBuilder.append(inputLine);
        }

        return stringBuilder.toString();
    }

    public static URL urlFromResources(Class clazz, String fileName) throws IOException {
        return clazz.getClassLoader().getResource(fileName);
    }
}
