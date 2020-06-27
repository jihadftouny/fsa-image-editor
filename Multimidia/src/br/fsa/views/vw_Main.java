package br.fsa.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLayeredPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

import java.awt.Color;
import java.awt.Component;

import javax.swing.border.BevelBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.LayeredHighlighter;

import br.fsa.atividade.Ex1;
import br.fsa.utils.Imagem;
import br.fsa.utils.Frame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JDesktopPane;

public class vw_Main {

	private JFrame frmEditorDeImagem;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vw_Main window = new vw_Main();
					window.frmEditorDeImagem.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public vw_Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */

	private void initialize() {
		frmEditorDeImagem = new JFrame();
		frmEditorDeImagem.getContentPane().setBackground(Color.LIGHT_GRAY);
		frmEditorDeImagem.setTitle("Editor de Imagem Java");
		frmEditorDeImagem.setBounds(100, 100, 800, 600);
		frmEditorDeImagem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		frmEditorDeImagem.setJMenuBar(menuBar);

		JMenu mnArquivo = new JMenu("Arquivo");
		menuBar.add(mnArquivo);

		JMenuItem mnArquivoAbrir = new JMenuItem("Abrir");

		mnArquivo.add(mnArquivoAbrir);

		JMenu mnFiltros = new JMenu("Filtros");
		menuBar.add(mnFiltros);

		JMenuItem mnFiltrosMediar = new JMenuItem("Média R");
		mnFiltros.add(mnFiltrosMediar);

		JMenuItem mnFiltrosMedia4 = new JMenuItem("Média 4");
		mnFiltros.add(mnFiltrosMedia4);

		JMenuItem mnFiltrosMedianar = new JMenuItem("Mediana R");
		mnFiltros.add(mnFiltrosMedianar);

		JMenu mnOperacoes = new JMenu("Operações");
		menuBar.add(mnOperacoes);

		JMenu mnAritmetica = new JMenu("Aritmética");
		mnOperacoes.add(mnAritmetica);

		JMenuItem mnAritmeticaBlending = new JMenuItem("Blending");
		mnAritmetica.add(mnAritmeticaBlending);

		JMenuItem mnAritmeticaSoma = new JMenuItem("Soma");
		mnAritmetica.add(mnAritmeticaSoma);

		JMenuItem mnAritmeticaSubtracao = new JMenuItem("Subtração");
		mnAritmetica.add(mnAritmeticaSubtracao);

		JMenuItem mnAritmeticaDivisao = new JMenuItem("Divisão");
		mnAritmetica.add(mnAritmeticaDivisao);

		JMenuItem mnAritmeticaMultiplicacao = new JMenuItem("Multiplicação");
		mnAritmetica.add(mnAritmeticaMultiplicacao);

		JMenu mnGeometrica = new JMenu("Geométrica");
		mnOperacoes.add(mnGeometrica);

		JMenuItem mnGeometricaTranslacao = new JMenuItem("Translação");
		mnGeometrica.add(mnGeometricaTranslacao);

		JMenuItem mnGeometricaRotacao = new JMenuItem("Rotação");
		mnGeometrica.add(mnGeometricaRotacao);

		JMenuItem mnGeometricaEscala = new JMenuItem("Escala");
		mnGeometrica.add(mnGeometricaEscala);

		JMenu mnLogica = new JMenu("Lógica");
		mnOperacoes.add(mnLogica);

		JMenuItem mnLogicaAnd = new JMenuItem("AND");
		mnLogica.add(mnLogicaAnd);

		JMenuItem mnLogicaOr = new JMenuItem("OR");
		mnLogica.add(mnLogicaOr);

		JMenuItem mnLogicaXor = new JMenuItem("XOR");
		mnLogica.add(mnLogicaXor);

		JMenuItem mnLogicaNot = new JMenuItem("NOT");
		mnLogica.add(mnLogicaNot);

		JMenu mnMascara = new JMenu("Máscara");
		mnOperacoes.add(mnMascara);

		JMenuItem mnMascaraBurn = new JMenuItem("Burn");
		mnMascara.add(mnMascaraBurn);

		JMenu mnPontuais = new JMenu("Pontuais");
		menuBar.add(mnPontuais);

		JMenuItem mnPontuaisCinzamedio = new JMenuItem("Cinza Médio");
		mnPontuais.add(mnPontuaisCinzamedio);

		JMenuItem mnPontuaisCinzantsc = new JMenuItem("Cinza NTSC");
		mnPontuais.add(mnPontuaisCinzantsc);

		JMenuItem mnPontuaisCinzahdtv = new JMenuItem("Cinza HDTV");
		mnPontuais.add(mnPontuaisCinzahdtv);

		JMenuItem mnPontuaisCinzahdr = new JMenuItem("Cinza HDR");
		mnPontuais.add(mnPontuaisCinzahdr);

		JMenuItem mnPontuaisHistograma = new JMenuItem("Histograma");
		mnPontuais.add(mnPontuaisHistograma);

		JMenuItem mnPontuaisLimiar = new JMenuItem("Limiar (Threshold)");
		mnPontuais.add(mnPontuaisLimiar);

		JMenu mnAjuda = new JMenu("Ajuda");
		menuBar.add(mnAjuda);

		JLabel lbCamadas = new JLabel("Camadas");
		lbCamadas.setFont(new Font("Dialog", Font.BOLD, 24));

		JLabel lblFerramentas = new JLabel("Ferramentas");
		lblFerramentas.setFont(new Font("Dialog", Font.BOLD, 11));

		JDesktopPane mainDesktop = new JDesktopPane() {
			private static final long serialVersionUID = 1L;

			public void paintComponent(Graphics g) {
//				Descomentar esse bloco para adicionar imagem de fundo no mainDesktop

//				ImageIcon icon = new ImageIcon(getClass().getResource("/br/fsa/imagem/fsa1.png"));
//				Image image = icon.getImage();
//				
//				g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
			}
		};

		GroupLayout groupLayout = new GroupLayout(frmEditorDeImagem.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup().addGap(13).addComponent(lblFerramentas).addGap(18)
						.addComponent(mainDesktop, GroupLayout.DEFAULT_SIZE, 529, Short.MAX_VALUE).addGap(26)
						.addComponent(lbCamadas).addGap(22)));

		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup().addGap(61).addComponent(lblFerramentas))
								.addGroup(groupLayout.createSequentialGroup().addGap(51).addComponent(lbCamadas)))
						.addContainerGap(456, Short.MAX_VALUE))
				.addComponent(mainDesktop, GroupLayout.DEFAULT_SIZE, 539, Short.MAX_VALUE));

		frmEditorDeImagem.getContentPane().setLayout(groupLayout);

		mnArquivoAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setDialogTitle("Selecione uma imagem");
				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

				FileNameExtensionFilter filter = new FileNameExtensionFilter("Imagem (*.jpg, *.png)", "jpg", "png",
						"jpeg");

				fileChooser.setFileFilter(filter);
				Component c = null;
				int retorno = fileChooser.showOpenDialog(c);

				if (retorno == JFileChooser.APPROVE_OPTION) {
					if (fileChooser.getSelectedFile().getAbsolutePath().endsWith(".jpg") ||
					fileChooser.getSelectedFile().getAbsolutePath().endsWith(".png")) {

						File file = fileChooser.getSelectedFile();
						Frame frame = new Frame(file);
						mainDesktop.add(frame);

					} else {

						JOptionPane.showMessageDialog(null, "Você deve selecionar uma imagem!");

					}
				}

			}
		});

		mnAritmeticaSoma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (mainDesktop.getAllFrames() != null) { // TODO arrumar condição
					ArrayList<JInternalFrame> frames = new ArrayList<JInternalFrame>(Arrays.asList(mainDesktop.getAllFrames()));
					
					vw_AritmeticaSoma soma = new vw_AritmeticaSoma(frames);
					soma.setVisible(true);
				}else {
					System.out.println("Abra pelo menos uma imagem antes!");
				}

			}
		});

	}
}
