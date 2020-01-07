package graphelements.elements;

import factory.Factory;
import graphelements.interfaces.InterfaceArc;
import graphelements.interfaces.InterfaceSommet;

public class Arc<S> implements InterfaceArc<S>
{
	//Sommets
	private InterfaceSommet<S> depart;
	private InterfaceSommet<S> arrivee;
	//Constructeurs
	public Arc(InterfaceSommet<S> dep, InterfaceSommet<S> arr)
	{
		setDepart(Factory.sommet(dep));
		setArrivee(Factory.sommet(arr));
	}
	public Arc(S idDep, S idArr)
	{
		setDepart(Factory.sommet(idDep));
		setArrivee(Factory.sommet(idArr));
	}
	public Arc(InterfaceArc<S> arc)
	{
		setDepart(arc.getDepart());
		setArrivee(arc.getArrivee());
	}
	@Override
	public String toString()
	{
		return "("+getDepart().getId()+","+getArrivee().getId()+")";
		
	}
	//Getters
	@Override
	public InterfaceSommet<S> getDepart() 
	{
		return Factory.sommet(depart);
	}
	@Override
	public InterfaceSommet<S> getArrivee() 
	{
		return Factory.sommet(arrivee);
	}
	//Setters
	@Override
	public void setDepart(InterfaceSommet<S> depart) 
	{
		this.depart = Factory.sommet(depart);
	}
	@Override
	public void setArrivee(InterfaceSommet<S> arrivee) 
	{
		this.arrivee = Factory.sommet(arrivee);
	}
	//test pour savoir si deux arc sont équivalents
	@Override
	public boolean memeArc(InterfaceSommet<S> depart, InterfaceSommet<S> arrivee)
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
		if(obj!=null&&obj.getClass()==getClass()&&memeArc(((Arc<S>)obj)))
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