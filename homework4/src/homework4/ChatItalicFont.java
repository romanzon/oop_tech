package homework4;

import java.awt.Font;

/**
 *	Return new italic font
 */
public class ChatItalicFont implements ChatFont {

	/**
	 * @return New italic font according to its name and size
	 */
	@Override
	public Font setFont(String name, int size) {
		return new Font(name, Font.ITALIC, size);
	}

}
