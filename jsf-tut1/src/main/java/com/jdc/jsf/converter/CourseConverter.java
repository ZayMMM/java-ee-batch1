package com.jdc.jsf.converter;

import javax.ejb.EJB;
import javax.enterprise.inject.Model;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import com.jdc.jsf.entity.Course;
import com.jdc.jsf.service.CourseService;

@Model
public class CourseConverter implements Converter {
	
	@EJB
	private CourseService service;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		if(null != value && !value.isEmpty()) {
			int id = Integer.parseInt(value);
			return service.findById(id);
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		if(null != value) {
			Course c = (Course) value;
			return String.valueOf(c.getId());
		}
		
		return null;
	}

}
