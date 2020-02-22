package graphelements.interfaces;

import java.util.Map;

public interface CFC<S>extends Map<Sommet<S>,EnsembleSommet<S>>
{
	void memeCFC(Sommet<S> x, Sommet<S> y);
	// void replace(InterfaceSommet<S> s, InterfaceEnsembleSommet<S> es);
}
