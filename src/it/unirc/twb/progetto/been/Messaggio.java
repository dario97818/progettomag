package it.unirc.twb.progetto.been;

import java.sql.Timestamp;

public class Messaggio {
	int id_messaggio;
	int id_mittente;
	int id_annuncio;
	String messaggio;
	int id_destinatatio;
	boolean letto;
	Timestamp data;
	public Messaggio(int id_messaggio, int id_mittente, int id_annuncio, String messaggio, int id_destinatatio,
			boolean letto, Timestamp data) {
		super();
		this.id_messaggio = id_messaggio;
		this.id_mittente = id_mittente;
		this.id_annuncio = id_annuncio;
		this.messaggio = messaggio;
		this.id_destinatatio = id_destinatatio;
		this.letto = letto;
		this.data = data;
	}
	public Messaggio() {
		super();
	}
	public int getId_messaggio() {
		return id_messaggio;
	}
	public void setId_messaggio(int id_messaggio) {
		this.id_messaggio = id_messaggio;
	}
	public int getId_mittente() {
		return id_mittente;
	}
	public void setId_mittente(int id_mittente) {
		this.id_mittente = id_mittente;
	}
	public int getId_annuncio() {
		return id_annuncio;
	}
	public void setId_annuncio(int id_annuncio) {
		this.id_annuncio = id_annuncio;
	}
	public String getMessaggio() {
		return messaggio;
	}
	public void setMessaggio(String messaggio) {
		this.messaggio = messaggio;
	}
	public int getId_destinatatio() {
		return id_destinatatio;
	}
	public void setId_destinatatio(int id_destinatatio) {
		this.id_destinatatio = id_destinatatio;
	}
	public boolean isLetto() {
		return letto;
	}
	public void setLetto(boolean letto) {
		this.letto = letto;
	}
	public Timestamp getData() {
		return data;
	}
	public void setData(Timestamp data) {
		this.data = data;
	}

	
	

}
