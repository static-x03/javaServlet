<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS y FontAwesone -->
    <jsp:include page="/WEB-INF/paginas/src/stylos.jsp"/>
<title>Control de clientes</title>
</head>
<body>

	<!-- Cabezero -->
	<jsp:include page="/WEB-INF/paginas/comons/header.jsp"/>
	
	<!-- Botones de Navegacion -->
	<jsp:include page="/WEB-INF/paginas/comons/botonesNv.jsp"/>
	
	<!-- Listado de Clientes -->
	<jsp:include page="/WEB-INF/paginas/cliente/listadoClientes.jsp"/>
	
	<!-- Pie de Pagina -->
	<jsp:include page="/WEB-INF/paginas/comons/footer.jsp"/>
	
	<!-- Option 2: Separate Popper and Bootstrap JS -->	
	<jsp:include page="/WEB-INF/paginas/src/dinamic.jsp"/>
</body>
</html>