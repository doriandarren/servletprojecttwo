<%@ include file="templates/header.jsp" %>  

<title>Card</title>

<%@ include file="templates/menu.jsp" %>


<div class="container">
	<div class="row">
		<div class="col-md-12 text-center">
			<h1>Card</h1>
		</div>
		
		<div class="col-md-12 text-center">
			<h1>Empezar a jugar</h1>
		</div>
		<form action="./servlet/card" method="post" class="form-horizontal">
			<div class="form-group">
                <div class="col-md-6 col-md-offset-4">
					<input type="submit" class="btn btn-primary" role="button" value="Submit" />
				</div>
			</div>
		</form>
		
	</div>
</div>

<%@ include file="templates/footer.jsp" %>  