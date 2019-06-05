<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="AtmApp.Deposit"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form method="post">
		<%
			if (request.getAttribute("error") != null) {
		%>
		<%=request.getAttribute("error")%>
		<%
			}
		%>
		<select name="deposit_id_to">
			<%
				ArrayList<Deposit> deposits = (ArrayList<Deposit>) request.getAttribute("deposits");

				for (Deposit d : deposits) {
			%>
			<option value="<%=d.getId()%>">Account ID:
				<%=d.getId()%> Balance: <%=d.getAmount()%>RON
			</option>
			<%
				}
			%>
		</select>
		<input type="text" placeholder="From" name="deposit_id_from" required>
		
		<input type="text" placeholder="Value" name="amount" required>
		<input type="submit" value="Add funds">
	</form>
</body>
</html>