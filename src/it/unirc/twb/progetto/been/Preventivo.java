package it.unirc.twb.progetto.been;

import java.sql.Date;

public class Preventivo {
	int Id_Piano;

	int Id_Richiedente;
	int N_mesi;
	float Prezzo_Bloccato;
	int Id_Preventivo;
	Date data;
	
	public Preventivo() {
		// TODO Auto-generated constructor stub
	}

	public int getId_Piano() {
		return Id_Piano;
	}

	public void setId_Piano(int id_Piano) {
		Id_Piano = id_Piano;
	}



	

	

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public int getN_mesi() {
		return N_mesi;
	}

	public void setN_mesi(int n_mesi) {
		N_mesi = n_mesi;
	}

	public float getPrezzo_Bloccato() {
		return Prezzo_Bloccato;
	}

	public void setPrezzo_Bloccato(float prezzo_Bloccato) {
		Prezzo_Bloccato = prezzo_Bloccato;
	}

	public int getId_Preventivo() {
		return Id_Preventivo;
	}

	public void setId_Preventivo(int id_Preventivo) {
		Id_Preventivo = id_Preventivo;
	}

	

	@Override
	public String toString() {
		return "Preventivo [Id_Piano=" + Id_Piano + ", Id_Richiedente=" + Id_Richiedente + ", N_mesi=" + N_mesi
				+ ", Prezzo_Bloccato=" + Prezzo_Bloccato + ", Id_Preventivo=" + Id_Preventivo + "]";
	}

	public int getId_Richiedente() {
		return Id_Richiedente;
	}

	public void setId_Richiedente(int id_Richiedente) {
		Id_Richiedente = id_Richiedente;
	}

	public Preventivo(int id_Piano, int id_Richiedente, int n_mesi, float prezzo_Bloccato,
			int id_Preventivo) {
		super();
		Id_Piano = id_Piano;

		Id_Richiedente = id_Richiedente;
		N_mesi = n_mesi;
		Prezzo_Bloccato = prezzo_Bloccato;
		Id_Preventivo = id_Preventivo;
	}

	
	
}
