package graphelements.interfaces;

/**
 * Vertex with an assossiated cost
 * 
 * @author valentin
 *
 * @param <S>
 *          the type of objects wrapped in the edges
 */
public interface ArcValue<S>extends Arc<S>
{
	// Un Cout a un attribut valeur qui est un flottant
	// Getter
	float getCout();// Renvoie la valeur
	// Setter
	void setCout(Float cout);// Modifie la valeur
}