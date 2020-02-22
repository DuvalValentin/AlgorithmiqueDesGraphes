package graphelements.elements;

import java.util.HashMap;
import java.util.Optional;
import factory.Factory;
import graphelements.interfaces.Cout;
import graphelements.interfaces.GrapheValue;
import graphelements.interfaces.Sommet;
import graphelements.interfaces.TableauPlusCC;

public class TableauPlusCCImpl<S> implements TableauPlusCC<S>
{
	// Eléments
	private Sommet<S> principal;
	private HashMap<Sommet<S>,Cout> d;
	private HashMap<Sommet<S>,Sommet<S>> pred;

	// Constructeur
	public TableauPlusCCImpl(Sommet<S> prin)
	{
		d=new HashMap<>();
		pred=new HashMap<>();
		principal=prin;
		initSommet(principal);
		modifDistance(principal,Factory.cout());
	}
	public TableauPlusCCImpl(Sommet<S> prin, GrapheValue<S> graphe)
	{
		d=new HashMap<>();
		pred=new HashMap<>();
		principal=prin;
		for(Sommet<S> sommet : graphe.getEnsembleSommet().getEnsemble())
		{
			initSommet(sommet);
			Optional<Cout> oCout=graphe.getCout(principal,sommet);
			if(oCout.isPresent())
			{
				modifDistance(sommet,oCout.get());
			}
		}
		modifDistance(principal,Factory.cout());
	}
	// Getters
	public HashMap<Sommet<S>,Cout> getD()
	{
		return new HashMap<>(d);
	}
	public HashMap<Sommet<S>,Sommet<S>> getPred()
	{
		return new HashMap<>(pred);
	}
	public Sommet<S> getPrincipal()
	{
		return Factory.sommet(principal);
	}
	public Cout getDistance(Sommet<S> sommet)
	{
		return d.get(sommet);
	}
	public Sommet<S> getPredecesseur(Sommet<S> sommet)
	{
		return pred.get(sommet);
	}
	// Modification d'éléments
	public void initSommet(Sommet<S> sommet)
	{
		d.put(sommet,null);
		pred.put(sommet,principal);
	}
	public void modifDistance(Sommet<S> sommet, Cout cout)
	{
		d.replace(sommet,cout);
	}
	public void modifPredecesseur(Sommet<S> sommet, Sommet<S> predessesseur)
	{
		pred.replace(sommet,predessesseur);
	}
	// toString et equals
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
		if(obj!=null&&obj instanceof TableauPlusCCImpl&&((TableauPlusCCImpl<S>)obj).getPrincipal().equals(getPrincipal())&&((TableauPlusCCImpl<S>)obj).getPred().equals(getPred())&&((TableauPlusCCImpl<S>)obj).getD().equals(getD()))
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