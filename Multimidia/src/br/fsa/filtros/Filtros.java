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

				int[] acumR = { red(a.getP(i, j - 1)), red(a.getP(i, j)), red(a.getP(i, j + 1)), red(a.getP(i - 1, j)),
						red(a.getP(i + 1, j)) };

				int[] acumG = { green(a.getP(i, j - 1)), green(a.getP(i, j)), green(a.getP(i, j + 1)),
						green(a.getP(i - 1, j)), green(a.getP(i + 1, j)) };

				int[] acumB = { blue(a.getP(i, j - 1)), blue(a.getP(i, j)), blue(a.getP(i, j + 1)),
						blue(a.getP(i - 1, j)), blue(a.getP(i + 1, j)) };

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

	public static Imagem convolucao(Imagem a, Kernel k) {
		Imagem result = new Imagem(a.getW(), a.getH());

		for (int j = 0; j < a.getH(); j++) {
			for (int i = 0; i < a.getW(); i++) {
				float accumR = 0;
				float accumG = 0;
				float accumB = 0;

				for (int l = 0; l < k.k.length; l++) {
					for (int m = 0; m < k.k[0].length; m++) {
						int color = a.getP(i - k.cx + l, j - k.cy + m);
						int r = red(color);
						int g = green(color);
						int b = blue(color);
						accumR += k.k[l][m] * r;
						accumG += k.k[l][m] * g;
						accumB += k.k[l][m] * b;
					}
				}

				int r = Math.round(accumR);
				int g = Math.round(accumG);
				int b = Math.round(accumB);

				r = r > 255 ? 255 : r;
				g = g > 255 ? 255 : g;
				b = b > 255 ? 255 : b;

				result.setP(i, j, r << 16 | g << 8 | b);
			}
		}
		return result;
	}
	
	public static Imagem convolucao2(Imagem a, Kernel k) {
		Imagem result = new Imagem(a.getW(), a.getH());
		// Imagem
		for (int j = 0; j < a.getH(); j++) {
			for (int i = 0; i < a.getW(); i++) {
				float accumR = 0;
				float accumG = 0;
				float accumB = 0;
				// Kernel
				for (int l = 0; l < k.k.length; j++) {
					for (int m = 0; m < k.k[0].length; m++) {
						int cor = a.getP(i - k.cx + l, j - k.cy + m);
						int r = red(cor);
						int g = green(cor);
						int b = blue(cor);
						accumR += k.k[l][m] * r;
						accumG += k.k[l][m] * g;
						accumB += k.k[l][m] * b;
					}
				}

				int r = Math.round(accumR);
				int g = Math.round(accumG);
				int b = Math.round(accumB);

				r = r > 255 ? 255 : r;
				g = g > 255 ? 255 : g;
				b = b > 255 ? 255 : b;

				int corR = r << 16 | g << 8 | b;

				result.setP(i, j, corR);

			}
		}

		return result;
	}
	
