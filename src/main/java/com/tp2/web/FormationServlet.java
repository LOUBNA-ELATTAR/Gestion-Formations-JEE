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

import com.tp2.dao.FormationDao;
import com.tp2.model.Formation;

@WebServlet("/F")
public class FormationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FormationDao formationDao;
	
	public void init() {
		formationDao = new FormationDao();
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
				showNewForma(request, response);
				break;
			case "/inserer":
				insererFormation(request, response);
				break;
			case "/supprimer":
				supprimerFormation(request, response);
				break;
			case "/modifier":
				showEditForma(request, response);
				break;
			case "/update":
				updateFormation(request, response);
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
		List<Formation> listFormation = formationDao.selectAllFormations();
		request.setAttribute("listFormation", listFormation);
		RequestDispatcher dispatcher = request.getRequestDispatcher("liste.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForma(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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

}