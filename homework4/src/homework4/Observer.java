package homework4;

/**
 * Interface of observer. 
 * Responsible for update something when notified by observable
 */
public interface Observer {
	
	/**
	 * @effects Update something when notified by observable. The observable may pass arg
	 * @requires o != null
	 */
	public void update(Observable o, Object arg);
}
