<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>

</head>
<style>
    #fileContainer {
        border : 1px solid black;
        width : 800px;
        height : 300px;
        overflow-y : auto;
    }

    ul {
        padding-left : 20px;
        list-style: none;
    }

    li {
        display: flex;
        padding-right : 20px;
        align-items : center;
    }

    .folderButton {
        width: 50px;
        height:50px;
    }

    .folderAddress {
        display: flex;
        align-items: center;
    }

    .previousButton {
        width: 30px;
        height: 30px;
        padding : 10px;
    }

    .address {
        font-size: 20px;
        font-weight: bold;
    }
</style>
<body>
    <div>
        <h1>D드라이브</h1>
        <div id="fileContainer">
            <!--
            <ul>
                <li>
                    <img src="/css/image/file.png" width="50" height="50">
                </li>
                <li>
                    <img src="/css/image/folder.png" width="50" height="50">
                </li>
            </ul>
            -->
        </div>
    </div>

    <script src="/js/jquery-3.7.1.min.js"></script>
    <script src="/js/jquery-ui.min.js"></script>
    <script src="/js/file/file.js"></script>
    
</body>
</html>