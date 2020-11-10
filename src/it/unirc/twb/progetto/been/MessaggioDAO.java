package it.unirc.twb.progetto.been;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Vector;

import com.sun.jmx.snmp.Timestamp;

import it.unirc.twb.progetto.been.utils.DBManager;
import oracle.jdbc.internal.OracleTypes;

public class MessaggioDAO {
	public boolean NuovoMessaggio(Messaggio m) {
		boolean successo=false;
		String query = "INSERT INTO MESSAGGIO (ID_MITTENTE, ID_ANNUNCIO, MESSAGGIO, ID_DESTINATARIO, LETTO, DATA) VALUES (?,?,?,?,?,?)";
		PreparedStatement ps;
		Connection conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, m.getId_mittente());
			ps.setInt(2, m.getId_annuncio());
			ps.setString(3, m.getMessaggio());
			ps.setInt(4, m.getId_destinatatio());
			ps.setBoolean(5, false);
			Date date= new Date();

			long time = date.getTime();


			java.sql.Timestamp ts = new  java.sql.Timestamp(time);
			ps.setTimestamp(6, ts);
			int tmp = ps.executeUpdate();
			if (tmp==1)
				successo=true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return successo;
	}
	public int MessaggiNonLetti(Utente u) {
		String query = "{ ? = call numeromesnonletti(?) }";
		ResultSet rs;
		int res=0;
		Connection conn = DBManager.startConnection();
		try {
			CallableStatement nome;
			nome=conn.prepareCall(query);
			nome.setInt(2, u.getId());
			nome.registerOutParameter(1, OracleTypes.INTEGER);
			nome.execute();
			res=nome.getInt(1);

		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}
	public Vector<Messaggio> getMessaggiUtente(Utente u){
		String query = "SELECT * FROM messaggio where id_destinatario=? ORDER BY DATA DESC";
		Vector<Messaggio> res = new Vector<Messaggio>();
		PreparedStatement ps;
		Connection conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, u.getId());
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Messaggio m=recordToMessaggio(rs);
				res.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}
	public Messaggio getMessaggio(Messaggio me){
		String query = "SELECT * FROM messaggio where id_messaggio=?";
		Messaggio res =null;
		PreparedStatement ps;
		Connection conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, me.getId_messaggio());
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				res=recordToMessaggio(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}
	public boolean SegnaLetto(Messaggio m) {
		boolean successo=false;
		String query = "UPDATE MESSAGGIO SET LETTO=? WHERE ID_MESSAGGIO=?";
		PreparedStatement ps;
		Connection conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setBoolean(1, true);
			ps.setInt(2, m.getId_messaggio());
			int tmp = ps.executeUpdate();
			if (tmp==1)
				successo=true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return successo;
	}
	private Messaggio recordToMessaggio(ResultSet rs) throws SQLException {
		Messaggio m=new Messaggio();
		m.setId_messaggio(rs.getInt(1));
		m.setId_mittente(rs.getInt(2));
		m.setId_annuncio(rs.getInt(3));
		m.setMessaggio(rs.getString(4));
		m.setId_destinatatio(rs.getInt(5));
		m.setLetto(rs.getBoolean(6));
		m.setData(rs.getTimestamp(7));
		return m;
	}

}
