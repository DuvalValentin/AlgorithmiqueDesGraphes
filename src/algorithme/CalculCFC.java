package algorithme;

import java.util.HashMap;
import java.util.Stack;

import graphElements.Abstract.AbstractGraphe;
import graphElements.Elements.*;
import graphElements.Interfaces.InterfaceArc;

public class CalculCFC
{
	public static <S,A extends InterfaceArc<S>> CFC<S> Foulkes(AbstractGraphe<S,A> G) 
	{
		//FermetureTransitive.Roy_Warshall(G);
		//Ici on considère que G est fermé transitivement
		EnsembleSommet<S> ensembleSommet = G.getX();
		EnsembleSommet<S> Pris = new EnsembleSommet<S>();
		EnsembleSommet<S> PasPris = new EnsembleSommet<S>(ensembleSommet);
		CFC<S>cfc = new CFC<S>(ensembleSommet);
		for (Sommet<S> x : ensembleSommet.getEnsemble())
		{
			if(!Pris.existeSommet(x))
			{
				PasPris.supprSommet(x);
				if(G.existeBoucle(x))
				{
					for (Sommet<S> y : PasPris.getEnsemble())
					{
						if(G.existeArc(x, y)&&G.existeArc(y, x))
						{
						cfc.replace(x, EnsembleSommet.union(cfc.get(x), cfc.get(y)));
						}
					}
				}
				for(Sommet<S> y : cfc.get(x).getEnsemble())
				{
					PasPris.supprSommet(x);
					cfc.replace(y, cfc.get(x));
				}
				Pris =EnsembleSommet.union(Pris, cfc.get(x));
			}
		}
		return cfc;
	}
	
	public static <S,A extends Arc<S>> CFC<S> TarjanDFS (GrapheNonValue<S> G)
	{
		EnsembleSommet<S> D;//Ensemble des sommets non visités
		EnsembleSommet<S> Y;
		EnsembleSommet<S> Z;
		Stack<Sommet<S>> A=new Stack<Sommet<S>>(); 
		CFC<S> CFC;
		HashMap<Sommet<S>,Integer> pospile=new HashMap<Sommet<S>,Integer>();
		
		Sommet<S>y;
		Sommet<S>z;
		//Init
		CFC=new CFC<S>(G.getX());
		for(Sommet<S> sommet : G.getX().getEnsemble())
		{
			pospile.put(sommet, 0);
		}
		D=new EnsembleSommet<S>(G.getX());
		GrapheNonValue<S> Gavisiter=new GrapheNonValue<S>(G); //TODO à changer pour que TarjanDFS puisse prendre un AbstractGraph en entrée

		while(!D.isEmpty())
		{
			A.push(Gavisiter.firstSommet());
			pospile.replace(Gavisiter.firstSommet(), A.size());
			D.supprSommet(Gavisiter.firstSommet());
			while(!A.isEmpty())
			{
				y=A.peek();
				Y=Gavisiter.listSucc(y);
				if(!Y.isEmpty())
				{
					z=Y.firstSommet();
					Gavisiter.supprArc(y, z);
					if (D.existeSommet(z))
					{
						D.supprSommet(z);
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
							CFC.get(z).ajouteSommet(A.get(i));
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