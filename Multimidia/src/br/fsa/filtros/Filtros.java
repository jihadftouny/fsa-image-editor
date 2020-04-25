package br.fsa.filtros;

import br.fsa.utils.Imagem;
import static br.fsa.utils.Utils.red;
import static br.fsa.utils.Utils.green;
import static br.fsa.utils.Utils.blue;
public class Filtros {
	
	public static Imagem media4(Imagem a) {
		Imagem result = new Imagem(a.getW(), a.getH());
		
		for(int j = 0; j < a.getH(); j++) {
			for(int i = 0; i < a.getW(); i++) {
				int acumR = red(a.getP(i,j-1)) + red(a.getP(i, j)) + red(a.getP(i, j+1)) + red(a.getP(i-1, j)) + red(a.getP(i+1, j));
				int acumG = green(a.getP(i,j-1)) + green(a.getP(i, j)) + green(a.getP(i, j+1)) + green(a.getP(i-1, j)) + green(a.getP(i+1, j));
				int acumB = blue(a.getP(i,j-1)) + blue(a.getP(i, j)) + blue(a.getP(i, j+1)) + blue(a.getP(i-1, j)) + blue(a.getP(i+1, j));
				acumR /= 5;
				acumG /= 5;
				acumB /= 5;
				
				result.setP(i, j, acumR<<16|acumG<<8|acumB);
			}
		}
		
		return result;
	}
	
	public static Imagem media(Imagem a, int r) {
		Imagem result = null;
		for(int j = 0; j < a.getH(); j++) {
			for(int i = 0; i < a.getW(); i++) {
				
				//for x em função do raio 
				//for y em função do raio
					//Acumular média r, g e b
					//dividir pelo total
					//colocar na resultante i, j
			}
		}
		
		return result;
	}
	
	public static Imagem mediana(Imagem a, int r) {
		Imagem result = null;
		for(int j = 0; j < a.getH(); j++) {
			for(int i = 0; i < a.getW(); i++) {
				
				//for x em função do raio 
				//for y em função do raio
					//Guardar valores de r, g e b
					//Ordenar os valores guardados
					//Pegar o do meio
					//colocar na resultante i, j
			}
		}
		
		return result;
	}
	
	public static Imagem convolve(Imagem a, Kernel k) {
		Imagem result = null;
		
		
		return result;
	}
	
	public static Imagem gaussianBlur(Imagem a, int r, float sigma, float amp) {
		Kernel k = new Kernel(2*r+1, 2*r+1, (2*r+1)/2, (2*r+1)/2);
		//Calcular a função Gaussiana para cada posição do Kernel;
		
		
		Imagem result = convolve(a,k);
		return result;
	}
	
}

class Kernel{
	
	float[][] k;
	int x;
	int y;
	
	public Kernel(int w, int h, int x, int y) {
		k = new float[h][w];
		this.x = x;
		this.y = y;
	 
	}
}