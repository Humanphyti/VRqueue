import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.Timer;
import java.util.ArrayList;
import java.awt.BorderLayout;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.UIManager;
import java.awt.Font;
import java.util.Scanner;
import javax.sound.sampled.*;
import sun.audio.*;

public class QueueWindow extends JFrame implements ActionListener,  MouseMotionListener{

	
	private JPanel contentPane;
	Toolkit toolkit = Toolkit.getDefaultToolkit();
	JLabel lblWarningsLabel;
	private Timer timer;
	private int count = 10;
	private JTextField textField;
	public ArrayList<FuturePlayers> futurePlayer = new ArrayList<FuturePlayers>();
	private DefaultListModel<FuturePlayers> players = new DefaultListModel<FuturePlayers>();
	private FuturePlayers selected;
	private String currentPlayer;
	private String currentGame;
	private int startClick = 0;
	private int resetClick = 0;
	private int textFieldClick = 0;
	private int vari = 1;
	private String status;
	AudioInputStream audioInputStream;
	InputStream in;
	private Clip clip;
	
	
	
	private String[] games = {"Select Game Please", "BeatSaber", "Superhot", "The Lab", "Fruit Ninja", "HoloPoint", "Raw Data", "Sairento", "Unseen Diplomacy", "Keep Talking & Nobody Explodes"};
	
	

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
		setBounds(100, 100, 799, 583);
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
		
