package algorithme;

import java.util.LinkedList;
import java.util.Stack;
import factory.Factory;
import graphelements.interfaces.*;

public class Parcours
{
	private Parcours()
	{}
	@SuppressWarnings("unchecked")
	public static <S,A extends Arc<S>> void dfs(Graphe<S,A> graphe)
	{
		EnsembleArc<S,A> ensembleArcVisites;
		EnsembleSommet<S> ensembleSommetNonVisites;
		EnsembleSommet<S> ensembleSommetVisites=Factory.ensembleSommet();
		EnsembleSommet<S> successeursY;
		EnsembleSommet<S> successeursZ;
		Stack<Sommet<S>> pileSommet=new Stack<>();
		Sommet<S> y;
		Sommet<S> z;
		// Init
		ensembleSommetNonVisites=Factory.ensembleSommet(graphe.getEnsembleSommet());// Ensemble
																																								// de
																																								// sommets
																																								// non
																																								// visité
		ensembleArcVisites=Factory.ensembleArc(graphe.getGamma().getClass().getSimpleName());
		Graphe<S,A> graphAVisiter=Factory.graphe(graphe);
		while(!ensembleSommetNonVisites.isEmpty())
		{
			pileSommet.push(graphAVisiter.pickSommet());
			ensembleSommetNonVisites.supprElement(graphAVisiter.pickSommet());
			while(!pileSommet.isEmpty())
			{
				y=pileSommet.peek();
				successeursY=graphAVisiter.listSucc(y);
				if(!successeursY.isEmpty())
				{
					z=successeursY.pickSommet();
					A arc=(A)Factory.arc(graphe.getClass().getSimpleName(),y,z);
					graphAVisiter.supprArc(arc);
					ensembleArcVisites.ajouteElement(arc);
					if(ensembleSommetNonVisites.existeSommet(z))
					{
						ensembleSommetNonVisites.supprElement(z);
						successeursZ=graphAVisiter.listSucc(z);
						if(!successeursZ.isEmpty())
						{
							pileSommet.push(z);
						}
						else
						{
							ensembleSommetVisites.ajouteElement(z);
						}
					}
				}
				else
				{
					graphAVisiter.supprSommet(y);
					ensembleSommetVisites.ajouteElement(y);
					pileSommet.pop();
				}
			}
		}
	}
	@SuppressWarnings("unchecked")
	public static <S,A extends Arc<S>> void wfs(Graphe<S,A> graphe, Sommet<S> sommet)
	{
		EnsembleArc<S,A> ensembleArcVisites;// Ensemble des arcs parcours
		EnsembleSommet<S> ensembleSommetNonVisites;// Ensemble des sommets non
																								// visités
		EnsembleSommet<S> ensembleSommetVisites;// Ensemble des sommets visités
		EnsembleSommet<S> successeursY;// Liste de successeur de y
		LinkedList<Sommet<S>> fileSommets=new LinkedList<>();// File
		Sommet<S> y;
		// Init
		ensembleSommetNonVisites=Factory.ensembleSommet(graphe.getEnsembleSommet());
		ensembleSommetNonVisites.supprElement(sommet);
		fileSommets.add(sommet);
		ensembleSommetVisites=Factory.ensembleSommet();
		ensembleArcVisites=Factory.ensembleArc(graphe.getGamma().getClass().getSimpleName());
		while(!fileSommets.isEmpty())
		{
			y=fileSommets.poll();
			ensembleSommetVisites.ajouteElement(y);
			successeursY=graphe.listSucc(y);
			for(Sommet<S> z : successeursY.getEnsemble())
			{
				A arc=(A)Factory.arc(graphe.getClass().getSimpleName(),y,z);
				ensembleArcVisites.ajouteElement(arc);
				if(ensembleSommetNonVisites.existeSommet(z))
				{
					ensembleSommetNonVisites.supprElement(z);
					fileSommets.add(z);
				}
			}
		}
	}
}