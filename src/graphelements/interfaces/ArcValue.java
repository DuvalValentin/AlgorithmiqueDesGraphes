package graphelements.interfaces;

public interface ArcValue<S>extends Arc<S>, Cout
{
	// Un arc avec un attribut cout
	// Getter
	Cout getCout();// Renvoi une copie de cout
	// Pas de setCout => on se contente de modifier la valeur du coup avec
	// setValeur() (InterfaceCout)
}