package it.unirc.twb.progetto.servlet.piano_preventivo;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.unirc.twb.progetto.been.Annuncio;
import it.unirc.twb.progetto.been.AnnuncioDAO;
import it.unirc.twb.progetto.been.Piano;
import it.unirc.twb.progetto.been.PianoDAO;
import it.unirc.twb.progetto.been.Utente;

/**
 * Servlet implementation class NuovoPiano
 */
@WebServlet("/NuovoPiano")
public class NuovoPiano extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NuovoPiano() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession se=request.getSession();
		if(se.getAttribute("auth")==null)
		{
			response.sendRedirect("/Errore.jsp");
		}
		PianoDAO pDAO=new PianoDAO();
		Piano p =new Piano();
		String id_nuovo_annuncio =request.getParameter("id");
		int id_Annuncio=0;
		int max_mesi=Integer.valueOf(request.getParameter("max_mesi"));
		int perc=Integer.valueOf(request.getParameter("percentuale"));
		System.out.println(id_nuovo_annuncio);
		response.getWriter().append(id_nuovo_annuncio); 
		//SE PROVIENE DA UN ANNUNCIO APPENA MESSO
		if (id_nuovo_annuncio!=null) {
			id_Annuncio=Integer.valueOf(id_nuovo_annuncio);
		}
		else {
			id_Annuncio=Integer.valueOf(request.getParameter("id_annuncio"));
			System.out.println("????????????????????????????? "+id_Annuncio); 
		}
		//CREAZIONE DELL'OGGETTO DA PASSARE ALLA QUERY
		p.setId_annuncio(id_Annuncio);
		p.setMax_mesi(max_mesi);
		p.setPerc_anticipo(perc);
		if (pDAO.salvaPiano(p))
			System.out.println("piano creato correttamente");
		response.sendRedirect("/RichiestaGestionePreventivi");
	
		
		
		
		

	}

}
