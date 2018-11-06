package com.helio4.bancol.avaluos.dominio.gmap;

import java.util.StringTokenizer;

public class GLatLng {
	
	private final double lat;
	private final double lng;
	private final boolean unbounded;

	/**
	 * Construct.
	 *
	 * @param lat
	 * @param lng
	 */
	public GLatLng(double lat, double lng)
	{
		this(lat, lng, false);
	}

	/**
	 * Construct.
	 *
	 * @param lat
	 * @param lng
	 * @param unbounded
	 */
	public GLatLng(double lat, double lng, boolean unbounded)
	{
		this.lat = lat;
		this.lng = lng;
		this.unbounded = unbounded;
	}

	public double getLat()
	{
		return lat;
	}

	public double getLng()
	{
		return lng;
	}

	@Override
	public String toString()
	{
		return lat+", "+lng;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int PRIME = 31;
		int result = super.hashCode();
		long temp;
		temp = Double.doubleToLongBits(lat);
		result = PRIME * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(lng);
		result = PRIME * result + (int) (temp ^ (temp >>> 32));
		result = PRIME * result + (unbounded
				? 1231
				: 1237);
		return result;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (!super.equals(obj))
		{
			return false;
		}
		if (getClass() != obj.getClass())
		{
			return false;
		}
		final GLatLng other = (GLatLng) obj;
		if (Double.doubleToLongBits(lat) != Double.doubleToLongBits(other.lat))
		{
			return false;
		}
		if (Double.doubleToLongBits(lng) != Double.doubleToLongBits(other.lng))
		{
			return false;
		}
		if (unbounded != other.unbounded)
		{
			return false;
		}
		return true;
	}

	/**
	 * (37.34068368469045, -122.48519897460936)
	 */
	public static GLatLng parse(String value)
	{
		try
		{
			StringTokenizer tokenizer = new StringTokenizer(value, "(, )");

			float lat = Float.valueOf(tokenizer.nextToken());
			float lng = Float.valueOf(tokenizer.nextToken());
			return new GLatLng(lat, lng);
		}
		catch (Exception e)
		{
			return null;
		}
	}

}
