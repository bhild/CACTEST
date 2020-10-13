package com.example.myapplication;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import java.net.MalformedURLException;

public class GetPostal {
    private static String postal = "";
        public static String getCode(double[] latlong) throws IOException {
        String url = "http://open.mapquestapi.com/geocoding/v1/reverse?key=SAd4Ez053FHMazmKomIl1l87zIrtLmFO&location="+latlong[0]+","+latlong[1]+"&outFormat=xml&includeRoadMetadata=false&includeNearestIntersection=false\\r\\n"
                + "\r\n"
                + "";
        readFromWeb(url);
        return postal;
    }
    private static void readFromWeb(String webURL) throws IOException {
        URL url = new URL(webURL);
        InputStream is =  url.openStream();
        try( BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = br.readLine()) != null) {
                if ( getPostalCode(line)!="") {
                    postal = getPostalCode(line);
                }
            }
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
            throw new MalformedURLException("URL is malformed!!");
        }
        catch (IOException e) {
            e.printStackTrace();
            throw new IOException();
        }

    }
    private static String getPostalCode(String line) {
        String out = "";
        if(line.contains("<postalCode>")) {
            out = line.replaceAll("[^0-9]", "");
        }
        return out;
    }
}