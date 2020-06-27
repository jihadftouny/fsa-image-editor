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

import br.fsa.filtros.Filtros;
import br.fsa.operacoes.Aritmetica;
import br.fsa.operacoes.Geometricas;
import br.fsa.operacoes.Logica;
import br.fsa.pontuais.Pontuais;
import br.fsa.utils.Imagem;

import com.jgoodies.forms.layout.FormSpecs;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class vw_GeometricaEscala extends JDialog {
	private JTextField txtX;
	private JTextField txtY;

	public vw_GeometricaEscala(ArrayList<JInternalFrame> frames) {
		setTitle("Escala");

		setResizable(false);
		setBounds(100, 100, 455, 207);
		getContentPane()
				.setLayout(new MigLayout("", "[200][73.00,center][-21.00][61.00,grow][]", "[33px][24px][24px][44.00px][43.00px]"));

		JLabel lblIntro = new JLabel("Selecione a imagem que fará a Escala");
		lblIntro.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblIntro.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblIntro, "cell 0 0 5 1,grow");
		
		JLabel lblTexto2 = new JLabel("Eixo Y");
		getContentPane().add(lblTexto2, "cell 4 1");

		JComboBox<String> cbb1 = new JComboBox<String>();
		cbb1.setFont(new Font("Dialog", Font.PLAIN, 12));
		cbb1.setToolTipText("Imagem 1");
		getContentPane().add(cbb1, "cell 0 2 2 1,growx,aligny top");

		for (int i = 0; i < frames.size(); i++) {
			cbb1.addItem(frames.get(i).getTitle());
		}

		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtX.getText().matches("[^0-9]") || txtX.getText().isBlank() || txtX.getText().contains(" ") ||
					txtY.getText().matches("[^0-9]") || txtY.getText().isBlank() || txtY.getText().contains(" ")) {
					JOptionPane.showMessageDialog(getParent(), "Digite um número float positivo");
				} else {
					float eixoX = Float.parseFloat(txtX.getText());
					float eixoY = Float.parseFloat(txtY.getText());
					if (eixoX >= 0 && eixoY >= 0) {
						Imagem imagem1 = null;

						for (int i = 0; i < frames.size(); i++) {
							if (cbb1.getSelectedItem() == frames.get(i).getTitle()) {

								try {
									imagem1 = new Imagem(new File(frames.get(i).getName()));
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}

							}

							if (imagem1 != null) {
								Imagem result = Geometricas.escala(imagem1, eixoX, eixoY);

								JDialog dialog = new JDialog();

								dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
								dialog.setBounds(0, 0, result.getW(), result.getH());
								dialog.getContentPane().add(result);
								dialog.setLocationRelativeTo(null);
								dialog.setVisible(true);
								dialog.setModal(true);
								setVisible(false);
								break;
							}
						}
					} else {
						JOptionPane.showMessageDialog(getParent(), "Digite um número float positivo");
					}
				}

			}
		});

		txtX = new JTextField();
		txtX.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(txtX, "flowx,cell 3 2,alignx left");
		txtX.setColumns(10);
		
		txtY = new JTextField();
		getContentPane().add(txtY, "cell 4 2");
		txtY.setColumns(10);

		getContentPane().add(btnOk, "cell 0 4,alignx center,aligny top");

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});

		getContentPane().add(btnCancelar, "cell 3 4,alignx center,aligny top");
		
				JLabel lblTexto = new JLabel("Eixo X");
				lblTexto.setHorizontalTextPosition(SwingConstants.RIGHT);
				lblTexto.setHorizontalAlignment(SwingConstants.RIGHT);
				getContentPane().add(lblTexto, "flowx,cell 3 1,alignx left");

	}
}
