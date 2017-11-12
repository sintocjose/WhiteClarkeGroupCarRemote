package com.wcg.service;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.wcg.model.RemoteInput;

/**
 * 
 * This is the implementaion for processing the car remote actions.
 * @author Sinto
 *
 */
@Service
public class CarRemoteServiceImpl implements CarRemoteService {

	private static Map<String, String> directionMapping = new HashMap<>();
	static {
		directionMapping.put("NR", "E");
		directionMapping.put("NL", "W");
		directionMapping.put("ER", "S");
		directionMapping.put("EL", "N");
		directionMapping.put("SR", "W");
		directionMapping.put("SL", "E");
		directionMapping.put("WR", "N");
		directionMapping.put("WL", "S");
	}
	/**
	 * This method process the car remote actions.
	 * This function do the activities such as validation , convert input to model object, and finally result process.
	 */
	@Override
	public int[] processCarRemote(String input) {
		validateInput(input);
		RemoteInput remoteObject = extractInput(input);
		System.out.println(remoteObject);
		int finalPosition[] = calculateCarPosition(remoteObject);
		return finalPosition;
	}
	private int[] calculateCarPosition(RemoteInput remoteObject) {
		String currentPosition = "N";
		for (char action : remoteObject.getInstructions()) {
			switch(action){
			case 'F':moveCarFront(currentPosition,remoteObject);break;
			case 'R':
			case 'L':
				currentPosition=directionMapping.get(currentPosition+action);
				break;
			}
		}
		return new int []{remoteObject.getX(),remoteObject.getY()};
	}
	private void moveCarFront(String currentPosition,RemoteInput remoteObject) {
		switch(currentPosition){
		case "N":
			remoteObject.setX(remoteObject.getX()+1);
			break;
		case "S":
			remoteObject.setX(remoteObject.getX()-1);
			break;
		case "E":
			remoteObject.setY(remoteObject.getY()+1);
		break;
		case "W":
			remoteObject.setY(remoteObject.getY()-1);
			break;
		}
		checkValidityOfPosition(remoteObject.getX());
		checkValidityOfPosition(remoteObject.getY());
	}
	private void checkValidityOfPosition(int position) {
		if(position<1){
			throw new RuntimeException("Car Position Moved to out of Grid");
		}
	}
	private RemoteInput extractInput(String input) {
		RemoteInput ri = new RemoteInput();
		String part1[] = input.split(",");
		ri.setX(Integer.parseInt(part1[0]));
		String secondHalf = part1[1];
		String part2[] = secondHalf.split(":");
		ri.setY(Integer.parseInt(part2[0]));
		ri.setInstructions(part2[1].toCharArray());
		return ri;
	}
	private boolean validateInput(String input) {
		if (input != null && input.length() > 0) {
			Pattern p = Pattern.compile("^[\\d]{1,2}[,][\\d]{1,2}[:]{1}[RFL]*");
			Matcher m = p.matcher(input);
			if (m.matches()) {
				System.out.println("matched");
				return true;
			} else {
				throw new RuntimeException("Not valid input");
			}
		} else {
			return false;
		}
	}	

}
