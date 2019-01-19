package homework4;

import javax.swing.JTextArea;

public class ChatUpdater implements Observer{
	
	private JTextArea textArea;
	
	public ChatUpdater(JTextArea textArea)
	{
		this.textArea = textArea;
	}

	@Override
	public void update(Observable o, Object arg) {
		this.textArea.append((String)arg + "\n");
	}
}
