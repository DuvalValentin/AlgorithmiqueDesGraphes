package graphelements.interfaces;

import java.util.Map;

public interface InterfaceCFC<S> extends Map<InterfaceSommet<S>,InterfaceEnsembleSommet<S>>
{
	void memeCFC(InterfaceSommet<S> x, InterfaceSommet<S>y);
	//void replace(InterfaceSommet<S> s, InterfaceEnsembleSommet<S> es);
}
