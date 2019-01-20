package homework4;

import java.awt.Font;

/**
 *	Return new bold font
 */
public class ChatBoldFont implements ChatFont {

	/**
	 * @return New bold font according to its name and size
	 */
	@Override
	public Font setFont(String name, int size) {
		return new Font(name, Font.BOLD, size);
	}

}
