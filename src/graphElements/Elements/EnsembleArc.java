package graphElements.Elements;

import java.util.HashSet;
import graphElements.Abstract.AbstractEnsemble;
import graphElements.Abstract.AbstractEnsembleArc;
import graphElements.Interfaces.InterfaceEnsembleArc;

public class EnsembleArc<S> extends AbstractEnsembleArc<S,Arc<S>> implements InterfaceEnsembleArc<S>
{
	//Constructeur
	public EnsembleArc(EnsembleArc<S> Ensemble)
	{
		for (Arc<S> arc : Ensemble.ensemble)
		{
			ajouteArc(new Arc<S>(arc));
		}
	}
	public EnsembleArc()
	{
		super();
	}
	//Getter
	@Override
	public HashSet<Arc<S>> getEnsemble()
	{
		return new HashSet<Arc<S>>(ensemble);
	}
	//Ajout et suppression d'éléments
	@Override
	public void ajouteArc(Arc<S> arc)
	{
		super.ajouteArc(new Arc<S>(arc));
	}
	@Override
	public void ajouteArc(Sommet<S> depart,Sommet<S>arrivee)
	{
		ajouteArc(new Arc<S>(depart,arrivee));
	}
	@Override
	public void supprArc(Sommet<S> depart, Sommet<S> arrivee)
	{
		supprArc(new Arc<S>(depart,arrivee));
	}
	//TODO pas sur que se soit vraiment bon
	@Override
	public void intersection(AbstractEnsemble<Arc<S>> Ensemble)
	{
		EnsembleArc<S> copie = new EnsembleArc<S>(this);
		for(Arc<S> arc : copie.getEnsemble())
		{
			if(Ensemble.getEnsemble().contains(arc))
			{
				this.supprArc(arc);
			}
		}
	}
}