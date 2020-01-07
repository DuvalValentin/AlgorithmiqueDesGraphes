package graphelements.interfaces;

public interface InterfaceSommet<S>
{
	//Un sommet contient un attribut id GÉNÉRIQUE
	//Getter et Setters
	S getId();//Renvoie une copie de id
	void setId(S id);//Change id
}
