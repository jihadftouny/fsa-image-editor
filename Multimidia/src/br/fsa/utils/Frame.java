package br.fsa.utils;

import java.util.ArrayList;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

import java.awt.Component;
import java.io.File;
import java.io.IOException;

public class Frame extends JInternalFrame {

	File file;
	String caminho;
	String filename;

	public Frame(File file) {

		this.file = file;
		this.caminho = file.getAbsolutePath();
		this.filename = file.getName();

		Imagem imagem = null;

		try {
			imagem = new Imagem(new File(file.getPath()));
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		add(imagem);
		setTitle(filename);
		setName(caminho);
		setResizable(true);
		setClosable(true);
		setIconifiable(true);
		setMaximizable(true);
		setVisible(true);
		
		if (imagem.getW() > 800 && imagem.getH() > 600) {
			setBounds(0, 0, 800, 600);
		} else {
			setBounds(0, 0, imagem.getW(), imagem.getH());
		}
	}

	public String getCaminho() {
		return caminho;
	}

	public static ArrayList<String> getFrameNames(JInternalFrame[] internalFrames) {

		ArrayList<String> frames = new ArrayList<String>();

		for (int i = 0; i < internalFrames.length; i++) {
			frames.add(internalFrames[i].getTitle());

			JOptionPane.showMessageDialog(null, internalFrames[i].getComponent(0));
		}

		return frames;
	}

}
