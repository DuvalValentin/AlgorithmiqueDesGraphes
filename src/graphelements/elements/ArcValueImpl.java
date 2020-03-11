package graphelements.elements;

import graphelements.interfaces.ArcValue;
import graphelements.interfaces.Sommet;

public class ArcValueImpl<S>extends ArcImpl<S> implements ArcValue<S>
{
	// Le Cout
	private Float cout;

	// Constructeurs
	public ArcValueImpl(Sommet<S> depart, Sommet<S> arrivee, Float cout)
	{
		super(depart,arrivee);
		this.cout=cout;
	}
	public ArcValueImpl(ArcValue<S> arcValue)
	{
		super(arcValue.getDepart(),arcValue.getArrivee());
		cout=arcValue.getCout();
	}
	// Getters
	@Override
	public float getCout()
	{
		return cout;
	}
	// Setters
	@Override
	public void setCout(Float cout)
	{
		this.cout=cout;
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
		if(super.equals(obj)&&((ArcValueImpl<S>)obj).getCout()==getCout())
		{
			result=true;
		}
		return result;
	}
	@Override
	public int hashCode()
	{
		return super.hashCode()+cout.hashCode();
	}
}