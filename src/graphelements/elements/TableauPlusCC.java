package graphelements.elements;

import java.util.HashMap;
import java.util.Optional;

import factory.Factory;
import graphelements.interfaces.InterfaceCout;
import graphelements.interfaces.InterfaceGrapheValue;
import graphelements.interfaces.InterfaceSommet;
import graphelements.interfaces.InterfaceTableauPlusCC;

public class TableauPlusCC<S> implements InterfaceTableauPlusCC<S>
{
	//Eléments
	private InterfaceSommet<S> principal;
	private HashMap<InterfaceSommet<S>,InterfaceCout> d;
	private HashMap<InterfaceSommet<S>,InterfaceSommet<S>> pred;
	//Constructeur
	public TableauPlusCC(InterfaceSommet<S>prin)
	{ 
		d=new HashMap<>();
		pred= new HashMap<>();
		principal=prin;
		initSommet(principal);
		modifDistance(principal,Factory.cout());
	}
	public TableauPlusCC(InterfaceSommet<S>prin,InterfaceGrapheValue<S>graphe)
	{
		d=new HashMap<>();
		pred= new HashMap<>();
		principal=prin;
		for(InterfaceSommet<S> sommet : graphe.getEnsembleSommet().getEnsemble())
		{
			initSommet(sommet);
			Optional<InterfaceCout> oCout=graphe.getCout(principal, sommet);
			if(oCout.isPresent())
			{
				modifDistance(sommet,oCout.get());
			}
		}
		modifDistance(principal,Factory.cout());
	}
	//Getters
	public HashMap<InterfaceSommet<S>,InterfaceCout> getD()
	{
		return new HashMap<>(d);
	}
	public HashMap<InterfaceSommet<S>,InterfaceSommet<S>> getPred()
	{
		return new HashMap<>(pred);
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
		if(obj!=null&&obj.getClass()==getClass()&&((TableauPlusCC<S>)obj).getPrincipal().equals(getPrincipal())&&((TableauPlusCC<S>)obj).getD().equals(getD())&&((TableauPlusCC<S>)obj).getPred().equals(getPred()))
		{
			result=true;
		}
		return result;
	}
	@Override
	public int hashCode()
	{
		return principal.hashCode()+d.hashCode()+pred.hashCode();
	}
}