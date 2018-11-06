package com.helio4.bancol.avaluos.dominio.gmap;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("singleton")
public class Geocoder implements Serializable {

	private static final long serialVersionUID = 1L;
	// Constants
	public static final String OUTPUT_CSV = "csv";
	public static final String OUTPUT_XML = "xml";
	public static final String OUTPUT_KML = "kml";
	public static final String OUTPUT_JSON = "json";
	private final String output = OUTPUT_JSON;

	public Geocoder()
	{
	}

	public GLatLng decode(String response) throws GeocoderException {
		
		Double latitude = null;
		Double longitude = null;
		
		JSONObject jsonObject = new JSONObject(response);

		String status = jsonObject.getString("status");
		
		JSONArray jSONArray = jsonObject.getJSONArray("results");
		
		if(jSONArray!=null && jSONArray.getJSONObject(0) !=null){
			latitude = jSONArray.getJSONObject(0).getJSONObject("geometry").getJSONObject("location").getDouble("lat");
			longitude = jSONArray.getJSONObject(0).getJSONObject("geometry").getJSONObject("location").getDouble("lng");			
		}

		if (!status.equals(GeocoderException.G_GEO_SUCCESS))
		{
			throw new GeocoderException(status);
		}

		return new GLatLng(latitude, longitude);
	}

	/**
	 * builds the google geo-coding url
	 *
	 * @param address
	 * @return
	 */
	public String encode(final String address) {
		return "http://maps.googleapis.com/maps/api/geocode/" + output + "?address=" + urlEncode(address) + "&components=country:CO&sensor=false";
	}

	/**
	 * @param address
	 * @return
	 * @throws IOException
	 */
	public GLatLng geocode(final String address) throws IOException {
		InputStream is = invokeService(encode(address));
		if (is != null)
		{
			try
			{
				String content = org.apache.wicket.util.io.IOUtils.toString(is);
				return decode(content);
			}
			finally
			{
				is.close();
			}
		}
		return null;
	}

	/**
	 * fetches the url content
	 *
	 * @param address
	 * @return
	 * @throws IOException
	 */
	protected InputStream invokeService(final String address) throws IOException
	{
		URL url = new URL(address);
		return url.openStream();
	}

	/**
	 * url-encode a value
	 *
	 * @param value
	 * @return
	 */
	private String urlEncode(final String value)
	{
		try
		{
			return URLEncoder.encode(value, "UTF-8");
		}
		catch (UnsupportedEncodingException ex)
		{
			throw new RuntimeException(ex.getMessage());
		}
	}
}
