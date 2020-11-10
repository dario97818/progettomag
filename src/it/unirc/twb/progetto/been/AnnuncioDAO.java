package it.unirc.twb.progetto.been;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Vector;

import javax.imageio.ImageIO;

import it.unirc.twb.progetto.been.utils.DBManager;


//SELECT COUNT(*) AS NUMERO FROM ANNUNCIO WHERE ID_UTENTE=4



public class AnnuncioDAO {
	public Vector<Annuncio> ricercaAnnuncio(Annuncio an){
		String query = "SELECT * FROM ANNUNCIO WHERE UPPER(TITOLO) LIKE UPPER(?) AND TIPOLOGIA LIKE ?";
		Vector<Annuncio> res = new Vector<Annuncio>();
		PreparedStatement ps;
		Connection conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, "%" + an.getTitolo() + "%" );
			ps.setString(2, "%" + an.getTipologia() + "%" );

			ResultSet rs = ps.executeQuery();
			while(rs.next()){ 

				Annuncio a=recordToAnnuncio(rs);
				res.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}

	public int getAnnunciUtente(int ID){
		String query = "SELECT COUNT(*) AS NUMERO FROM ANNUNCIO WHERE ID_UTENTE=?";
		int risultato=0;
		PreparedStatement ps;
		Connection conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, ID );
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				risultato=rs.getInt("NUMERO");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return risultato;
	}

	public Vector<Annuncio> getAnnunci(){
		String query = "SELECT * FROM ANNUNCIO";
		Vector<Annuncio> res = new Vector<Annuncio>();
		PreparedStatement ps;
		Connection conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Annuncio a=recordToAnnuncio(rs);
				res.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}
	public Vector<Annuncio> getAnnunciUtente(Annuncio an){
		String query = "SELECT * FROM ANNUNCIO WHERE ID_UTENTE=?";
		Vector<Annuncio> res = new Vector<Annuncio>();
		PreparedStatement ps;
		Connection conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, an.getId_utente());
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Annuncio a=recordToAnnuncio(rs);
				res.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}
	
	
	public Vector<Annuncio> getAnnunciFomIdUtente(Utente utente){
		String query = "SELECT * FROM ANNUNCIO WHERE ID_UTENTE=?";
		Vector<Annuncio> res = new Vector<Annuncio>();
		PreparedStatement ps;
		Connection conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, utente.getId());
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Annuncio a=recordToAnnuncio(rs);
				res.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}
	
	
	
	public boolean salvaAnnuncio(Annuncio a){
		String query = "INSERT INTO ANNUNCIO (MARCA, MODELLO, IMMAGINI, PREZZO, TITOLO, CARBURANTE, CILINDRATA, KM, ID_UTENTE, DATA_CARICAMENTO, TIPOLOGIA) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		boolean esito=false;
		PreparedStatement ps;
		Connection conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);

			ps.setString(1, a.getMarca() );
			ps.setString(2, a.getModello() );
			FileInputStream fis=null;
			try {
				fis = new FileInputStream(a.getImg());
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ps.setBinaryStream(3, fis, (int) a.getImg().length());
			//ps.setBlob(4, a.getImg() );
			ps.setFloat(4, a.getPrezzo() );
			ps.setString(5, a.getTitolo() );
			ps.setString(6, a.getCatburante() );
			ps.setInt(7, a.getCilindrata() );
			ps.setInt(8, a.getKm() );
			ps.setInt(9, a.getId_utente() );
			Date date = new Date(System.currentTimeMillis());
			ps.setDate(10, date );
			ps.setString(11, a.getTipologia());
			int tmp=ps.executeUpdate();
			if (tmp==1)
				esito=true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return esito;
	} 
	public Annuncio getAnnuncio(Annuncio an){
		String query = "SELECT * FROM ANNUNCIO WHERE ID_ANNUNCIO=?";
		Annuncio res = new Annuncio();
		PreparedStatement ps;
		Connection conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, an.getId_annuncio());
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				res=recordToAnnuncio(rs);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}
	public boolean Delete(Annuncio a) {
		boolean successo=false;
		String query = "DELETE FROM ANNUNCIO WHERE ID_ANNUNCIO=?";
		PreparedStatement ps;
		Connection conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, a.getId_annuncio());
			int tmp = ps.executeUpdate();
			if (tmp==1)
				successo=true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return successo;
	} 
	private Annuncio recordToAnnuncio(ResultSet rs) throws SQLException {
		Annuncio a=new Annuncio();
		a.setId_annuncio(rs.getInt(1));
		a.setMarca(rs.getString(2));
		a.setModello(rs.getString(3));
		if(rs.getBlob(4)!=null)
		{
			InputStream in = rs.getBlob(4).getBinaryStream();  
			BufferedImage image = null;
			try {
				image = ImageIO.read(in);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			File outputfile = new File(a.getId_annuncio()+"_"+a.getId_utente());
			try {
				ImageIO.write(image, "png", outputfile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			a.setImg(outputfile);
			image.flush();
			outputfile.deleteOnExit();
		}
		else
		{
			a.setImg(null);
		}
		a.setPrezzo(rs.getFloat(5));
		a.setTitolo(rs.getString(6));
		a.setCarburante(rs.getString(7));
		a.setCilindrata(rs.getInt(8));
		a.setKm(rs.getInt(9));
		a.setId_utente(rs.getInt(10));
		a.setData_inserimento(rs.getDate(11));
		a.setTipologia(rs.getString(12));
		return a;
	}
	public int lastInsertAnnuncioFromIdUtente(int id){
		String query = "select max(ID_ANNUNCIO) FROM ANNUNCIO WHERE ID_UTENTE=?";
		int res = 0;
		PreparedStatement ps;
		Connection conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				res=rs.getInt(1);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}
}
