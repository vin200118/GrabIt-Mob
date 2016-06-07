package com.grabIt.dao;

import java.util.List;

import com.grabIt.domain.Sample;

public interface SampleDao {
	
	public Sample getSampleData(String id);

	public List<Sample> getListSample();

	public void addSample(Sample sample);

	public void updateSample(Sample sample);

	public void deleteSampleData(int id);

}
