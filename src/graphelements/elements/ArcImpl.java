package graphelements.elements;

import factory.Factory;
import graphelements.interfaces.Arc;
import graphelements.interfaces.Sommet;

public class ArcImpl<S> implements Arc<S>
{
	// Sommets
	private Sommet<S> depart;
	private Sommet<S> arrivee;

	// Constructeurs
	public ArcImpl(Sommet<S> dep, Sommet<S> arr)
	{
		setDepart(Factory.sommet(dep));
		setArrivee(Factory.sommet(arr));
	}
	public ArcImpl(S idDep, S idArr)
	{
		setDepart(Factory.sommet(idDep));
		setArrivee(Factory.sommet(idArr));
	}
	public ArcImpl(Arc<S> arc)
	{
		setDepart(arc.getDepart());
		setArrivee(arc.getArrivee());
	}
	@Override
	public String toString()
	{
		return "("+getDepart().getId()+","+getArrivee().getId()+")";
	}
	// Getters
	@Override
	public Sommet<S> getDepart()
	{
		return Factory.sommet(depart);
	}
	@Override
	public Sommet<S> getArrivee()
	{
		return Factory.sommet(arrivee);
	}
	// Setters
	@Override
	public void setDepart(Sommet<S> depart)
	{
		this.depart=Factory.sommet(depart);
	}
	@Override
	public void setArrivee(Sommet<S> arrivee)
	{
		this.arrivee=Factory.sommet(arrivee);
	}
	// test pour savoir si deux arc sont équivalents
	@Override
	public boolean memeArc(Sommet<S> depart, Sommet<S> arrivee)
	{
		return(getDepart().equals(depart)&&getArrivee().equals(arrivee));
	}
	@Override
	public boolean memeArc(Arc<S> arc)
	{
		return memeArc(arc.getDepart(),arc.getArrivee());
	}
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj)
	{
		boolean result=false;
		if(obj!=null&&obj.getClass()==getClass()&&memeArc(((ArcImpl<S>)obj)))
		{
			result=true;
		}
		return result;
	}
	@Override
	public int hashCode()
	{
		return getArrivee().hashCode()+getDepart().hashCode();
	}
}