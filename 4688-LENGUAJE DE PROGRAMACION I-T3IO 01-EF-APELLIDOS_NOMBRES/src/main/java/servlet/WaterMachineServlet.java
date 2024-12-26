package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAOFactory;
import entidades.Brand;
import entidades.WaterMachine;
import interfaces.BrandInterface;
import interfaces.WaterMachineInterface;
import modelo.WaterMachineModel;

/**
 * Servlet implementation class WaterMachineServlet
 */
@WebServlet("/WaterMachineServlet")
public class WaterMachineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub		
		
		String type = request.getParameter("type");
		System.out.print("type es: " + type + "\n");
		
		switch(type) {
		case "listWaterMachines": listWaterMachines(request, response);break;
		case "registerWM": registerWaterMachine(request, response); break;
		case "consultarxID": getWaterMachine(request, response); break;
		case "actualizar": updateWaterMachine(request, response); break;
		case "eliminar": deleteWaterMachine(request, response); break;
		default: 
			request.setAttribute("mensaje", "Ocurrió un problema");
			request.getRequestDispatcher("waterMachine.jsp").forward(request, response);
		}
	}

	private void deleteWaterMachine(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DAOFactory daoFactory = DAOFactory.getDaoFactory(DAOFactory.MYSQL);
		WaterMachineInterface dao = daoFactory.getWaterMachine();
		
		String id = request.getParameter("idWaterMachine");
		System.out.println("Su id para eliminar es: " + id);
		int valor = dao.deleteWaterMachine(id);
		
		if (valor >= 0) {
			request.setAttribute("mensaje","Water Machine se eliminó con exito!!");
		    request.setAttribute("alertType", "alert-success");
		    listWaterMachines(request, response);
		} else {
		    request.setAttribute("mensaje", "Ocurrió un problema");
		    request.setAttribute("alertType", "alert-danger");
		    request.getRequestDispatcher("WaterMachine.jsp").forward(request, response);
		}
		
	}

	private void updateWaterMachine(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DAOFactory daoFactory = DAOFactory.getDaoFactory(DAOFactory.MYSQL);
		WaterMachineInterface dao = daoFactory.getWaterMachine();
		
		
		String id = request.getParameter("idWaterMachine");
		String model = request.getParameter("model");
		String mainColor = request.getParameter("mainColor");
		String washingCapacity = request.getParameter("washingCapacity");
		String warranty = request.getParameter("warranty");
		String price = request.getParameter("price");
		String brand = request.getParameter("brand");
		
		WaterMachine waterMachine = new WaterMachine();
		waterMachine.setIdWaterMachine(id);
		waterMachine.setModel(model);
		waterMachine.setMainColor(mainColor);
		waterMachine.setWashingCapacity(washingCapacity);
		waterMachine.setWarranty(warranty);
		waterMachine.setPrice(price);
		waterMachine.setBrand(brand);
		
        int valor = dao.updateWaterMachine(waterMachine);
        
		if (valor >= 0) {
		    request.setAttribute("mensaje", "Se actualizó correctamente!!");
		    request.setAttribute("alertType", "alert-success");
		    listWaterMachines(request, response);
		} else {
		    request.setAttribute("mensaje", "Ocurrió un problema");
		    request.setAttribute("alertType", "alert-danger");
		    request.getRequestDispatcher("WaterMachine.jsp").forward(request, response);
		}
		
	}

	private void getWaterMachine(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DAOFactory daoFactory = DAOFactory.getDaoFactory(DAOFactory.MYSQL);
		WaterMachineInterface dao = daoFactory.getWaterMachine();
		//BrandInterface daoBrand = daoFactory.getBrand();
		
		String id = request.getParameter("idWaterMachine");  
	    WaterMachine water = dao.getWaterMachine(id);
	        
	    request.setAttribute("water", water);
	    listWaterMachines(request, response);
	    request.getRequestDispatcher("WaterMachine.jsp").forward(request, response);
	}

	private void registerWaterMachine(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DAOFactory daoFactory = DAOFactory.getDaoFactory(DAOFactory.MYSQL);
		WaterMachineInterface dao = daoFactory.getWaterMachine();
		//BrandInterface daoBrand = daoFactory.getBrand();
		
		WaterMachine wm = new WaterMachine();
		
		String model = request.getParameter("txtModel");
		String mainColor = request.getParameter("txtMainColor");
		String washingCapacity = request.getParameter("txtWashingCapacity");
		String warranty = request.getParameter("txtWarranty");
		String price = request.getParameter("txtPrice");
		String brand = request.getParameter("txtBrand");
		
		wm.setModel(model);
		wm.setMainColor(mainColor);
		wm.setWashingCapacity(washingCapacity);
		wm.setWarranty(warranty);
		wm.setPrice(price);
		wm.setBrand(brand);
		
		int valor = dao.registerWaterMachine(wm);
		
		if (valor >= 0) {
		    request.setAttribute("mensaje","Water Machine agregada con exito!!");
		    request.setAttribute("alertType", "alert-success");
		    listWaterMachines(request, response);
		} else {
		    request.setAttribute("mensaje", "Ocurrió un problema");
		    request.setAttribute("alertType", "alert-danger");
		    request.getRequestDispatcher("WaterMachine.jsp").forward(request, response);
		}	    

	}

	private void listWaterMachines(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DAOFactory daoFactory = DAOFactory.getDaoFactory(DAOFactory.MYSQL);
		WaterMachineInterface dao = daoFactory.getWaterMachine();
		BrandInterface daoBrand = daoFactory.getBrand();
		
		List<WaterMachine> dataWaterMachine = dao.listWaterMachines();
		List<Brand> dataBrand = daoBrand.listBrands();
		
		request.setAttribute("dataWaterMachine", dataWaterMachine);
		request.setAttribute("dataBrand", dataBrand);
		request.getRequestDispatcher("WaterMachine.jsp").forward(request, response);
		
	}

}
