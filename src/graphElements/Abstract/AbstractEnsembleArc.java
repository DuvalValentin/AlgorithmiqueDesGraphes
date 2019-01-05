package graphElements.Abstract;

import graphElements.Elements.Arc;
import graphElements.Elements.EnsembleSommet;
import graphElements.Elements.Sommet;
import graphElements.Interfaces.InterfaceAbstractEnsembleArc;

public abstract class AbstractEnsembleArc<S,A extends Arc<S>> extends AbstractEnsemble<A> implements InterfaceAbstractEnsembleArc<S, A>
{
	//Constructeurs
	@SuppressWarnings("unchecked")
	public AbstractEnsembleArc(AbstractEnsemble<A> ensemble)
	{
		for (A arc : ensemble)
		{
			ajouteArc((A)arc.clone());
		}
	}
	public AbstractEnsembleArc()
	{
		super();
	}

	private static final long serialVersionUID = -4099925554493145279L;

	@Override
	public EnsembleSommet<S> listSucc(Sommet<S> sommet)
	{
		EnsembleSommet<S> XSucc=new EnsembleSommet<S>();
		for (Arc<S> arc : this)
		{
			if (arc.getDepart().equals(sommet))
			{
				XSucc.add(arc.getArrivee());
			}
		}
		return XSucc;
	}

	@Override
	public EnsembleSommet<S> listPred(Sommet<S> sommet)
	{
		EnsembleSommet<S> XPred=new EnsembleSommet<S>();
		for (Arc<S> arc : this)
		{
			if (arc.getArrivee().equals(sommet))
			{
				XPred.add(arc.getDepart());
			}
		}
		return XPred;
	}
	
	@Override
	public boolean existeArc(Arc<S> arc)
	{
		return contains(arc);
	}
	
	@Override
	public boolean existeArc(Sommet<S> arrivee, Sommet<S> depart)
	{
		Arc<S> arc=new Arc<S>(arrivee,depart);
		return existeArc(arc);
	}
	@Override
	public boolean existeBoucle()
	{
		boolean resultat=false;
		for(A Arc : this)
		{
			if(Arc.getDepart().equals(Arc.getArrivee()))
			{
				resultat=true;
				break;
			}
		}
		return resultat;
	}
	
	@Override
	public boolean existeBoucle(Sommet<S> sommet)
	{
		Arc<S> arc = new Arc<S>(sommet,sommet);
		return existeArc(arc);
	}
	
	@Override
	public void ajouteArc(A arc)
	{
		add(arc);
	}
	@Override
	public void supprArc(A arc)
	{
		remove(arc);
	}
	//TODO mettre dans l'interface
	public void union(AbstractEnsembleArc<S,A> ensemble)
	{
		for(A arc : ensemble)
		{
			ajouteArc(arc);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public  AbstractEnsembleArc<S,A> clone ()
	{
		return (AbstractEnsembleArc<S,A>)super.clone();
	}
}