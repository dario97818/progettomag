package it.unirc.twb.progetto.been;

import java.io.File;
import java.sql.Blob;
import java.util.Date;

public class Annuncio {
	int id_annuncio;
	int id_utente;
	String marca;
	String modello;
	File img;
	float prezzo;
	String titolo;
	String carburante;
	int cilindrata;
	int km;
	Date data_inserimento;
	String tipologia;
	
	
	@Override
	public String toString() {
		return "Annuncio [id_annuncio=" + id_annuncio + ", id_utente=" + id_utente + ", marca=" + marca + ", modello="
				+ modello + ", prezzo=" + prezzo + ", titolo=" + titolo + ", carburante=" + carburante
				+ ", cilindrata=" + cilindrata + ", km=" + km + ", data_inserimento=" + data_inserimento
				+ ", tipologia=" + tipologia + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id_annuncio;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Annuncio other = (Annuncio) obj;
		if (id_annuncio != other.id_annuncio)
			return false;
		return true;
	}
	public Annuncio() {
		super();
	}
	public Annuncio(int id_annuncio, int id_utente, String marca, String modello, File img, float prezzo,
			String titolo, String carburante, int cilindrata, int km, Date data_inserimento, String tipologia) {
		super();
		this.id_annuncio = id_annuncio;
		this.id_utente = id_utente;
		this.marca = marca;
		this.modello = modello;
		this.img = img;
		this.prezzo = prezzo;
		this.titolo = titolo;
		this.carburante = carburante;
		this.cilindrata = cilindrata;
		this.km = km;
		this.data_inserimento = data_inserimento;
		this.tipologia = tipologia;
	}
	public int getId_annuncio() {
		return id_annuncio;
	}
	public void setId_annuncio(int id_annuncio) {
		this.id_annuncio = id_annuncio;
	}
	public int getId_utente() {
		return id_utente;
	}
	public void setId_utente(int id_utente) {
		this.id_utente = id_utente;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getModello() {
		return modello;
	}
	public void setModello(String modello) {
		this.modello = modello;
	}
	public File getImg() {
		return img;
	}
	public void setImg(File img) {
		this.img = img;
	}
	public float getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}
	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	public String getCatburante() {
		return carburante;
	}
	public void setCarburante(String carburante) {
		this.carburante = carburante;
	}
	public int getCilindrata() {
		return cilindrata;
	}
	public void setCilindrata(int cilindrata) {
		this.cilindrata = cilindrata;
	}
	public int getKm() {
		return km;
	}
	public void setKm(int km) {
		this.km = km;
	}
	public Date getData_inserimento() {
		return data_inserimento;
	}
	public void setData_inserimento(Date data_inserimento) {
		this.data_inserimento = data_inserimento;
	}
	public String getTipologia() {
		return tipologia;
	}
	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
	}
	public void Pulisci() {
		img.delete();
	}
	public long getPrezzoLong() {
		long a=(long) prezzo;
		return a;
	}

}
