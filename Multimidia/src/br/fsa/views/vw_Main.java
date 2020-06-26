package br.fsa.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;

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
		
		JMenuItem mnFiltrosConvolve = new JMenuItem("Convolução");
		mnFiltros.add(mnFiltrosConvolve);
		
		JMenu mnOperacoes = new JMenu("Operações");
		menuBar.add(mnOperacoes);
		
		JMenu mnPontuais = new JMenu("Pontuais");
		menuBar.add(mnPontuais);
		
		JMenu mnAjuda = new JMenu("Ajuda");
		menuBar.add(mnAjuda);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		layeredPane.setBackground(Color.WHITE);
		
		JLabel lbCamadas = new JLabel("Camadas");
		lbCamadas.setFont(new Font("Dialog", Font.BOLD, 24));
		
		JLabel lblNewLabel = new JLabel("Ferramentas");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 11));
		
		GroupLayout groupLayout = new GroupLayout(frmEditorDeImagem.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
					.addComponent(layeredPane, GroupLayout.PREFERRED_SIZE, 515, GroupLayout.PREFERRED_SIZE)
					.addGap(36)
					.addComponent(lbCamadas)
					.addGap(30))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(layeredPane, GroupLayout.DEFAULT_SIZE, 539, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(51)
					.addComponent(lbCamadas)
					.addContainerGap(456, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(61)
					.addComponent(lblNewLabel)
					.addContainerGap(463, Short.MAX_VALUE))
		);
		frmEditorDeImagem.getContentPane().setLayout(groupLayout);
		
		mnArquivoAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setDialogTitle("Selecione uma imagem");
				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Imagem (*.jpg, *.png)", "jpg", "png", "jpeg");
				
				fileChooser.setFileFilter(filter);
				Component c = null;
				int retorno = fileChooser.showOpenDialog(c);
				

				if (retorno == JFileChooser.APPROVE_OPTION) {
					if (fileChooser.getSelectedFile().getAbsolutePath().contains(".jpg") || 
						fileChooser.getSelectedFile().getAbsolutePath().contains(".png")) {
						
						File file = fileChooser.getSelectedFile();

						ImageIcon img = new ImageIcon(file.getPath());
						JLabel lblImg = new JLabel(img);
						
						lblImg.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
						layeredPane.add(lblImg);
						
					} else {
						
						JOptionPane.showMessageDialog(null, "Você deve selecionar uma imagem!");
					
					}
				}
				
			}
		});
		
	}
}
