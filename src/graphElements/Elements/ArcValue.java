package graphElements.Elements;

import graphElements.Interfaces.InterfaceArcValue;

public class ArcValue<S> extends Arc<S> implements InterfaceArcValue<S>
{
	//Le Cout
	private Cout cout;
	//Constructeurs
	public ArcValue (Sommet<S> depart, Sommet<S> arrivee, Cout cout)
	{
		super(depart,arrivee);
		this.cout=new Cout(cout);
	}
	public ArcValue(ArcValue<S> arcValue)
	{
		super(arcValue.getDepart(),arcValue.getArrivee());
		cout=arcValue.getCout();
	}
	//Getters
	@Override
	public Cout getCout()
	{
		return new Cout(cout);
	}
	@Override
	public float getValeur()
	{
		return cout.getValeur();
	}
	//Setters
	@Override
	public void setValeur(float valeur)
	{
		cout.setValeur(valeur);
	}
	//toString/equals/hashCode
	@Override
	public String toString()
	{
		return super.toString()+"=>["+getValeur()+"]";
	}
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj)
	{
		boolean result=false;
		if (super.equals(obj))
		{
			if(((ArcValue<S>)obj).getCout().equals(getCout()))
			{
				result=true;
			}
		}
		return result;
	}
	@Override public int hashCode()
	{
		return super.hashCode()+(int)getValeur();
	}
}