package it.unirc.twb.progetto.been;

public class Piano {
	int Id_Piano;
	int Id_annuncio;
	int Max_mesi;
	int Perc_anticipo;
	public Piano() {
		// TODO Auto-generated constructor stub
	}
	public int getId_Piano() {
		return Id_Piano;
	}
	public void setId_Piano(int id_Piano) {
		Id_Piano = id_Piano;
	}
	public int getId_annuncio() {
		return Id_annuncio;
	}
	public void setId_annuncio(int id_annuncio) {
		Id_annuncio = id_annuncio;
	}
	public int getMax_mesi() {
		return Max_mesi;
	}
	public void setMax_mesi(int max_mesi) {
		Max_mesi = max_mesi;
	}
	public int getPerc_anticipo() {
		return Perc_anticipo;
	}
	public void setPerc_anticipo(int perc_anticipo) {
		Perc_anticipo = perc_anticipo;
	}
	@Override
	public String toString() {
		return "Piano [Id_Piano=" + Id_Piano + ", Id_annuncio=" + Id_annuncio + ", Max_mesi=" + Max_mesi
				+ ", Perc_anticipo=" + Perc_anticipo + "]";
	}
	public Piano(int id_Piano, int id_annuncio, int max_mesi, int perc_anticipo) {
		super();
		Id_Piano = id_Piano;
		Id_annuncio = id_annuncio;
		Max_mesi = max_mesi;
		Perc_anticipo = perc_anticipo;
	}
	
}
