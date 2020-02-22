package graphelements.elements;

import factory.Factory;
import graphelements.interfaces.ArcValue;
import graphelements.interfaces.Cout;
import graphelements.interfaces.Sommet;

public class ArcValueImpl<S>extends ArcImpl<S> implements ArcValue<S>
{
	// Le Cout
	private Cout cout;

	// Constructeurs
	public ArcValueImpl(Sommet<S> depart, Sommet<S> arrivee, Cout cout)
	{
		super(depart,arrivee);
		this.cout=cout;
	}
	public ArcValueImpl(ArcValue<S> arcValue)
	{
		super(arcValue.getDepart(),arcValue.getArrivee());
		cout=Factory.cout(arcValue.getValeur());
	}
	// Getters
	@Override
	public Cout getCout()
	{
		return Factory.cout(cout);
	}
	@Override
	public float getValeur()
	{
		return cout.getValeur();
	}
	// Setters
	@Override
	public void setValeur(float valeur)
	{
		cout.setValeur(valeur);
	}
	// toString/equals/hashCode
	@Override
	public String toString()
	{
		return super.toString()+"=>["+getCout()+"]";
	}
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj)
	{
		boolean result=false;
		if(super.equals(obj)&&((ArcValueImpl<S>)obj).getCout().equals(getCout()))
		{
			result=true;
		}
		return result;
	}
	@Override
	public int hashCode()
	{
		return super.hashCode()+(int)getValeur();
	}
}