package graphelements.elements;

import java.util.HashSet;
import java.util.Optional;

import factory.Factory;
import graphelements.abstracts.AbstractEnsembleArc;
import graphelements.interfaces.InterfaceArcValue;
import graphelements.interfaces.InterfaceCout;
import graphelements.interfaces.InterfaceEnsembleArcValue;
import graphelements.interfaces.InterfaceSommet;

public class EnsembleArcValue<S> extends AbstractEnsembleArc<S,InterfaceArcValue<S>> implements InterfaceEnsembleArcValue<S> 
{
	//Constructeur
	public EnsembleArcValue(InterfaceEnsembleArcValue<S> ensemble)
	{
		for (InterfaceArcValue<S> arc : ensemble.getEnsemble())
		{
			ajouteElement(arc);
		}
	}
	public EnsembleArcValue()
	{
		super();
	}
	//getter
	@Override
	public Optional<InterfaceCout> getCout(InterfaceSommet<S> depart, InterfaceSommet<S> arrivee)
	{
		InterfaceCout cout=null;
		for(InterfaceArcValue<S> arcV : ensemble)
		{
			if (arcV.getDepart().equals(depart) && arcV.getArrivee().equals(arrivee))
			{
				cout=arcV.getCout();
				break;
			}
		}
		Optional<InterfaceCout> oCout;
		if(cout!=null)
		{
			oCout=Optional.of(cout);
		}
		else
		{
			oCout=Optional.empty();
		}
		
		return oCout;
	}
	//setter
	@Override
	public boolean setValeur(InterfaceSommet<S> depart, InterfaceSommet<S> arrivee,InterfaceCout cout)
	{
		boolean succes=false;
		InterfaceArcValue<S> arcS=Factory.arcValue(depart, arrivee, cout);
		for(InterfaceArcValue<S> arcV : ensemble)
		{
			if (arcV.memeArc(arcS))
			{
				((ArcValue<S>) arcV).setValeur(cout.getValeur());
				succes=true;
				break;
			}
		}
		return succes;
	}
	//ajout et suppression d'élément
	@Override
	public void ajouteElement(InterfaceArcValue<S> arc)//Si le coût est inferieur l'arc est remplacé
	{
		if(existeArc(arc))
		{
			for(InterfaceArcValue<S> a : ensemble)
			{
				if(arc.getDepart().equals(a.getDepart())&&arc.getArrivee().equals(a.getArrivee())&&arc.getCout().getValeur()<a.getCout().getValeur())
				{
					ensemble.remove(a);
					ensemble.add(Factory.arcValue(arc));
					break;
				}
			}
		}
		else
		{
			ensemble.add(Factory.arcValue(arc));
		}
	}
	@Override
	public void ajouteElement(InterfaceSommet<S> depart,InterfaceSommet<S>arrivee,InterfaceCout cout)
	{
		ajouteElement(Factory.arcValue(depart,arrivee,cout));
	}
	@Override
	public void supprElement(InterfaceSommet<S>depart,InterfaceSommet<S>arrivee)
	{
		Optional<InterfaceCout> oCout=getCout(depart,arrivee);
		if(oCout.isPresent())
		{
			supprElement(Factory.arcValue(depart,arrivee,oCout.get()));
		}
	}
	@Override
	public HashSet<InterfaceArcValue<S>> getEnsemble()
	{
		return new HashSet<>(ensemble);
	}
}