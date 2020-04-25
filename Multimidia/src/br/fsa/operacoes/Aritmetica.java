package br.fsa.operacoes;

import br.fsa.utils.Imagem;

public class Aritmetica {
	
	public static Imagem soma(Imagem a, Imagem b) {
		Imagem result = new Imagem(Math.max(a.getW(), b.getW()), Math.max(a.getH(), b.getH()));
		
		for(int j = 0; j < result.getH(); j++) {
			for(int i = 0; i < result.getW(); i++) {
				int cora = a.getP(i,j);
				int ra = cora >> 16 & 0xff;
				int ga = cora >> 8 & 0xff;
				int ba = cora & 0xff;
				
				int corb = b.getP(i,j);
				int rb = corb >> 16 & 0xff;
				int gb = corb >> 8 & 0xff;
				int bb = corb & 0xff;
				
				int resR = (ra + rb)>255 ? 255:ra+rb; //if ternário
				int resG = ga + gb;
				if(resG > 255) 	//if normal
					resG = 255;
				int resB = ba + bb;
				if(resB > 255)
					resB = 255;
				
				int corResult = resR << 16 | resG << 8 | resB;
				
				result.setP(i, j, corResult);
			}
		}
		return result;
			
	}
	
	public static Imagem soma(Imagem a, int cor) {
		Imagem result = new Imagem(a.getW(), a.getH());
		int r = cor >> 16 & 0xff;
		int g = cor >> 8 & 0xff;
		int b = cor & 0xff;
		for(int j = 0; j < result.getH(); j++) {
			for(int i = 0; i < result.getW(); i++) {
				
				//separa os canasi de a
				int cora = a.getP(i,j);
				int ra = cora >> 16 & 0xff;
				int ga = cora >> 8 & 0xff;
				int ba = cora & 0xff;
				
				//faz propriamente a operação de soma
				int resR = ra + r>255?255:ra+r; //if ternário
				int resG = ga + g;
				if(resG > 255) 	//if normal
					resG = 255;
				int resB = ba + b;
				if(resB > 255)
					resB = 255;
				
				//junta os canais no resultado
				int corResult = resR << 16 | resG << 8 | resB;
				
				result.setP(i, j, corResult);
				
			}
		}
		
		return result;	
	}
	
	public static Imagem blending(Imagem a, Imagem b, int pA) {
		float pa = pA/100.0f;
		return blending(a, b, pa);
	}
	
	public static Imagem blending(Imagem a, Imagem b, float pA) {
		Imagem result = new Imagem(Math.max(a.getW(), b.getW()), Math.max(a.getH(), b.getH()));
		float pB = 1 - pA;
		for(int j = 0; j < result.getH(); j++) {
			for(int i = 0; i < result.getW(); i++) {
				int cora = a.getP(i,j);
				int ra = cora >> 16 & 0xff;
				int ga = cora >> 8 & 0xff;
				int ba = cora & 0xff;
				
				int corb = b.getP(i,j);
				int rb = corb >> 16 & 0xff;
				int gb = corb >> 8 & 0xff;
				int bb = corb & 0xff;
				
				int resR = Math.round(pA*ra + pB*rb);
				int resG = Math.round(pA*ga + pB*gb);
				int resB = Math.round(pA*ba + pB*bb);
				
				int corResult = resR << 16 | resG << 8 | resB;
				
				result.setP(i, j, corResult);
			}
		}
		
		
		return result;
	}	
	
}
