package it.unirc.twb.progetto.servlet.annuncio;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unirc.twb.progetto.been.Annuncio;
import it.unirc.twb.progetto.been.AnnuncioDAO;
import it.unirc.twb.progetto.been.Piano;
import it.unirc.twb.progetto.been.PianoDAO;
import it.unirc.twb.progetto.been.Utente;
import it.unirc.twb.progetto.been.UtenteDAO;

/**
 * Servlet implementation class RichiediVisualizzaAnnuncio
 */
@WebServlet("/RichiediVisualizzaAnnuncio")
public class RichiediVisualizzaAnnuncio extends HttpServlet {
	AnnuncioDAO aDAO=new AnnuncioDAO();
	UtenteDAO uDAO=new UtenteDAO();
	PianoDAO pDAO=new PianoDAO();
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RichiediVisualizzaAnnuncio() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id_annuncio=Integer.parseInt( request.getParameter("id"));
		int id_utente=Integer.parseInt(request.getParameter("id_utente"));
		Annuncio a =new Annuncio();
		Utente u =new Utente();
		u.setId(id_utente);
		a.setId_annuncio(id_annuncio);
		Piano p= pDAO.getPianoFromAnnuncio(a);
		Annuncio risultato= aDAO.getAnnuncio(a);
		Utente venditore=uDAO.getUtente(u);
		int id_piano=0;
		if (p!=null)	//SE ESISTE UN PIANO PER QUELL'ANNUNCIO VERRà PASSATO L'ID DEL PIANO ALTRIMENTI VERRà PASSATO 0
			id_piano=p.getId_Piano();
		request.setAttribute("id_piano", id_piano);
		request.setAttribute("risultato", risultato);
		request.setAttribute("venditore", venditore);
		request.getRequestDispatcher("/Annuncio/VisualizzaAnnuncio.jsp").forward(request, response);
		
	}

}
