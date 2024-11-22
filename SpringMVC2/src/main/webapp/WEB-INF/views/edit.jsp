<!DOCTYPE html>
<%@page import="springmvc.dto.UserDTO"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Edit-Page</title>
<style>
body {
	font-family: 'Arial', sans-serif;
	background-color: #f7f9fc;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
	margin: 0;
}

.signup-container {
	background-color: #ffffff;
	padding: 20px;
	border-radius: 8px;
	box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
	width: 300px;
}

.signup-container h2 {
	text-align: center;
	margin-bottom: 20px;
	color: #333;
}

.input-group {
	margin-bottom: 15px;
}

.input-group label {
	display: block;
	margin-bottom: 5px;
	color: #555;
}

.input-group input {
	width: 100%;
	padding: 10px;
	border: 1px solid #ccc;
	border-radius: 4px;
	box-sizing: border-box;
}

.signup-button {
	width: 100%;
	padding: 10px;
	background-color: #28a745;
	border: none;
	border-radius: 4px;
	color: #fff;
	font-size: 16px;
	cursor: pointer;
	margin: 5px 0px;
	transition: background-color 0.3s;
}

.home {
	display: inline-block; 
	width : 93%;
	text-align:center;
	padding: 10px;
	background-color: #28a745;
	border: none;
	text-decoration: none;
	border-radius: 4px;
	color: #fff;
	font-size: 16px;
	cursor: pointer;
	margin: 5px 0px;
	transition: background-color 0.3s;

}

.signup-button:hover, .home:hover {
	background-color: #218838;
}
</style>
</head>
<body>
	<%
	UserDTO user = (UserDTO) request.getAttribute("user");
	%>
	<div class="signup-container">
		<h2>
			Hello<%=" " + user.getUserName().toUpperCase()%>
		</h2>
		<h2>Edit Now</h2>
		<form action="./update-user" method="post">
			<div class="input-group">
				<label for="id">UserId</label> <input type="text" id="id" name="id"
					value="<%=user.getId()%>" readonly="readonly">
			</div>
			<div class="input-group">
				<label for="username">Username</label> <input type="text"
					id="username" name="username" value="<%=user.getUserName()%>"
					readonly="readonly">
			</div>
			<div class="input-group">
				<label for="email">Email</label> <input type="email" id="email"
					name="email" readonly="readonly" value="<%=user.getEmail()%>">
			</div>
			<div class="input-group">
				<label for="mobile">Mobile No.</label> <input type="tel" id="mobile"
					name="mobile" pattern="[0-9]{10}" value=<%=user.getMobile()%>
					required>
			</div>
			<div class="input-group">
				<label for="password">Password</label> <input type="text"
					id="password" name="password" value=<%=user.getPassword()%>
					required>
			</div>
			<div class="input-group">
				<label for="confirm-password">Confirm Password</label> <input
					type="text" id="confirm-password" name="confirm-password"
					value=<%=user.getPassword()%> required>
			</div>

			<button type="submit" value="Save" class="signup-button">Save</button>
			<a href="home" class="home">HOME</a>


		</form>
	</div>
</body>
</html>
