package com.grabIt.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Post {
	
	private String id;
	private String userId;
	private String title;
	private String subCategoryId;
	private String categoryId;
	private String imagePath;
	private String  type;
	private String condition;
	private long price;
	private String dateOfPurchase;
	private String description;
	private String contactName;
	private String contactNumber;
	private String emailId;
	private String brand;
	private String model;


}
