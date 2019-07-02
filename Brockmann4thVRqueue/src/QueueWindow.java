import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.Timer;
import java.time.Duration;
import java.awt.BorderLayout;
import javax.swing.JList;
import javax.swing.JSpinner;

public class QueueWindow extends JFrame implements ActionListener,  MouseMotionListener{

	private JPanel contentPane;
	private Timer timer;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QueueWindow frame = new QueueWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public QueueWindow() {
		setTitle("VR Queue");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 759, 583);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.WEST);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{30, 30,30, 30,30,30,30};
		gbl_panel.rowHeights = new int[]{30,30,30,30,30,30,30,30,30,30,30,30,30,30,30,30,30};
		gbl_panel.columnWeights = new double[]{/*Double.MIN_VALUE,*/ 0.0, 0.0, 1.0};
		gbl_panel.rowWeights = new double[]{0.0};
		panel.setLayout(gbl_panel);
		
		JLabel lblNewLabel = new JLabel("Enter Name");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 7;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 2;
		panel.add(lblNewLabel, gbc_lblNewLabel);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 7;
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 4;
		panel.add(textField, gbc_textField);
		textField.setColumns(10);
		
		JButton btnSubmit = new JButton("Submit");
		GridBagConstraints gbc_btnSubmit = new GridBagConstraints();
		gbc_btnSubmit.gridwidth = 7;
		gbc_btnSubmit.insets = new Insets(0, 0, 5, 0);
		gbc_btnSubmit.gridx = 0;
		gbc_btnSubmit.gridy = 6;
		panel.add(btnSubmit, gbc_btnSubmit);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.EAST);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{30, 30, 30, 30,30, 30,30};
		gbl_panel_1.rowHeights = new int[]{30,30,30,30,30,30,30,30,30,30,30,30,30,30,30,30,30};
		gbl_panel_1.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0};
		gbl_panel_1.rowWeights = new double[]{0.0};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lblCurrentPlayer = new JLabel("Current Player");
		GridBagConstraints gbc_lblCurrentPlayer = new GridBagConstraints();
		gbc_lblCurrentPlayer.gridwidth = 8;
		gbc_lblCurrentPlayer.insets = new Insets(0, 0, 5, 0);
		gbc_lblCurrentPlayer.gridx = 0;
		gbc_lblCurrentPlayer.gridy = 2;
		panel_1.add(lblCurrentPlayer, gbc_lblCurrentPlayer);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.gridwidth = 8;
		gbc_textField_1.insets = new Insets(0, 0, 5, 0);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 0;
		gbc_textField_1.gridy = 4;
		panel_1.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.CENTER);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{30,30,30,30,30,30,30,30,30,30,30,30,30};
		gbl_panel_2.rowHeights = new int[]{30,30,30,30,30,30,30,30,30,30,30,30,30,30,30,30,30};
		gbl_panel_2.columnWeights = new double[]{1.0, 0.0};
		gbl_panel_2.rowWeights = new double[]{0.0, 0.0};
		panel_2.setLayout(gbl_panel_2);
		
		JLabel lblCurrentFuturePlayer = new JLabel("Current Future Player Queue");
		GridBagConstraints gbc_lblCurrentFuturePlayer = new GridBagConstraints();
		gbc_lblCurrentFuturePlayer.gridwidth = 13;
		gbc_lblCurrentFuturePlayer.insets = new Insets(0, 0, 5, 0);
		gbc_lblCurrentFuturePlayer.gridx = 0;
		gbc_lblCurrentFuturePlayer.gridy = 0;
		panel_2.add(lblCurrentFuturePlayer, gbc_lblCurrentFuturePlayer);
		
		JList list = new JList();
		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.gridheight = 15;
		gbc_list.gridwidth = 12;
		gbc_list.insets = new Insets(0, 0, 5, 5);
		gbc_list.fill = GridBagConstraints.BOTH;
		gbc_list.gridx = 0;
		gbc_list.gridy = 1;
		panel_2.add(list, gbc_list);
		
		JButton btnRemoveFuturePlayer = new JButton("Remove Future Player");
		GridBagConstraints gbc_btnRemoveFuturePlayer = new GridBagConstraints();
		gbc_btnRemoveFuturePlayer.gridwidth = 6;
		gbc_btnRemoveFuturePlayer.insets = new Insets(0, 0, 0, 5);
		gbc_btnRemoveFuturePlayer.gridx = 0;
		gbc_btnRemoveFuturePlayer.gridy = 16;
		panel_2.add(btnRemoveFuturePlayer, gbc_btnRemoveFuturePlayer);
		
		JButton btnMakeThePlayer = new JButton("Make the Player!");
		GridBagConstraints gbc_btnMakeThePlayer = new GridBagConstraints();
		gbc_btnMakeThePlayer.gridwidth = 6;
		gbc_btnMakeThePlayer.gridx = 7;
		gbc_btnMakeThePlayer.gridy = 16;
		panel_2.add(btnMakeThePlayer, gbc_btnMakeThePlayer);
		
		
		
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
