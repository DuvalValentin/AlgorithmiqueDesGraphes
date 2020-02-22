package graphelements.abstracts;

import java.util.HashSet;
import factory.Factory;
import graphelements.interfaces.Arc;
import graphelements.interfaces.EnsembleArc;
import graphelements.interfaces.EnsembleSommet;
import graphelements.interfaces.Graphe;
import graphelements.interfaces.Sommet;

public abstract class AbstractGraphe<S,A extends Arc<S>> implements Graphe<S,A>
{
	// Ensembles -------------------------------------------------------------
	protected EnsembleSommet<S> ensembleSommet;
	protected EnsembleArc<S,A> gamma;

	// Constructeurs ---------------------------------------------------------
	public AbstractGraphe()
	{
		setX(Factory.ensembleSommet());
	}
	public AbstractGraphe(EnsembleSommet<S> ensembleSommet, EnsembleArc<S,A> gamma)
	{
		setX(ensembleSommet);
		setGamma(gamma);
	}
	public AbstractGraphe(Graphe<S,A> graphe)
	{
		setX(graphe.getEnsembleSommet());
		setGamma(graphe.getGamma());
	}
	// Getters ---------------------------------------------------------------
	@Override
	public EnsembleSommet<S> getEnsembleSommet()
	{
		return Factory.ensembleSommet(ensembleSommet);
	}
	@Override
	public EnsembleArc<S,A> getGamma()
	{
		return Factory.ensembleArc(gamma);
	}
	// Setters --------------------------------------------------------------
	protected void setX(EnsembleSommet<S> ensembleSommet)
	{
		this.ensembleSommet=Factory.ensembleSommet(ensembleSommet);
	}
	protected void setGamma(EnsembleArc<S,A> gamma)
	{
		if(correctGamma(gamma))
		{
			this.gamma=gamma;
		}
	}
	// Existences d'arc et de sommets ---------------------------------------
	@Override
	public boolean existeSommet(Sommet<S> sommet)
	{
		return ensembleSommet.existeSommet(sommet);
	}
	@Override
	public boolean existeArc(Arc<S> arc)
	{
		return gamma.existeArc(arc);
	}
	@Override
	public boolean existeArc(Sommet<S> arrivee, Sommet<S> depart)
	{
		return gamma.existeArc(arrivee,depart);
	}
	@Override
	public boolean existeBoucle()
	{
		return gamma.existeBoucle();
	}
	@Override
	public boolean existeBoucle(Sommet<S> sommet)
	{
		return gamma.existeBoucle(sommet);
	}
	// Vérification des éléments -------------------------------------------
	@Override
	public boolean ajoutableArc(A arc)
	{
		boolean ajoutable;
		if(existeSommet(arc.getArrivee())&&existeSommet(arc.getDepart()))
		{
			if(null!=gamma)
			{
				ajoutable=!existeArc(arc);
			}
			else
			{
				ajoutable=true;
			}
		}
		else
		{
			ajoutable=false;
		}
		return ajoutable;
	}
	@Override
	public boolean correctGamma(EnsembleArc<S,A> gamma)
	{
		boolean result=true;
		for(A arc : gamma.getEnsemble())
		{
			if(!ajoutableArc(arc))
			{
				result=false;
			}
		}
		return result;
	}
	@Override
	public boolean isEmpty()
	{
		return ensembleSommet.getEnsemble().isEmpty();
	}
	// Selections d'éléments -----------------------------------------------
	// Selction d'un élément
	@Override
	public Sommet<S> pickSommet()
	{
		return ensembleSommet.pickSommet();
	}
	// Liste des prédecesseurs et successeurs
	@Override
	public EnsembleSommet<S> listSucc(Sommet<S> sommet)
	{
		return gamma.listSucc(sommet);
	}
	@Override
	public EnsembleSommet<S> listPred(Sommet<S> sommet)
	{
		return gamma.listPred(sommet);
	}
	// Liste des points d'entrées et de sorties
	@Override
	public EnsembleSommet<S> pointsEntree()
	{
		EnsembleSommet<S> ensembleEntree=Factory.ensembleSommet(ensembleSommet);
		for(Sommet<S> ensemble : ensembleSommet.getEnsemble())
		{
			for(A arc : gamma.getEnsemble())
			{
				if(arc.getArrivee().equals(ensemble))
				{
					ensembleEntree.supprElement(ensemble);
					break;
				}
			}
		}
		return ensembleEntree;
	}
	@Override
	public EnsembleSommet<S> pointsSortie()
	{
		EnsembleSommet<S> ensembleSortie=Factory.ensembleSommet(ensembleSommet);
		for(Sommet<S> ensemble : ensembleSommet.getEnsemble())
		{
			for(A arc : gamma.getEnsemble())
			{
				if(arc.getDepart().equals(ensemble))
				{
					ensembleSortie.supprElement(ensemble);
					break;
				}
			}
		}
		return ensembleSortie;
	}
	// Ajouts et suppressions d'éléments ----------------------------------------
	// Sommet
	@Override
	public void ajouteSommet(Sommet<S> sommet)
	{
		ensembleSommet.ajouteElement(sommet);
	}
	@Override
	public void ajouteSommet(S id)
	{
		ensembleSommet.ajouteSommet(id);
	}
	@Override
	public void supprSommet(Sommet<S> sommet)
	{
		HashSet<A> ens=new HashSet<>(gamma.getEnsemble());
		for(A arc : ens)
		{
			if(arc.getDepart().equals(sommet)||arc.getArrivee().equals(sommet))
			{
				supprArc(arc);
			}
		}
		ensembleSommet.supprElement(sommet);
	}
	// Arc
	@Override
	public void ajouteArc(A arc)
	{
		if(ajoutableArc(arc))
		{
			gamma.ajouteElement(arc);
		}
	}
	@Override
	public void supprArc(A arc)
	{
		gamma.supprElement(arc);
	}
	@Override
	public void supprArc(Sommet<S> depart, Sommet<S> arrivee)
	{
		gamma.supprElement(depart,arrivee);
	}
	// toString equals et hashCode
	// ------------------------------------------------------------
	@Override
	public String toString()
	{
		return "["+ensembleSommet.toString()+" | "+gamma.toString()+"]";
	}
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj)
	{
		boolean result=false;
		if(obj!=null&&obj.getClass()==getClass()&&((AbstractGraphe<S,A>)obj).getEnsembleSommet().equals(ensembleSommet)&&((AbstractGraphe<S,A>)obj).getGamma().equals(gamma))
		{
			result=true;
		}
		return result;
	}
	@Override
	public int hashCode()
	{
		return ensembleSommet.hashCode()+gamma.hashCode();
	}
}