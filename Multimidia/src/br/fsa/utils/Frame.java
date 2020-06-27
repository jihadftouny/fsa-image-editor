package br.fsa.utils;

import java.util.ArrayList;

import javax.swing.JInternalFrame;

public class Frame {

	public static ArrayList<String> getFrameNames(JInternalFrame[] internalFrames) {

		ArrayList<String> frames = new ArrayList<String>();
		
		for (int i = 0; i < internalFrames.length; i++) {
			frames.add(internalFrames[i].getTitle());
		}

		return frames;
	}

}
