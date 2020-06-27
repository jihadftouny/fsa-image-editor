package br.fsa.operacoes;

import br.fsa.utils.Imagem;

import static br.fsa.utils.Utils.red;
import static br.fsa.utils.Utils.green;
import static br.fsa.utils.Utils.blue;

public class Geometricas {
	
	public static Imagem translacao(Imagem a, int tx, int ty) {
        Imagem result = new Imagem(a.getW() + Math.abs(tx), a.getH() + Math.abs(ty));

        for (int j = 0; j < a.getH(); j++) {
            for (int i = 0; i < a.getW(); i++) {
                // TODO Alterar o código de forma a deixar a translação funcional
                // para todo valor positivo ou negativo de tx e ty
                result.setP(i + tx, j + ty, a.getP(i, j)); // grande sagada das operções geométricas
            }
        }

        return result;
    }

    public static Imagem rotacao(Imagem a, int alpha, int cx, int cy) {
        return rotacao(a, Math.toRadians(alpha), cx, cy);
    }

    public static Imagem rotacao(Imagem a, double radians, int cx, int cy) {
        Imagem result = null;
        result = new Imagem(a.getW(), a.getH());
        for (int j = 0; j < a.getH(); j++) {
            for (int i = 0; i < a.getW(); i++) {
                int ni = i - cx;
                int nj = j - cy;
                int x = (int) Math.round(ni * Math.cos(radians) + nj * Math.sin(radians));
                int y = (int) Math.round(-ni * Math.sin(radians) + nj * Math.cos(radians));
                int nx = x + cx;
                int ny = y + cy;
                result.setP(nx, ny, a.getP(i, j));
            }
        }
        return result;
    }
	
	public static Imagem escala(Imagem a, float s) {	
		return escala(a, s, s);
	}
 
    private static float lerp(float s, float e, float t) {
        return s + (e - s) * t;
    }
 
    private static float blerp(final Float c00, float c10, float c01, float c11, float tx, float ty) {
        return lerp(lerp(c00, c10, tx), lerp(c01, c11, tx), ty);
    }
	
	 public static Imagem escala(Imagem a, float sx, float sy) {
	        int newW = (int) (a.getW() * sx);
	        int newH = (int) (a.getH() * sy);
	        Imagem result = new Imagem(newW, newH);
	        for (int x = 0; x < newW; ++x) {
	            for (int y = 0; y < newH; ++y) {
	                float gx = ((float) x) / newW * (a.getWidth() - 1);
	                float gy = ((float) y) / newH * (a.getHeight() - 1);
	                int gxi = (int) gx;
	                int gyi = (int) gy;
	                int c00 = a.getP(gxi, gyi);
	                int c10 = a.getP(gxi + 1, gyi);
	                int c01 = a.getP(gxi, gyi + 1);
	                int c11 = a.getP(gxi + 1, gyi + 1);
	                
	                int r = ((int) blerp((float) red(c00), red(c10), red(c01), red(c11), gx - gxi, gy - gyi)) ;
	                int g = ((int) blerp((float) green(c00), green(c10), green(c01), green(c11), gx - gxi, gy - gyi)) ;
	                int b = ((int) blerp((float) blue(c00), blue(c10), blue(c01), blue(c11), gx - gxi, gy - gyi));

	                int resultRGB = r << 16 | g << 8 | b;
	                result.setP(x, y, resultRGB);
	            }
	        }
	        return result;
	    }
	 
	 public static Imagem escalaPixel(Imagem a, int sx, int sy) {
		 	
	        int newW = (int) sx;
	        int newH = (int) sy;
	        Imagem result = new Imagem(newW, newH);
	        for (int x = 0; x < newW; ++x) {
	            for (int y = 0; y < newH; ++y) {
	                float gx = ((float) x) / newW * (a.getWidth() - 1);
	                float gy = ((float) y) / newH * (a.getHeight() - 1);
	                int gxi = (int) gx;
	                int gyi = (int) gy;
	                int c00 = a.getP(gxi, gyi);
	                int c10 = a.getP(gxi + 1, gyi);
	                int c01 = a.getP(gxi, gyi + 1);
	                int c11 = a.getP(gxi + 1, gyi + 1);
	                
	                int r = ((int) blerp((float) red(c00), red(c10), red(c01), red(c11), gx - gxi, gy - gyi)) ;
	                int g = ((int) blerp((float) green(c00), green(c10), green(c01), green(c11), gx - gxi, gy - gyi)) ;
	                int b = ((int) blerp((float) blue(c00), blue(c10), blue(c01), blue(c11), gx - gxi, gy - gyi));

	                int resultRGB = r << 16 | g << 8 | b;
	                result.setP(x, y, resultRGB);
	            }
	        }
	        return result;
	    }
}