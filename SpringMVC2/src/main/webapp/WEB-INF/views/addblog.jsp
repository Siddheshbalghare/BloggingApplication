<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create Blog Post</title>
<style>
body {
	font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
	background-color: #f8f9fa;
	margin: 0;
	padding: 0;
	color: #495057;
}

.container {
	width: 50%;
	margin: 40px auto;
	background-color: #ffffff;
	padding: 30px;
	box-shadow: 0 8px 16px rgba(0, 0, 0, 0.1);
	border-radius: 12px;
}

h1 {
	text-align: center;
	color: #343a40;
	font-size: 28px;
	margin-bottom: 20px;
}

label {
	display: block;
	font-weight: 600;
	margin-bottom: 8px;
	color: #495057;
	font-size: 16px;
}

input[type="text"], textarea {
	width: 96%;
	padding: 12px;
	margin-bottom: 20px;
	border: 2px solid #ced4da;
	border-radius: 6px;
	font-size: 16px;
	background-color: #f8f9fa;
	transition: border-color 0.3s ease;
}

input[type="text"]:focus, textarea:focus {
	border-color: #007bff;
	outline: none;
	background-color: #ffffff;
}

textarea {
	height: 150px;
	resize: vertical;
}

.btn {
	display: inline-block;
	padding: 12px 24px;
	background-color: #007bff;
	color: #ffffff;
	text-decoration: none;
	border-radius: 6px;
	border: none;
	font-size: 16px;
	cursor: pointer;
	transition: background-color 0.3s ease, transform 0.2s ease;
	margin-top: 10px;
	text-align: center;
}

.btn:hover {
	background-color: #0056b3;
	transform: scale(1.05);
}

.btn:active {
	background-color: #004085;
}

.btn:focus {
	outline: none;
}

.error {
	color: red;
	font-size: 14px;
}

@media screen and (max-width: 768px) {
	.container {
		width: 90%;
	}
}
</style>
</head>
<body>

	<div class="container">
		<h1>Create New Blog Post</h1>
		<form action="./addblog" method="post">
			<label for="title">Blog Title:</label> <input type="text" id="title"
				name="title" placeholder="Enter the blog title" required /> <label
				for="content">Content:</label>
			<textarea id="content" name="content"
				placeholder="Write the blog content here..." required></textarea>

			<label for="author">Author:</label> <input type="text" id="author"
				name="author" placeholder="Enter the author's name" required /> 
				<input type="submit" class="btn" value="Submit Blog" />
				<a class="btn"  href="home">HOME</a>
		</form>
	</div>

</body>
</html>
