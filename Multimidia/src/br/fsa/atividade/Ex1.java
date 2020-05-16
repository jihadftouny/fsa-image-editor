package br.fsa.atividade;

import br.fsa.utils.Imagem;
import static br.fsa.utils.Utils.red;


public class Ex1 {
	
	public static Imagem thresholdAtv(Imagem a, int limiar) {
		Imagem result = new Imagem(a.getW(), a.getH());

		for(int j = 0; j < a.getH(); j++) {
			for(int i = 0; i < a.getW(); i++) {
				
				int pixel = red(a.getP(i, j));
				if (pixel < limiar && j < a.getH()/2) {
					pixel = 255 << 16 | 255 << 8 | 255;
				}else if(pixel > limiar && j > a.getH()/2) {
					pixel = 0 << 16 | 0 << 8 | 0;
				}else {
					pixel = pixel << 16 | pixel << 8 | pixel;
				}
				result.setP(i, j, pixel);
			}
		}
		return result;
	}
	
}


// percorrer vetor pra ver qual o máximo
// valor-0/max-0 = y-150/0-150
// y= -150*valor/max + 150

