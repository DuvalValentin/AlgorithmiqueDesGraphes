package graphelements.elements;

import java.util.HashMap;

import factory.Factory;
import graphelements.interfaces.InterfaceCFC;
import graphelements.interfaces.InterfaceEnsemble;
import graphelements.interfaces.InterfaceEnsembleSommet;
import graphelements.interfaces.InterfaceSommet;

public class CFC<S> extends HashMap<InterfaceSommet<S>,InterfaceEnsembleSommet<S>> implements InterfaceCFC<S>
{
	private static final long serialVersionUID = 7529318242814868720L;

	//Constructeur
	public CFC(InterfaceEnsembleSommet<S> ensembleSommet)
	{
		for(InterfaceSommet<S> sommet : ensembleSommet.getEnsemble())
		{
			InterfaceEnsembleSommet<S> composante=Factory.ensembleSommet();
			composante.ajouteElement(sommet);
			this.put(sommet,composante);
		}
	}
	
	//Quand deux sommet sont dans la même CFC on fait l'union de leur CFC
	public void memeCFC(InterfaceSommet<S> sommet1, InterfaceSommet<S>sommet2)
	{
		InterfaceEnsembleSommet<S> ensembleSommet1 = get(sommet1);
		InterfaceEnsembleSommet<S> ensembleSommet2 = get(sommet2);
		InterfaceEnsembleSommet<S> union=(InterfaceEnsembleSommet<S>) InterfaceEnsemble.union(ensembleSommet1, ensembleSommet2);
		replace(sommet1, union);
		replace(sommet2, union);
	}
}