		textField = new JTextField("Enter Name Here Please");
		MouseListener mouseListener = new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (textFieldClick == 0) {
					textField.setText("");
					textFieldClick++;
				}
			}
		};
		textField.addMouseListener(mouseListener);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 7;
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 4;
		panel.add(textField, gbc_textField);
		textField.setColumns(10);
		
		JComboBox<String> comboBox = new JComboBox<String>(games);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String game = comboBox.getSelectedItem().toString();
				//if (game.contains("Raw Data") || game.contains("Sairento") || game.contains("Keep Talking & Nobody Explodes")) {
				if (vari >= 0) {
					if (game.contains("Raw Data"))
						editWarningsLabel("<html>WARNING!<br/>May cause MINOR NAUSEA<html>");
					else if (game.contains("Sairento"))
						editWarningsLabel("<html>WARNING!<br/>May cause MODERATE/SEVERE NAUSEA<html>");
					else if (game.contains("Keep Talking & Nobody Explodes"))
						editWarningsLabel("<html>WARNING!<br/>Requires 2 more people. NPC grab Manual<html>");
				}
			}
		});
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.gridwidth = 7;
		gbc_comboBox.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 0;
		gbc_comboBox.gridy = 8;
		panel.add(comboBox, gbc_comboBox);
		
		JLabel lblChoosePreferredVr = new JLabel("Choose Preferred VR Game");
		GridBagConstraints gbc_lblChoosePreferredVr = new GridBagConstraints();
		gbc_lblChoosePreferredVr.gridwidth = 7;
		gbc_lblChoosePreferredVr.insets = new Insets(0, 0, 5, 0);
		gbc_lblChoosePreferredVr.gridx = 0;
		gbc_lblChoosePreferredVr.gridy = 6;
		panel.add(lblChoosePreferredVr, gbc_lblChoosePreferredVr);
		
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textFieldClick == 1) {
					String tempName = textField.getText();
					String tempGame = comboBox.getSelectedItem().toString();
					FuturePlayers fp = new FuturePlayers(tempName, tempGame);
					futurePlayer.add(fp);
					players.addElement(fp);
					textField.setText("Enter Name Here Please");
					textFieldClick = 0;
				}
			}
		});
		GridBagConstraints gbc_btnSubmit = new GridBagConstraints();
		gbc_btnSubmit.gridwidth = 7;
		gbc_btnSubmit.insets = new Insets(0, 0, 5, 0);
		gbc_btnSubmit.gridx = 0;
		gbc_btnSubmit.gridy = 10;
		panel.add(btnSubmit, gbc_btnSubmit);
		
		lblWarningsLabel = new JLabel("Warnings Label");
		lblWarningsLabel.setFont(new Font("SansSerif", Font.PLAIN, 11));
		lblWarningsLabel.setEnabled(false);
		GridBagConstraints gbc_lblWarningsLabel = new GridBagConstraints();
		gbc_lblWarningsLabel.gridheight = 2;
		gbc_lblWarningsLabel.gridwidth = 7;
		gbc_lblWarningsLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblWarningsLabel.gridx = 0;
		gbc_lblWarningsLabel.gridy = 12;
		panel.add(lblWarningsLabel, gbc_lblWarningsLabel);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.EAST);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{30, 30, 30, 30,30, 30,20};
		gbl_panel_1.rowHeights = new int[]{30,30,30,30,30,30,30,30,30,30,30,30,30,30,30,30,30};
		gbl_panel_1.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		gbl_panel_1.rowWeights = new double[]{0.0};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lblTimerLabel = new JLabel("Timer Label");
		lblTimerLabel.setFont(new Font("Bookman Old Style", Font.PLAIN, 34));
		GridBagConstraints gbc_lblTimerLabel = new GridBagConstraints();
		gbc_lblTimerLabel.gridheight = 2;
		gbc_lblTimerLabel.gridwidth = 7;
		gbc_lblTimerLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblTimerLabel.gridx = 0;
		gbc_lblTimerLabel.gridy = 10;
		panel_1.add(lblTimerLabel, gbc_lblTimerLabel);
		
		JLabel lblCurrentPlayer = new JLabel("Current Player");
		GridBagConstraints gbc_lblCurrentPlayer = new GridBagConstraints();
		gbc_lblCurrentPlayer.gridwidth = 7;
		gbc_lblCurrentPlayer.insets = new Insets(0, 0, 5, 0);
		gbc_lblCurrentPlayer.gridx = 0;
		gbc_lblCurrentPlayer.gridy = 0;
		panel_1.add(lblCurrentPlayer, gbc_lblCurrentPlayer);
		
		JLabel lblCurrentPlayer_1 = new JLabel("Displays the Current Player");
		lblCurrentPlayer_1.setFont(new Font("Baskerville Old Face", Font.PLAIN, 13));
		GridBagConstraints gbc_lblCurrentPlayer_1 = new GridBagConstraints();
		gbc_lblCurrentPlayer_1.gridwidth = 7;
		gbc_lblCurrentPlayer_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblCurrentPlayer_1.gridx = 0;
		gbc_lblCurrentPlayer_1.gridy = 2;
		panel_1.add(lblCurrentPlayer_1, gbc_lblCurrentPlayer_1);
		String defaultLabel = lblCurrentPlayer_1.getText();
		
		JButton btnStartTimer = new JButton("Start Timer");
		btnStartTimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!lblCurrentPlayer_1.getText().contains(defaultLabel)) {
					if (startClick == 0) {
						timer.start();
						startClick++;
					} else
						startClick = 0;
				}
			}
		});
		GridBagConstraints gbc_btnStartTimer = new GridBagConstraints();
		gbc_btnStartTimer.gridwidth = 3;
		gbc_btnStartTimer.insets = new Insets(0, 0, 5, 5);
		gbc_btnStartTimer.gridx = 0;
		gbc_btnStartTimer.gridy = 12;
		panel_1.add(btnStartTimer, gbc_btnStartTimer);
		
		JLabel lblCurrentGamepreferredGame = new JLabel("Current Game/Preferred Game");
		GridBagConstraints gbc_lblCurrentGamepreferredGame = new GridBagConstraints();
		gbc_lblCurrentGamepreferredGame.gridwidth = 7;
		gbc_lblCurrentGamepreferredGame.insets = new Insets(0, 0, 5, 0);
		gbc_lblCurrentGamepreferredGame.gridx = 0;
		gbc_lblCurrentGamepreferredGame.gridy = 4;
		panel_1.add(lblCurrentGamepreferredGame, gbc_lblCurrentGamepreferredGame);
		
		JLabel lblDisplaysCurrentGamepreferred = new JLabel("Displays Current Game/Preferred Game");
		lblDisplaysCurrentGamepreferred.setFont(new Font("Baskerville Old Face", Font.PLAIN, 12));
		GridBagConstraints gbc_lblDisplaysCurrentGamepreferred = new GridBagConstraints();
		gbc_lblDisplaysCurrentGamepreferred.gridwidth = 7;
		gbc_lblDisplaysCurrentGamepreferred.insets = new Insets(0, 0, 5, 5);
		gbc_lblDisplaysCurrentGamepreferred.gridx = 0;
		gbc_lblDisplaysCurrentGamepreferred.gridy = 6;
		panel_1.add(lblDisplaysCurrentGamepreferred, gbc_lblDisplaysCurrentGamepreferred);
		
		JLabel lblLabel = new JLabel("Play Time Remaining");
		GridBagConstraints gbc_lblLabel = new GridBagConstraints();
		gbc_lblLabel.gridwidth = 7;
		gbc_lblLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblLabel.gridx = 0;
		gbc_lblLabel.gridy = 8;
		panel_1.add(lblLabel, gbc_lblLabel);
		
		
		JButton btnResetTimer = new JButton("Reset Timer");
		btnResetTimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!lblCurrentPlayer_1.getText().contains("Displays the Current Player")) {
					if (resetClick == 0) {
						timer.stop();
						count = 10;
						clip.stop();
						resetClick++;
					} else if (resetClick == 1) {
						timer.restart();
						resetClick++;
					}
				}
			}
		});
		GridBagConstraints gbc_btnResetTimer = new GridBagConstraints();
		gbc_btnResetTimer.gridwidth = 3;
		gbc_btnResetTimer.insets = new Insets(0, 0, 5, 0);
		gbc_btnResetTimer.gridx = 4;
		gbc_btnResetTimer.gridy = 12;
		panel_1.add(btnResetTimer, gbc_btnResetTimer);
		
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.CENTER);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{25,25,25,25,25,25,25,10};
		gbl_panel_2.rowHeights = new int[]{30,30,30,30,30,30,30,30,30,30,30,30,30,30,30,30,30};
		gbl_panel_2.columnWeights = new double[]{0.0};
		gbl_panel_2.rowWeights = new double[]{0.0, 0.0};
		panel_2.setLayout(gbl_panel_2);
		
		JLabel lblCurrentFuturePlayer = new JLabel("Current Future Player Queue");
		GridBagConstraints gbc_lblCurrentFuturePlayer = new GridBagConstraints();
		gbc_lblCurrentFuturePlayer.gridwidth = 9;
		gbc_lblCurrentFuturePlayer.insets = new Insets(0, 0, 5, 0);
		gbc_lblCurrentFuturePlayer.gridx = 1;
		gbc_lblCurrentFuturePlayer.gridy = 0;
		panel_2.add(lblCurrentFuturePlayer, gbc_lblCurrentFuturePlayer);
		
		JList<FuturePlayers> list = new JList<FuturePlayers>(players);
		JScrollPane scrollpane = new JScrollPane(list);
		MouseListener mouseListener_1 = new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() >= 1) {
					selected = list.getSelectedValue();
				}
			}
		};
		list.addMouseListener(mouseListener_1);
		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.gridheight = 15;
		gbc_list.gridwidth = 10;
		gbc_list.insets = new Insets(0, 0, 5, 0);
		gbc_list.fill = GridBagConstraints.BOTH;
		gbc_list.gridx = 0;
		gbc_list.gridy = 1;
		panel_2.add(scrollpane, gbc_list);
		
		JButton btnMakeThePlayer = new JButton("Make the Player!");
		btnMakeThePlayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentPlayer = selected.getName();
				currentGame = selected.getGame();
				lblCurrentPlayer_1.setText(currentPlayer);
				lblDisplaysCurrentGamepreferred.setText(currentGame);
				futurePlayer.remove(selected);
				players.removeElement(selected);
				startClick = 0;
				resetClick = 0;
				timer.stop();
				if (clip.isActive()) {
					clip.stop();
				}
			}
		});
		GridBagConstraints gbc_btnMakeThePlayer = new GridBagConstraints();
		gbc_btnMakeThePlayer.gridwidth = 4;
		gbc_btnMakeThePlayer.gridx = 6;
		gbc_btnMakeThePlayer.gridy = 16;
		panel_2.add(btnMakeThePlayer, gbc_btnMakeThePlayer);
		
		JButton btnRemoveFuturePlayer = new JButton("Remove Future Player");
		btnRemoveFuturePlayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				futurePlayer.remove(selected);
				players.removeElement(selected);
			}
		});
		GridBagConstraints gbc_btnRemoveFuturePlayer = new GridBagConstraints();
		gbc_btnRemoveFuturePlayer.gridwidth = 5;
		gbc_btnRemoveFuturePlayer.insets = new Insets(0, 0, 0, 5);
		gbc_btnRemoveFuturePlayer.gridx = 0;
		gbc_btnRemoveFuturePlayer.gridy = 16;
		panel_2.add(btnRemoveFuturePlayer, gbc_btnRemoveFuturePlayer);
		
		timer = new Timer(1000, new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				if (count <= 0) {
					lblTimerLabel.setText("Done");
					timer.stop();
					count = 10;
					try {
						System.out.print("Here\n");
						audioFilePath();
						
						//clip.start();
					} catch (UnsupportedAudioFileException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (LineUnavailableException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (URISyntaxException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				} else {
					lblTimerLabel.setText(format(count));
					count--;
				}
				//LocalDateTime now = LocalDateTime.now();
				//Duration runningTime = Duration.between(startTime, now);
				//Duration timeLeft = duration.minus(runningTime);
				//if (timeLeft.isZero() || timeLeft.isNegative()) {
					//timeLeft = Duration.ZERO;
					
				//}
				//lblTimerLabel.setText(format(timeLeft));
			}
		});
		
	}
	
	protected String format(int duration) {
		long mins = duration / 60;
		long secs = duration % 60;
		//long seconds = duration ;
		return String.format("%02dm:%02ds", mins, secs);
	}
	
	public void audioFilePath() throws UnsupportedAudioFileException, IOException, LineUnavailableException, URISyntaxException{
		
		//com.sun.javafx.application.PlatformImpl.startup(()->{});
		//String timerEnd = "G:\\Music\\[Rocksfull] ONE OK ROCK - Ambitions (Japanese Ver)\\09. I was King.mp3";
		///URL audioURL = getClass().getResource("src\\resources\\09. I was King.mp3");
		//Media hit = new Media(new File(audioURL).toURI().toString());
		//String sound = "src\\resources\\09. I was King.mp3";
		//Media hit = new Media(new File(sound).toURI().toString());
		//MediaPlayer mediaPlayer = new MediaPlayer(hit);
		/*
		in = this.getClass().getResourceAsStream(s);
		audioStream = new AudioStream(in);
		audioStream.
		AudioPlayer.player.start(audioStream);*/
		
		
		/*Method 1 of audio output*/
		//URL doneSound = getClass().getResource("/resource/19 ...Steel for Humans.wav");
		//System.out.print(doneSound);
		//File soundFile = new File(getClass().getResourceAsStream(doneSound));
		//FileInputStream in = new FileInputStream(soundFile);
		AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource("/resource/19 ...Steel for Humans.wav"));
		clip = AudioSystem.getClip();
		clip.open(audioInputStream);
		clip.loop(Clip.LOOP_CONTINUOUSLY);
		clip.start();
	}
	
	public void editWarningsLabel(String s) {
		lblWarningsLabel.setText(s);
		lblWarningsLabel.setEnabled(true);
		
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
