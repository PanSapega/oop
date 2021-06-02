<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en_US" class="html">
<head>
    <title>Data Security</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="${pageContext.request.contextPath}/static/styles/columnar.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/static/styles/general-styles.css" rel="stylesheet" type="text/css">
</head>
<body>
<jsp:include page="../parts/header.jsp"/>
<div class="center-content">
    <jsp:include page="../parts/nav.jsp"/>
    <main>
        <div class="main-header">
            <h1 id="caesar-text">
                Vigenere cipher
            </h1>
        </div>
        <div class="content">
            <form class="form" method="POST"
                  action="${pageContext.request.contextPath}/controller?command=vigenere-cipher-resolve">
                <div class="enter-data">
                    <h1>
                        Load data file or enter below:
                    </h1>
                    <input type="file" name="file" id="selectedFile" onchange="loadFile(this)" style="display: none;" />
                    <input type="button" value="Browse..." onclick="browse()" />
                </div>
                <textarea name="data" class="data" id="data" placeholder="Enter data here" required><c:if test="${not empty data}">${data}</c:if></textarea>
                <h1>
                    Enter key:
                </h1>
                <textarea name="key" class="data" placeholder="Enter key here" required><c:if test="${not empty key}">${key}</c:if></textarea>
                <div class="buttons">
                    <button type="submit" id="encrypt-button" onclick="encrypt()">
                        Encrypt
                    </button>
                    <button type="submit" id="decrypt-button">
                        Decrypt
                    </button>
                </div>
                <input type="text" name="isEncrypting" id="isEncrypting" value="false"/>
            </form>
            <h1 id="result">
                Result:
            </h1>
            <textarea placeholder="Here will be the result" id="resultData"
                      readonly class="data" required><c:if test="${not empty result}">${result}</c:if></textarea>
            <c:if test="${not empty errorMessage}">
                <h1 id="error-message">
                        ${errorMessage}
                </h1>
            </c:if>
        </div>
    </main>
</div>
<jsp:include page="../parts/footer.jsp"/>
</body>
</html>

<script src="<c:url value="/static/js/cipher.js"/>"></script>