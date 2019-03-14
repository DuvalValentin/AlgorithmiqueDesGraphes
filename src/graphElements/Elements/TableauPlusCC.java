package graphElements.Elements;

import java.util.HashMap;

import factory.Factory;
import graphElements.Interfaces.InterfaceCout;
import graphElements.Interfaces.InterfaceGrapheValue;
import graphElements.Interfaces.InterfaceSommet;
import graphElements.Interfaces.InterfaceTableauPlusCC;

public class TableauPlusCC<S> implements InterfaceTableauPlusCC<S>
{
	//Eléments
	private InterfaceSommet<S> principal;
	private HashMap<InterfaceSommet<S>,InterfaceCout> d;
	private HashMap<InterfaceSommet<S>,InterfaceSommet<S>> pred;
	//Constructeur
	public TableauPlusCC(Sommet<S>prin)
	{ 
		d=new HashMap<InterfaceSommet<S>,InterfaceCout>();
		pred= new HashMap<InterfaceSommet<S>,InterfaceSommet<S>>();
		principal=prin;
		initSommet(principal);
		modifDistance(principal,Factory.cout());
	}
	public TableauPlusCC(InterfaceSommet<S>prin,InterfaceGrapheValue<S>G)
	{
		d=new HashMap<InterfaceSommet<S>,InterfaceCout>();
		pred= new HashMap<InterfaceSommet<S>,InterfaceSommet<S>>();
		principal=prin;
		for(InterfaceSommet<S> sommet : G.getX().getEnsemble())
		{
			initSommet(sommet);
			modifDistance(sommet,G.getCout(principal, sommet));
		}
		modifDistance(principal,Factory.cout());
	}
	//Getters
	public HashMap<InterfaceSommet<S>,InterfaceCout> getD()
	{
		return new HashMap<InterfaceSommet<S>,InterfaceCout>(d);
	}
	public HashMap<InterfaceSommet<S>,InterfaceSommet<S>> getPred()
	{
		return new HashMap<InterfaceSommet<S>,InterfaceSommet<S>>(pred);
	}
	public InterfaceSommet<S> getPrincipal()
	{
		return Factory.sommet(principal);
	}
	public InterfaceCout getDistance(InterfaceSommet<S> sommet)
	{
		return d.get(sommet);
	}
	
	public InterfaceSommet<S> getPredecesseur(InterfaceSommet<S> sommet)
	{
		return pred.get(sommet);
	}
	//Modification d'éléments
	public void initSommet(InterfaceSommet<S> sommet)
	{
		d.put(sommet, null);
		pred.put(sommet,principal);
	}
	public void modifDistance(InterfaceSommet<S> sommet, InterfaceCout cout)
	{
		d.replace(sommet,cout);
	}
	public void modifPredecesseur(InterfaceSommet<S> sommet,InterfaceSommet<S> predessesseur)
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
		boolean result=false;
		if(obj!=null)
		{
			if(obj.getClass()==getClass())
			{
				if(((TableauPlusCC<S>)obj).getPrincipal().equals(getPrincipal())&&((TableauPlusCC<S>)obj).getD().equals(getD())&&((TableauPlusCC<S>)obj).getPred().equals(getPred()))
				{
					result=true;
				}
			}
		}
		return result;
	}
}