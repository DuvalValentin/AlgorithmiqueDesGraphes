package graphElements;

public class ArcValue<S> extends Arc<S>
{
	private Cout cout;
	
	public ArcValue (Sommet<S> depart, Sommet<S> arrivee, Cout cout)
	{
		super(depart,arrivee);
		this.cout=cout;
	}
	
	public Cout getCout()
	{
		return cout;
	}
}
