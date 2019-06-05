<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
		 pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="ATM.Deposit"%>
<%@ page import="ATM.Request"%>
<!doctype html>
<html lang="en">
<head>
	<!-- Required meta tags -->
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

	<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<link rel="stylesheet" href="style.css">

	<title>OnlineBanking</title>
</head>
<body>

<!-- A grey horizontal navbar that becomes vertical on small screens -->
<nav class="navbar navbar-light bg-light">
	<div class="navbar-header">
		<form class="form-inline p-10">
			<div class="navbar-brand">OnlineBanking
				<small>Hello, <%=session.getAttribute("username")%>!</small>
			</div>
			<a href="#" class="btn btn-outline-secondary active" role="button">My account</a>
			<a href="/ATM/account/add-funds" class="btn btn-outline-secondary" role="button">Add funds</a>
			<a href="/ATM/account/withdraw" class="btn btn-outline-secondary" role="button">Withdraw</a>
			<a href="/ATM/account/transfer" class="btn btn-outline-secondary" role="button">Transfer</a>
			<a href="/ATM/account/move" class="btn btn-outline-secondary" role="button">Move</a>
			<a href="/ATM/account/request" class="btn btn-outline-secondary" role="button">Request</a>
			<a href="/ATM/account/new-deposit" class="btn btn-outline-secondary" role="button">Create deposit</a>
			<a href="/ATM/account/logout" class="btn btn-outline-secondary" role="button">Logout</a>
		</form>
	</div>
</nav>
<%
	ArrayList<Deposit> deposits = (ArrayList<Deposit>) request.getAttribute("deposits");
	ArrayList<Request> requests = (ArrayList<Request>) request.getAttribute("requests");
%>

<div class="row">
	<div class="col-sm-4">
		<div class="container deposits-table">
			<p class="text-white">You have <%=deposits.size()%> deposits.</p>
			<table class="table table-hover table-bordered" style="background-color: white">
				<thead class="thead-light">
				<tr>
					<th>Account Number</th>
					<th>Amount</th>
					<th></th>
				</tr>
				</thead>
				<tbody>
				<%
					for (Deposit d : deposits) {
				%>
				<tr>
					<td><%=d.getId()%></td>
					<td><%=d.getAmount()%> RON</td>
					<td><a href="/ATM/account/remove-deposit?id=<%=d.getId()%>">Delete</a></td>
				</tr>

				<%
					}
				%>
				</tbody>
			</table>
		</div>
	</div>
	<div class="col-sm-5">
		<div class="container requests-table">
			<p class="text-white">You have <%=requests.size()%> requests.</p>
			<table class="table table-hover table-bordered" style="background-color: white">
				<thead class="thead-light">
				<tr>
					<th>From Your Account Number</th>
					<th>Requested Account Number</th>
					<th>Amount</th>
					<th></th>
					<th></th>
				</tr>
				</thead>
				<tbody>
				<%
					for (Request req : requests) {
				%>
				<tr>
					<td><%=req.getFromDepositId()%></td>
					<td><%=req.getToDepositId()%></td>
					<td><%=req.getAmount()%> RON</td>
					<td><a href="/ATM/account/pending-request?id=<%=req.getId()%>&type=1">Accept</a></td>
					<td><a href="/ATM/account/pending-request?id=<%=req.getId()%>&type=0">Decline</a></td>
				</tr>

				<%
					}
				%>
				</tbody>
			</table>
		</div>
	</div>
</div>


<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>