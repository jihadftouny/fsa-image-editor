package br.fsa.operacoes;

import br.fsa.utils.Imagem;
import static br.fsa.utils.Utils.red;
import static br.fsa.utils.Utils.green;
import static br.fsa.utils.Utils.blue;

public class Mascara {
	public static Imagem burn(Imagem a, Imagem b) {
		Imagem result = new Imagem(Math.max(a.getW(), b.getW()), 
								   Math.max(a.getH(), b.getH()));
		
		for(int j = 0; j < result.getH(); j++) {
			for(int i = 0; i < result.getW(); i++) {
				
				int ra = red(a.getP(i, j));
				int ga = green(a.getP(i, j));
				int ba = blue(a.getP(i, j));
				
				int rb = red(a.getP(i, j));
				int gb = green(a.getP(i, j));
				int bb = blue(a.getP(i, j));
/*				
				int resR = (255 - ((256*(255-rb)/(ra+1)))) < 0 ? 0:(255 - ((256*(255-rb)/(ra+1))));
				int resG = (255 - ((256*(255-gb)/(ga+1)))) < 0 ? 0:(255 - ((256*(255-gb)/(ga+1))));
				int resB = (255 - ((256*(255-bb)/(ba+1)))) < 0 ? 0:(255 - ((256*(255-bb)/(ba+1))));

				int resR = (255 - ((256*(255-rb)/(ra+1))));
				int resG = (255 - ((256*(255-gb)/(ga+1))));
				int resB = (255 - ((256*(255-bb)/(ba+1))));

*/			
				int resR = 0;
				int resG = 0;
				int resB = 0;
				
				if((255 - ((256*(255-rb)/(ra+1)))) < 0) {
					resR = 0;
				}else if((255 - ((256*(255-rb)/(ra+1)))) > 255){
					resR = 255;
				}else {
					resR = (255 - ((256*(255-rb)/(ra+1))));
				}
				
				if((255 - ((256*(255-gb)/(ga+1)))) < 0) {
					resG = 0;
				}else if((255 - ((256*(255-gb)/(ga+1)))) > 255){
					resG = 255;
				}else {
					resG = (255 - ((256*(255-gb)/(ga+1))));
				}
				
				if((255 - ((256*(255-bb)/(ba+1)))) < 0) {
					resB = 0;
				}else if((255 - ((256*(255-bb)/(ba+1)))) > 255){
					resB = 255;
				}else {
					resB = (255 - ((256*(255-bb)/(ba+1))));
				}
				
				
				result.setP(i, j, resR << 16 | resG << 8 | resB);

			}
		}
		return result;
			
	} 
}
