package homework4;

import java.util.ArrayList;

public class Observable {

	private ArrayList<Observer> observers;
	private boolean isChanged;
	
	public Observable()
	{
		observers = new ArrayList<Observer>();
		isChanged = false;
	}
	
	public void addObserver(Observer o)
	{
		if (observers.contains(o))
			return;
		
		// Add new observer
		observers.add(o);
	}
	
	public void deleteObserver(Observer o)
	{
		if (!observers.contains(o))
			return;
		
		// Delete the observer
		observers.remove(o);	
	}
	
	public void deleteObservers()
	{
		// Delete all observers
		observers.clear();
	}
	
	public void notifyObservers()
	{
		for (Observer observer : observers) {
			observer.update(this, null);
		}
		clearChanged();
	}
	
	public void notifyObservers(Object arg)
	{
		for (Observer observer : observers) {
			observer.update(this, arg);
		}
		clearChanged();
	}
	
	public int countObservers()
	{
		return observers.size();
	}
	
	public void setChanged()
	{
		this.isChanged = true;
	}
	
	public void clearChanged()
	{
		this.isChanged = false;
	}
	
	public boolean hasChanged()
	{
		return this.isChanged;
	}
}
