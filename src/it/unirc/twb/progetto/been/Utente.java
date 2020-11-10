package it.unirc.twb.progetto.been;

import java.sql.Date;

public class Utente {
	int id;
	String nome;
	String cognome;
	String email;
	String pass;
	String tel;
	String indirizzo;
	Date datadinascita;
	Boolean attivo;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Utente other = (Utente) obj;
		if (id != other.id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Utente [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", email=" + email
				+ ", pass=" + pass + ", tel=" + tel + ", indirizzo=" + indirizzo + ", datadinascita=" + datadinascita
				+ ", attivo=" + attivo + "]";
	}
	public Utente() {
		super();
	}
	public Utente(int id, String nome, String cognome, String email, String pass, String tel, String indirizzo,
			Date datadinascita, Boolean attivo) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.pass = pass;
		this.tel = tel;
		this.indirizzo = indirizzo;
		this.datadinascita = datadinascita;
		this.attivo = attivo;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getIndirizzo() {
		return indirizzo;
	}
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	public Date getDatadinascita() {
		return datadinascita;
	}
	public void setDatadinascita(Date datadinascita) {
		this.datadinascita = datadinascita;
	}
	public Boolean getAttivo() {
		return attivo;
	}
	public void setAttivo(Boolean attivo) {
		this.attivo = attivo;
	}

}
