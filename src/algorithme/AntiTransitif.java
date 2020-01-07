package algorithme;

import factory.Factory;
import graphelements.interfaces.*;

public class AntiTransitif
{
	private AntiTransitif(){}
	
	public static <S> InterfaceGrapheNonValue<S> tauMinalite(InterfaceGrapheNonValue<S> graphe)
	{
		InterfaceEnsembleSommet<S>ensembleSommet=graphe.getEnsembleSommet();
		InterfaceGrapheNonValue<S> royWarshall=Factory.grapheNonValue(graphe);
		InterfaceGrapheNonValue<S> tauMin=Factory.grapheNonValue(graphe);//Graphe tauMinimal
		
		for(InterfaceSommet<S> z : ensembleSommet.getEnsemble()) 
		{
			for(InterfaceSommet<S> x : ensembleSommet.getEnsemble())
			{
				if(graphe.existeArc(x,z))
				{
					for(InterfaceSommet<S> y : ensembleSommet.getEnsemble())
					{
						if(graphe.existeArc(z,y))
						{
							royWarshall.ajouteArc(x, y);
							tauMin.supprArc(x, y);
						}
					}
				}
			}
		}
		return tauMin;
	}
}
