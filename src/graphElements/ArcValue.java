package graphElements;

public class ArcValue<S,V> extends Arc<S>
{
	private V valeur;
	
	public ArcValue (Sommet<S> depart, Sommet<S> arrivee, V valeur)
	{
		super(arrivee,depart);
		this.valeur=valeur;
	}
	
	public V getValeur()
	{
		return valeur;
	}
}
