package com.helio4.bancol.avaluos.servicio.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class MapUtils {

	  public static boolean  isCoordinatesValid(String lng, String lat)
	            throws MalformedURLException, IOException, org.json.simple.parser.ParseException {
	         
	        URL url = new URL("http://maps.googleapis.com/maps/api/geocode/json?latlng="+ lat + "," + lng + "&sensor=true");
	        try {
	            InputStream in = url.openStream();
	            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
	            String result, line = reader.readLine();
	            result = line;
	            while ((line = reader.readLine()) != null) {
	                result += line;
	            }
	 
	            JSONParser parser = new JSONParser();
	            JSONObject rsp = (JSONObject) parser.parse(result);
	 
	            if (rsp.containsKey("results")) {
	                JSONArray matches = (JSONArray) rsp.get("results");
	                return matches.size()>0;
	            }
	 
	            return Boolean.FALSE;
	        }
	        catch(Exception e){
	        	return Boolean.FALSE;
	        }
	        
	    }
}
