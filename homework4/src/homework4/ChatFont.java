package homework4;

import java.awt.Font;

/**
 *	Return new font
 */
public interface ChatFont {
	
	/**
	 * @return New font according to its name and size
	 * @requires name != null && size != 0
	 */
	public Font setFont(String name, int size);
}
