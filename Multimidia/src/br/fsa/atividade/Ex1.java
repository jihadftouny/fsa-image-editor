package br.fsa.atividade;

import br.fsa.utils.Imagem;
import static br.fsa.utils.Utils.red;


public class Ex1 {
	public static Imagem histogramAtv(Imagem a) {
		Imagem result = new Imagem(256, 150);
		int[] cor = new int[256];
		int max = 0;
		int linhaCor = 255 << 16 | 255 << 8 | 255;
		
		for(int j = 0; j < a.getH(); j++) {
			for(int i = 0; i < a.getW(); i++) {
				
				int pixel = red(a.getP(i, j));
				cor[pixel]++;
				
				if(cor[pixel] > max) {
					max = cor[pixel];
				}
				
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
	
	public static Imagem thresholdAtv(Imagem a, int limiar) {
		Imagem result = new Imagem(a.getW(), a.getH());
//		int[] cor = new int[256];
//		int max = 0;
		
		for(int j = 0; j < a.getH(); j++) {
			for(int i = 0; i < a.getW(); i++) {
				
				int pixel = red(a.getP(i, j));
				if (pixel <= limiar) {
					pixel = 0 << 16 | 0 << 8 | 0;
				}else {
					pixel = 255 << 16 | 255 << 8 | 255;
				}
				
				result.setP(i, j, pixel);
			}
		}
		return result;
	}
	
	public static int map(int valor, int valorMax, int num) {
		// valor -> Valor que sera mapeado
		// valorMax -> Valor máximo que o valor que sera mapeado pode chegar
		// num -> Valor que sera comparado com o máximo para se fazer o mapeamento
		int valorMap = (((-1*num)*valor)/valorMax) + num;
		return valorMap; 
	}
}


// percorrer vetor pra ver qual o máximo
// valor-0/max-0 = y-150/0-150
// y= -150*valor/max + 150

