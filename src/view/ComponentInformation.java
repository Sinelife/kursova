package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ComponentInformation extends JFrame {

	private JPanel contentPane;
	private JTextField TypeField;
	private JTextField UnitMeasurementField;
	private JTextField TechnicalInfoField;
	


	/**
	 * Create the frame.
	 */
	public ComponentInformation(JFrame parent) 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 646, 558);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Інформація про компонент");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblNewLabel.setBounds(103, 13, 421, 59);
		contentPane.add(lblNewLabel);
		
		JLabel TypeLabel = new JLabel("Тип компоненту");
		TypeLabel.setBounds(36, 157, 136, 22);
		contentPane.add(TypeLabel);
		
		JLabel UnitMeasurementLabel = new JLabel("Unit measurement");
		UnitMeasurementLabel.setBounds(36, 209, 136, 22);
		contentPane.add(UnitMeasurementLabel);
		
		JLabel TechnicalInfoLabel = new JLabel("Технічна інформація");
		TechnicalInfoLabel.setBounds(36, 267, 136, 22);
		contentPane.add(TechnicalInfoLabel);
		
		
		TypeField = new JTextField();
		TypeField.setEditable(false);
		TypeField.setBounds(209, 157, 350, 22);
		contentPane.add(TypeField);
		TypeField.setColumns(10);
		
		UnitMeasurementField = new JTextField();
		UnitMeasurementField.setEditable(false);
		UnitMeasurementField.setBounds(209, 209, 350, 22);
		contentPane.add(UnitMeasurementField);
		UnitMeasurementField.setColumns(10);
		
		TechnicalInfoField = new JTextField();
		TechnicalInfoField.setEditable(false);
		TechnicalInfoField.setColumns(10);
		TechnicalInfoField.setBounds(209, 267, 350, 71);
		contentPane.add(TechnicalInfoField);
		
		
		
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (parent != null)
					parent.setVisible(true);
				ComponentInformation.this.setVisible(false);
				ComponentInformation.this.dispose();
			}
		});
		btnBack.setBounds(489, 427, 97, 25);
		contentPane.add(btnBack);
	}

}
