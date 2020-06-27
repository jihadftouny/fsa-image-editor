package br.fsa.filtros;

import br.fsa.utils.Imagem;
import static br.fsa.utils.Utils.red;
import static br.fsa.utils.Utils.green;
import static br.fsa.utils.Utils.blue;
import java.util.Arrays;

public class Filtros {

	public static Imagem media4(Imagem a) {
		Imagem result = new Imagem(a.getW(), a.getH());

		for (int j = 0; j < a.getH(); j++) {
			for (int i = 0; i < a.getW(); i++) {

				int acumR = red(a.getP(i, j - 1)) + // Pixel acima
						red(a.getP(i, j)) + // Pixel central
						red(a.getP(i, j + 1)) + // Pixel abaixo
						red(a.getP(i - 1, j)) + // Pixel a esquerda
						red(a.getP(i + 1, j)); // Pixel a direita

				int acumG = green(a.getP(i, j - 1)) + // Pixel acima
						green(a.getP(i, j)) + // Pixel central
						green(a.getP(i, j + 1)) + // Pixel abaixo
						green(a.getP(i - 1, j)) + // Pixel a esquerda
						green(a.getP(i + 1, j)); // Pixel a direita

				int acumB = blue(a.getP(i, j - 1)) + // Pixel acima
						blue(a.getP(i, j)) + // Pixel central
						blue(a.getP(i, j + 1)) + // Pixel abaixo
						blue(a.getP(i - 1, j)) + // Pixel a esquerda
						blue(a.getP(i + 1, j)); // Pixel a direita

				acumR = acumR / 5;
				acumG = acumG / 5;
				acumB = acumB / 5;

				result.setP(i, j, acumR << 16 | acumG << 8 | acumB);
			}
		}

		return result;
	}

	public static Imagem media(Imagem a, int r) {
		Imagem result = new Imagem(a.getW(), a.getH());

		for (int j = 0; j < a.getH(); j++) {
			for (int i = 0; i < a.getW(); i++) {

				int acumR = 0;
				int acumG = 0;
				int acumB = 0;

				for (int y = -r; y <= r; y++) {
					for (int x = -r; x <= r; x++) {
						acumR = acumR + red(a.getP(i + x, j + y));
						acumG = acumG + green(a.getP(i + x, j + y));
						acumB = acumB + blue(a.getP(i + x, j + y));
					}
				}

				acumR = acumR / ((2 * r + 1) * (2 * r + 1)); // (2r+1)�
				acumG = acumG / ((2 * r + 1) * (2 * r + 1));
				acumB = acumB / ((2 * r + 1) * (2 * r + 1));

				result.setP(i, j, acumR << 16 | acumG << 8 | acumB);
			}
		}

		return result;
	}

	public static Imagem mediana4(Imagem a) {
		Imagem result = new Imagem(a.getW(), a.getH());

		for (int j = 0; j < a.getH(); j++) {
			for (int i = 0; i < a.getW(); i++) {

				int[] acumR = { 
						red(a.getP(i, j - 1)), 
						red(a.getP(i, j)), 
						red(a.getP(i, j + 1)), 
						red(a.getP(i - 1, j)),
						red(a.getP(i + 1, j)) 
				};

				int[] acumG = { 
						green(a.getP(i, j - 1)), 
						green(a.getP(i, j)), 
						green(a.getP(i, j + 1)),
						green(a.getP(i - 1, j)),
						green(a.getP(i + 1, j)) 
				};
				
				int[] acumB = { 
						blue(a.getP(i, j - 1)), 
						blue(a.getP(i, j)), 
						blue(a.getP(i, j + 1)),
						blue(a.getP(i - 1, j)), 
						blue(a.getP(i + 1, j)) 
				};

				Arrays.sort(acumR);
				Arrays.sort(acumG);
				Arrays.sort(acumB);

				int newR = acumR[2];
				int newG = acumG[2];
				int newB = acumB[2];

				result.setP(i, j, newR << 16 | newG << 8 | newB);
			}
		}

		return result;
	}

	public static Imagem mediana(Imagem a, int r) {
		Imagem result = new Imagem(a.getW(), a.getH());

		for (int j = 0; j < a.getH(); j++) {
			for (int i = 0; i < a.getW(); i++) {

				int[] acumR = new int[((2 * r + 1) * (2 * r + 1))];
				int[] acumG = new int[((2 * r + 1) * (2 * r + 1))];
				int[] acumB = new int[((2 * r + 1) * (2 * r + 1))];
				int index = 0;

				for (int y = -r; y <= r; y++) {
					for (int x = -r; x <= r; x++) {
						acumR[index] = red(a.getP(i + x, j + y));
						acumG[index] = green(a.getP(i + x, j + y));
						acumB[index] = blue(a.getP(i + x, j + y));
						index++;
					}
				}

				Arrays.sort(acumR);
				Arrays.sort(acumG);
				Arrays.sort(acumB);

				int newR = acumR[Math.round(r / 2)];
				int newG = acumG[Math.round(r / 2)];
				int newB = acumB[Math.round(r / 2)];

				result.setP(i, j, newR << 16 | newG << 8 | newB);
			}
		}

		return result;
	}

	public static Imagem convolve(Imagem a, Kernel k) {
		Imagem result = null;

		return result;
	}

	public static Imagem gaussianBlur(Imagem a, int r, float sigma, float amp) {
		Kernel k = new Kernel(2 * r + 1, 2 * r + 1, (2 * r + 1) / 2, (2 * r + 1) / 2);
		// Calcular a fun��o Gaussiana para cada posi��o do Kernel;

		Imagem result = convolve(a, k);
		return result;
	}
}

class Kernel {
	float[][] k;
	int x;
	int y;

	public Kernel(int w, int h, int x, int y) {
		k = new float[h][w];
		this.x = x;
		this.y = y;
	}
}
