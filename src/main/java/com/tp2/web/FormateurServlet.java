package com.tp2.web;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import com.tp2.dao.FormateurDao;
import com.tp2.dao.FormationDao;
import com.tp2.dao.LieuDao;
import com.tp2.dao.FFDao;
import com.tp2.model.Formateur;
import com.tp2.model.Formation;
import com.tp2.model.Lieu;
import com.tp2.model.FF;

@WebServlet("/")
public class FormateurServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FormateurDao formateurDao;
	private FormationDao formationDao;
	private LieuDao lieuDao;
	private FFDao ffDao;
	
	public void init() {
		formationDao = new FormationDao();
		formateurDao = new FormateurDao();
		lieuDao = new LieuDao();
		ffDao = new FFDao();
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/inserer":
				insererFormateur(request, response);
				break;
			case "/supprimer":
				supprimerFormateur(request, response);
				break;
			case "/modifier":
				showEditForm(request, response);
				break;
			case "/update":
				updateFormateur(request, response);
				break;
			case "/newF":
				showNewForma(request, response);
				break;
			case "/insererF":
				insererFormation(request, response);
				break;
			case "/supprimerF":
				supprimerFormation(request, response);
				break;
			case "/modifierF":
				showEditForma(request, response);
				break;
			case "/updateF":
				updateFormation(request, response);
				break;
			case "/newL":
				showNewFormL(request, response);
				break;
			case "/insererL":
				insererLieu(request, response);
				break;
			case "/supprimerL":
				supprimerLieu(request, response);
				break;
			case "/modifierL":
				showEditFormL(request, response);
				break;
			case "/updateL":
				updateLieu(request, response);
				break;
			case "/newFF":
				showNewFormFF(request, response);
				break;
			case "/insererFF":
				insererFF(request, response);
				break;
			case "/supprimerFF":
				supprimerFF(request, response);
				break;
			case "/modifierFF":
				showEditFormFF(request, response);
				break;
			case "/updateFF":
				updateFF(request, response);
				break;
			default:
				listFormation(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listFormation(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Formateur> listFormateur = formateurDao.selectAllFormateurs();
		request.setAttribute("listFormateur", listFormateur);
		List<Formation> listFormation = formationDao.selectAllFormations();
		request.setAttribute("listFormation", listFormation);
		List<Lieu> listLieu = lieuDao.selectAllLieux();
		request.setAttribute("listLieu", listLieu);
		List<FF> listFF = ffDao.selectAllFF();
		request.setAttribute("listFF", listFF);
		RequestDispatcher dispatcher = request.getRequestDispatcher("liste.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("add_formateur.jsp");
		dispatcher.forward(request, response);
	}


	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Formateur existingUser = formateurDao.selectionnerFormateur(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("add_formateur.jsp");
		request.setAttribute("formateur", existingUser);
		dispatcher.forward(request, response);

	}


	private void insererFormateur(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String cin = request.getParameter("cin");
		String nom = request.getParameter("nom");
		int age = Integer.parseInt(request.getParameter("age"));
		Formateur newFormateur = new Formateur(cin, nom, age);
		formateurDao.insererFormateur(newFormateur);
		response.sendRedirect("list");
	}

	private void updateFormateur(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String cin = request.getParameter("cin");
		String nom = request.getParameter("nom");
		int age = Integer.parseInt(request.getParameter("age"));
		Formateur book = new Formateur(id, cin, nom, age);
		formateurDao.updateFormateur(book);
		response.sendRedirect("list");
	}

	private void supprimerFormateur(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		formateurDao.supprimerFormateur(id);
		response.sendRedirect("list");

	}
	

	private void showNewForma(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Lieu> listLieu = lieuDao.selectAllLieux();
		request.setAttribute("listLieu", listLieu);
		RequestDispatcher dispatcher = request.getRequestDispatcher("inserer_formation.jsp");
		dispatcher.forward(request, response);
	}


	private void showEditForma(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Formation existingUser = formationDao.selectionnerFormation(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("inserer_formation.jsp");
		request.setAttribute("formation", existingUser);
		dispatcher.forward(request, response);

	}


	private void insererFormation(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String theme = request.getParameter("theme");
		String lieu = request.getParameter("lieu");
		Formation newFormation = new Formation(theme, lieu);
		formationDao.insererFormation(newFormation);
		response.sendRedirect("list");
	}

	private void updateFormation(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String theme = request.getParameter("theme");
		String lieu = request.getParameter("lieu");
		Formation book = new Formation(id, theme, lieu);
		formationDao.updateFormation(book);
		response.sendRedirect("list");
	}

	private void supprimerFormation(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		formationDao.supprimerFormation(id);
		response.sendRedirect("list");

	}
	private void showNewFormL(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("inserer_lieu.jsp");
		dispatcher.forward(request, response);
	}


	private void showEditFormL(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Lieu existingUser = lieuDao.selectionnerLieu(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("inserer_lieu.jsp");
		request.setAttribute("lieu", existingUser);
		dispatcher.forward(request, response);

	}


	private void insererLieu(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String adresse = request.getParameter("adresse");
		String ville = request.getParameter("ville");
		Lieu newLieu = new Lieu(adresse, ville);
		lieuDao.insererLieu(newLieu);
		response.sendRedirect("list");
	}

	private void updateLieu(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String adresse = request.getParameter("adresse");
		String ville = request.getParameter("ville");
		Lieu book = new Lieu(id, adresse, ville);
		lieuDao.updateLieu(book);
		response.sendRedirect("list");
	}

	private void supprimerLieu(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		lieuDao.supprimerLieu(id);
		response.sendRedirect("list");

	}
	private void showNewFormFF(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Formateur> listFormateur = formateurDao.selectAllFormateurs();
		request.setAttribute("listFormateur", listFormateur);
		List<Formation> listFormation = formationDao.selectAllFormations();
		request.setAttribute("listFormation", listFormation);
		RequestDispatcher dispatcher = request.getRequestDispatcher("formateur_formation.jsp");
		dispatcher.forward(request, response);
	}


	private void showEditFormFF(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		FF existingUser = ffDao.selectionnerFF(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("formateur_formation.jsp");
		request.setAttribute("ff", existingUser);
		dispatcher.forward(request, response);

	}


	private void insererFF(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String theme = request.getParameter("theme");
		String nom = request.getParameter("nom");
		FF newFF = new FF(theme, nom);
		ffDao.insererFF(newFF);
		response.sendRedirect("list");
	}

	private void updateFF(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String theme = request.getParameter("theme");
		String nom= request.getParameter("nom");
		FF book = new FF(id, theme, nom);
		ffDao.updateFF(book);
		response.sendRedirect("list");
	}

	private void supprimerFF(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		ffDao.supprimerFF(id);
		response.sendRedirect("list");
	}
}