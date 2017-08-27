package com.jdc.jsf.model;

import javax.enterprise.inject.Model;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import com.jdc.jsf.entity.Friend.Interest;

@Model
public class InterestConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {

		if(null != value && !value.isEmpty()) {
			return Interest.valueOf(value);
		}
		
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {

		if(null != value) {
			return value.toString();
		}
		
		return null;
	}
	

}
