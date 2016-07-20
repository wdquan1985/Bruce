package com.bruce.model;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@JsonIgnoreProperties (value = {"friendlyName"})
public abstract class BaseModel implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public abstract String GetFriendlyName();

}
