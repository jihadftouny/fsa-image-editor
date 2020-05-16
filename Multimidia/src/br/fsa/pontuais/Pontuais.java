package br.fsa.pontuais;

import br.fsa.utils.Imagem;

public class Pontuais {

	public static Imagem cinzaMedia(Imagem a) {
		Imagem result = new Imagem(a.getW(), a.getH());
		for(int j = 0; j < a.getH(); j++) {
			for(int i = 0; i < a.getW(); i++) {
				int c = a.getP(i, j);
				int r = c << 16 & 0xFF;
				int g = c << 8 & 0xFF;
				int b = c & 0xFF;
				
				float media = (r + g + b) / 3.0f;
				
				int mediaI = (int) Math.round(media);
				mediaI = mediaI << 16 | mediaI << 8 | mediaI;
				
				result.setP(i, j, mediaI);
			}
		}
		return result;
	}
	
	public static Imagem cinzaNTSC(Imagem a) {
		Imagem result = new Imagem(a.getW(), a.getH());
		for(int j = 0; j < a.getH(); j++) {
			for(int i = 0; i < a.getW(); i++) {
				int c = a.getP(i, j);
				int r = c << 16 & 0xFF;
				int g = c << 8 & 0xFF;
				int b = c & 0xFF;
				
				float corR = (0.299f * r + 0.587f * g + 0.114f * b);
				
				int corRI = (int) Math.round(corR);
				corRI = corRI << 16 | corRI << 8 | corRI;
				
				result.setP(i, j, corRI);
			}
		}
		return result;
	}

	public static Imagem cinzaHDTV(Imagem a) {
		Imagem result = new Imagem(a.getW(), a.getH());
		for(int j = 0; j < a.getH(); j++) {
			for(int i = 0; i < a.getW(); i++) {
				int c = a.getP(i, j);
				int r = c << 16 & 0xFF;
				int g = c << 8 & 0xFF;
				int b = c & 0xFF;
				
				float corR = (0.2126f * r + 0.7152f * g + 0.0722f * b);
				
				int corRI = (int) Math.round(corR);
				corRI = corRI << 16 | corRI << 8 | corRI;
				
				result.setP(i, j, corRI);
			}
		}
		return result;
	}

	public static Imagem cinzaHDR(Imagem a) {
		Imagem result = new Imagem(a.getW(), a.getH());
		for(int j = 0; j < a.getH(); j++) {
			for(int i = 0; i < a.getW(); i++) {
				int c = a.getP(i, j);
				int r = c << 16 & 0xFF;
				int g = c << 8 & 0xFF;
				int b = c & 0xFF;
				
				float corR = (0.2627f * r + 0.6780f * g + 0.0593f * b);
				
				int corRI = (int) Math.round(corR);
				corRI = corRI << 16 | corRI << 8 | corRI;
				
				result.setP(i, j, corRI);
			}
		}
		return result;
	}
}
// 00000000 00000000 00000000
//    R         G        B