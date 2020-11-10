package it.unirc.twb.progetto.been;

public class Studente {
	private int matricola;
	private String nome;
	private String cognome;
	private String password;
	
	public Studente() {
		super();
	}
	public Studente(int matricola, String nome, String cognome, String password) {
		super();
		this.matricola = matricola;
		this.nome = nome;
		this.cognome = cognome;
		this.password = password;
	}
	public int getMatricola() {
		return matricola;
	}
	public void setMatricola(int matricola) {
		this.matricola = matricola;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	@Override
	public String toString() {
		return "Studente [matricola=" + matricola + ", nome=" + nome + ", cognome=" + cognome + ", password=" + password
				+ "]";
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	
	
}
