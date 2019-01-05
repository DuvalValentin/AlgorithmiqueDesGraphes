package graphElements.Interfaces;

import graphElements.Elements.Sommet;

public interface InterfaceArc<S> extends Cloneable
{
	//Getters et Setters
	public Sommet<S> getDepart();
	public Sommet<S> getArrivee();
	public void setDepart(Sommet<S> depart);
	public void setArrivee(Sommet<S> arrivee);
}
