<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Sign Up Page</title>
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
	transition: background-color 0.3s;
}

.signup-button:hover {
	background-color: #218838;
}
</style>
</head>
<body>
	<div class="signup-container">
		<h2>Sign Up</h2>
		<form action="./users" method="post">
			<div class="input-group">
				<label for="username">Username</label> <input type="text"
					id="username" name="username" required>
			</div>
			<div class="input-group">
				<label for="email">Email</label> <input type="email" id="email"
					name="email" required>
			</div>
			<div class="input-group">
				<label for="mobile">Mobile No.</label> <input type="tel" id="mobile"
					name="mobile" pattern="[0-9]{10}" required>
			</div>
			<div class="input-group">
				<label for="password">Password</label> <input type="password"
					id="password" name="password" required>
			</div>
			<div class="input-group">
				<label for="confirm-password">Confirm Password</label> <input
					type="password" id="confirm-password" name="confirm-password"
					required>
			</div>
			<div class="input-group">
				<select name="role">
							<option disabled="disabled" selected="selected">Select Role</option>
							<option value="ADMIN">ADMIN</option>
							<option value="USER">USER</option>
					</select>			</div>

			<button type="submit" value="signup" class="signup-button">Sign
				Up</button>
		</form>
	</div>
</body>
</html>
