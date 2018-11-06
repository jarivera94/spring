package com.helio4.bancol.avaluos.dominio.gmap;

public class Constructor {
	
	private StringBuffer buffer = new StringBuffer();

	private boolean first = true;

	public Constructor(String name)
	{
		buffer.append("new ");
		buffer.append(name);
		buffer.append("(");
	}

	public Constructor addString(Object value)
	{
		return add("\"" + value + "\"");
	}

	public Constructor add(Object value)
	{
		if (!first)
		{
			buffer.append(", ");
		}
		buffer.append(value);

		first = false;
		return this;
	}

	public String toJS()
	{
		buffer.append(")");

		String string = buffer.toString();

		buffer.deleteCharAt(buffer.length() - 1);

		return string;
	}

}
