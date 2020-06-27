package br.fsa.views;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import br.fsa.operacoes.Aritmetica;
import br.fsa.utils.Imagem;

import com.jgoodies.forms.layout.FormSpecs;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class vw_AritmeticaDivisao extends JDialog {

	public vw_AritmeticaDivisao(ArrayList<JInternalFrame> frames) {
		setTitle("Divisão");

		setResizable(false);
		setBounds(100, 100, 455, 207);
		getContentPane().setLayout(new MigLayout("", "[200][center][200]", "[33px][24px][24px][44.00px][43.00px]"));

		JLabel lblIntro = new JLabel("Selecione as imagens que serão divididas");
		lblIntro.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblIntro.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblIntro, "cell 0 0 3 1,grow");

		JComboBox<String> cbb1 = new JComboBox<String>();
		cbb1.setFont(new Font("Dialog", Font.PLAIN, 12));
		cbb1.setToolTipText("Imagem 1");
		getContentPane().add(cbb1, "cell 0 1 3 1,growx,aligny top");

		JLabel lblOperacao = new JLabel("/");
		lblOperacao.setFont(new Font("Dialog", Font.BOLD, 18));
		lblOperacao.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblOperacao, "cell 0 2 3 1,growx,aligny top");

		JComboBox<String> cbb2 = new JComboBox<String>();
		cbb2.setToolTipText("Imagem 2");
		cbb2.setFont(new Font("Dialog", Font.PLAIN, 12));
		getContentPane().add(cbb2, "cell 0 3 3 1,growx,aligny top");

		for (int i = 0; i < frames.size(); i++) {
			cbb1.addItem(frames.get(i).getTitle());
			cbb2.addItem(frames.get(i).getTitle());
		}

		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Imagem imagem1 = null;
				Imagem imagem2 = null;
				
				for (int i = 0; i < frames.size(); i++) {
					if (cbb1.getSelectedItem() == frames.get(i).getTitle()) {
						
						try {
							imagem1 = new Imagem(new File(frames.get(i).getName()));
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					}
					
					if (cbb2.getSelectedItem() == frames.get(i).getTitle()) {
						
						try {
							imagem2 = new Imagem(new File(frames.get(i).getName()));
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					}
					
					if (imagem1 != null && imagem2 != null) {
						Imagem result = Aritmetica.divisao(imagem1, imagem2);
						
						JDialog dialog = new JDialog();
						
						dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						dialog.setBounds(0, 0, result.getW(), result.getH());
						dialog.add(result);
						dialog.setVisible(true);
						dialog.setModal(true);
						setVisible(false);
						break;
					}
				}
			}
		});

		getContentPane().add(btnOk, "cell 0 4,alignx center,aligny top");

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});

		getContentPane().add(btnCancelar, "cell 2 4,alignx center,aligny top");

	}
}
