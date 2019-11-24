package algorithme;

import java.util.HashMap;
import java.util.Stack;

import factory.Factory;
import graphElements.Interfaces.*;

public class CalculCFC
{
	private CalculCFC(){}
	
	public static <S,A extends InterfaceArc<S>> InterfaceCFC<S> Foulkes(InterfaceGraphe<S,A> G) 
	{
		//FermetureTransitive.Roy_Warshall(G);
		//Ici on considère que G est fermé transitivement
		InterfaceEnsembleSommet<S> ensembleSommet =Factory.ensembleSommet(G.getX());
		InterfaceEnsembleSommet<S> Pris = Factory.ensembleSommet();
		InterfaceEnsembleSommet<S> PasPris = Factory.ensembleSommet(ensembleSommet);
		InterfaceCFC<S>cfc = Factory.CFC(ensembleSommet);
		for (InterfaceSommet<S> x : ensembleSommet.getEnsemble())
		{
			if(!Pris.existeSommet(x))
			{
				PasPris.supprElement(x);
				if(G.existeBoucle(x))
				{
					for (InterfaceSommet<S> y : PasPris.getEnsemble())
					{
						if(G.existeArc(x, y)&&G.existeArc(y, x))
						{
						cfc.replace(x, (InterfaceEnsembleSommet<S>) InterfaceEnsemble.union(cfc.get(x), cfc.get(y)));
						}
					}
				}
				for(InterfaceSommet<S> y : cfc.get(x).getEnsemble())
				{
					PasPris.supprElement(x);
					cfc.replace(y, cfc.get(x));
				}
				Pris =(InterfaceEnsembleSommet<S>) InterfaceEnsemble.union(Pris, cfc.get(x));
			}
		}
		return cfc;
	}
	
	public static <S,A extends InterfaceArc<S>> InterfaceCFC<S> TarjanDFS (InterfaceGraphe<S,A> G)
	{
		InterfaceEnsembleSommet<S> D;//Ensemble des sommets non visités
		InterfaceEnsembleSommet<S> Y;
		InterfaceEnsembleSommet<S> Z;
		Stack<InterfaceSommet<S>> A=new Stack<InterfaceSommet<S>>(); 
		InterfaceCFC<S> CFC;
		HashMap<InterfaceSommet<S>,Integer> pospile=new HashMap<InterfaceSommet<S>,Integer>();
		
		InterfaceSommet<S>y;
		InterfaceSommet<S>z;
		//Init
		CFC=Factory.CFC(G.getX());
		for(InterfaceSommet<S> sommet : G.getX().getEnsemble())
		{
			pospile.put(sommet, 0);
		}
		D=Factory.ensembleSommet(G.getX());
		InterfaceGraphe<S,A> Gavisiter=Factory.graphe(G);

		while(!D.isEmpty())
		{
			A.push(Gavisiter.pickSommet());
			pospile.replace(Gavisiter.pickSommet(), A.size());
			D.supprElement(Gavisiter.pickSommet());
			while(!A.isEmpty())
			{
				y=A.peek();
				Y=Gavisiter.listSucc(y);
				if(!Y.isEmpty())
				{
					z=Y.pickSommet();
					Gavisiter.supprArc(y, z);
					if (D.existeSommet(z))
					{
						D.supprElement(z);
						Z=Gavisiter.listSucc(z);
						if (!Z.isEmpty())
						{
							A.push(z);
							pospile.replace(z, A.size());
						}
					}
					else
					{
						//Pour le moment la répétition permet au tout de marcher
						for(int i = pospile.get(z);i<A.size();i++)//pospile.get(z) correspond à la position du sommet au dessus de z sur la pile
						{
							CFC.get(z).ajouteElement(A.get(i));
						}
						for(int i = pospile.get(z);i<A.size();i++)//pospile.get(z) correspond à la position du sommet au dessus de z sur la pile
						{
							CFC.memeCFC(z,A.get(i));
						}
					}
				}
				else
				{
					A.pop();
				}
			}
		}
		return CFC;
	}
}