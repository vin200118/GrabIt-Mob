package com.grabIt.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.grabIt.domain.Post;

public class PostValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Post.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors es) {
		 /*ValidationUtils.rejectIfEmpty(es, "type", "", "type is empty");
		 ValidationUtils.rejectIfEmpty(es, "condtion", "", "condition is empty");
		 ValidationUtils.rejectIfEmpty(es, "price", "", "price is empty");
		 ValidationUtils.rejectIfEmpty(es, "dateOfPurchase", "", "dateOfPurchase is empty");
		 ValidationUtils.rejectIfEmpty(es, "contactName", "", "contactName is empty");
		 ValidationUtils.rejectIfEmpty(es, "contactNumber", "", "contactNumber is empty");
		 ValidationUtils.rejectIfEmpty(es, "emailId", "", "emailId is empty");*/
			
	}

}
