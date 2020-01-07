package algorithme;

import java.util.LinkedList;
import java.util.Stack;

import factory.Factory;
import graphelements.interfaces.*;

public class Parcours
{
	private Parcours(){}
	
	@SuppressWarnings("unchecked")
	public static <S,A extends InterfaceArc<S>> void dfs (InterfaceGraphe<S,A> graphe)
	{
		InterfaceEnsembleArc<S,A> ensembleArcVisites;
		InterfaceEnsembleSommet<S> ensembleSommetNonVisites;
		InterfaceEnsembleSommet<S> ensembleSommetVisites=Factory.ensembleSommet();
		InterfaceEnsembleSommet<S> successeursY;
		InterfaceEnsembleSommet<S> successeursZ;
		Stack<InterfaceSommet<S>> pileSommet=new Stack<>();
		InterfaceSommet<S>y;
		InterfaceSommet<S>z;
		//Init
		ensembleSommetNonVisites=Factory.ensembleSommet(graphe.getEnsembleSommet());//Ensemble de sommets non visité
		ensembleArcVisites=Factory.ensembleArc(graphe.getGamma().getClass().getSimpleName());
		
		InterfaceGraphe<S,A> graphAVisiter = Factory.graphe(graphe);

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
					A arc = (A) Factory.arc(graphe.getClass().getSimpleName(), y, z,Factory.cout());
					graphAVisiter.supprArc( arc);
					ensembleArcVisites.ajouteElement(arc);
					if (ensembleSommetNonVisites.existeSommet(z))
					{
						ensembleSommetNonVisites.supprElement(z);
						successeursZ=graphAVisiter.listSucc(z);
						if (!successeursZ.isEmpty())
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
	public static <S,A extends InterfaceArc<S>> void wfs (InterfaceGrapheNonValue<S> graphe, InterfaceSommet<S> sommet)
	{
		InterfaceEnsembleArc<S,A> ensembleArcVisites;//Ensemble des arcs parcours
		InterfaceEnsembleSommet<S> ensembleSommetNonVisites;//Ensemble des sommets non visités
		InterfaceEnsembleSommet<S> ensembleSommetVisites;//Ensemble des sommets visités
		InterfaceEnsembleSommet<S> successeursY;//Liste de successeur de y
		LinkedList<InterfaceSommet<S>> fileSommets=new LinkedList<>();//File
		InterfaceSommet<S>y;
		//Init
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
			for (InterfaceSommet<S> z : successeursY.getEnsemble())
			{
				A arc = (A) Factory.arc(graphe.getClass().getSimpleName(), y, z, Factory.cout());
				ensembleArcVisites.ajouteElement(arc);
				if (ensembleSommetNonVisites.existeSommet(z))
				{
					ensembleSommetNonVisites.supprElement(z);
					fileSommets.add(z);
				}
			}
		}
	}
}