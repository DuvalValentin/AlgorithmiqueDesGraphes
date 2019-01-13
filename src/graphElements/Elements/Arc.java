package graphElements.Elements;

import graphElements.Interfaces.InterfaceArc;

public class Arc<S> implements InterfaceArc<S>
{
	//Sommets
	private Sommet<S> depart;
	private Sommet<S> arrivee;
	//Constructeurs
	public Arc(Sommet<S> dep, Sommet<S> arr)
	{
		setDepart(new Sommet<S>(dep));
		setArrivee(new Sommet<S>(arr));
	}
	public Arc(Arc<S> arc)
	{
		setDepart(arc.getDepart());
		setArrivee(arc.getArrivee());
	}
	@Override
	public String toString()
	{
		String str="("+getDepart().getId()+","+getArrivee().getId()+")";
		return str;
	}
	//Getters
	@Override
	public Sommet<S> getDepart() 
	{
		return new Sommet<S>(depart);
	}
	@Override
	public Sommet<S> getArrivee() 
	{
		return new Sommet<S>(arrivee);
	}
	//Setters
	@Override
	public void setDepart(Sommet<S> depart) 
	{
		this.depart = new Sommet<S>(depart);
	}
	@Override
	public void setArrivee(Sommet<S> arrivee) 
	{
		this.arrivee = new Sommet<S>(arrivee);
	}
	//test pour savoir si deux arc sont équivalents
	@Override
	public boolean memeArc(Sommet<S> depart, Sommet<S> arrivee)
	{
		return(getDepart().equals(depart)&&getArrivee().equals(arrivee));
	}
	@Override
	public boolean memeArc(InterfaceArc<S> arc)
	{
		return memeArc(arc.getDepart(),arc.getArrivee());
	}
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj)
	{
		boolean result=false;
		if(obj!=null)
		{
			if(obj.getClass()==getClass())
			{
				if(memeArc(((Arc<S>)obj)))
				{
					result=true;
				}
			}
		}
		return result;
	}
	@Override
	public int hashCode()
	{
		return getArrivee().hashCode()+getDepart().hashCode();
	}
}