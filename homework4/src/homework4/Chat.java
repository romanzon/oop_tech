package homework4;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.AbstractListModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.Font;
import javax.swing.JTextPane;

public class Chat {

	private final int NUM_OF_USERS = 4;
	private JFrame frame;
	
	/** A list of all users panels **/
	private ArrayList<JPanel> usersPanels = new ArrayList<JPanel>();
	
	/** A list of all users enter text **/
	private ArrayList<Observable> usersEnterText = new ArrayList<Observable>();
	
	/** A list of all chat updaters **/
	private ArrayList<Observer> chatUpdaters = new ArrayList<Observer>();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Chat window = new Chat();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Chat() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
		});
		frame.setBounds(100, 100, 1000, 469);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
								
		// Build user panel
		for (int i = 0; i< NUM_OF_USERS; i++)
		{
			buildUserPanel("Student " + Integer.toString(i),10 + i*210, 5);
		}

		// Add observables to observers
		for (Observable observable : usersEnterText) {
			for (Observer observer : chatUpdaters) {
				observable.addObserver(observer);
			}
		}

	}
	
	@SuppressWarnings("serial")
	private void buildUserPanel(String name, int x, int y)
	{
		JPanel panel = new JPanel();
		panel.setBounds(x, y, 200, 400);
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel(name);
		lblNewLabel.setBounds(10, 5, 180, 14);
		panel.add(lblNewLabel);
		
		JTextField textField = new JTextField();
		textField.setBounds(10, 65, 180, 20);
		panel.add(textField);
		
		Observable userEnterText = new Observable();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == 10) // Enter key
				{
					// Update that new user text is entered
					userEnterText.setChanged();
					userEnterText.notifyObservers(name + ": " + textField.getText());
					textField.setText("");
				}
			}
		});
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 96, 180, 203);
		panel.add(textArea);
						
		JList<String> list = new JList<String>();	
		list.setModel(new AbstractListModel<String>() {
			String[] values = new String[] {"Regular", "Bold", "Italic"};
			public int getSize() {
				return values.length;
			}
			public String getElementAt(int index) {
				return values[index];
			}
		});
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				// Get the font style list index and set the selected font style
				int index = list.getSelectedIndex();
				ChatFont chatFont;
				switch (index)
				{
					default:
					case 0:
						chatFont = new ChatRegularFont();
						break;
					case 1:
						chatFont = new ChatBoldFont();
						break;
					case 2:
						chatFont = new ChatItalicFont();
						break;						
				}
				Font currentFont = textArea.getFont();
				textArea.setFont(chatFont.setFont(currentFont.getName(), currentFont.getSize()));
			}
		});
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setBounds(10, 310, 180, 52);
		panel.add(list);
		
		usersPanels.add(panel);
		usersEnterText.add(userEnterText);
		chatUpdaters.add(new ChatUpdater(textArea));
	}
}
