package com.wcg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wcg.service.CarRemoteService;

/**
 * Rest Endpoint for finding the car position
 * @author Sinto
 *
 */
@RestController
public class CarRemoteEndpoint {

	@Autowired
	CarRemoteService carRemoteService;
	
	/**
	 * This method is the get endpoint for finding the car position after doing the remote action.
	 * @param input
	 * @return
	 */

	@RequestMapping(value="/carposition/{input}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public int[] getCarPosition(@PathVariable("input") String input) {
		return carRemoteService.processCarRemote(input);
	}

}
