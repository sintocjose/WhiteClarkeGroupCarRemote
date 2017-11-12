package com.wcg.service;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CarRemoteServiceImplTest {
	
	@Autowired
	CarRemoteService carRemoteService;


	@Test
	public void carRemoteTestPositive1() {
		int[] output = carRemoteService.processCarRemote("5,5:RFLFRFLF");
		assertArrayEquals(output,new int[]{7,7});
	}
	@Test
	public void carRemoteTestPositive2() {
		int[] output = carRemoteService.processCarRemote("6,6:FFLFFLFFLFF");
		assertArrayEquals(output,new int[]{6,6});
	}	
	@Test
	public void carRemoteTestPositive3() {
		int[] output = carRemoteService.processCarRemote("5,5:FLFLFFRFFF");
		assertArrayEquals(output,new int[]{4,1});
	}	
	
	@Test(expected = RuntimeException.class)
	public void carRemoteTestNullInputCheck() {
		int[] output = carRemoteService.processCarRemote(null);
	}	
	@Test(expected = RuntimeException.class)
	public void carRemoteTestInvalidInputCheck() {
		int[] output = carRemoteService.processCarRemote("SBC78");
	}	
}
