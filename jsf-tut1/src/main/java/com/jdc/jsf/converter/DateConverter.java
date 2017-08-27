package com.jdc.jsf.converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("dateConverter")
public class DateConverter implements Converter {
	
	private DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if(null != value && !value.isEmpty()) {
			try {
				return df.parse(value);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		if(null != value) {
			return df.format(value);
		}
		
		return null;
	}


}
