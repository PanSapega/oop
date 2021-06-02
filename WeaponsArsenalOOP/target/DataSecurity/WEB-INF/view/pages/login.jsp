<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html lang="en_US" class="html">
	<head>
		<title>Data Security</title>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0"> 
		<link href="${pageContext.request.contextPath}/static/styles/login.css" rel="stylesheet" type="text/css">
		<link href="${pageContext.request.contextPath}/static/styles/general-styles.css" rel="stylesheet" type="text/css">
	</head>
	<body>
		<jsp:include page="../parts/header.jsp"/>
		<div class="center-content">
			<jsp:include page="../parts/nav.jsp"/>
			<main>
				<div class="main-header">
					<h1 id="log-in-text">
						Log in
					</h1>
				</div>
				<div class="content">
					<a href="${pageContext.request.contextPath}/controller?command=sing-up">
						<div class="sign-up-block">
							<span class="sign-click-text">
								Click for 
							</span>
							<span class="sign-up-text">
								SIGN UP
							</span>
						</div>
					</a>
					<form class="login-form" method="POST" action="${pageContext.request.contextPath}/controller?command=info">
						<div class="username-input">
							<h1>
								Username:
							</h1>
							<input type="text"
							   placeholder="Input Username"
							   name="login" maxlength="20" required>
						</div>
						<div class="password-input">
							<h1>
								Password:
							</h1>
							<input type="password"
							   placeholder="Input Password"
							   name="password" maxlength="25" required>
						</div>
						<button type="submit">
							Submit
						</button>
					</form>
                </div>
			</main>
		</div>
		<jsp:include page="../parts/footer.jsp"/>
	</body>
</html>