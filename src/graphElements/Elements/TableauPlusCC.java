package graphElements.Elements;

import java.util.HashMap;
import java.util.Optional;

import factory.Factory;
import graphElements.Interfaces.InterfaceArcValue;
import graphElements.Interfaces.InterfaceCout;
import graphElements.Interfaces.InterfaceGrapheValue;
import graphElements.Interfaces.InterfaceSommet;
import graphElements.Interfaces.InterfaceTableauPlusCC;

public class TableauPlusCC<S,AV extends InterfaceArcValue<S>> implements InterfaceTableauPlusCC<S>
{
	//Eléments
	private InterfaceSommet<S> principal;
	private HashMap<InterfaceSommet<S>,InterfaceCout> d;
	private HashMap<InterfaceSommet<S>,InterfaceSommet<S>> pred;
	//Constructeur
	public TableauPlusCC(InterfaceSommet<S>prin)
	{ 
		d=new HashMap<InterfaceSommet<S>,InterfaceCout>();
		pred= new HashMap<InterfaceSommet<S>,InterfaceSommet<S>>();
		principal=prin;
		initSommet(principal);
		modifDistance(principal,Factory.cout());
	}
	public TableauPlusCC(InterfaceSommet<S>prin,InterfaceGrapheValue<S,AV>G)
	{
		d=new HashMap<InterfaceSommet<S>,InterfaceCout>();
		pred= new HashMap<InterfaceSommet<S>,InterfaceSommet<S>>();
		principal=prin;
		for(InterfaceSommet<S> sommet : G.getX().getEnsemble())
		{
			initSommet(sommet);
			Optional<InterfaceCout> oCout=G.getCout(principal, sommet);
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
				if(((TableauPlusCC<S,AV>)obj).getPrincipal().equals(getPrincipal())&&((TableauPlusCC<S,AV>)obj).getD().equals(getD())&&((TableauPlusCC<S,AV>)obj).getPred().equals(getPred()))
				{
					result=true;
				}
			}
		}
		return result;
	}
}