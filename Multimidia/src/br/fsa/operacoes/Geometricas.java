package br.fsa.operacoes;

import br.fsa.utils.Imagem;

public class Geometricas {
	
	public static Imagem translacao(Imagem a, int tx, int ty) {
		Imagem result = new Imagem(a.getW()+Math.abs(tx), a.getH()+Math.abs(ty)) ;
		
		for(int j = 0; j < a.getH(); j++) {
			for(int i = 0; i < a.getW(); i++) {
				//TODO Alterar o código de forma a deixar a translação funcional
				//para todo valor positivo ou negativo de tx e ty
				result.setP(i+tx, j+ty, a.getP(i, j)); //grande sagada das operções geométricas
			}
		}
		
		return result;
	}
	
	public static Imagem rotacao(Imagem a, int alpha, int cx, int cy) {
		return rotacao(a, Math.toRadians(alpha),  cx, cy);
	}

	public static Imagem rotacao(Imagem a, double radians, int cx, int cy) {
		Imagem result = null;
		//Operações necessárias à rotação.
		//Calcular o tamanho da imagem resultante
		//Realizar a translação
		//Realizar a rotação (operação com a matriz
		//Realizar a operação inversa
		return result;
	}
	
	public static Imagem escala(Imagem a, float s) {
		return escala(a, s, s);
	}
	
	public static Imagem escala(Imagem a, float sx, float sy) {
		Imagem result = null;
		
		//Definir tamanho da resultante
		//Definir onde as cores da original cairão na resultante
		//Preencher os pixels intermediários usando interpolação ou
		//cópia
		
		return result;
	}
}