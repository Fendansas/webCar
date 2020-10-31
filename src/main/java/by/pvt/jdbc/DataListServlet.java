package by.pvt.jdbc;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.pvt.jdbc.model.Car;
import by.pvt.jdbc.service.CarService;

@WebServlet(name = "CarListServlet", urlPatterns = { "/" })
public class DataListServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		List<Car> data = CarService.getInstance().getCarList();
		
		req.setAttribute("cars", data);
	
		getServletContext().getRequestDispatcher("/carList.jsp").forward(req, resp);
	}
}
