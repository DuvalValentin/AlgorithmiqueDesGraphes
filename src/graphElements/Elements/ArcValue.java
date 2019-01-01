package graphElements.Elements;

import graphElements.Interfaces.InterfaceArcValue;

public class ArcValue<S> extends Arc<S> implements InterfaceArcValue<S>
{
	private Cout cout;
	
	public ArcValue (Sommet<S> depart, Sommet<S> arrivee, Cout cout)
	{
		super(depart,arrivee);
		this.cout=cout;
	}
	
	@Override
	public Cout getCout()
	{
		return cout;
	}
}
