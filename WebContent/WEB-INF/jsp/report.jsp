<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Report Page</title>
		<style>
		<style>
		table {
			border-collapse: collapse;
			width: 100%;
		}
		th, td {
			text-align: center;
			padding: 8px;
		}	
		tr:nth-child(even){background-color: #99CCFF}
			
		th {
			background-color: #0066FF;
			color: white;
			padding: 4px;
		}
		td {
			color: black;
		}
		h2 {margin-left: 20%;
		}
		</style>
		
		
	</head>
	<body>
		<h2 >REPORT</h2>
		<table id="report">
			<tr>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Address1</th>
				<th>Address2</th>
				<th>City</th>
				<th>State</th>
				<th>Zip Code</th>
				<th>Country</th>
				<th>Date</th>
			</tr>
			<c:forEach items="${persons}" var="person">
				<tr>
					<td>${person.firstName}</td>
					<td>${person.lastName}</td>
					<td>${person.address1}</td>
					<td>${person.address2}</td>
					<td>${person.city}</td>
					<td>${person.state}</td>
					<td>${person.zipCode}</td>
					<td>${person.country}</td>
					<td>${person.timestamp}</td>
				</tr>
			</c:forEach>
		</table>
	</body>
</html>



<%--
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Display</title>
		<style>
		table#nat {
			width: 50%;
			background-color: #c48ec5;
		}
		</style>
	</head>
	<body>
		<%
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String address1 = request.getParameter("address1");
        String address2 = request.getParameter("address2");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String zipCodeStr = request.getParameter("zipCode");
        int zipCode = Integer.parseInt(zipCodeStr);
        String country = request.getParameter("country");
        String 
		%>
		<table id="nat">
			<tr>
				<th>First Name</th>
				<th>Last Name</th>				
				<th>Address1</th>
				<th>Address2</th>
				<th>City</th>
				<th>State</th>
				<th>Zipcode</th>
				<th>Country</th>
				<th>Date</th>
							
			</tr>
			<tr>
				<td>Address</td>
				<td><%=Addr%></td>
			</tr>
			<tr>
				<td>Age</td>
				<td><%=age%></td>
			</tr>
			<tr>
				<td>Qualification</td>
				<td><%=Qual%></td>
			</tr>
			<tr>
				<td>Percentage</td>
				<td><%=Persent%></td>
			</tr>
			<tr>
				<td>Year of Passout</td>
				<td><%=Year%></td>
			</tr>
		</table>
	</body>
</html> --%>