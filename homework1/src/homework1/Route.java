package homework1;

import java.util.Iterator;

/**
 * A Route is a path that traverses arbitrary GeoSegments, regardless
 * of their names.
 * <p>
 * Routes are immutable. New Routes can be constructed by adding a segment 
 * to the end of a Route. An added segment must be properly oriented; that 
 * is, its p1 field must correspond to the end of the original Route, and
 * its p2 field corresponds to the end of the new Route.
 * <p>
 * Because a Route is not necessarily straight, its length - the distance
 * traveled by following the path from start to end - is not necessarily
 * the same as the distance along a straight line between its endpoints.
 * <p>
 * Lastly, a Route may be viewed as a sequence of geographical features,
 * using the <tt>getGeoFeatures()</tt> method which returns an Iterator of
 * GeoFeature objects.
 * <p>
 * <b>The following fields are used in the specification:</b>
 * <pre>
 *   start : GeoPoint            // location of the start of the route
 *   end : GeoPoint              // location of the end of the route
 *   startHeading : angle        // direction of travel at the start of the route, in degrees
 *   endHeading : angle          // direction of travel at the end of the route, in degrees
 *   geoFeatures : sequence      // a sequence of geographic features that make up this Route
 *   geoSegments : sequence      // a sequence of segments that make up this Route
 *   length : real               // total length of the route, in kilometers
 *   endingGeoSegment : GeoSegment  // last GeoSegment of the route
 * </pre>
 **/
public class Route {

	
 	// TODO Write abstraction function and representation invariant
	private final GeoPoint start;  
	private final GeoPoint end;
	private final double start_heading; //angle direction 
	private final double end_heading; 	//angle direction 
	private final ArrayList<GeoFeature> geo_features; 
	private final ArrayList<GeoSegment> geo_segments; 
	private final double length;
	private final GeoSegment last_geo_segmnet;  
	
  	/**
  	 * Constructs a new Route.
     * @requires gs != null
     * @effects Constructs a new Route, r, such that
     *	        r.startHeading = gs.heading &&
     *          r.endHeading = gs.heading &&
     *          r.start = gs.p1 &&
     *          r.end = gs.p2
     **/
  	public Route(GeoSegment gs) { 
  		this.start = gs.getP1(); 
  		this.end = gs.getP2(); 
  		this.start_heading = gs.getHeading(); 
  		this.end_heading = gs.getHeading(); 
  		this.geo_features = new ArrayList<GeoFeature>();
  		this.geo_features.add(gs); 
  		this.geo_segments = new ArrayList<GeoSegment>();
  		this.geo_segments.add(gs);
  		this.length = gs.getLength();
  		this.last_geo_segment = gs; 
  	}

  	/**
  	 * Construct a new Route by GeoSegments  
  	 * @requires geoSegments != null 
  	 */
  	private Route(ArrayList<GeoSegment> geo_segments , ArrayList<Feature> geo_features)
  	{
  		this.start = geo_segments.get(0).getP1();
  		this.end = geo_segments.get(geo_segments.size()-1).getP2();
  		this.start_heading = geo_segments.get(0).getHeading(); 
  		this.end_heading = geo_segments.get(geo_segments.size()-1).getHeading(); 
  		this.last_geo_segment = geo_segments.get(geo_segments.size()-1); 
		double length_tmp = 0;
  		for (GeoSegment geoSegment : geo_segments) 
  		{
  			length_tmp +=geoSegment.getLength();
		}
  		this.length = length_tmp;
  		this.geo_segments = geo_segments;
		this.geo_features = geo_features; 
  	}

    /**
     * Returns location of the start of the route.
     * @return location of the start of the route.
     **/
  	public GeoPoint getStart() {
  		return this.start;  
  	}


  	/**
  	 * Returns location of the end of the route.
     * @return location of the end of the route.
     **/
  	public GeoPoint getEnd() {
  		return this.end; 
  	}


  	/**
  	 * Returns direction of travel at the start of the route, in degrees.
   	 * @return direction (in compass heading) of travel at the start of the
   	 *         route, in degrees.
   	 **/
  	public double getStartHeading() {
  		return this.start_heading; 
  	}


