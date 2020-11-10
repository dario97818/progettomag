package it.unirc.twb.progetto.been;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import it.unirc.twb.progetto.been.utils.DBManager;
public class AmministratoreDAO {
	public boolean Login(Amministratore a) {
		boolean successo=false;
		String query = "SELECT * FROM AMMINISTRATORE WHERE ID_AMMINISTRATORE=? AND PW=?";

		PreparedStatement ps;
		Connection conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, a.getId_amministratore());
			ps.setString(2, a.getPass());
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				successo=true;
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return successo;
	} 
	
	public boolean Create(Amministratore a) {
		boolean successo=false;
		String query = "INSERT INTO AMMINISTRATORE (PW) VALUES (?)";
		PreparedStatement ps;
		Connection conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, a.getPass());
			int tmp = ps.executeUpdate();
			if (tmp==1)
				successo=true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return successo;
	} 
	public Vector<Amministratore> getAmministratori(){
		String query = "SELECT * FROM AMMINISTRATORE order by ID_AMMINISTRATORE";
		Vector<Amministratore> res = new Vector<Amministratore>();
		PreparedStatement ps;
		Connection conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Amministratore admin=recordToAmministratore(rs);
				res.add(admin);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}
	public boolean Delete(Amministratore a) {
		boolean successo=false;
		String query = "DELETE FROM AMMINISTRATORE WHERE ID_AMMINISTRATORE=?";
		PreparedStatement ps;
		Connection conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, a.getId_amministratore());
			int tmp = ps.executeUpdate();
			if (tmp==1)
				successo=true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return successo;
	} 
	private Amministratore recordToAmministratore(ResultSet rs) throws SQLException {
		Amministratore a=new Amministratore();
		a.setId_amministratore(rs.getInt(1));
		a.setPass(rs.getString(2));
		return a;
	}
	

}
