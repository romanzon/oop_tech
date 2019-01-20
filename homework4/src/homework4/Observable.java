package homework4;

import java.util.ArrayList;

/**
 * Implements instance of an observable thing.
 */
public class Observable {

	/**
	 * A list of observers to notify
	 */
	private ArrayList<Observer> observers;
	
	/**
	 * Indicate if the observable has been changed
	 */
	private boolean hasChanged;
	
	/**
	 * @effects Construct a new observable thing 
	 */
	public Observable()
	{
		observers = new ArrayList<Observer>();
		hasChanged = false;
	}
	
	/**
	 * @effects Add a new observer o to observers' list
	 * @requires o != null
	 */
	public void addObserver(Observer o)
	{
		if (observers.contains(o))
			return;
		
		// Add new observer
		observers.add(o);
	}
	
	/**
	 * @effects Delete an observer o from observers' list
	 * @requires o != null
	 */
	public void deleteObserver(Observer o)
	{
		if (!observers.contains(o))
			return;
		
		// Delete the observer
		observers.remove(o);	
	}
	
	/**
	 * @effects Delete all observers
	 */
	public void deleteObservers()
	{
		// Delete all observers
		observers.clear();
	}
	
	/**
	 * @effects Notify all observer that this.hasChanged == true
	 */
	public void notifyObservers()
	{
		this.notifyObservers(null);
	}
	
	/**
	 * @effects Notify all observer that this.hasChanged == true and pass argument arg
	 */
	public void notifyObservers(Object arg)
	{
		for (Observer observer : observers) {
			observer.update(this, arg);
		}
		clearChanged();
	}
	
	/**
	 * @return Observers' list count
	 */
	public int countObservers()
	{
		return observers.size();
	}
	
	/**
	 * @effects Set "observable has been changed"
	 */
	public void setChanged()
	{
		this.hasChanged = true;
	}

	/**
	 * @effects Clear "observable has been changed"
	 */
	public void clearChanged()
	{
		this.hasChanged = false;
	}
	
	/**
	 * @return If observable has been changed
	 */
	public boolean hasChanged()
	{
		return this.hasChanged;
	}
}
