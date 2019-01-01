package graphElements.Elements;

import graphElements.Abstract.AbstractEnsembleArc;
import graphElements.Abstract.AbstractEnsemble;
import graphElements.Interfaces.InterfaceEnsembleArcValue;

public class EnsembleArcValue<S> extends AbstractEnsembleArc<S,ArcValue<S>> implements InterfaceEnsembleArcValue<S> 
{
	private static final long serialVersionUID = -7163498825360866323L;
	
	public EnsembleArcValue(AbstractEnsemble<ArcValue<S>> ensemble)
	{
		super(ensemble);
	}
	public EnsembleArcValue()
	{
		super();
	}
	
	@Override
	public void ajouteArc(Sommet<S> depart,Sommet<S>arrivee,Cout value)
	{
		boolean absent=true;
		for(ArcValue<S> AV : this)
		{
			if(AV.getDepart()==depart&&AV.getArrivee()==arrivee)
			{
				absent=false;
			}
		}
		if(absent)
		{
			add(new ArcValue<S>(depart,arrivee,value));
		}
	}
	
	
	@Override
	public boolean getCout(Sommet<S> depart, Sommet<S> arrivee,Cout result)
	{
		boolean succes=false;
		for(ArcValue<S> arcV : this)
		{
			if (arcV.getDepart()==depart && arcV.getArrivee()==arrivee)
			{
				result.setValeur(arcV.getCout().getValeur());
				succes=true;
				break;
			}
		}
		return succes;
	}
}