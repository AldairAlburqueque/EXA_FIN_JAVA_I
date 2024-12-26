<%@page import="entidades.Brand"%>
<%@page import="entidades.WaterMachine"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!--  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>-->
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-
1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
</head>
<body>
<%
	List<WaterMachine> listaWM = (List<WaterMachine>) request.getAttribute("dataWaterMachine");
	List<Brand> listaBrand = (List<Brand>) request.getAttribute("dataBrand");
	WaterMachine water = (WaterMachine) request.getAttribute("water");
	String mensaje = (String) request.getAttribute("mensaje");
	String alertType = (String) request.getAttribute("alertType");
%>
<div class="container-fluid">
	<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">Sistema de mantenimientos</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarScroll" aria-controls="navbarScroll" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarScroll">
      <ul class="navbar-nav me-auto my-2 my-lg-0 navbar-nav-scroll" style="--bs-scroll-height: 100px;">
        <li class="nav-item">
          <a class="nav-link" href="#">Lavadora</a>
        </li>
      </ul>
      <form action="" class="d-flex">
        <button class="btn btn-outline-light" type="submit">Cerrar Sesión</button>
      </form>
    </div>
  </div>
</nav>
<div class="row">
			<div class="col-12 text-center">
				<h3>Listado de lavadora</h3>
			</div>
			<div class="col-12">
				<button type="button" class="btn btn-primary btn-lg"
					onclick="openDialog()">Nueva Lavadora</button>
			</div>
			
				<% if (mensaje != null) { %>
    				<div id="alert-message" class="alert <%= alertType %>" role="alert">
        				<%=mensaje %>
    				</div>
				<% } %>
			
			<br> 
			<br>
			<div class="col-12">
				<table class="table">
						<thead>
							<tr>
								<th>Id Lavadora</th>
								<th>Modelo</th>
								<th>Color Principal</th>
								<th>Capacidad de lavado</th>
								<th>Garantia</th>
								<th>Precio</th>
								<th>Marca</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
						<%
							if(listaWM != null) {
								for(WaterMachine item: listaWM){%>
									<tr>
										<td><%=item.getIdWaterMachine() %></td>
										<td><%=item.getModel() %></td>
										<td><%=item.getMainColor() %></td>
										<td><%=item.getWashingCapacity() %></td>
										<td><%=item.getWarranty() %></td>
										<td><%=item.getPrice() %></td>
										<td><%=item.getBrand() %></td>
										<td>
										
										<!-- <a href="javascript:void(0);" class="btn btn-primary btn-lg" tabindex="-1" role="button"                                        aria-disabled="true" 
                                           onclick="openDialogUpdateDelete(<%= item.getIdWaterMachine() %>)">Editar
                                    	</a>-->
                                    	
										<a href="#<%= item.getIdWaterMachine() %>"
  											 class="btn btn-primary btn-lg"
  											 tabindex="-1"
  											 role="button"
  											 data-idWaterMachine="<%= item.getIdWaterMachine() %>"
  											 data-model="<%= item.getModel() %>"
  											 data-main-color="<%= item.getMainColor() %>"
  											 data-washing-capacity="<%= item.getWashingCapacity() %>"
  											 data-warranty="<%= item.getWarranty() %>"
  											 data-price="<%= item.getPrice() %>"
  											 data-brand="<%= item.getBrand() %>"
  											 onClick="openDialogUpdateDelete(this)">
  											 Editar
										</a>
										
										</td>
									</tr>
							<%	}
							}
						%>
							
						</tbody>
					</table>
					
					
			</div>
			<div class="modal fade" id="modalForm" role="dialog">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
						<h4 class="modal-title text-center" id="myModalLabel">Mantenimiento de Lavadora</h4>
						<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>			
						</div>
						
						<div class="modal-body">
							<form action="WaterMachineServlet" method="post" role="form" name="registerWM">
								<input type="hidden" name="type" value="registerWM">
								<div class="form-group">
									<label>Modelo</label>
									<input class="form-control" type="text" name="txtModel">
								</div>
								<div class="form-group">
									<label>Color principal</label>
									<input class="form-control" type="text" name="txtMainColor">
								</div>
								<div class="form-group">
									<label>Capacidad de lavado</label>
									<input class="form-control" type="text" name="txtWashingCapacity">
								</div>
								<div class="form-group">
									<label>Garantia</label>
									<input class="form-control" type="text" name="txtWarranty">
								</div>
								<div class="form-group">
									<label>Precio</label>
									<input class="form-control" type="text" name="txtPrice">
								</div>
								<div class="form-group">
									<label>Marca</label>
									<select class="form-select" name="txtBrand" aria-label="Default select example">
									  <option selected>Seleccione la marca</option>
									  <%
									  if(listaBrand != null) {
										  for(Brand item : listaBrand) {%>
											  <option value="<%=item.getIdBrand() %>"><%=item.getName() %> </option>
										<%  }
									  }
									  %>
									</select>
									
								</div>
							</form>
						</div>
						
						<div class="modal-footer">
							<button type="button" class="btn btn-primary submitBtn d-grid gap-2 col-6 mx-auto" onClick="document.forms['registerWM'].submit()">Registrar</button>
						</div>
					</div>
				</div>
			</div>
			
			<div class="modal fade" id="modalFormUpdateDelete" role="dialog">
			
				<div class="modal-dialog">
				
					<div class="modal-content">
						<div class="modal-header">
						<h4 class="modal-title text-center" id="myModalLabel">Actualización y eliminación de Lavadora</h4>
						<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>			
						</div>
						
						<div class="modal-body">
							<form action="WaterMachineServlet" role="form" >
							
								<input type="hidden" name="type" id="actionType" value="">
      						<input type="hidden" name="idWaterMachine" id="txtIdWaterMachine" >
								<div class="form-group">
									<label>Modelo</label>
									<input class="form-control" type="text" name="model" id="txtModel" >
								</div>
								<div class="form-group">
									<label>Color principal</label>
									<input class="form-control" type="text" name="mainColor" id="txtMainColor" >
								</div>
								<div class="form-group">
									<label>Capacidad de lavado</label>
									<input class="form-control" type="text" name="washingCapacity" id="txtWashingCapacity">
								</div>
								<div class="form-group">
									<label>Garantia</label>
									<input class="form-control" type="text" name="warranty" id="txtWarranty" >
								</div>
								<div class="form-group">
									<label>Precio</label>
									<input class="form-control" type="text" name="price" id="txtPrice" >
								</div>
								<div class="form-group">
									<label>Marca</label>
									<select class="form-select" name="brand" id="txtBrand"  aria-label="Default select example">
									 <%
									  if(listaBrand != null) {
										  for(Brand item : listaBrand) {%>
											  <option value="<%=item.getIdBrand() %>"><%=item.getName() %> </option>
										<%  }
									  }
									  %>
									</select>
									
								</div>
							</form>
						</div>
						
						<div class="modal-footer">
							<button type="button" onclick="submitForm('actualizar')" id="btnUpdate" class="btn btn-primary submitBtn d-grid gap-2 col-5 mx-auto">Actualizar</button>
							<button type="button" onclick="submitForm('eliminar')" id="btnDelete" class="btn btn-danger submitBtn d-grid gap-2 col-5 mx-auto">Eliminar</button>
						</div>
					</div>
				</div>
			</div>

					
			
			
		</div>

		<div class="row"></div>
	</div>
	
