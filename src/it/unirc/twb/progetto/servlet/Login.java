package it.unirc.twb.progetto.servlet;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.buf.StringUtils;

import it.unirc.twb.progetto.been.Amministratore;
import it.unirc.twb.progetto.been.AmministratoreDAO;
import it.unirc.twb.progetto.been.PreventivoDAO;
import it.unirc.twb.progetto.been.Studente;
import it.unirc.twb.progetto.been.StudenteDAO;
import it.unirc.twb.progetto.been.Utente;
import it.unirc.twb.progetto.been.UtenteDAO;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getParameter("name").equals("") || request.getParameter("password").equals(""))
		{
			
			response.sendRedirect("/Errore.jsp");
			return;
		}
		
		String name=(String) request.getParameter("name");
		String pw=(String) request.getParameter("password");
		
		PreventivoDAO preventivoDAO=new PreventivoDAO();
		Utente u=new Utente();
		u.setEmail(name);
		u.setPass(converti(pw));
		UtenteDAO udao =new UtenteDAO();
		//response.getWriter().append(u.toString());
		if(!name.contains("@")&& isNumeric(name)) {//CONTROLLO SE SI VUOLE LOGGARE COME AMMINISTRATORE
			//	response.getWriter().append("stai cercando di fare l'accesso come amministratore");
			int id_admin=Integer.parseInt(request.getParameter("name"));
			pw=(String) request.getParameter("password");
			Amministratore a=new Amministratore();
			a.setId_amministratore(id_admin); 
			a.setPass(converti(pw));
			AmministratoreDAO aDAO=new AmministratoreDAO();
			if (aDAO.Login(a)) {
				response.getWriter().append("hai effettuaato l'accesso");
				preventivoDAO.ProceduraPreventiviScaduti();	//permette di eliminare i preventivi dopo 30 giorni dalla loro emissione
				HttpSession se=request.getSession();
				se.setAttribute("titolo", "Amministratore n. "+Integer.toString(a.getId_amministratore()));
				se.setAttribute("amministratore", a);
				se.setAttribute("auth-admin", true);
				request.getRequestDispatcher("/generic.jsp").forward(request, response);
			}
			else
				response.sendRedirect("/Errore.jsp");
		}
		else {
			//response.getWriter().append("NON stai cercando di fare l'accesso come amministratore");
			if(udao.Login(u)!=null)
			{
				preventivoDAO.ProceduraPreventiviScaduti();	//permette di eliminare i preventivi dopo 30 giorni dalla loro emissione
				HttpSession se=request.getSession();
				u.setId(udao.Login(u));
				u=udao.getUtente(u);
				if(u.getAttivo())
				{
					se.setAttribute("titolo", u.getNome());
					se.setAttribute("utente", u);
					se.setAttribute("id_utente", u.getId());
					se.setAttribute("auth", true);
					request.getRequestDispatcher("/generic.jsp").forward(request, response);
				}
				else
					response.getWriter().append("Sei stato bloccato");
			}
			else
				response.sendRedirect("/Errore.jsp");

		}
	}
	
	
	private String converti(String message) {
		String md5 = "";
		try {
            MessageDigest digest = MessageDigest.getInstance("MD5");//Create MessageDigest object for MD5
            digest.update(message.getBytes(), 0, message.length());//Update input string in message digest
            md5 = new BigInteger(1, digest.digest()).toString(16);//Converts message digest value in base 16 (hex)
            
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
		return md5;
	}
	public static boolean isNumeric(String str) { 
		  try {  
		    Double.parseDouble(str);  
		    return true;
		  } catch(NumberFormatException e){  
		    return false;  
		  }  
		}

}
