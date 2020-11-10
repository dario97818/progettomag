package it.unirc.twb.progetto.been;

public class Amministratore {
int id_amministratore;
String pass;
public Amministratore(int id_amministratore, String pass) {
	super();
	this.id_amministratore = id_amministratore;
	this.pass = pass;
}

public int getId_amministratore() {
	return id_amministratore;
}

public void setId_amministratore(int id_amministratore) {
	this.id_amministratore = id_amministratore;
}

public String getPass() {
	return pass;
}

public void setPass(String pass) {
	this.pass = pass;
}



public Amministratore() {
	super();
	// TODO Auto-generated constructor stub
}

@Override
public String toString() {
	return "Amministratore [id_amministratore=" + id_amministratore + ", pass=" + pass + "]";
}
}
