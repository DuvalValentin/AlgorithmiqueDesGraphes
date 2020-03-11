package graphelements.interfaces;

/**
 * A vertex is composed of two edges : start and arrival. It means there is a
 * way from the start to the arrival.
 * 
 * @author valentin
 *
 * @param <S>
 *          the type of objects wrapped in the edges
 */
public interface Arc<S>
{
	/**
	 * Get the starting edge
	 * 
	 * @return the starting edge
	 */
	Sommet<S> getDepart();
	/**
	 * Get the arrival edge
	 * 
	 * @return the arrival edge
	 */
	Sommet<S> getArrivee();
	/**
	 * Set the starting edge
	 * 
	 * @param the
	 *          new starting edge
	 */
	void setDepart(Sommet<S> depart);
	/**
	 * Set the arrival edge
	 * 
	 * @param the
	 *          new arrival edge
	 */
	void setArrivee(Sommet<S> arrivee);
	/**
	 * Check if the given vertex is the same has the same edges
	 * 
	 * @param arc
	 *          the comparing vertex return true if their edges are the same
	 */
	boolean memeArc(Arc<S> arc);
	/**
	 * Check if the given edges are the same as those in the vertex
	 * 
	 * @param depart
	 *          the starting edge
	 * @param arrivee
	 *          the arrival edge
	 * @return true if the edges are the same
	 */
	boolean memeArc(Sommet<S> depart, Sommet<S> arrivee);
}
