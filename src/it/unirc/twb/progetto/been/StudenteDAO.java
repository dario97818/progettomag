package it.unirc.twb.progetto.been;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import it.unirc.twb.progetto.been.utils.DBManager;

public class StudenteDAO {
	public Studente getStudente(Studente studente) {
		String query = "SELECT * FROM STUDENTE WHERE matricola =?";
		Studente res = null;
		PreparedStatement ps;
		Connection conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, studente.getMatricola());
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				res=recordToStudente(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	} 
	private Studente recordToStudente(ResultSet rs) throws SQLException {
		Studente studente=new Studente();
		studente.setMatricola(rs.getInt(1));
		studente.setNome(rs.getString(2));
		studente.setCognome(rs.getString(3));
		studente.setPassword(rs.getString(4));
		return studente;
	}
	public Vector<Studente> getStudenti(){
		String query = "SELECT * FROM STUDENTE order by matricola";
		Vector<Studente> res = new Vector<Studente>();
		PreparedStatement ps;
		Connection conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Studente studente=recordToStudente(rs);
				res.add(studente);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}
	public boolean salvaStudente(Studente studente){
		String query = "INSERT INTO STUDENTE VALUES ( ?, ?, ?, ?)";
		boolean esito=false;
		PreparedStatement ps;
		Connection conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, studente.getMatricola());
			ps.setString(2, studente.getNome() );
			ps.setString(3, studente.getCognome() );
			ps.setString(4, studente.getPassword() );
			int tmp=ps.executeUpdate();
			if (tmp==1)
				esito=true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return esito;
	} 
	public boolean Login(Studente studente) {
		String query = "SELECT * FROM STUDENTE WHERE nome =? AND password=?";
		boolean res = false;
		PreparedStatement ps;
		Connection conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, studente.getNome());
			ps.setString(2, studente.getPassword());
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				res=true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	} 

}
