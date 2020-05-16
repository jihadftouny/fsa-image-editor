package br.fsa.atividade;

import br.fsa.utils.Imagem;
import static br.fsa.utils.Utils.red;



public class Ex1 {
	
	public static Imagem thresholdAtv(Imagem a) {
		Imagem result = new Imagem(a.getW(), a.getH());

		for(int j = 0; j < a.getH(); j++) {
			for(int i = 0; i < a.getW(); i++) {
				
				int pixel = red(a.getP(i, j));
				int acumP = 0;
				
				for(int y = -1; y <= 1; y++) {
					for(int x = -1; x <= 1; x++) {
						acumP = acumP + red(a.getP(i + x, j + y));
					}
				}
				
				acumP = acumP / 9;
				
				if(pixel < acumP) {
					pixel = 0 << 16 | 0 << 8 | 0;
				}else {
					pixel = 255 << 16 | 255 << 8 | 255;
				}
				
				result.setP(i, j, pixel << 16 | pixel << 8 | pixel);

			}
		}
		return result;
	}
	
	public static Imagem histogramAtv(Imagem a) {
		Imagem result = new Imagem(a.getW(), a.getH());
		int[] cor = new int[256];
		int[] cdf = new int[256];
		int[] eql = new int[256];
		float cdfMin = 255;
		
		for(int j = 0; j < a.getH(); j++) {
			for(int i = 0; i < a.getW(); i++) {
				int pixel = red(a.getP(i, j));
				cor[pixel]++;
			}
		}
		
		cdf[0] = cor[0];
		for(int x = 1; x < cor.length; x++) {
			cdf[x] = cdf[x-1] + cor[x];
		}
		
		for(int x = 0; x < cor.length; x++) {
			if(cor[x] > 0) {
				cdfMin = x;
			}
		}
		
		for(int x = 0; x < 256; x++) {
			eql[x] = Math.round(((cdf[x]-cdfMin) / (a.getW()*a.getH()-cdfMin)) * 255);
		}
		
		for(int j = 0; j < a.getH(); j++) {
			for(int i = 0; i < a.getW(); i++) {
				int pixel = red(a.getP(i, j));
				result.setP(i, j, eql[pixel] << 16 | eql[pixel] << 8 | eql[pixel]);
			}
		}

		return result;
	}
	
	// round( 

}
