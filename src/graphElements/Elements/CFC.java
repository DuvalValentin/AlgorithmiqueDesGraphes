package graphElements.Elements;

import java.util.HashMap;

public class CFC<S> extends HashMap<Sommet<S>,EnsembleSommet<S>>
{
	private static final long serialVersionUID = 7529318242814868720L;

	//Constructeur
	public CFC(EnsembleSommet<S> X)
	{
		for(Sommet<S> sommet : X.getEnsemble())
		{
			EnsembleSommet<S> composante=new EnsembleSommet<S>();
			composante.ajouteSommet(sommet);
			this.put(sommet,composante);
		}
	}
	
	//Quand deux sommet sont dans la même CFC on fait l'union de leur CFC
	public void memeCFC(Sommet<S> x, Sommet<S>y)
	{
		EnsembleSommet<S> X = get(x);
		EnsembleSommet<S> Y = get(y);
		X.union(Y);
		replace(x, X);
		replace(y, X);
	}
}