package com.codeclan.example.WhiskyTracker;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.DistilleryRepository;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WhiskyTrackerApplicationTests {

	@Autowired
	WhiskyRepository whiskyRepository;

	@Autowired
	DistilleryRepository distilleryRepository;

	@Test
	public void contextLoads() {
	}

	@Test
	public void canFindWhiskyByYear(){
		List<Whisky> found = whiskyRepository.findByYear(2007);
		assertEquals(2, found.size());
	}

	@Test
	public void canFindDistilleryByRegion(){
		List<Distillery> foundDistillery = distilleryRepository.findByRegion("Highland");
		assertEquals(3, foundDistillery.size());
	}

	@Test
	public void canFindWhiskyByAgeAndDistillery(){
		Distillery distillery = distilleryRepository.getOne(1L);
		List<Whisky> foundWhisky = whiskyRepository.findByAgeAndDistillery(15, distillery);
		assertEquals(1, foundWhisky.size());
	}
}
