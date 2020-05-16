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
	
}


// percorrer vetor pra ver qual o máximo
// valor-0/max-0 = y-150/0-150
// y= -150*valor/max + 150

