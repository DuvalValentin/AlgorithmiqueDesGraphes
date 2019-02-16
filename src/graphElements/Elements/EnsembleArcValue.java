package graphElements.Elements;

import java.util.HashSet;

import graphElements.Abstract.AbstractEnsembleArc;
import graphElements.Interfaces.InterfaceArcValue;
import graphElements.Interfaces.InterfaceEnsembleArcValue;

public class EnsembleArcValue<S> extends AbstractEnsembleArc<S,InterfaceArcValue<S>> implements InterfaceEnsembleArcValue<S> 
{
	//Constructeur
	public EnsembleArcValue(EnsembleArcValue<S> Ensemble)
	{
		for (InterfaceArcValue<S> arc : Ensemble.ensemble)
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
	public Cout getCout(Sommet<S> depart, Sommet<S> arrivee)
	{
		Cout cout=null;
		for(InterfaceArcValue<S> arcV : ensemble)
		{
			if (arcV.getDepart().equals(depart) && arcV.getArrivee().equals(arrivee))
			{
				cout=((ArcValue<S>) arcV).getCout();
				break;
			}
		}
		return cout;
	}
	//setter
	@Override
	public boolean setValeur(Sommet<S> depart, Sommet<S> arrivee,Cout cout)
	{
		boolean succes=false;
		ArcValue<S> arcS=new ArcValue<S>(depart,arrivee,cout);
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
		super.ajouteArc(new ArcValue<S>((ArcValue<S>) arc));//Le new permet de créer une nouvelle instance
	}
	@Override
	public void ajouteArc(Sommet<S> depart,Sommet<S>arrivee,Cout value)
	{
		ajouteArc(new ArcValue<S>(depart,arrivee,value));
	}
	@Override
	public void supprArc(Sommet<S>depart,Sommet<S>arrivee)
	{
		Cout cout=getCout(depart,arrivee);
		if(cout!=null)
		{
			supprArc(new ArcValue<S>(depart,arrivee,cout));
		}
	}
	@Override
	public HashSet<InterfaceArcValue<S>> getEnsemble()
	{
		return new HashSet<InterfaceArcValue<S>>(ensemble);
	}
	
	
	public static <S> EnsembleArcValue<S> union(EnsembleArcValue<S> Ensemble1,EnsembleArcValue<S> Ensemble2)
	{
		EnsembleArcValue<S> union = new EnsembleArcValue<S>(Ensemble1);
		for(InterfaceArcValue<S> arc : Ensemble2.getEnsemble())
		{
			union.ajouteArc(arc);
		}
		return union;
	}
	
	public static <S,A extends InterfaceArcValue<S>> EnsembleArcValue<S> intersection (EnsembleArcValue<S> Ensemble1,EnsembleArcValue<S> Ensemble2)
	{
		EnsembleArcValue<S> intersection = new EnsembleArcValue<S>(Ensemble1);
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