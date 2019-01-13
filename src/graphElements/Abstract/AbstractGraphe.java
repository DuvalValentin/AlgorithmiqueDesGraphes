package graphElements.Abstract;

import java.util.HashSet;

import graphElements.Elements.Arc;
import graphElements.Elements.EnsembleSommet;
import graphElements.Elements.Sommet;
import graphElements.Interfaces.InterfaceAbstractGraphe;

public abstract class AbstractGraphe<S,A extends Arc<S>> implements InterfaceAbstractGraphe<S, A>
{
	//Ensembles -------------------------------------------------------------
	protected EnsembleSommet<S> X;
	protected AbstractEnsembleArc<S,A> Gamma;
	//Constructeurs ---------------------------------------------------------
	public AbstractGraphe(){setX(new EnsembleSommet<S>());}
	public AbstractGraphe(EnsembleSommet<S> x,AbstractEnsembleArc<S,A> gamma)
	{
		setX(x);
		setGamma(gamma);
	}
	public AbstractGraphe(AbstractGraphe<S, A> G)
	{
		setX(G.getX());
		setGamma(G.getGamma());
	}
	//Getters ---------------------------------------------------------------
	@Override
	public EnsembleSommet<S> getX() 
	{
		return new EnsembleSommet<S>(X);
	}
	@Override
	public AbstractEnsembleArc<S, A> getGamma()
	{
		return Gamma;
	}
	//Setters --------------------------------------------------------------
	protected void setX(EnsembleSommet<S> x) 
	{
		X = new EnsembleSommet<S>(x);
	}
	protected void setGamma(AbstractEnsembleArc<S,A> gamma) 
	{
		if (correctGamma(gamma))
		{
			Gamma = gamma;
		}
		assert gamma.equals(Gamma) : "Le gamma passé en paramètre n'est pas correct";
	}
	//Existences d'arc et de sommets ---------------------------------------
	@Override
	public boolean existeSommet (Sommet<S> sommet)
	{
		return X.existeSommet(sommet);
	}
	@Override
	public boolean existeArc (A arc)
	{
		return Gamma.existeArc(arc);
	}
	@Override
	public boolean existeArc (Sommet<S> arrivee,Sommet<S> depart)
	{
		return Gamma.existeArc(arrivee, depart);
	}
	@Override
	public boolean existeBoucle()
	{
		return Gamma.existeBoucle();
	}
	@Override
	public boolean existeBoucle(Sommet<S> sommet)
	{
		return Gamma.existeArc(sommet,sommet);
	}
	//Vérification des éléments -------------------------------------------
	@Override
	public boolean ajoutableArc(A arc)
	{
		boolean ajoutable;
		if(existeSommet(arc.getArrivee()) && existeSommet(arc.getDepart()))
		{
			if(null!=Gamma)
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
	public boolean correctGamma(AbstractEnsembleArc<S, A> gamma)
	{
		boolean result=true;
		
		for(A arc : gamma.ensemble)
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
		return X.ensemble.isEmpty()&&Gamma.ensemble.isEmpty();
	}
	//Selections d'éléments -----------------------------------------------
	//Selction d'un élément
	@Override
	public Sommet<S> firstSommet()
	{
		return X.firstSommet();
	}
	//Liste des prédecesseurs et successeurs
	@Override
	public EnsembleSommet<S> listSucc(Sommet<S> sommet)
	{
		return Gamma.listSucc(sommet);
	}
	@Override
	public EnsembleSommet<S> listPred(Sommet<S> sommet)
	{
		return Gamma.listPred(sommet);
	}
	//Liste des points d'entrées et de sorties
	@Override
	public EnsembleSommet<S> pointsEntree()
	{
		EnsembleSommet<S> Ent=new EnsembleSommet<S>(X);
		for(Sommet<S> S : X.ensemble)
		{
			for(A Arc : Gamma.ensemble)
			{
				if(Arc.getArrivee().equals(S))
				{
					Ent.supprSommet(S);
					break;
				}
			}
		}
		return Ent;
	}
	@Override
	public EnsembleSommet<S> pointsSortie()
	{
		EnsembleSommet<S> Sor=new EnsembleSommet<S>(X);
		for(Sommet<S> S : X.ensemble)
		{
			for(A Arc : Gamma.ensemble)
			{
				if(Arc.getDepart().equals(S))
				{
					Sor.supprSommet(S);
					break;
				}
			}
		}
		return Sor;
	}
	//Ajouts et suppressions d'éléments ----------------------------------------
	//Sommet
	@Override
	public void ajouteSommet(Sommet<S> sommet)
	{
		X.ajouteSommet(sommet);
	}
	@Override
	public void supprSommet(Sommet<S> sommet)
	{
		HashSet<A> ens=new HashSet<A>(Gamma.ensemble);
		for(A arc : ens)
		{
			if(arc.getDepart().equals(sommet) || arc.getArrivee().equals(sommet))
			{
				supprArc(arc);
			}
		}
		X.supprSommet(sommet);
	}
	//Arc
	@Override
	public void ajouteArc(A arc)
	{
		if (ajoutableArc(arc))
		{
			Gamma.ajouteArc(arc);
		}
	}
	@Override
	public void supprArc(A arc)
	{
		Gamma.supprArc(arc);
	}
	@Override
	public void supprArc(Sommet<S> depart, Sommet<S> arrivee)
	{
		Gamma.supprArc(depart, arrivee);
	}
	//Union
	@Override
	public void union(InterfaceAbstractGraphe<S, A> G)
	{
		X.union(G.getX());
		Gamma.union(G.getGamma());
	}
	//toString equals et hashCode ------------------------------------------------------------
	@Override
	public String toString()
	{
		return "["+X.toString()+" | "+Gamma.toString()+"]";
	}
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj)
	{
		boolean result=false;
		if(obj!=null)
		{
			if(obj.getClass()==getClass())
			{
				if(((AbstractGraphe<S,A>)obj).getX().equals(X)&&((AbstractGraphe<S,A>)obj).getGamma().equals(Gamma))
				{
					result=true;
				}
			}

		}
		return result;
	}
	@Override
	public int hashCode()
	{
		return X.hashCode()+Gamma.hashCode();
	}
}