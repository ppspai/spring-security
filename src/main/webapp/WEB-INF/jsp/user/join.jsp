<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>

    <link rel="styleSheet" type="text/css" href="/css/user/join.css">
</head>

<body>
    <div>
        <div>
            <div>
                <h2>회원가입</h2>
            </div>
            <div>
                <table>
                    <tr>
                        <td>이름</td>
                        <td><input type="text" id="joinName"></td>
                    </tr>
                    <tr>
                        <td>아이디</td>
                        <td><input type="text" id="joinId"></td>
                    </tr>
                    <tr>
                        <td>패스워드</td>
                        <td><input type="text" id="joinPassword"></td>
                    </tr>
                    <tr>
                        <td>이메일</td>
                        <td><input type="text" id="joinEmail"></td>
                    </tr>
                </table>
                <button type="button" id="join">회원가입</button>
            </div>
        </div>
    </div>

    <script src="/js/jquery-3.7.1.min.js"></script>
    <script src="/js/jquery-ui.min.js"></script>
    <script src="/js/user/join.js"></script>
    
</body>
</html>