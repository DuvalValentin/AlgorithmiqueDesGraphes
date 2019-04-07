package graphElements.Interfaces;

public interface InterfaceArcValue<S> extends InterfaceArc<S>, InterfaceCout
{
	//Un arc avec un attribut cout
	//Getter
	InterfaceCout getCout();//Renvoi une copie de cout
	//void setCout(float cout);
	//Pas de setCout => on se contente de modifier la valeur du coup avec setValeur() (InterfaceCout)
}