<!-- Option 1: Bootstrap Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

    <!-- Option 2: Separate Popper and Bootstrap JS -->
    
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
    
<script src="https://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js">
	</script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript">

function openDialog(){
	$('#modalForm').modal('show');
}
function openDialogUpdateDelete(link) {
    // Extrae los valores de los atributos data
    const idWaterMachine = link.getAttribute("data-idWaterMachine");
    const model = link.getAttribute("data-model");
    const mainColor = link.getAttribute("data-main-color");
    const washingCapacity = link.getAttribute("data-washing-capacity");
    const warranty = link.getAttribute("data-warranty");
    const price = link.getAttribute("data-price");
    const brand = link.getAttribute("data-brand");

    // Rellena los campos del modal con los datos
    document.getElementById("txtIdWaterMachine").value = idWaterMachine;
    document.getElementById("txtModel").value = model;
    document.getElementById("txtMainColor").value = mainColor;
    document.getElementById("txtWashingCapacity").value = washingCapacity;
    document.getElementById("txtWarranty").value = warranty;
    document.getElementById("txtPrice").value = price;
    document.getElementById("txtBrand").value = brand;

    // Muestra el modal
    const modal = new bootstrap.Modal(document.getElementById('modalFormUpdateDelete'));
    modal.show();
  }
//Oculta la alerta después de 3 segundos
$(document).ready(function(){ 
	setTimeout(function(){ 
		$('#alert-message').fadeOut('slow'); 
		}, 3000); 
	});
function submitForm(actionType) {
    // Establece el tipo de acción
    document.getElementById("actionType").value = actionType;
    // Envía el formulario
    document.querySelector("#modalFormUpdateDelete form").submit();
}

</script>


</body>
</html>