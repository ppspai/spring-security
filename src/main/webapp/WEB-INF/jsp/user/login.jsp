<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>

    <link rel="styleSheet" type="text/css" href="/css/user/login.css">
</head>

<body>
    <div>
        <div class="loginInput">
            <div>
                <td><input type="text" id="id"></td>
                <td><input type="text" id="password"></td>
            </div>
            <button type="button" id="login">로그인</button>
            <form action="/user/joinPage" method="GET">
                <button type="submit" id="join">회원가입</button>
            </form>
        </div>
    </div>

    <script src="/js/jquery-3.7.1.min.js"></script>
    <script src="/js/jquery-ui.min.js"></script>
    <script src="/js/user/login.js"></script>
    
</body>
</html>