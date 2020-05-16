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
				
				int resR = (ra + rb)>255 ? 255:ra+rb; //if ternario
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
	
	public static Imagem substracao(Imagem a, Imagem b){
		Imagem result = new Imagem(Math.max(a.getW(), b.getW()), Math.max(a.getH(), b.getH()));

		for(int j = 0; j < result.getH(); j++){
			for(int i = 0; i < result.getW(); i++){
				int cora = a.getP(i,j);
				int ra = cora >> 16 & 0xFF;
				int ga = cora >> 8 & 0xFF;
				int ba = cora & 0xFF;
				
				int corb = b.getP(i,j);
				int rb = corb >> 16 & 0xFF;
				int gb = corb >> 8 & 0xFF;
				int bb = corb & 0xFF;

				int resR = ra - rb < 0 ? 0 : ra - rb;
				int resG = ga - gb < 0 ? 0 : ga - gb;
				int resB = ba - bb < 0 ? 0 : ba - bb;

				int corResult = resR << 16 | resG << 8 | resB;

				result.setP(i,j,corResult);
			}
		}
		return result;
	}
	
	public static Imagem multiplicacao(Imagem a, Imagem b){
		Imagem result = new Imagem(Math.max(a.getW(), b.getW()), Math.max(a.getH(), b.getH()));

		for(int j = 0; j < result.getH(); j++){
			for(int i = 0; i < result.getW(); i++){
				int cora = a.getP(i,j);
				int ra = cora >> 16 & 0xFF;
				int ga = cora >> 8 & 0xFF;
				int ba = cora & 0xFF;
				
				int corb = b.getP(i,j);
				int rb = corb >> 16 & 0xFF;
				int gb = corb >> 8 & 0xFF;
				int bb = corb & 0xFF;

				int resR = ra * rb > 255 ? 255 : ra * rb;
				int resG = ga * gb > 255 ? 255 : ga * gb;
				int resB = ba * bb > 255 ? 255 : ba * bb;

				int corResult = resR << 16 | resG << 8 | resB;

				result.setP(i,j,corResult);
			}
		}
		return result;
	}

	public static Imagem divisao(Imagem a, Imagem b){
		Imagem result = new Imagem(Math.max(a.getW(), b.getW()), Math.max(a.getH(), b.getH()));

		for(int j = 0; j < result.getH(); j++){
			for(int i = 0; i < result.getW(); i++){
				int cora = a.getP(i,j);
				int ra = cora >> 16 & 0xFF;
				int ga = cora >> 8 & 0xFF;
				int ba = cora & 0xFF;
				
				int corb = b.getP(i,j);
				int rb = corb >> 16 & 0xFF;
				int gb = corb >> 8 & 0xFF;
				int bb = corb & 0xFF;

				int resB = 0;
				int resR = 0;
				int resG = 0;
				
				if(ra != 0 && rb != 0) {
					resR = ra / rb;
				}
				if(ga != 0 && gb != 0) {
					resG = ga / gb;
				}
				if(ba != 0 && bb != 0) {
					resB = ba / bb;
				}

				int corResult = resR << 16 | resG << 8 | resB;

				result.setP(i,j,corResult);
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
				
				int cora = a.getP(i,j);
				int ra = cora >> 16 & 0xff;
				int ga = cora >> 8 & 0xff;
				int ba = cora & 0xff;
				
				int resR = ra + r>255?255:ra+r;
				int resG = ga + g;
				if(resG > 255)
					resG = 255;
				int resB = ba + b;
				if(resB > 255)
					resB = 255;
				
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
