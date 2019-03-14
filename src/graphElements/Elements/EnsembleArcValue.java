package graphElements.Elements;

import java.util.HashSet;

import factory.Factory;
import graphElements.Abstract.AbstractEnsembleArc;
import graphElements.Interfaces.InterfaceArcValue;
import graphElements.Interfaces.InterfaceCout;
import graphElements.Interfaces.InterfaceEnsembleArcValue;
import graphElements.Interfaces.InterfaceSommet;

public class EnsembleArcValue<S> extends AbstractEnsembleArc<S,InterfaceArcValue<S>> implements InterfaceEnsembleArcValue<S> 
{
	//Constructeur
	public EnsembleArcValue(InterfaceEnsembleArcValue<S> Ensemble)
	{
		for (InterfaceArcValue<S> arc : Ensemble.getEnsemble())
		{
			ajouteArc(arc);
		}
	}
	public EnsembleArcValue()
	{
		super();
	}
	//getter
	@Override
	public InterfaceCout getCout(InterfaceSommet<S> depart, InterfaceSommet<S> arrivee)
	{
		InterfaceCout cout=null;
		for(InterfaceArcValue<S> arcV : ensemble)
		{
			if (arcV.getDepart().equals(depart) && arcV.getArrivee().equals(arrivee))
			{
				cout=((InterfaceArcValue<S>) arcV).getCout();
				break;
			}
		}
		return cout;
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
	public void ajouteArc(InterfaceArcValue<S> arc)
	{
		super.ajouteArc(Factory.arcValue(arc));
	}
	@Override
	public void ajouteArc(InterfaceSommet<S> depart,InterfaceSommet<S>arrivee,InterfaceCout cout)
	{
		ajouteArc(Factory.arcValue(depart,arrivee,cout));
	}
	@Override
	public void supprArc(InterfaceSommet<S>depart,InterfaceSommet<S>arrivee)
	{
		InterfaceCout cout=getCout(depart,arrivee);
		if(cout!=null)
		{
			supprArc(Factory.arcValue(depart,arrivee,cout));
		}
	}
	@Override
	public HashSet<InterfaceArcValue<S>> getEnsemble()
	{
		return new HashSet<InterfaceArcValue<S>>(ensemble);
	}
	
	
	public static <S> InterfaceEnsembleArcValue<S> union(InterfaceEnsembleArcValue<S> Ensemble1,InterfaceEnsembleArcValue<S> Ensemble2)
	{
		InterfaceEnsembleArcValue<S> union = Factory.ensembleArcValue(Ensemble1);
		for(InterfaceArcValue<S> arc : Ensemble2.getEnsemble())
		{
			union.ajouteArc(arc);
		}
		return union;
	}
	
	public static <S,A extends InterfaceArcValue<S>> InterfaceEnsembleArcValue<S> intersection (InterfaceEnsembleArcValue<S> Ensemble1,InterfaceEnsembleArcValue<S> Ensemble2)
	{
		InterfaceEnsembleArcValue<S> intersection = Factory.ensembleArcValue(Ensemble1);
		for(InterfaceArcValue<S> arc : Ensemble1.getEnsemble())
		{
			if(!Ensemble2.existeArc(arc))
			{
				intersection.supprArc(arc);
			}
		}
		return intersection;
	}
	
}