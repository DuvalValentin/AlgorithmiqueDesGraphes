package factory;

import graphElements.Elements.*;
import graphElements.Interfaces.*;
@SuppressWarnings("unchecked")
public class Factory
{
	//Sommets
	public static <S> InterfaceSommet<S> sommet(InterfaceSommet<S> sommet)
	{
		return new Sommet<S>(sommet);
	}
	
	public static <S> InterfaceSommet<S> sommet(S value)
	{
		return new Sommet<S>(value);
	}
	
	//Arc
	//TODO améliorer méthodes de créations dynamiques de factory
	public static <S> InterfaceArc<S> arc(String className,InterfaceSommet<S> depart, InterfaceSommet<S> arrivee, InterfaceCout cout)
	{
		InterfaceArc<S> arc;
		if(className.equals("Arc")||className.equals("GrapheNonValue"))
		{
			arc = (InterfaceArc<S>) Factory.arcNonValue(depart,arrivee);
		}
		else //if (className.equals("ArcValue")||className.equals("GrapheValue"))
		{
			arc = (InterfaceArc<S>) Factory.arcValue(depart,arrivee,cout);
		}/*
		else
		{
			throw  new Exception("pas marché");
		}*/
		return arc;
	}
	
	public static <S> InterfaceArc<S> arcNonValue(InterfaceSommet<S> depart, InterfaceSommet<S> arrivee)
	{
		return new Arc<S>(depart,arrivee);
	}
	
	public static <S> InterfaceArc<S> arcNonValue(InterfaceArc<S> arc)
	{
		return new Arc<S>(arc);
	}
	
	public static <S> InterfaceArcValue<S> arcValue(InterfaceSommet<S> depart, InterfaceSommet<S> arrivee,InterfaceCout cout)
	{
		return new ArcValue<S>(depart,arrivee,cout);
	}

	public static <S> InterfaceArcValue<S> arcValue(InterfaceArcValue<S> arc)
	{
		return new ArcValue<S>(arc);
	}
	
	//EnsembleSommet
	public static <S> InterfaceEnsembleSommet<S> ensembleSommet()
	{
		return new EnsembleSommet<S>();
	}
	
	public static <S> InterfaceEnsembleSommet<S> ensembleSommet(InterfaceEnsembleSommet<S> ensembleSommet)
	{
		return new EnsembleSommet<S>(ensembleSommet);
	}
	
	//EnsembleArc
	public static <S,A extends InterfaceArc<S>> InterfaceEnsembleArc<S, A> ensembleArc (String className)
	{
		InterfaceEnsembleArc<S,A> ensembleArc;
		if(className.equals("EnsembleArcNonValue"))
		{
			ensembleArc = (InterfaceEnsembleArc<S,A>) Factory.ensembleArcNonValue();
		}
		else //if(className.equals("EnsembleArcValue"))
		{
			ensembleArc = (InterfaceEnsembleArc<S,A>) Factory.ensembleArcValue();
		}
		/*else
		{
			throw new Exception("pas marché");
		}*/
		return ensembleArc;
	}
	
	public static <S> InterfaceEnsembleArcNonValue<S> ensembleArcNonValue()
	{
		return new EnsembleArcNonValue<S>();
	}
	
	public static <S> InterfaceEnsembleArcNonValue<S> ensembleArcNonValue(InterfaceEnsembleArcNonValue<S> ensembleArcNonValue)
	{
		return new EnsembleArcNonValue<S>(ensembleArcNonValue);
	}
	
	public static <S, A extends InterfaceArc<S>> InterfaceEnsembleArc<S, A> ensembleArc(InterfaceEnsembleArc<S, A> gamma) //throws Exception
	{
		InterfaceEnsembleArc<S, A> ensembleArc;
		String className = gamma.getClass().getSimpleName();
		if(className.equals("EnsembleArcNonValue"))
		{
			ensembleArc = (InterfaceEnsembleArc<S,A>) Factory.ensembleArcNonValue((InterfaceEnsembleArcNonValue<S>) gamma);
		}
		else //if (className.equals("EnsembleArcValue"))
		{
			ensembleArc = (InterfaceEnsembleArc<S,A>) Factory.ensembleArcValue((InterfaceEnsembleArcValue<S>) gamma);
		}
		/*else
		{
			throw  new Exception("pas marché");
		}*/
		return ensembleArc;
	}
	
	public static <S> InterfaceEnsembleArcValue<S> ensembleArcValue(InterfaceEnsembleArcValue<S> ensembleArcValue)
	{
		return new EnsembleArcValue<S>(ensembleArcValue);
	}
	
	public static <S> InterfaceEnsembleArcValue<S> ensembleArcValue()
	{
		return new EnsembleArcValue<S>();
	}
	
