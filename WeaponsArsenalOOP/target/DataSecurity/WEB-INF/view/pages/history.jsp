<html lang="en" class="html">
    <head>
        <title>Data Security</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="${pageContext.request.contextPath}/static/styles/history.css" rel="stylesheet" type="text/css">
        <link href="${pageContext.request.contextPath}/static/styles/general-styles.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <jsp:include page="../parts/header.jsp"/>
        <div class="center-content">
            <jsp:include page="../parts/nav.jsp"/>
            <main>
                <div class="main-header">
                    <h1 id="history-info-text">
                        History
                    </h1>
                </div>
                <div class="content">
                    <div class="history-description">
                        <div class="history-header">
                            <h1 class="date-text">
                                12.02.2021 17:46
                            </h1>
                            <h1 class="cipher-text">
                                Cipher: Atbash
                            </h1>
                        </div>
                        <div class="cipher-content">
                            <div class="flipper">
                                <div class="front">
                                    <h1 class="cipher-data-text">
                                        Plaintext: AbA
                                    </h1>
                                </div>
                                <div class="back">
                                    <h1 class="cipher-data-text">
                                        Ciphertext: ZyZ
                                    </h1>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="history-description">
                        <div class="history-header">
                            <h1 class="date-text">
                                12.02.2021 17:55
                            </h1>
                            <h1 class="cipher-text">
                                Cipher: Caeasar
                            </h1>
                        </div>
                        <div class="cipher-content">
                            <div class="flipper">
                                <div class="front">
                                    <h1 class="cipher-data-text">
                                        Plaintext: AbcZ
                                    </h1>
                                </div>
                                <div class="back">
                                    <h1 class="cipher-data-text">
                                        Ciphertext: BcdA
                                    </h1>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </main>
        </div>
        <jsp:include page="../parts/footer.jsp"/>
    </body>
</html>