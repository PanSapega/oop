<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html lang="en_US" class="html">
	<head>
		<title>Data Security</title>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0"> 
		<link href="${pageContext.request.contextPath}/static/styles/ciphers.css" rel="stylesheet" type="text/css">
		<link href="${pageContext.request.contextPath}/static/styles/general-styles.css" rel="stylesheet" type="text/css">
	</head>
	<body>
		<jsp:include page="../parts/header.jsp"/>
		<div class="center-content">
			<jsp:include page="../parts/nav.jsp"/>
			<main>
				<div class="main-header">
					<h1 id="ciphers-text">
						Ciphers
					</h1>
				</div>
				<div class="content">
					<div class="items">
						<a class="cipher-block"
						   href="${pageContext.request.contextPath}/controller?command=columnar-cipher">
							<h1 class="cipher-block-text">
								Columnar Cipher
							</h1>
						</a>
						<a class="cipher-block second-block"
						   href="${pageContext.request.contextPath}/controller?command=vigenere-cipher">
							<h1 class="cipher-block-text">
								Vigenere cipher
							</h1>
						</a>
					</div>
					<div class="items">
						<a class="cipher-block"
						   href="${pageContext.request.contextPath}/controller?command=decimation-cipher">
							<h1 class="cipher-block-text">
								Decimation cipher
							</h1>
						</a>
					</div>
				</div>
			</main>
		</div>
		<jsp:include page="../parts/footer.jsp"/>
	</body>
</html>