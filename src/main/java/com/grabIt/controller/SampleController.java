package com.grabIt.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.grabIt.domain.Sample;
import com.grabIt.service.SampleService;
import com.grabIt.validator.SampleValidator;

@CrossOrigin(maxAge = 3600)
@RestController
public class SampleController {
	
	private static final Logger logger = LoggerFactory.getLogger(SampleController.class);
	
	@Autowired
	private SampleService sampleService;
	
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
	    binder.setValidator(new SampleValidator());
	}
	
	@RequestMapping(value = "/sample-message",method = RequestMethod.GET)
	public ResponseEntity<String> getSampleMessage(){
		return new ResponseEntity<String>("Welcome in GrabIt.",HttpStatus.OK);
	}
	
	@RequestMapping(value = "/sample/{id}",method = RequestMethod.GET)
	public ResponseEntity<Sample> getSampleJson(@PathVariable("id") String id){
		return new ResponseEntity<Sample>(sampleService.getSampleData(id),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/sample",method = RequestMethod.GET)
	public ResponseEntity<List<Sample>> getListSample(){
		logger.info("Getting sample api... info level");
		logger.debug("Getting sample api... debug level");
		return new ResponseEntity<List<Sample>>(sampleService.getListSample(),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/sample",method = RequestMethod.POST)
	public ResponseEntity<?>  addSample(@Valid @RequestBody Sample sample, BindingResult result){
		if(result.hasErrors()) {
			return new ResponseEntity<List<FieldError>>(result.getFieldErrors(), HttpStatus.FORBIDDEN);
	    }
		sampleService.addSample(sample);
        return new ResponseEntity<Sample>(sample, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/sample",method = RequestMethod.PUT)
	public ResponseEntity<Sample>  updateSample(@RequestBody Sample sample){
		sampleService.updateSample(sample);
        return new ResponseEntity<Sample>(sampleService.getSampleData(sample.getId()), HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/sample/{id}",method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteSampleJson(@PathVariable("id") int id){
		sampleService.deleteSampleData(id);
		return new ResponseEntity<String>("Record Deleted.",HttpStatus.OK);
	}
	
	

}
