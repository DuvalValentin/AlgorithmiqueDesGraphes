package graphelements.elements;

import java.util.HashSet;
import java.util.Optional;
import factory.Factory;
import graphelements.abstracts.AbstractEnsembleArc;
import graphelements.interfaces.ArcValue;
import graphelements.interfaces.EnsembleArcValue;
import graphelements.interfaces.Sommet;

public class EnsembleArcValueImpl<S>extends AbstractEnsembleArc<S,ArcValue<S>> implements EnsembleArcValue<S>
{
	// Constructeur
	public EnsembleArcValueImpl(EnsembleArcValue<S> ensemble)
	{
		for(ArcValue<S> arc : ensemble.getEnsemble())
		{
			ajouteElement(arc);
		}
	}
	public EnsembleArcValueImpl()
	{
		super();
	}
	// getter
	@Override
	public Optional<Float> getCout(Sommet<S> depart, Sommet<S> arrivee)
	{
		Float cout=null;
		for(ArcValue<S> arcV : ensemble)
		{
			if(arcV.getDepart().equals(depart)&&arcV.getArrivee().equals(arrivee))
			{
				cout=arcV.getCout();
				break;
			}
		}
		Optional<Float> oCout;
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
	// setter
	@Override
	public boolean setValeur(Sommet<S> depart, Sommet<S> arrivee, Float cout)
	{
		boolean succes=false;
		ArcValue<S> arcS=Factory.arcValue(depart,arrivee,cout);
		for(ArcValue<S> arcV : ensemble)
		{
			if(arcV.memeArc(arcS))
			{
				((ArcValueImpl<S>)arcV).setCout(cout);
				succes=true;
				break;
			}
		}
		return succes;
	}
	// ajout et suppression d'élément
	@Override
	public void ajouteElement(ArcValue<S> arc)// Si le coût est inferieur l'arc est remplacé
	{
		if(existeArc(arc))
		{
			for(ArcValue<S> a : ensemble)
			{
				if(arc.getDepart().equals(a.getDepart())&&arc.getArrivee().equals(a.getArrivee())&&arc.getCout()<a.getCout())
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
	public void ajouteElement(Sommet<S> depart, Sommet<S> arrivee, Float cout)
	{
		ajouteElement(Factory.arcValue(depart,arrivee,cout));
	}
	@Override
	public void supprElement(Sommet<S> depart, Sommet<S> arrivee)
	{
		Optional<Float> oCout=getCout(depart,arrivee);
		if(oCout.isPresent())
		{
			supprElement(Factory.arcValue(depart,arrivee,oCout.get()));
		}
	}
	@Override
	public HashSet<ArcValue<S>> getEnsemble()
	{
		return new HashSet<>(ensemble);
	}
}