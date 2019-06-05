<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="AtmApp.Deposit"%>
<%@ page import="AtmApp.Request"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	Hello,
	<%=session.getAttribute("username")%>
	<a href="./account/add-funds">Add funds</a>
	<a href="./account/withdraw">Withdraw</a>
	<a href="./account/transfer">Transfer</a>
	<a href="./account/move">Move</a>
	<a href="./account/request">Request</a>
	<a href="./account/new-deposit">Create deposit</a>
	<a href="./logout">Logout</a>
	<%
		ArrayList<Deposit> deposits = (ArrayList<Deposit>) request.getAttribute("deposits");
		ArrayList<Request> requests = (ArrayList<Request>) request.getAttribute("requests");
	%>

	You have
	<%=deposits.size()%>
	deposits.
	<table>
		<tr>
			<th>ID</th>
			<th>Amount</th>
			<th></th>
		</tr>
		<%
			for (Deposit d : deposits) {
		%>
		<tr>
			<td><%=d.getId()%></td>
			<td><%=d.getAmount()%></td>
			<td><a href="/ATM/account/remove-deposit?id=<%=d.getId()%>">Delete</a></td>
		</tr>

		<%
			}
		%>
	</table>

	You have
	<%=requests.size()%>
	request in pending.
	<table>
		<tr>
			<th>From</th>
			<th>To</th>
			<th>Amount</th>
			<th></th>
			<th></th>
		</tr>
		<%
			for (Request req : requests) {
		%>
		<tr>
			<td><%=req.getFromDepositId()%></td>
			<td><%=req.getToDepositId()%></td>
			<td><%=req.getAmount()%></td>
			<td><a href="/ATM/account/pending-request?id=<%=req.getId()%>&type=1">Accept</a></td>
			<td><a href="/ATM/account/pending-request?id=<%=req.getId()%>&type=0">Decline</a></td>
		</tr>

		<%
			}
		%>
	</table>
</body>
</html>