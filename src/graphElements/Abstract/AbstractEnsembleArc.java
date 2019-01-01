package graphElements.Abstract;

import graphElements.Elements.Arc;
import graphElements.Elements.EnsembleSommet;
import graphElements.Elements.Sommet;
import graphElements.Interfaces.InterfaceAbstractEnsembleArc;

public abstract class AbstractEnsembleArc<S,A extends Arc<S>> extends AbstractEnsemble<A> implements InterfaceAbstractEnsembleArc<S, A>
{
	//Constructeurs
	public AbstractEnsembleArc(AbstractEnsemble<A> ensemble)
	{
		super(ensemble);
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
			if (arc.getDepart()==sommet)
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
			if (arc.getArrivee()==sommet)
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
	public void ajouteArc(A arc)
	{
		add(arc);
	}
	@Override
	public void supprArc(A arc)
	{
		remove(arc);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public  AbstractEnsembleArc<S,A> clone ()
	{
		return (AbstractEnsembleArc<S,A>)super.clone();
	}
}