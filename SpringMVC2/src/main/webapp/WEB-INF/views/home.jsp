
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="springmvc.dto.Role"%>
<%@page import="springmvc.dto.UserDTO"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>My Beautiful Home Page</title>
<style>
* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
}

body {
	font-family: 'Arial', sans-serif;
	line-height: 1.6;
	color: #333;
	background-color: black;
}

header {
	background: #4a90e2;
	color: #fff;
	padding: 1rem 0;
	position: fixed;
	width: 100%;
	top: 0;
	z-index: 1000;
}

nav {
	display: flex;
	justify-content: center;
}

.nav-menu {
	list-style: none;
	display: flex;
}

.nav-menu li {
	margin: 0 15px;
}

.nav-menu a {
	color: #fff;
	text-decoration: none;
	font-weight: bold;
	transition: color 0.3s ease;
}

.nav-menu a:hover {
	color: #ff6347;
}

main {
	padding: 2rem;
	padding-top: 5rem;
}

.section {
	margin-bottom: 2rem;
}

.home {
	background-color: aqua;
	background-image:
		url("${pageContext.request.contextPath}/resources/img/bg2.jpg");
	background-position: center;
	background-repeat: no-repeat;
	background-size: cover;
	color: red;
	text-align: center;
	padding: 2rem 2rem;
	min-height: 100vh;
	background-position: center;
	color: red;
}

.about, .contact, .logout {
	background: #fff;
	border: 1px solid #ddd;
	border-radius: 8px;
	padding: 2rem;
	margin-bottom: 1rem;
	box-shadow: 0 4px 8px #777;
}

.about {
	background-color: #e6f9ff;
}

.contact {
	background-color: #f9e6ff;
}

.logout {
	background-color: #e6ffe6;
}

footer {
	background: #4a90e2;
	color: #fff;
	text-align: center;
	padding: 1rem 0;
	position: fixed;
	width: 100%;
	bottom: 0;
}
</style>
</head>
<body>
	<header>
		<nav>
			<ul class="nav-menu">
				<%
				UserDTO user=(UserDTO) request.getAttribute("user");
				if (user.getRole().equals(Role.USER)) {
				%>
				<li><a href="#home">Home</a></li>
				<li><a href="#about">About</a></li>
				<li><a href="#contact">Contact</a></li>
				<li><a href="./logout" id="logoutBtn">Logout</a></li>
				<li><a href="./delete" id="deleteBtn">Delete Account</a></li>
				<li><a href="./update" id="updateBtn">Update Profile</a></li>
				<li><a href="./add-blog" id="blogBtn">Add Blog</a></li>
				<li><a href="./all-blogs" id="blogBtn">All Blog</a></li>
				<li><a href="./my-blogs" id="blogBtn">My Blog</a></li>
				<%
				} else {
				%>
				<li><a href="#home">Home</a></li>
				<li><a href="#about">About</a></li>
				<li><a href="#contact">Contact</a></li>
				<li><a href="./logout" id="logoutBtn">Logout</a></li>
				<li><a href="./delete" id="deleteBtn">Delete Account</a></li>
				<li><a href="./update" id="updateBtn">Update Profile</a></li>
				<li><a href="./all-blogs" id="blogBtn">All Blog</a></li>
				<li><a href="./allusers">All User</a></li>
				<%
				}
				%>
			</ul>
		</nav>
	</header>

	<main>
		<section id="home" class="section home">
			<!--<img src="${pageContext.request.contextPath}/resources/img/edit.png" alt="img">-->
			<h1>Welcome to My Website</h1>
			<p>Your journey to amazing experiences starts here.</p>
		</section>
		<section id="about" class="section about">
			<h2>About Us</h2>
			<p>Learn more about who we are and what we do.</p>
		</section>

		<section id="contact" class="section contact">
			<h2>Contact Us</h2>
			<p>Get in touch with us for any inquiries or support.</p>
		</section>

		<section id="logout" class="section logout">
			<h2>Logout</h2>
			<p>
				Thank you for visiting. Click <a href="#home">here</a> to return to
				the homepage.
			</p>
		</section>
	</main>

	<footer>
		<p>&copy; 2024 Sid Balghare Home Page. All rights reserved.</p>
	</footer>

	<script>
	<%String message = (String) request.getAttribute("message");
if (message != null) {%>
	alert('<%=message%>
		');
		n++;
	<%}%>
		document.getElementById('logoutBtn').addEventListener('click',
				function(event) {
					event.preventDefault();
					alert('You have been logged out.');
					window.location.href = './logout';
				});
	</script>
</body>
</html>
