package br.fsa.extras;

import br.fsa.utils.Imagem;

public class Extras {
	
	static public Imagem drawMand(int w, int h) {
		Imagem img = new Imagem(w, h);
		img.getP(801,700);
		float cr;
		float ci;
		float xmin = -2.5f;
		float xmax = 1.f;
		float ymin = -((xmax-xmin)*img.getH()/(float)img.getW())/2.0f;
		float ymax = -ymin;
		float width = img.getW();
		float height = img.getH();
		for(int j = 0; j < height; j++) {
			for(int i = 0; i < width; i++) {
				cr =  i*(xmax-xmin)/width + xmin;
				ci = j*(ymin-ymax)/height + ymax;
				float zr = cr;
				float zi = ci;
				boolean is = true;
				int k;
				for(k = 0; k <= 1000; k++) {
					if(zr*zr+zi*zi>4) {
						is = false;
						break;
					}
					float tmp = zr*zr-zi*zi+cr;
					zi = 2*zr*zi + ci;
					zr = tmp;
				}
				if(!is) {
					int color = k>255?255:k; 
					img.setP(i,j,color<<16|color<<8|color);
				}
			}
		}
		return img;
	}

}
