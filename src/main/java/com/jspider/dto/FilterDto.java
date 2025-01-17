package com.jspider.dto;

import com.jspider.enums.ClassMode;
import com.jspider.enums.Courses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilterDto {
private ClassMode classMode;
	
	private Courses course;
}
