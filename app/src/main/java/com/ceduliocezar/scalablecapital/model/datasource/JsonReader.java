package com.ceduliocezar.scalablecapital.model.datasource;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;


public class JsonReader {

    public String read(URL url) throws Exception {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;

        try {
            URLConnection urlConnection = url.openConnection();

            bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String inputLine;

            while ((inputLine = bufferedReader.readLine()) != null) {
                stringBuilder.append(inputLine);
            }
        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        }

        return stringBuilder.toString();
    }
}
