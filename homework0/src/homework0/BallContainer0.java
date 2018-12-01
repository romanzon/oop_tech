package homework0;

import java.util.ArrayList;

/**
 * A container that can be used to contain Balls. A given Ball may only
 * appear in a BallContainer once.
 */
public class BallContainer0 {
	
	private ArrayList <Ball> ball_list;
	private double total_volume;
	
    /**
     * @effects Creates a new BallContainer.
     */
    public BallContainer0() {
    	this.ball_list = new ArrayList<>();
    	this.total_volume = 0;
    }


    /**
     * @modifies this
     * @effects Adds ball to the container.
     * @return true if ball was successfully added to the container,
     * 		   i.e. ball is not already in the container; false otherwise.
     */
    public boolean add(Ball ball) {
    	if (ball == null)
    		return false;
    	
    	if (contains(ball))
    		return false;
    	
		this.ball_list.add(ball);
		this.total_volume += ball.getVolume();
		return true;
    }


    /**
     * @modifies this
     * @effects Removes ball from the container.
     * @return true if ball was successfully removed from the container,
     * 		   i.e. ball is actually in the container; false otherwise.
     */
    public boolean remove(Ball ball) {
    	if (ball == null)
    		return false;
    	
		if (!contains(ball))
			return false;
		
		this.ball_list.remove(ball);
		this.total_volume -= ball.getVolume();
		return true;
    }


    /**
     * @return the volume of the contents of the container, i.e. the
     * 		   total volume of all Balls in the container.
     */
    public double getVolume() {
    	return total_volume;
    }


    /**
     * @return the number of Balls in the container.
     */
    public int size() {
		return this.ball_list.size();
    }


    /**
     * @modifies this
     * @effects Empties the container, i.e. removes all its contents.
     */
    public void clear() {
		this.ball_list.clear();
		this.total_volume = 0;
    }


    /**
     * @return true if this container contains ball; false, otherwise.
     */
    public boolean contains(Ball ball) {
    	if (ball == null)
    		return false;
    	
    	return this.ball_list.contains(ball);
    }

	/**
	 * @effects Runs the main program of BallContainer
	 **/
    public static void main(String args[])  
    {  
    	boolean status = false;
    	Ball ball0 = new Ball(1.2);
    	Ball ball1 = new Ball(2.3);
    	Ball ball2 = new Ball(3.5);
    	
    	BallContainer0 ballContainer = new BallContainer0();

    	System.out.printf("Get total volume\n");
    	System.out.println(ballContainer.getVolume());
    	
    	System.out.printf("Add ball0(%f)\n",ball0.getVolume());
    	status = ballContainer.add(ball0);
    	System.out.println(status);
    	
    	System.out.printf("Add ball1(%f)\n",ball1.getVolume());
    	status = ballContainer.add(ball1);
    	System.out.println(status);    	
    	
    	System.out.printf("Add ball2(%f)\n",ball2.getVolume());
    	status = ballContainer.add(ball2);
    	System.out.println(status);
    	
    	System.out.printf("Get total volume\n");
    	System.out.println(ballContainer.getVolume());
    	
    	System.out.printf("Add ball1\n");
    	status = ballContainer.add(ball1);
    	System.out.println(status);
    	
    	System.out.printf("Remove ball1\n");
    	status = ballContainer.remove(ball1);
    	System.out.println(status);
    	
    	System.out.printf("Remove ball1\n");
    	status = ballContainer.remove(ball1);
    	System.out.println(status);

    	System.out.printf("Does container contain ball0?\n");
    	status = ballContainer.contains(ball0);
    	System.out.println(status);
    	
    	System.out.printf("Does container contain ball1?\n");
    	status = ballContainer.contains(ball1);
    	System.out.println(status);
    	
    	System.out.printf("Does container contain ball2?\n");
    	status = ballContainer.contains(ball2);
    	System.out.println(status);
    	
    	System.out.printf("Get total volume\n");
    	System.out.println(ballContainer.getVolume());

    	System.out.printf("Clear container\n");
    	ballContainer.clear();
    	
    	System.out.printf("Get total volume\n");
    	System.out.println(ballContainer.getVolume());
    }
}
