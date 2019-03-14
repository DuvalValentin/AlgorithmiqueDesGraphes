package graphElements.Elements;

import factory.Factory;
import graphElements.Interfaces.InterfaceArcValue;
import graphElements.Interfaces.InterfaceCout;
import graphElements.Interfaces.InterfaceSommet;

public class ArcValue<S> extends Arc<S> implements InterfaceArcValue<S>
{
	//Le Cout
	private InterfaceCout cout;
	//Constructeurs
	public ArcValue (InterfaceSommet<S> depart, InterfaceSommet<S> arrivee, InterfaceCout cout)
	{
		super(depart,arrivee);
		this.cout=Factory.cout(cout);
	}
	public ArcValue(InterfaceArcValue<S> arcValue)
	{
		super(arcValue.getDepart(),arcValue.getArrivee());
		cout=arcValue.getCout();
	}
	//Getters
	@Override
	public InterfaceCout getCout()
	{
		return Factory.cout(cout);
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