package it.unirc.twb.progetto.been;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import it.unirc.twb.progetto.been.utils.DBManager;
import oracle.jdbc.OracleTypes;

public class PreventivoDAO {
	
	public Preventivo getPreventivo(Preventivo preventivo) {
		String query = "SELECT * FROM PREVENTIVO WHERE ID_PREVENTIVO =?";
		Preventivo res = null;
		PreparedStatement ps;
		Connection conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, preventivo.getId_Preventivo());
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				res=recordToPreventivo(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	} 
	private Preventivo recordToPreventivo(ResultSet rs) throws SQLException {
		Preventivo preventivo=new Preventivo();
		preventivo.setId_Richiedente(rs.getInt(2));
		preventivo.setId_Piano(rs.getInt(1));
		preventivo.setId_Preventivo(rs.getInt(5));
		preventivo.setData(rs.getDate(6));
		preventivo.setN_mesi(rs.getInt(3));
		preventivo.setPrezzo_Bloccato(rs.getInt(4));
		return preventivo;
	}
	
	
	public boolean deletePreventivo(Preventivo preventivo){
		String query = "DELETE FROM PREVENTIVO WHERE ID_PREVENTIVO =?";
		boolean esito=false;
		int ID =preventivo.getId_Preventivo();
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
	
	
	public Vector<Preventivo> getPreventivi(){
		String query = "SELECT * FROM PREVENTIVO";
		Vector<Preventivo> res = new Vector<Preventivo>();
		PreparedStatement ps;
		Connection conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Preventivo preventivo=recordToPreventivo(rs);
				res.add(preventivo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}
	
	public Vector<Preventivo> getPreventiviFromRichiedente(int ID){
		String query = "SELECT * FROM PREVENTIVO WHERE ID_RICHIEDENTE=?";
		Vector<Preventivo> res = new Vector<Preventivo>();
		PreparedStatement ps;
		Connection conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, ID);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Preventivo preventivo=recordToPreventivo(rs);
				res.add(preventivo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}
	
	public boolean salvaPreventivo(Preventivo preventivo){
		String query = "INSERT INTO PREVENTIVO (ID_PIANO, DATA, ID_RICHIEDENTE, N_MESI, PREZZO_BLOCCATO) VALUES ( ?, ?, ?, ?, ?)";
		boolean esito=false;
		PreparedStatement ps;
		Connection conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, preventivo.getId_Piano());
			ps.setDate(2, preventivo.getData());
			ps.setInt(3, preventivo.getId_Richiedente());
			ps.setInt(4, preventivo.getN_mesi());
			ps.setFloat(5, preventivo.getPrezzo_Bloccato());
			int tmp=ps.executeUpdate();
			if (tmp==1)
				esito=true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return esito;
	} 
	
		
	
	
	
	public int function_Calcola(int n_mesi, int id_piano, float prezzo_bloc) {
		Connection conn=DBManager.startConnection();
		int result=0;
		
		String query="{? = call FUNCTION_CALCOLO_PREVENTIVO(?,?,?) }";//	QUESTA QUERY SERVE PER RICHIAMARE UNA FUNZIONE E ASSEGNare il VALORE AD UNA VARIABILE
		try {
			CallableStatement cs;	//QUEST'OGGETTO SERVE PER CHIAMARE UNA PROCEDURA O UNA FUNZIONE ORACLE
			cs=conn.prepareCall(query);
			cs.setFloat(2, prezzo_bloc);
			cs.setInt(3, n_mesi);
			cs.setInt(4, id_piano);
			cs.registerOutParameter(1, OracleTypes.NUMBER); //il primo ? sarà un risultato in uscita del tipo integer di oracle
			cs.execute();
			result =cs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		System.out.println("RISULTATOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO===== "+result);
		return result;
		
	}
	public void ProceduraPreventiviScaduti() {
		Connection conn=DBManager.startConnection();
		String query="{ call ELIMINA_PREVENTIVI_SCADUTI() }";//	QUESTA QUERY SERVE PER RICHIAMARE UNA PROCEDURA
		try {
			//ps = conn.prepareStatement(query);//SERVE PER COSTRURE LA QUERY
			CallableStatement cs;	//QUEST'OGGETTO SERVE PER CHIAMARE UNA PROCEDURA O UNA FUNZIONE ORACLE
			cs=conn.prepareCall(query);
			cs.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		
	}
	
	
	
	
	
	public PreventivoDAO() {
		// TODO Auto-generated constructor stub
	}

}