  	/**
  	 * Returns direction of travel at the end of the route, in degrees.
     * @return direction (in compass heading) of travel at the end of the
     *         route, in degrees.
     **/
  	public double getEndHeading() {
  		return this.end_heading; 
  	}


  	/**
  	 * Returns total length of the route.
     * @return total length of the route, in kilometers.  NOTE: this is NOT
     *         as-the-crow-flies, but rather the total distance required to
     *         traverse the route. These values are not necessarily equal.
   	 **/
  	public double getLength() {
  		return this.length; 
  	}


  	/**
     * Creates a new route that is equal to this route with gs appended to
     * its end.
   	 * @requires gs != null && gs.p1 == this.end
     * @return a new Route r such that
     *         r.end = gs.p2 &&
     *         r.endHeading = gs.heading &&
     *         r.length = this.length + gs.length
     **/
  	public Route addSegment(GeoSegment gs) {
		ArrayList<GeoSegment> geo_segments = (ArrayList<GeoSegment>) this.geo_segments.clone();
		geo_segments.add(gs);
  		return new Route(geo_segments);
  	}

    /**
     * Returns an Iterator of GeoFeature objects. The concatenation
     * of the GeoFeatures, in order, is equivalent to this route. No two
     * consecutive GeoFeature objects have the same name.
     * @return an Iterator of GeoFeatures such that
     * <pre>
     *      this.start        = a[0].start &&
     *      this.startHeading = a[0].startHeading &&
     *      this.end          = a[a.length - 1].end &&
     *      this.endHeading   = a[a.length - 1].endHeading &&
     *      this.length       = sum(0 <= i < a.length) . a[i].length &&
     *      for all integers i
     *          (0 <= i < a.length - 1 => (a[i].name != a[i+1].name &&
     *                                     a[i].end  == a[i+1].start))
     * </pre>
     * where <code>a[n]</code> denotes the nth element of the Iterator.
     * @see homework1.GeoFeature
     **/
  	public Iterator<GeoFeature> getGeoFeatures() {
  		return this.geo_features.iterator(); 
  	}


  	/**
     * Returns an Iterator of GeoSegment objects. The concatenation of the
     * GeoSegments, in order, is equivalent to this route.
     * @return an Iterator of GeoSegments such that
     * <pre>
     *      this.start        = a[0].p1 &&
     *      this.startHeading = a[0].heading &&
     *      this.end          = a[a.length - 1].p2 &&
     *      this.endHeading   = a[a.length - 1].heading &&
     *      this.length       = sum (0 <= i < a.length) . a[i].length
     * </pre>
     * where <code>a[n]</code> denotes the nth element of the Iterator.
     * @see homework1.GeoSegment
     **/
  	public Iterator<GeoSegment> getGeoSegments() {
		return this.geo_features.getGeoSegments(); 
  	}


  	/**
     * Compares the specified Object with this Route for equality.
     * @return true iff (o instanceof Route) &&
     *         (o.geoFeatures and this.geoFeatures contain
     *          the same elements in the same order).
     **/
  	public boolean equals(Object o) {
		if (o instanceof Route == false)
  			return false;
		
		if(this.getGeoFeatures().equals(o.getGeoFeatures()))
			retrun false; 
		
		return true; 
		
		//if(this.getGeoSegments().equals(o.getGeoSegments()))
		//	retrun false; 
  	}


    /**
     * Returns a hash code for this.
     * @return a hash code for this.
     **/
  	public int hashCode() {
    	// This implementation will work, but you may want to modify it
    	// for improved performance.
	int sum = 0;
    	for (GeoSegment geo_segment : this.geo_segments) {
			sum +=geo_segment.hashCode();
		}
    	return sum;
	}


    /**
     * Returns a string representation of this.
     * @return a string representation of this.
     **/
  	public String toString() {	
		String str; 
		for (GeoFeature geo_feature : this.geo_features)
			str +=geo_feature.toString(); 
		
		return str; 		
  	}

}
