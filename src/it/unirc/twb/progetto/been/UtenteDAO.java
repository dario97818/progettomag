package it.unirc.twb.progetto.been;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import it.unirc.twb.progetto.been.utils.DBManager;

public class UtenteDAO {
	public Utente getUtente(Utente utente) {
		String query = "SELECT * FROM UTENTE WHERE id_utente =?";
		Utente res = null;
		PreparedStatement ps;
		Connection conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, utente.getId());
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				res=recordToUtente(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	} 
	
	public Vector<Utente> getUtenti(){
		String query = "SELECT * FROM UTENTE";
		Vector<Utente> res = new Vector<Utente>();
		PreparedStatement ps;
		Connection conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Utente utente=recordToUtente(rs);
				res.add(utente);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}
	public boolean salvaUtente(Utente utente){
		String query = "INSERT INTO UTENTE (NOME, COGNOME, EMAIL, PW, TELEFONO, INDIRIZZO, DATA_NASCITA, ATTIVO) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?)";
		boolean esito=false;
		PreparedStatement ps;
		Connection conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			
			ps.setString(1, utente.getNome() );
			ps.setString(2, utente.getCognome() );
			ps.setString(4, utente.getPass() );
			ps.setString(3, utente.getEmail() );
			ps.setString(5, utente.getTel() );
			ps.setString(6, utente.getIndirizzo() );
			ps.setDate(7, utente.getDatadinascita() );
			ps.setBoolean(8, true);
			int tmp=ps.executeUpdate();
			if (tmp==1)
				esito=true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return esito;
	} 
	public Integer Login(Utente utente) {
		String query = "SELECT * FROM UTENTE WHERE email =? AND pw=?";
		Integer res=null;
		PreparedStatement ps;
		Connection conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, utente.getEmail());
			ps.setString(2, utente.getPass());
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				res=rs.getInt(8);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	} 
	public boolean ControlloEmail(Utente utente) { //torna false se trova email uguali gia nel db true altrimenti
		String query = "SELECT * FROM UTENTE WHERE email =?";
		boolean res = true;
		PreparedStatement ps;
		Connection conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, utente.getEmail());
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				res=false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	} 
	private Utente recordToUtente(ResultSet rs) throws SQLException {
		Utente utente=new Utente();
		utente.setId(rs.getInt(8));
		utente.setNome(rs.getString(1));
		utente.setCognome(rs.getString(2));
		utente.setEmail(rs.getString(3));
		utente.setPass(rs.getString(4));
		utente.setTel(rs.getString(5));
		utente.setIndirizzo(rs.getString(6));
		utente.setDatadinascita(rs.getDate(7));
		utente.setAttivo(rs.getBoolean(9));
		return utente;
	}
	public boolean modificaUtente(Utente utente){
		String query = "UPDATE UTENTE SET NOME = ?, COGNOME = ?, EMAIL = ?, PW = ?, TELEFONO = ?, INDIRIZZO = ?, DATA_NASCITA = ?, ATTIVO = ? WHERE ID_UTENTE=?";
		boolean esito=false;
		PreparedStatement ps;
		Connection conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			
			ps.setString(1, utente.getNome() );
			ps.setString(2, utente.getCognome() );
			ps.setString(4, utente.getPass() );
			ps.setString(3, utente.getEmail() );
			ps.setString(5, utente.getTel() );
			ps.setString(6, utente.getIndirizzo() );
			ps.setDate(7, utente.getDatadinascita() );
			ps.setBoolean(8, utente.getAttivo());
			ps.setInt(9, utente.getId());
			int tmp=ps.executeUpdate();
			if (tmp==1)
				esito=true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return esito;
	}

}
