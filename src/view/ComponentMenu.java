package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.SwingConstants;

public class ComponentMenu extends JFrame {

	private JPanel contentPane;



	/**
	 * Create the frame.
	 */
	public ComponentMenu(JFrame parent)
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 740, 527);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel MenuTitleLabel = new JLabel("Меню роботи з компонентами");
		MenuTitleLabel.setFont(new Font("Tahoma", Font.BOLD, 21));
		MenuTitleLabel.setBounds(157, 13, 417, 38);
		contentPane.add(MenuTitleLabel);
		
		JButton InfoButton = new JButton("1)Переглянути інформацію про наявні компоненти.");
		InfoButton.setHorizontalAlignment(SwingConstants.LEFT);
		InfoButton.setForeground(Color.RED);
		InfoButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		InfoButton.setBounds(42, 111, 456, 43);
		contentPane.add(InfoButton);
		
		JButton AddButton = new JButton("2)Додати інфориацію про новий компонент.");
		AddButton.setHorizontalAlignment(SwingConstants.LEFT);
		AddButton.setForeground(Color.RED);
		AddButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		AddButton.setBounds(42, 191, 456, 43);
		contentPane.add(AddButton);
		
		JButton EditButton = new JButton("3)Редагувати інформацію про компонент.");
		EditButton.setHorizontalAlignment(SwingConstants.LEFT);
		EditButton.setForeground(Color.RED);
		EditButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		EditButton.setBounds(42, 271, 456, 43);
		contentPane.add(EditButton);
		
		JButton InDeviceButton = new JButton("4)Переглянути список приладів,які містять компонент");
		InDeviceButton.setHorizontalAlignment(SwingConstants.LEFT);
		InDeviceButton.setForeground(Color.RED);
		InDeviceButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		InDeviceButton.setBounds(42, 350, 456, 43);
		contentPane.add(InDeviceButton);
		
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (parent != null)
					parent.setVisible(true);
				ComponentMenu.this.setVisible(false);
				ComponentMenu.this.dispose();
			}
		});
		btnBack.setBounds(596, 427, 97, 25);
		contentPane.add(btnBack);
	}

}
