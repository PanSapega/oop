<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<nav>
    <a href="${pageContext.request.contextPath}/controller?command=info">
        <div class="info-block">
            <img class="image" src="${pageContext.request.contextPath}/static/images/info.png"/>
            <h1>
                Info
            </h1>
        </div>
    </a>
    <a href="${pageContext.request.contextPath}/controller?command=ciphers">
        <div class="ciphers-block">
            <img class="image" src="${pageContext.request.contextPath}/static/images/ciphers.png"/>
            <h1>
                Ciphers
            </h1>
        </div>
    </a>
    <a href="${pageContext.request.contextPath}/controller?command=history">
        <div class="history-block">
            <img class="image" src="${pageContext.request.contextPath}/static/images/history.png"/>
            <h1>
                History
            </h1>
        </div>
    </a>
</nav>