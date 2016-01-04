package com.grabIt.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.grabIt.domain.Sample;
import com.grabIt.service.SampleService;

@RestController
public class SampleController {
	
	@Autowired
	private SampleService sampleService;
	
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
		return new ResponseEntity<List<Sample>>(sampleService.getListSample(),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/sample",method = RequestMethod.POST)
	public ResponseEntity<Sample>  addSample(@Valid @RequestBody Sample sample){
		
		sampleService.addSample(sample);
        return new ResponseEntity<Sample>(sampleService.getSampleData(sample.getId()), HttpStatus.CREATED);
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
