package com.grabIt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grabIt.dao.SampleDao;
import com.grabIt.domain.Sample;

@Service
public class SampleService {

	@Autowired
	private SampleDao sampleDao;
	
	public Sample getSampleData(String id){
		return sampleDao.getSampleData(id);
		
	}

	public List<Sample> getListSample() {
		return sampleDao.getListSample();
	}

	public void addSample(Sample sample) {
		sampleDao.addSample(sample);
		
	}

	public void updateSample(Sample sample) {
		sampleDao.updateSample(sample);
	}

	public void deleteSampleData(int id) {
		sampleDao.deleteSampleData(id);
	}
	
}
