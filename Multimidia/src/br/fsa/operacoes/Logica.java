package br.fsa.operacoes;

import br.fsa.utils.Imagem;

public class Logica {

	public static Imagem and(Imagem a, Imagem b) {
		Imagem result = new Imagem(Math.max(a.getW(),  b.getW()), Math.max(a.getH(), b.getH()));
		for(int j = 0; j < result.getH(); j++) {
			for(int i = 0; i < result.getW(); i++) {
				
				//Separa os canais de cor de A
				int cora = a.getP(i, j);
				int ra = cora >> 16 & 0xff;
				int ga = cora >> 8 & 0xff;
				int ba = cora & 0xff;
				
				//Separa os canais de cor de B
				int corb = b.getP(i, j);
				int rb = corb >> 16 & 0xff;
				int gb = corb >> 8 & 0xff;
				int bb = corb & 0xff;
				
				//Pra cada canal faz Ra & Rb
				int resR = ra & rb;
				int resG = ga & gb;
				int resB = ba & bb;
				
				//Junta os resultados e salva o pixel na imagem resultante
				int corR = resR << 16 | resG << 8 | resB;
				result.setP(i, j, corR);
			}
		}
		
		return result;
	}
	
	public static Imagem or(Imagem a, Imagem b) {
		Imagem result = new Imagem(Math.max(a.getW(),  b.getW()), Math.max(a.getH(), b.getH()));
		for(int j = 0; j < result.getH(); j++) {
			for(int i = 0; i < result.getW(); i++) {
				
				//Separa os canais de cor de A
				int cora = a.getP(i, j);
				int ra = cora >> 16 & 0xff;
				int ga = cora >> 8 & 0xff;
				int ba = cora & 0xff;
				
				//Separa os canais de cor de B
				int corb = b.getP(i, j);
				int rb = corb >> 16 & 0xff;
				int gb = corb >> 8 & 0xff;
				int bb = corb & 0xff;
				
				//Pra cada canal faz Ra | Rb
				int resR = ra | rb;
				int resG = ga | gb;
				int resB = ba | bb;
				
				//Junta os resultados e salva o pixel na imagem resultante
				int corR = resR << 16 | resG << 8 | resB;
				result.setP(i, j, corR);
			}
		}
		
		return result;
	}
	
	public static Imagem xor(Imagem a, Imagem b) {
		Imagem result = new Imagem(Math.max(a.getW(),  b.getW()), Math.max(a.getH(), b.getH()));
		for(int j = 0; j < result.getH(); j++) {
			for(int i = 0; i < result.getW(); i++) {
				
				//Separa os canais de cor de A
				int cora = a.getP(i, j);
				int ra = cora >> 16 & 0xff;
				int ga = cora >> 8 & 0xff;
				int ba = cora & 0xff;
				
				//Separa os canais de cor de B
				int corb = b.getP(i, j);
				int rb = corb >> 16 & 0xff;
				int gb = corb >> 8 & 0xff;
				int bb = corb & 0xff;
				
				//Pra cada canal faz Ra ^ Rb
				int resR = ra ^ rb;
				int resG = ga ^ gb;
				int resB = ba ^ bb;
				
				//Junta os resultados e salva o pixel na imagem resultante
				int corR = resR << 16 | resG << 8 | resB;
				result.setP(i, j, corR);
			}
		}
		
		return result;
	}
	
	public static Imagem not(Imagem a) {
		Imagem result = new Imagem(a.getW(), a.getH());
		for(int j = 0; j < result.getH(); j++) {
			for(int i = 0; i < result.getW(); i++) {
				
				//Separa os canais de cor de A
				int cora = a.getP(i, j);
				int ra = cora >> 16 & 0xff;
				int ga = cora >> 8 & 0xff;
				int ba = cora & 0xff;
				
				//Pra cada canal faz ~Ra 
				int resR = ~ra;
				int resG = ~ga;
				int resB = ~ba;
				
				//Junta os resultados e salva o pixel na imagem resultante
				int corR = resR << 16 | resG << 8 | resB;
				result.setP(i, j, corR);
			}
		}
		
		return result;
	}
}