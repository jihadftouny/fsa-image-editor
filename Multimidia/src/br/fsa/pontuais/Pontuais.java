package br.fsa.pontuais;

import static br.fsa.utils.Utils.red;
import static br.fsa.utils.Utils.map;

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
	
	public static Imagem histogram(Imagem a) {
		Imagem result = new Imagem(256, 150);
		int[] cor = new int[256];
		int max = 0;
		int linhaCor = 255 << 16 | 255 << 8 | 255;
		
		for(int j = 0; j < a.getH(); j++) {
			for(int i = 0; i < a.getW(); i++) {
				
				// Somando 1 na posicao referente a cor do pixel lido
				int pixel = red(a.getP(i, j));
				cor[pixel]++;
				
				// Condicao para pegar o maior valor do vetor cor[pixel]
				if(cor[pixel] > max) {
					max = cor[pixel];
				}
				
				// Conjunto de lacos para plotar o histograma
				for(int x = 0; x < result.getW(); x++) {
					int mapVar = map(cor[x], max, 150);
					result.setP(x, mapVar, linhaCor); 
					
					for(int y = mapVar; y < result.getH(); y++) {
						result.setP(x, y, linhaCor);
					}
				}
				
			}
		}
		return result;
	}
	
	public static Imagem threshold(Imagem a, int limiar) {
		Imagem result = new Imagem(a.getW(), a.getH());

		for(int j = 0; j < a.getH(); j++) {
			for(int i = 0; i < a.getW(); i++) {
				
				int pixel = red(a.getP(i, j));
				if(pixel <= limiar) {
					pixel = 0 << 16 | 0 << 8 | 0;
				}else {
					pixel = 255 << 16 | 255 << 8 | 255;
				}
				
				result.setP(i, j, pixel);
			}
		}
		return result;
	}
}
