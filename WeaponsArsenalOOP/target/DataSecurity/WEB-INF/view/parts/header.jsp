<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<header>
    <a href="${pageContext.request.contextPath}/controller?command=info">
        <div class="logo">
            <h1>
                <span id="first-logo-word">Data</span>
                <span id="second-logo-word">Security</span>
            </h1>
        </div>
    </a>
    <div class="header-nav">
        <div class="lang-menu">
            <h1 id="lang-text">
                Language
            </h1>
            <ul>
                <li>
                    <h1>
                        English
                    </h1>
                </li>
                <li>
                    <h1>
                        Русский
                    </h1>
                </li>
                <li>
                    <h1>
                        Беларуская
                    </h1>
                </li>
            </ul>
        </div>
        <a href="${pageContext.request.contextPath}/controller?command=login">
            <div class="login">
                <h1 id="login-text">
                    Login
                </h1>
            </div>
        </a>
    </div>
</header>
