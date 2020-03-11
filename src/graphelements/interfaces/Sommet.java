package graphelements.interfaces;

/**
 * This class represents an edge of a graph. The Sommet class wraps an object
 * called id in order to be treated by the rest of the package
 * 
 * @author valentin
 *
 * @param <S>
 *          the type of object wrapped in the Sommet
 */
public interface Sommet<S>
{
	/**
	 * @return the wrapped object
	 */
	S getId();
	/**
	 * @param id
	 *          the new value of the wrapped object
	 */
	void setId(S id);
}
