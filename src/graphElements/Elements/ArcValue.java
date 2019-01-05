package graphElements.Elements;

import graphElements.Interfaces.InterfaceArcValue;

public class ArcValue<S> extends Arc<S> implements InterfaceArcValue<S>
{
	private Cout cout;
	
	public ArcValue (Sommet<S> depart, Sommet<S> arrivee, Cout cout)
	{
		super(depart,arrivee);
		this.cout=cout.clone();
	}
	
	public ArcValue(ArcValue<S> arcValue)
	{
		super(arcValue.getDepart(),arcValue.getArrivee());
		cout=arcValue.getCout();
		
	}
	@Override
	public Cout getCout()
	{
		return cout.clone();
	}
	
	@Override
	public float getValeur()
	{
		return cout.getValeur();
	}
	
	@Override
	public void setValeur(float valeur)
	{
		cout.setValeur(valeur);
	}
	
	@Override
	public String toString()
	{
		return super.toString()+"=>["+getValeur()+"]";
	}
	
	//TODO ne marche pas encore
	@Override
	public ArcValue<S> clone()
	{
		return new ArcValue<S>(this);
	}
}
