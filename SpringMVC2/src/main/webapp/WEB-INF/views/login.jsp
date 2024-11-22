<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Login Page</title>
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

.login-container {
	background-color: #ffffff;
	padding: 20px;
	border-radius: 8px;
	box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
	width: 300px;
}

.login-container h2 {
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

.login-button {
	width: 100%;
	padding: 10px;
	background-color: #007bff;
	border: none;
	border-radius: 4px;
	color: #fff;
	font-size: 16px;
	cursor: pointer;
	transition: background-color 0.3s;
}

.login-button:hover {
	background-color: #0056b3;
}
</style>
</head>
<body>
	<div class="login-container">
		<h2>Login</h2>
		<form action="./login" method="post">
			<div class="input-group">
				<label for="email">Email</label> <input type="text" id="email"
					name="email" required>
			</div>
			<div class="input-group">
				<label for="password">Password</label> <input type="password"
					id="password" name="password" required>
			</div>
			<button type="submit" value="Login" class="login-button">Login</button>
			<h6>
				New User?Sign up <a href="signup">here</a>
			</h6>
			<%
			String message = (String) request.getAttribute("message");
			if (message != null) {
			%>
			<h3 style=color:red><%=message%></h3>
			<%
			}
			%>
		</form>
	</div>
</body>
</html>
