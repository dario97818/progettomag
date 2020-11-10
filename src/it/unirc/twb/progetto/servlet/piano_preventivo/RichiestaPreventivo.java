package it.unirc.twb.progetto.servlet.piano_preventivo;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

/**
 * Servlet implementation class RichiestaPreventivo
 */
@WebServlet("/RichiestaPreventivo")
public class RichiestaPreventivo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PianoDAO pDAO=new PianoDAO();
	AnnuncioDAO aDAO=new AnnuncioDAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RichiestaPreventivo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	//	response.getWriter().append("Served at: ").append(request.getContextPath());
		//DEVO CANCELLARE MOLTE VARIABILI PERCHè NON LE HO USATE
		HttpSession se=request.getSession();
		if(se.getAttribute("auth")==null)
		{
			response.sendRedirect("/Errore.jsp");
		}
		Annuncio annuncio=new Annuncio();
		Piano piano=new Piano();
		int id_piano=Integer.parseInt( request.getParameter("id_piano"));//ok
		piano.setId_Piano(id_piano);
		float prezzo_Bloccato;//ok
		int id_annuncio;
		int percentuale;//ok
		int max_mesi;//ok
		piano=pDAO.getPiano(piano);
		id_annuncio=piano.getId_annuncio();
		annuncio.setId_annuncio(id_annuncio);
		max_mesi=piano.getMax_mesi();
		percentuale=piano.getPerc_anticipo();
		annuncio =aDAO.getAnnuncio(annuncio);
		prezzo_Bloccato=annuncio.getPrezzo();
		
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
		request.setAttribute("piano", piano);
		request.setAttribute("data_emissione", data_emissione);
		request.setAttribute("prezzo_bloccato", prezzo_Bloccato);

		request.getRequestDispatcher("/Preventivi/CreaPreventivo.jsp").forward(request, response);
		
		

		
		
	}

}
