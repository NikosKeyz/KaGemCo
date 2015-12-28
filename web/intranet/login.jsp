<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="author" content="Nikos Katsos, Panos Kostas Gemos">

        <title>KaGemCom Intranet Login</title>
        <style>
            body {
                color: white;
                background-color: rgba( 0, 126, 128, 1);
            }
            main {
                padding-top: 30px;

                width: 50%;
                min-width: 400px;
                max-width: 600px;
                margin: 0 auto;
            }
            form {
                text-align: center;

                background-color: rgba(211, 211, 211, 0.7);
                border: 1px solid lightgrey;
                border-radius: 20px;

                padding: 20px;
            }
            form input {
                padding: 5px;
                margin: 2px;

                width: 50%;
                border-radius: 5px;
            }
            form a {
                color: white;
                font-size: 10pt;
                text-decoration: none; 
            }
            form a:hover {
                font-weight: 500;
                text-decoration: underline;
            }
            form input#login {

                color: white;
                background-color: rgba( 0, 126, 128, 0.4);

                font-size: 12pt;
                font-weight: 600;

                width: 33%;
                min-width: 110px;
                margin-top: 12px;
                padding-left: 20px;
                padding-right: 20px;

                border-radius: 20px;            
            }
            form input#login:hover {
                background-color: rgba( 0, 126, 128, 0.9);
                cursor: pointer;
            }
            #warning {
                color: red;
                margin: 0;
                margin-top: 5px;
            }
            footer {
                position: fixed;
                bottom: 0;

                width: 100%;
                margin: 0 auto;

                text-align: center;
            }

        </style>	
    </head>
    <body>

        <main>
            <form onsubmit="onLogin();return false;">
                <input id="username" type="text"     name="username" placeholder="Ψευδώνυμο"> <br>
                <input id="password" type="password" name="password" placeholder="Κωδικός"> <br>
                <a href="forgot-password.jsp">Ξέχασα τον κωδικό μου</a> <br>
                <input id="login" type="submit" name="login" value="Σύνδεση">
                <p id="warning"></p>
            </form>
        </main>	

        <footer> *Μήν δίνεται τον κωδικό σας σε email. Οι διαχειριστές του συστήματος ποτέ δεν θα σας τον ζητούσαν.*</footer>

        <script src="./js/login.js"></script>
    </body>
</html>
