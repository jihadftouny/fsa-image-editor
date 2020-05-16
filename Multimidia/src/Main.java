import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;

import br.fsa.atividade.Ex1;
import br.fsa.filtros.Filtros;
import br.fsa.operacoes.Geometricas;
import br.fsa.pontuais.Pontuais;
import br.fsa.utils.Imagem;
public class Main {

	public static void main(String[] args) {
		
		JFrame f = new JFrame("Imagem Original");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setBounds(0,0,800,600);
		
		JFrame f2 = new JFrame("Imagem Modificada");
		f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f2.setBounds(0,0,800,600);
		
		Imagem a = null;
		Imagem b = null;
		try {
			a = new Imagem(new File("C:\\Users\\Kana\\Desktop\\Facul\\Multimidia e Jogos Digitais\\sonnet_lena.png"));
			b = Pontuais.threshold(a, 125);
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}
		if(a != null && b != null) {
			f.add(a);
			f2.add(b);
		}
		f.setVisible(true);
		f2.setVisible(true);
	}
}


// Histograma - Desenho que mostra a quantidade de pixels em cada tom de cinza. (Precisa caber em uma imagem 150x256)


// Limiar (Threshold) - Determina um valor e se o pixel for menor que esse valor fica preto, se for maior, fica branco