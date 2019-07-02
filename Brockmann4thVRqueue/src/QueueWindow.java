import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.Timer;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.awt.BorderLayout;
import javax.swing.JList;
import javax.swing.JSpinner;
import javax.swing.JComboBox;

public class QueueWindow extends JFrame implements ActionListener,  MouseMotionListener{

	private JPanel contentPane;
	private Timer timer;
	private LocalDateTime startTime;
	private Duration duration = Duration.ofMinutes(7);
	private JTextField textField;
	public ArrayList<FuturePlayers> futurePlayer = new ArrayList<FuturePlayers>();
	private DefaultListModel<FuturePlayers> players = new DefaultListModel<FuturePlayers>();
	private FuturePlayers selected;
	private String currentPlayer;

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
		gbl_panel.columnWeights = new double[]{/*Double.MIN_VALUE,*/ 1.0, 0.0, 1.0};
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
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		JLabel lblChoosePreferredVr = new JLabel("Choose Preferred VR Game");
		GridBagConstraints gbc_lblChoosePreferredVr = new GridBagConstraints();
		gbc_lblChoosePreferredVr.gridwidth = 7;
		gbc_lblChoosePreferredVr.insets = new Insets(0, 0, 5, 5);
		gbc_lblChoosePreferredVr.gridx = 0;
		gbc_lblChoosePreferredVr.gridy = 6;
		panel.add(lblChoosePreferredVr, gbc_lblChoosePreferredVr);
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.gridwidth = 7;
		gbc_comboBox.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 0;
		gbc_comboBox.gridy = 8;
		panel.add(comboBox, gbc_comboBox);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String temp = textField.getText();
				FuturePlayers fp = new FuturePlayers(temp);
				futurePlayer.add(fp);
				players.addElement(fp);
				
			}
		});
		GridBagConstraints gbc_btnSubmit = new GridBagConstraints();
		gbc_btnSubmit.gridwidth = 7;
		gbc_btnSubmit.insets = new Insets(0, 0, 5, 0);
		gbc_btnSubmit.gridx = 0;
		gbc_btnSubmit.gridy = 10;
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
		gbc_lblCurrentPlayer.gridwidth = 7;
		gbc_lblCurrentPlayer.insets = new Insets(0, 0, 5, 0);
		gbc_lblCurrentPlayer.gridx = 0;
		gbc_lblCurrentPlayer.gridy = 2;
		panel_1.add(lblCurrentPlayer, gbc_lblCurrentPlayer);
		
		JLabel lblCurrentPlayer_1 = new JLabel("Displays the Current Player");
		GridBagConstraints gbc_lblCurrentPlayer_1 = new GridBagConstraints();
		gbc_lblCurrentPlayer_1.gridwidth = 7;
		gbc_lblCurrentPlayer_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblCurrentPlayer_1.gridx = 0;
		gbc_lblCurrentPlayer_1.gridy = 4;
		panel_1.add(lblCurrentPlayer_1, gbc_lblCurrentPlayer_1);
		
		JLabel lblLabel = new JLabel("Play Time");
		GridBagConstraints gbc_lblLabel = new GridBagConstraints();
		gbc_lblLabel.gridwidth = 7;
		gbc_lblLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblLabel.gridx = 0;
		gbc_lblLabel.gridy = 6;
		panel_1.add(lblLabel, gbc_lblLabel);
		
		JLabel lblTimerLabel = new JLabel("Timer Label");
		timer = new Timer(500, new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				LocalDateTime now = LocalDateTime.now();
				Duration runningTime = Duration.between(startTime, now);
				Duration timeLeft = duration.minus(runningTime);
				if (timeLeft.isZero() || timeLeft.isNegative()) {
					timeLeft = Duration.ZERO;
					
				}
				lblTimerLabel.setText(format(timeLeft));
			}
		});
		GridBagConstraints gbc_lblTimerLabel = new GridBagConstraints();
		gbc_lblTimerLabel.gridwidth = 7;
		gbc_lblTimerLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblTimerLabel.gridx = 0;
		gbc_lblTimerLabel.gridy = 8;
		panel_1.add(lblTimerLabel, gbc_lblTimerLabel);
		
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.CENTER);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{20,20,20,20,20,20,20,20,20,20,20,20,20};
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
		
		JList<FuturePlayers> list = new JList<FuturePlayers>(players);
		JScrollPane scrollpane = new JScrollPane(list);
		MouseListener mouseListener = new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() >= 1) {
					selected = list.getSelectedValue();
				}
			}
		};
		list.addMouseListener(mouseListener);
		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.gridheight = 15;
		gbc_list.gridwidth = 12;
		gbc_list.insets = new Insets(0, 0, 5, 5);
		gbc_list.fill = GridBagConstraints.BOTH;
		gbc_list.gridx = 0;
		gbc_list.gridy = 1;
		panel_2.add(scrollpane, gbc_list);
		
		JButton btnRemoveFuturePlayer = new JButton("Remove Future Player");
		btnRemoveFuturePlayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				futurePlayer.remove(selected);
				players.removeElement(selected);
			}
		});
		GridBagConstraints gbc_btnRemoveFuturePlayer = new GridBagConstraints();
		gbc_btnRemoveFuturePlayer.gridwidth = 4;
		gbc_btnRemoveFuturePlayer.insets = new Insets(0, 0, 0, 5);
		gbc_btnRemoveFuturePlayer.gridx = 3;
		gbc_btnRemoveFuturePlayer.gridy = 16;
		panel_2.add(btnRemoveFuturePlayer, gbc_btnRemoveFuturePlayer);
		
		JButton btnMakeThePlayer = new JButton("Make the Player!");
		btnMakeThePlayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentPlayer = selected.getName();
				lblCurrentPlayer_1.setText(currentPlayer);
				futurePlayer.remove(selected);
				players.removeElement(selected);
			}
		});
		GridBagConstraints gbc_btnMakeThePlayer = new GridBagConstraints();
		gbc_btnMakeThePlayer.gridwidth = 3;
		gbc_btnMakeThePlayer.gridx = 8;
		gbc_btnMakeThePlayer.gridy = 16;
		panel_2.add(btnMakeThePlayer, gbc_btnMakeThePlayer);
		
	}
	
	protected String format(Duration duration) {
		long mins = duration.toMinutes();
		long seconds = duration.minusMinutes(mins).toMillis()/1000;
		return String.format("%02dm %02ds", mins, seconds);
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
