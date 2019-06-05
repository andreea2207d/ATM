<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
		 pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="ATM.Deposit" %>
<!doctype html>
<html lang="en">
<head>
	<!-- Required meta tags -->
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

	<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<link rel="stylesheet" href="/ATM/style.css">

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
			<a href="/ATM/account" class="btn btn-outline-secondary" role="button">My account</a>
			<a href="/ATM/account/add-funds" class="btn btn-outline-secondary" role="button">Add funds</a>
			<a href="/ATM/account/withdraw" class="btn btn-outline-secondary" role="button">Withdraw</a>
			<a href="/ATM/account/transfer" class="btn btn-outline-secondary" role="button">Transfer</a>
			<a href="/ATM/account/move" class="btn btn-outline-secondary" role="button">Move</a>
			<a href="#" class="btn btn-outline-secondary active" role="button">Request</a>
			<a href="/ATM/account/new-deposit" class="btn btn-outline-secondary" role="button">Create deposit</a>
			<a href="/ATM/account/logout" class="btn btn-outline-secondary" role="button">Logout</a>
		</form>
	</div>
</nav>

<div class="row">
	<div class="col-sm-1">

	</div>
	<div class="col-sm-4 mb">
		<h4 class="mb-4 mt-3 ml-3">Request money</h4>
		<form method="post">
			<div class="form-group">
					<%
						if (request.getAttribute("error") != null) {
					%>
					<div class="alert alert-danger" role="alert">
						<%=request.getAttribute("error")%>
					</div>
					<%
						}
					%>
				<label for="deposit_id_to">Account number:</label>
				<select class="form-control" id="deposit_id_to" name="deposit_id_to">
					<%
						ArrayList<Deposit> deposits = (ArrayList<Deposit>) request.getAttribute("deposits");

						for (Deposit d : deposits) {
					%>
					<option value="<%=d.getId()%>">Account:
						<%=d.getId()%> Balance: <%=d.getAmount()%>RON
					</option>
					<%
						}
					%>
				</select>
			</div>
			<div class="form-group">
				<label for="deposit_id_from">Requesting account number:</label>
				<input type="text" class="form-control" name="deposit_id_from" id="deposit_id_from" placeholder="Account number">
			</div>
			<div class="form-group">
				<label for="amount">Amount:</label>
				<input type="text" class="form-control" name="amount" id="amount" placeholder="Amount">
			</div>
			<div class="form-group">
				<button class="btn btn-light">Request</button>
			</div>
		</form>
	</div>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>