//	public static Imagem convolucao2(Imagem a, Kernel k) {
//		Imagem result = new Imagem(a.getW(), a.getH());
//		
//		for (int j = 0; j < a.getH(); j++) {
//			for (int i = 0; i < a.getW(); i++) {
//				float accumR = 0;
//				float accumG = 0;
//				float accumB = 0;
//
//				for (int l = 0; l < k.k.length; l++) {
//					for (int m = 0; m < k.k[0].length; m++) {
//						int color = a.getP(i + l - 1, j + m - 1);
//						
////						int color = a.getP(i - k.cx + l, j - k.cy + m);
//						int r = red(color);
//						int g = green(color);
//						int b = blue(color);
//						accumR += k.k[l][m] * r;
//						accumG += k.k[l][m] * g;
//						accumB += k.k[l][m] * b;
//					}
//				}
//
//				int r = Math.round(accumR);
//				int g = Math.round(accumG);
//				int b = Math.round(accumB);
//
//				r = r > 255 ? 255 : r;
//				g = g > 255 ? 255 : g;
//				b = b > 255 ? 255 : b;
//
//				result.setP(i, j, r << 16 | g << 8 | b);
//			}
//		}
//		return result;
//	}

	public static Imagem gaussianBlur(Imagem a, int r, float sigma, float amp) {
		Kernel k = new Kernel(r);
		gauss(k, sigma, amp);

		Imagem result = Filtros.convolucao(a, k);
		return result;
	}

	private static Kernel gauss(Kernel k, float sigma, float amp) {
		float uini = -2;
		float uend = 2;

		for (int i = 0; i < k.k.length; i++) {
			for (int j = 0; j < k.k[0].length; j++) {
				float x = (uend - uini) * i / (float) (k.k.length - 1) + uini;
				float y = (uini - uend) * j / (float) (k.k[0].length - 1) + uend;
				k.k[i][j] = (float) (amp * Math.exp(-(x * x + y * y) / sigma));
			}
		}
		return k;
	}

	public static Imagem robertsCross(Imagem a) {
		Imagem result = new Imagem(a.getW(), a.getH());
		Kernel k1 = new Kernel(2, 2, 0, 0);
		Kernel k2 = new Kernel(2, 2, 0, 0);

		k1.k[0][0] = 1;
		k1.k[0][1] = 0;
		k1.k[1][0] = 0;
		k1.k[1][1] = -1;
		
		k2.k[0][0] = 0;
		k2.k[0][1] = 1;
		k2.k[1][0] = -1;
		k2.k[1][1] = 0;

		Imagem imagem1 = Filtros.convolucao2(a, k1);
		Imagem imagem2 = Filtros.convolucao2(a, k2);
		
		for (int j = 0; j < a.getH(); j++) {
			for (int i = 0; i < a.getW(); i++) {
				int p1 = imagem1.getP(i, j);
				int p2 = imagem2.getP(i, j);
				int pf = (int) Math.sqrt((p1*p1)+(p2*p2));
				
				result.setP(i, j, pf);
			}
		}
		
		return result;
	}
	
	public static Imagem roberts(Imagem a) {
		Imagem result = new Imagem(a.getW(),a.getH());
		Kernel kx = new Kernel(1);
		Kernel ky = new Kernel(1);
		
		Imagem gx = null;
		Imagem gy = null;
		
		
		kx.k[0][0] = 1;
		kx.k[0][1] = 0;
		kx.k[1][0] = 0;
		kx.k[1][1] = -1;
		
		ky.k[0][0] = 0;
		ky.k[0][1] = 1;
		ky.k[1][0] = -1;
		ky.k[1][1] = 0;

		
		gx = convolucao(a, kx);
		gy = convolucao(a, ky);
		
		for(int j = 0; j < gx.getH(); j++) {
			for(int i = 0; i < gx.getW(); i++) {
				int pixelGx = gx.getP(i, j);
				int pixelGy = gy.getP(i, j);
				
				int resR = (int) Math.sqrt(Math.pow(red(pixelGx), red(pixelGy)));
				int resG = (int) Math.sqrt(Math.pow(green(pixelGx), green(pixelGy)));
				int resB = (int) Math.sqrt(Math.pow(blue(pixelGx), blue(pixelGy)));
				
				int resultRGB = resR << 16 | resG << 8 | resB;
				 result.setP(i, j, resultRGB);
			}	
		}
		return result;
	}
	
	public static Imagem sobelOperator(Imagem a) {
		Imagem result = new Imagem(a.getW(), a.getH());
		Kernel k1 = new Kernel(3, 3, 1, 1);
		Kernel k2 = new Kernel(3, 3, 1, 1);

		k1.k[0][0] = 1;
		k1.k[0][1] = 0;
		k1.k[0][2] = -1;
		k1.k[1][0] = 2;
		k1.k[1][1] = 0;
		k1.k[1][2] = -2;
		k1.k[2][0] = 1;
		k1.k[2][1] = 0;
		k1.k[2][2] = -1;
		
		k2.k[0][0] = 1;
		k2.k[0][1] = 2;
		k2.k[0][2] = 1;
		k2.k[1][0] = 0;
		k2.k[1][1] = 0;
		k2.k[1][2] = 0;
		k2.k[2][0] = -1;
		k2.k[2][1] = -2;
		k2.k[2][2] = -1;

		Imagem imagem1 = Filtros.convolucao2(a, k1);
		Imagem imagem2 = Filtros.convolucao2(a, k2);
		
		for (int j = 0; j < a.getH(); j++) {
			for (int i = 0; i < a.getW(); i++) {
				int p1 = imagem1.getP(i, j);
				int p2 = imagem2.getP(i, j);
				int pf = (int) Math.sqrt((p1*p1)+(p2*p2));
				
				result.setP(i, j, pf);
			}
		}
		
		return result;
	}
}

class Kernel {

	float[][] k;
	int cx, cy; // Central Position

	public Kernel(int w, int h, int cx, int cy) {
		k = new float[h][w];
		this.cx = cx;
		this.cy = cy;
	}

	public Kernel(int r) {
		k = new float[2 * r + 1][2 * r + 1];
		cx = r;
		cy = r;

	}
}
