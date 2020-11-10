package it.unirc.twb.progetto.been;
import java.awt.Dialog;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import it.unirc.twb.progetto.been.utils.DBManager;
import javafx.scene.control.Alert;
public class PianoDAO {
	
	
	
	public Piano getPianoFromAnnuncio(Annuncio annuncio) {
		String query = "SELECT * FROM PIANO_PREVENTIVO WHERE ID_ANNUNCIO =?";
		Piano res = null;
		PreparedStatement ps;
		Connection conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, annuncio.getId_annuncio());
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				res=recordToPiano(rs);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	} 
	
	
	
	

		public Piano getPiano(Piano piano) {
			String query = "SELECT * FROM PIANO_PREVENTIVO WHERE ID_PIANO =?";
			Piano res = null;
			PreparedStatement ps;
			Connection conn = DBManager.startConnection();
			try {
				ps = conn.prepareStatement(query);
				ps.setInt(1, piano.getId_Piano());
				ResultSet rs = ps.executeQuery();
				if(rs.next()){
					res=recordToPiano(rs);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DBManager.closeConnection();
			return res;
		} 
		private Piano recordToPiano(ResultSet rs) throws SQLException {
			Piano piano=new Piano();
			piano.setId_annuncio(rs.getInt(2));
			piano.setId_Piano(rs.getInt(1));
			piano.setMax_mesi(rs.getInt(4));
			piano.setPerc_anticipo(rs.getInt(3));
			return piano;
		}
		
		
		public Vector<Piano> getPiani(){
			String query = "SELECT * FROM PIANO_PREVENTIVO";
			Vector<Piano> res = new Vector<Piano>();
			PreparedStatement ps;
			Connection conn = DBManager.startConnection();
			try {
				ps = conn.prepareStatement(query);
				ResultSet rs = ps.executeQuery();
				while(rs.next()){
					Piano piano=recordToPiano(rs);
					res.add(piano);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DBManager.closeConnection();
			return res;
		}
		
		
		public Vector<Piano> getPianoFromUtente(int ID){
			String query = "SELECT * FROM PIANO_PREVENTIVO WHERE ID_ANNUNCIO IN (SELECT ANNUNCIO.ID_ANNUNCIO FROM ANNUNCIO WHERE ANNUNCIO.ID_UTENTE = ?)";
			Vector<Piano> res = new Vector<Piano>();
			PreparedStatement ps;
			Connection conn = DBManager.startConnection();
			try {
				ps = conn.prepareStatement(query);
				ps.setInt(1, ID);
				ResultSet rs = ps.executeQuery();
				while(rs.next()){
					Piano piano=recordToPiano(rs);
					res.add(piano);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DBManager.closeConnection();
			return res;
		}
		
		
		public boolean deletePiano(Piano piano){
			String query = "DELETE FROM PIANO_PREVENTIVO WHERE ID_PIANO =?";
			boolean esito=false;
			int ID =piano.getId_Piano();
			PreparedStatement ps;
			Connection conn = DBManager.startConnection();
			try {
				ps = conn.prepareStatement(query);
				ps.setInt(1, ID);
				
				int tmp=ps.executeUpdate();
				if (tmp==1)
					esito=true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DBManager.closeConnection();
			return esito;
		} 
		
		
		
		public boolean salvaPiano(Piano piano){
			String query = "INSERT INTO PIANO_PREVENTIVO (ID_ANNUNCIO, PERC_ANTICIPO, MAX_MESI) VALUES ( ?, ?, ?)";
			boolean esito=false;
			PreparedStatement ps;
			Connection conn = DBManager.startConnection();
			try {
				ps = conn.prepareStatement(query);
				ps.setInt(1, piano.getId_annuncio());
				ps.setInt(2, piano.getPerc_anticipo());
				ps.setInt(3, piano.getMax_mesi());
				int tmp=ps.executeUpdate();
				if (tmp==1)
					esito=true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DBManager.closeConnection();
			return esito;
		} 
		
	
	public PianoDAO() {
		// TODO Auto-generated constructor stub
	}

}
