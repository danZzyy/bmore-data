<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page import="java.util.List" %>
<%@ page import="org.springframework.web.context.WebApplicationContext" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="com.vividsolutions.jts.geom.Point" %>
<%@ page import="com.bmore.spring.model.Grocery" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P id="a">  </P>
<c:forEach items="${groc}" var="groc">
	${groc.name} : ${groc.lng} , ${groc.lat}<br>
</c:forEach>
</body>
<script>
	
</script>
</html>
