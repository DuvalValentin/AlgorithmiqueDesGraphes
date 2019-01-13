package graphElements.Elements;

import java.util.HashMap;

import graphElements.Interfaces.InterfaceTableauPlusCC;

public class TableauPlusCC<S> implements InterfaceTableauPlusCC<S>
{
	//Eléments
	private Sommet<S> principal;
	private HashMap<Sommet<S>,Cout> d;
	private HashMap<Sommet<S>,Sommet<S>> pred;
	//Constructeur
	public TableauPlusCC(Sommet<S>prin)
	{ 
		d=new HashMap<Sommet<S>,Cout>();
		pred= new HashMap<Sommet<S>,Sommet<S>>();
		principal=prin;
		initSommet(principal);
		modifDistance(principal,new Cout());
	}
	//Getters
	public HashMap<Sommet<S>,Cout> getD()
	{
		return new HashMap<Sommet<S>,Cout>(d);
	}
	public HashMap<Sommet<S>,Sommet<S>> getPred()
	{
		return new HashMap<Sommet<S>,Sommet<S>>(pred);
	}
	public Sommet<S> getPrincipal()
	{
		return new Sommet<S>(principal);
	}
	public Cout getDistance(Sommet<S> sommet)
	{
		return d.get(sommet);
	}
	
	public Sommet<S> getPredecesseur(Sommet<S> sommet)
	{
		return pred.get(sommet);
	}
	//Modification d'éléments
	public void initSommet(Sommet<S> sommet)
	{
		d.put(sommet, null);
		pred.put(sommet,principal);
	}
	public void modifDistance(Sommet<S> sommet, Cout cout)
	{
		d.replace(sommet,cout);
	}
	public void modifPredecesseur(Sommet<S> sommet,Sommet<S> predessesseur)
	{
		pred.replace(sommet, predessesseur);
	}
	//toString et equals
	@Override 
	public String toString()
	{
		return principal.toString()+"=>"+d.toString()+pred.toString();
	}
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj)
	{
		boolean result;
		if(obj.getClass()!=getClass())
		{
			result=false;
		}
		else
		{
			if(((TableauPlusCC<S>)obj).getPrincipal().equals(getPrincipal())&&((TableauPlusCC<S>)obj).getD().equals(getD())&&((TableauPlusCC<S>)obj).getPred().equals(getPred()))
			{
				result=true;
			}
			else
			{
				result=false;
			}
		}
		return result;
	}
}