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

import com.tp2.dao.LieuDao;
import com.tp2.model.Lieu;

@WebServlet("/L")
public class LieuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LieuDao lieuDao;
	
	public void init() {
		lieuDao = new LieuDao();
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
			case "/Lnew":
				showNewFormL(request, response);
				break;
			case "/Linserer":
				insererLieu(request, response);
				break;
			case "/Lsupprimer":
				supprimerLieu(request, response);
				break;
			case "/Lmodifier":
				showEditFormL(request, response);
				break;
			case "/Lupdate":
				updateLieu(request, response);
				break;
			default:
				listLieu(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listLieu(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Lieu> listLieu = lieuDao.selectAllLieux();
		request.setAttribute("listLieu", listLieu);
		RequestDispatcher dispatcher = request.getRequestDispatcher("liste.jsp");
		dispatcher.forward(request, response);
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

}