	//Graphe
	public static <S,A extends InterfaceArc<S>>  InterfaceGraphe<S,A> graphe(String classGraph) //throws Exception
	{
		InterfaceGraphe<S,A> retour;
		if (classGraph.equals( "GrapheNonValue"))
		{
			retour = (InterfaceGraphe<S,A>) Factory.grapheNonValue();
		}
		else //if(classGraph.equals("GrapheValue"))
		{
			retour = (InterfaceGraphe<S,A>) Factory.grapheValue();
		}
		/*else
		{
			throw new Exception("pas maché");
		}*/
		return retour;
	}
	
	public static <S,A extends InterfaceArc<S>>  InterfaceGraphe<S,A> graphe(InterfaceGraphe<S,A> graphe)
	{
		InterfaceGraphe<S,A> retour;
		String classGraph = graphe.getClass().getSimpleName();
		if (classGraph.equals("GrapheNonValue"))
		{
			retour =(InterfaceGraphe<S,A>) Factory.grapheNonValue((GrapheNonValue<S>)graphe);
		}
		else //if (classGraph.equals("GrapheValue"))
		{
			retour =(InterfaceGraphe<S,A>) Factory.grapheValue((GrapheValue<S>)graphe);
		}
		/*else
		{
			throw new Exception("pas maché");
		}*/
		return retour;
	}
	
	public static <S,A extends InterfaceArc<S>> InterfaceGraphe<S,A> graphe(InterfaceEnsembleSommet<S>X,InterfaceEnsembleArc<S,A> Gamma)
	{
		InterfaceGraphe<S,A> retour;
		String classGamma = Gamma.getClass().getSimpleName();
		if(classGamma.equals("EnsembleArcNonValue"))
		{
			retour=(InterfaceGraphe<S, A>) Factory.grapheNonValue(X, (EnsembleArcNonValue<S>)Gamma);
		}
		else
		{
			retour=(InterfaceGraphe<S, A>) Factory.grapheValue(X, (EnsembleArcValue<S>)Gamma);
		}
		return retour;
	}
	
	public static <S> InterfaceGrapheNonValue<S> grapheNonValue(InterfaceEnsembleSommet<S> X, InterfaceEnsembleArcNonValue<S> Gamma)
	{
		return new GrapheNonValue<S>(X,Gamma);
	}
	
	public static <S> InterfaceGrapheNonValue<S> grapheNonValue(InterfaceGrapheNonValue<S> g)
	{
		return new GrapheNonValue<S>(g);
	}
	
	public static <S> InterfaceGrapheNonValue<S> grapheNonValue()
	{
		return new GrapheNonValue<S>();
	}
	
	public static <S> InterfaceGrapheValue<S> grapheValue(InterfaceGrapheValue<S> g)
	{
		return new GrapheValue<S>(g);
	}
	
	public static <S> InterfaceGrapheValue<S> grapheValue()
	{
		return new GrapheValue<S>();
	}
	public static <S> InterfaceGrapheValue<S> grapheValue(InterfaceEnsembleSommet<S> X, InterfaceEnsembleArcValue<S> Gamma)
	{
		return new GrapheValue<S>(X,Gamma);
	}
	//Cout
	
	public static InterfaceCout cout(InterfaceCout cout)
	{
		return new Cout(cout);
	}
	
	public static InterfaceCout cout(float valeur)
	{
		return new Cout(valeur);
	}
	
	public static InterfaceCout cout()
	{
		return new Cout();
	}
	//Autres
	public static <S> InterfaceTableauPlusCC<S> tableauPlusCC(InterfaceSommet<S>principal,InterfaceGrapheValue<S>G)
	{
		return new TableauPlusCC<S>(principal,G);
	}
	
	public static <S> InterfaceTableauPlusCC<S> tableauPlusCC(InterfaceSommet<S>principal)
	{
		return new TableauPlusCC<S>(principal);
	}
	
	public static <S> InterfaceCFC<S> CFC (InterfaceEnsembleSommet<S> X)
	{
		return new CFC<S>(X);
	}
	
	public static <E,S> InterfaceEnsemble<E> ensemble(InterfaceEnsemble<E> ensemble)
	{
		InterfaceEnsemble<E> ensembleRetour;
		String className = ensemble.getClass().getSimpleName();
		if(className.equals("EnsembleArcNonValue"))
		{
			ensembleRetour = (InterfaceEnsemble<E>) Factory.ensembleArcNonValue((InterfaceEnsembleArcNonValue<S>) ensemble);
		}
		else if (className.equals("EnsembleArcValue"))
		{
			ensembleRetour = (InterfaceEnsemble<E>) Factory.ensembleArcValue((InterfaceEnsembleArcValue<S>) ensemble);
		}
		else //if(className.equals("EnsembleSommet"))
		{
			ensembleRetour = (InterfaceEnsemble<E>) Factory.ensembleSommet((InterfaceEnsembleSommet<S>) ensemble);
		}
		return ensembleRetour;
	}
}