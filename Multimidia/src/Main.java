import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;

import br.fsa.operacoes.Geometricas;
import br.fsa.utils.Imagem;
public class Main {

	public static void main(String[] args) {
		JFrame f = new JFrame("Teste");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setBounds(0,0,800,600);
		Imagem a = null;
		Imagem b = null;
		try {
			a = new Imagem(new File("/home/user/Imagens/liliana.jpg"));
			b = Geometricas.translacao(a, 20, 20);
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}
		if(a != null && b != null) {
			 
			f.add(b);
		}
		f.setVisible(true);
	}
}
