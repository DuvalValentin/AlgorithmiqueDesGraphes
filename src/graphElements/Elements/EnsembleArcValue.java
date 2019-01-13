package graphElements.Elements;

import java.util.HashSet;
import graphElements.Abstract.AbstractEnsembleArc;
import graphElements.Interfaces.InterfaceEnsembleArcValue;

public class EnsembleArcValue<S> extends AbstractEnsembleArc<S,ArcValue<S>> implements InterfaceEnsembleArcValue<S> 
{
	//Constructeur
	public EnsembleArcValue(EnsembleArcValue<S> Ensemble)
	{
		for (ArcValue<S> arc : Ensemble.ensemble)
		{
			ajouteArc(new ArcValue<S>(arc));
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
		for(ArcValue<S> arcV : ensemble)
		{
			if (arcV.getDepart().equals(depart) && arcV.getArrivee().equals(arrivee))
			{
				cout=arcV.getCout();
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
		for(ArcValue<S> arcV : ensemble)
		{
			if (arcV.memeArc(arcS))
			{
				arcV.setValeur(cout.getValeur());
				succes=true;
				break;
			}
		}
		return succes;
	}
	//ajout et suppression d'élément
	@Override
	public void ajouteArc(ArcValue<S> arc)
	{
		super.ajouteArc(new ArcValue<S>(arc));
	}
	@Override
	public void ajouteArc(Sommet<S> depart,Sommet<S>arrivee,Cout value)
	{
		boolean absent=true;
		for(ArcValue<S> AV : ensemble)
		{
			if(AV.getDepart()==depart&&AV.getArrivee()==arrivee)
			{
				absent=false;
				break;
			}
		}
		if(absent)
		{
			ajouteArc(new ArcValue<S>(depart,arrivee,value));
		}
	}
	@Override
	public void supprArc(Sommet<S>depart,Sommet<S>arrivee)
	{
		Cout cout=getCout(depart,arrivee);
		if(cout!=null)
		{
			ArcValue<S> suppr=new ArcValue<S>(depart,arrivee,cout);
			supprArc(suppr);
		}
	}
	@Override
	public HashSet<ArcValue<S>> getEnsemble()
	{
		return new HashSet<ArcValue<S>>(ensemble);
	}
}