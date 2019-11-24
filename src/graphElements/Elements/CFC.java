package graphElements.Elements;

import java.util.HashMap;

import factory.Factory;
import graphElements.Interfaces.InterfaceCFC;
import graphElements.Interfaces.InterfaceEnsemble;
import graphElements.Interfaces.InterfaceEnsembleSommet;
import graphElements.Interfaces.InterfaceSommet;

public class CFC<S> extends HashMap<InterfaceSommet<S>,InterfaceEnsembleSommet<S>> implements InterfaceCFC<S>
{
	private static final long serialVersionUID = 7529318242814868720L;

	//Constructeur
	public CFC(InterfaceEnsembleSommet<S> X)
	{
		for(InterfaceSommet<S> sommet : X.getEnsemble())
		{
			InterfaceEnsembleSommet<S> composante=Factory.ensembleSommet();
			composante.ajouteElement(sommet);
			this.put(sommet,composante);
		}
	}
	
	//Quand deux sommet sont dans la même CFC on fait l'union de leur CFC
	public void memeCFC(InterfaceSommet<S> x, InterfaceSommet<S>y)
	{
		InterfaceEnsembleSommet<S> X = get(x);
		InterfaceEnsembleSommet<S> Y = get(y);
		X=(InterfaceEnsembleSommet<S>) InterfaceEnsemble.union(X, Y);
		replace(x, X);
		replace(y, X);
	}
}