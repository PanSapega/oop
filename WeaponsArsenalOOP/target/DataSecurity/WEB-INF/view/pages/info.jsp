<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html lang="en_US" class="html">
	<head>
		<title>Data Security</title>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0"> 
		<link href="${pageContext.request.contextPath}/static/styles/info.css" rel="stylesheet" type="text/css">
		<link href="${pageContext.request.contextPath}/static/styles/general-styles.css" rel="stylesheet" type="text/css">
	</head>
	<body>
		<jsp:include page="../parts/header.jsp"/>
		<div class="center-content">
			<jsp:include page="../parts/nav.jsp"/>
			<main>
				<div class="main-header">
					<h1 id="website-info-text">
						Website info
					</h1>
				</div>
				<div class="content">
					<div class="first-info-block">
						<img class="first-info-image" src="${pageContext.request.contextPath}/static/images/ciphers.png"/>
						<h1 class="first-info-text">
							This website is the universal site for decoding/encoding data using popular ciphers. All tools are available in <span id="cipher-text">Ciphers</span> item menu. 
						</h1>
					</div>
					<div class="second-info-block">
						<div class="second-info-text-block">
							<h1>
								Our website provides completely free functionality! 
							</h1>
							<h1 id="feedback-text">
								A suggestion? a feedback? a bug? an idea ? Write to us: yeeeeeah.right@gmail.com
							</h1>
						</div>
						<img class="second-info-image" src="${pageContext.request.contextPath}/static/images/bill.png"/>
					</div>
				</div>
			</main>
		</div>
		<jsp:include page="../parts/footer.jsp"/>
	</body>
</html>