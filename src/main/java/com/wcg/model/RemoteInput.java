package com.wcg.model;
import java.util.Arrays;
public class RemoteInput {
	private int x;
	private int y;
	private char [] instructions;
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public char[] getInstructions() {
		return instructions;
	}
	public void setInstructions(char[] instructions) {
		this.instructions = instructions;
	}
	@Override
	public String toString() {
		return "RemoteInput [x=" + x + ", y=" + y + ", instructions="
				+ Arrays.toString(instructions) + "]";	
	}
}
