package graphelements.abstracts;

import java.util.HashSet;

import factory.Factory;
import graphelements.interfaces.InterfaceArc;
import graphelements.interfaces.InterfaceEnsembleArc;
import graphelements.interfaces.InterfaceEnsembleSommet;
import graphelements.interfaces.InterfaceGraphe;
import graphelements.interfaces.InterfaceSommet;

public abstract class AbstractGraphe<S,A extends InterfaceArc<S>> implements InterfaceGraphe<S,A>
{
	//Ensembles -------------------------------------------------------------
	protected InterfaceEnsembleSommet<S> ensembleSommet;
	protected InterfaceEnsembleArc<S,A> gamma;
	//Constructeurs ---------------------------------------------------------
	public AbstractGraphe(){setX(Factory.ensembleSommet());}
	public AbstractGraphe(InterfaceEnsembleSommet<S> ensembleSommet,InterfaceEnsembleArc<S,A> gamma)
	{
		setX(ensembleSommet);
		setGamma(gamma);
	}
	public AbstractGraphe(InterfaceGraphe<S,A> graphe)
	{
		setX(graphe.getEnsembleSommet());
		setGamma(graphe.getGamma());
	}
	//Getters ---------------------------------------------------------------
	@Override
	public InterfaceEnsembleSommet<S> getEnsembleSommet() 
	{
		return Factory.ensembleSommet(ensembleSommet);
	}
	@Override
	public InterfaceEnsembleArc<S,A> getGamma()
	{
		return Factory.ensembleArc(gamma);
	}
	//Setters --------------------------------------------------------------
	protected void setX(InterfaceEnsembleSommet<S> ensembleSommet) 
	{
		this.ensembleSommet = Factory.ensembleSommet(ensembleSommet);
	}
	protected void setGamma(InterfaceEnsembleArc<S,A> gamma)
	{
		if (correctGamma(gamma))
		{
			this.gamma = gamma;
		}
	}
	//Existences d'arc et de sommets ---------------------------------------
	@Override
	public boolean existeSommet (InterfaceSommet<S> sommet)
	{
		return ensembleSommet.existeSommet(sommet);
	}
	@Override
	public boolean existeArc (InterfaceArc<S> arc)
	{
		return gamma.existeArc(arc);
	}
	@Override
	public boolean existeArc (InterfaceSommet<S> arrivee,InterfaceSommet<S> depart)
	{
		return gamma.existeArc(arrivee, depart);
	}
	@Override
	public boolean existeBoucle()
	{
		return gamma.existeBoucle();
	}
	@Override
	public boolean existeBoucle(InterfaceSommet<S> sommet)
	{
		return gamma.existeBoucle(sommet);
	}
	//Vérification des éléments -------------------------------------------
	@Override
	public boolean ajoutableArc(A arc)
	{
		boolean ajoutable;
		if(existeSommet(arc.getArrivee()) && existeSommet(arc.getDepart()))
		{
			if(null!=gamma)
			{
				ajoutable= !existeArc(arc);
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
	public boolean correctGamma(InterfaceEnsembleArc<S,A> gamma)
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
	//Selections d'éléments -----------------------------------------------
	//Selction d'un élément
	@Override
	public InterfaceSommet<S> pickSommet()
	{
		return ensembleSommet.pickSommet();
	}
	//Liste des prédecesseurs et successeurs
	@Override
	public InterfaceEnsembleSommet<S> listSucc(InterfaceSommet<S> sommet)
	{
		return gamma.listSucc(sommet);
	}
	@Override
	public InterfaceEnsembleSommet<S> listPred(InterfaceSommet<S> sommet)
	{
		return gamma.listPred(sommet);
	}
	//Liste des points d'entrées et de sorties
	@Override
	public InterfaceEnsembleSommet<S> pointsEntree()
	{
		InterfaceEnsembleSommet<S> ensembleEntree=Factory.ensembleSommet(ensembleSommet);
		for(InterfaceSommet<S> ensemble : ensembleSommet.getEnsemble())
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
	public InterfaceEnsembleSommet<S> pointsSortie()
	{
		InterfaceEnsembleSommet<S> ensembleSortie=Factory.ensembleSommet(ensembleSommet);
		for(InterfaceSommet<S> ensemble : ensembleSommet.getEnsemble())
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
	//Ajouts et suppressions d'éléments ----------------------------------------
	//Sommet
	@Override
	public void ajouteSommet(InterfaceSommet<S> sommet)
	{
		ensembleSommet.ajouteElement(sommet);
	}
	@Override
	public void ajouteSommet(S id)
	{
		ensembleSommet.ajouteSommet(id);
	}
	@Override
	public void supprSommet(InterfaceSommet<S> sommet)
	{
		HashSet<A> ens=new HashSet<>(gamma.getEnsemble());
		for(A arc : ens)
		{
			if(arc.getDepart().equals(sommet) || arc.getArrivee().equals(sommet))
			{
				supprArc(arc);
			}
		}
		ensembleSommet.supprElement(sommet);
	}
	//Arc
	@Override
	public void ajouteArc(A arc)
	{
		if (ajoutableArc(arc))
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
	public void supprArc(InterfaceSommet<S> depart, InterfaceSommet<S> arrivee)
	{
		gamma.supprElement(depart, arrivee);
	}
	//toString equals et hashCode ------------------------------------------------------------
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