package com.jdc.jsf.converter;

import java.time.DayOfWeek;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("daysConverter")
public class DaysConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {

		if (null != value && !value.isEmpty()) {
			return DayOfWeek.valueOf(value);
		}

		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {

		if (null != value) {
			return value.toString();
		}

		return null;
	}

}
