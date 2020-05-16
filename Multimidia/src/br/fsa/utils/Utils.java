package br.fsa.utils;

public class Utils {

	public static int red(int cor) {
		return cor >> 16 & 0xff;
	}
	
	public static int green(int cor) {
		return cor >> 8 & 0xff;
	}
	
	public static int blue(int cor) {
		return cor & 0xff;
	}
	
	public static int map(int valor, int valorMax, int num) {
		// valor -> Valor que sera mapeado
		// valorMax -> Valor máximo que o valor que sera mapeado pode chegar
		// num -> Valor que sera comparado com o máximo para se fazer o mapeamento
		int valorMap = (((-1*num)*valor)/valorMax) + num;
		return valorMap; 
	}
}