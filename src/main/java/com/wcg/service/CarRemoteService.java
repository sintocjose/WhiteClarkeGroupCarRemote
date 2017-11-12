package com.wcg.service;

/**
 * 
 * This interface acts as the service for processing the car remote action
 * @author Sinto
 *
 */
public interface CarRemoteService {
	
	/**
	 * Method for finding the car position depends on the input.
	 * @param input
	 * @return
	 */
	public int[] processCarRemote(String input);

}
