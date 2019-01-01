package graphElements.Elements;

import graphElements.Abstract.AbstractEnsembleArc;
import graphElements.Abstract.AbstractEnsemble;
import graphElements.Interfaces.InterfaceEnsembleArc;

public class EnsembleArc<S> extends AbstractEnsembleArc<S,Arc<S>> implements InterfaceEnsembleArc<S>
{
	private static final long serialVersionUID = -8258552281988584936L;
	
	public EnsembleArc(AbstractEnsemble<Arc<S>> ensemble)
	{
		super(ensemble);
	}
	public EnsembleArc()
	{
		super();
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
	
	@Override
	public EnsembleArc<S> clone ()
	{
		return (EnsembleArc<S>)super.clone();
	}
}
