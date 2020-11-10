package it.unirc.twb.progetto.servlet.piano_preventivo;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.unirc.twb.progetto.been.Piano;
import it.unirc.twb.progetto.been.PianoDAO;
import it.unirc.twb.progetto.been.Preventivo;
import it.unirc.twb.progetto.been.PreventivoDAO;

/**
 * Servlet implementation class NuovoPreventivo
 */
@WebServlet("/NuovoPreventivo")
public class NuovoPreventivo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PianoDAO pDAO=new PianoDAO();
	PreventivoDAO prDAO=new PreventivoDAO();
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NuovoPreventivo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession se=request.getSession();
		if(se.getAttribute("auth")==null)
		{
			response.sendRedirect("/Errore.jsp");
		}
		int n_mesi=Integer.valueOf(request.getParameter("max_mesi"));
		int id_piano=Integer.valueOf((String) request.getParameter("id_piano"));
		float prezzo_bloc=Float.valueOf(request.getParameter("prezzo_bloccato"));  
		response.getWriter().append("mesi scelti: " + n_mesi+"\n"+"id del piano: " + id_piano+"\n"+"prezzo bloccato: " + prezzo_bloc+"\n");
		
		prDAO.function_Calcola(n_mesi, id_piano, prezzo_bloc);
		Preventivo preventivo= new Preventivo();
		preventivo.setN_mesi(n_mesi);
		preventivo.setId_Piano(id_piano);
		preventivo.setPrezzo_Bloccato(prezzo_bloc);  
		int id_u=(int) se.getAttribute("id_utente");
		preventivo.setId_Richiedente(id_u);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date data=new java.util.Date();
		try {
			data = sdf.parse(data.toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		java.sql.Date data_emissione = new java.sql.Date(data.getTime());
		System.out.println(data.toString());
		System.out.println(data_emissione.toString());
		preventivo.setData(data_emissione);
		if (prDAO.salvaPreventivo(preventivo))
			System.out.println("PREVENTIVO creato correttamente!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		response.sendRedirect("/RichiestaGestionePreventivi");
		
		
	}

}
