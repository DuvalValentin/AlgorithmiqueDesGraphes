package graphElements.Elements;

import java.util.HashMap;

public class CFC<S> extends HashMap<Sommet<S>,EnsembleSommet<S>>
{
	private static final long serialVersionUID = 7529318242814868720L;

	public CFC(EnsembleSommet<S> X)
	{
		for(Sommet<S> sommet : X)
		{
			EnsembleSommet<S> composante=new EnsembleSommet<S>();
			composante.ajouteSommet(sommet);
			this.put(sommet,composante);
		}
	}
}