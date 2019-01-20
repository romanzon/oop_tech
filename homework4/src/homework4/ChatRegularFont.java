package homework4;

import java.awt.Font;

/**
 *	Return new regular font
 */
public class ChatRegularFont implements ChatFont{
	
	/**
	 * @return New regular font according to its name and size
	 */
	@Override
	public Font setFont(String name, int size) {
		return new Font(name, Font.PLAIN, size);
	}
	
}
