package graphelements.elements;

import java.util.HashMap;
import factory.Factory;
import graphelements.interfaces.CFC;
import graphelements.interfaces.Ensemble;
import graphelements.interfaces.EnsembleSommet;
import graphelements.interfaces.Sommet;

public class CFCImpl<S>extends HashMap<Sommet<S>,EnsembleSommet<S>> implements CFC<S>
{
	private static final long serialVersionUID=7529318242814868720L;

	// Constructeur
	public CFCImpl(EnsembleSommet<S> ensembleSommet)
	{
		for(Sommet<S> sommet : ensembleSommet.getEnsemble())
		{
			EnsembleSommet<S> composante=Factory.ensembleSommet();
			composante.ajouteElement(sommet);
			this.put(sommet,composante);
		}
	}
	// Quand deux sommet sont dans la même CFC on fait l'union de leur CFC
	public void memeCFC(Sommet<S> sommet1, Sommet<S> sommet2)
	{
		EnsembleSommet<S> ensembleSommet1=get(sommet1);
		EnsembleSommet<S> ensembleSommet2=get(sommet2);
		EnsembleSommet<S> union=(EnsembleSommet<S>)Ensemble.union(ensembleSommet1,ensembleSommet2);
		replace(sommet1,union);
		replace(sommet2,union);
	}
}