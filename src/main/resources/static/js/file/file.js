$(document).ready(function() {
    loadFile(defaultPath);
})

let defaultPath = "D:\\"

const loadFile = (path) => {
    
    const token = localStorage.getItem('token');
    console.log(token);

    $.ajax({
        url: "/file/fileSystem",
        type: "post",
        async: true,
        beforeSend: function(xhr) {
            xhr.setRequestHeader('Authorization', token); // JWT 토큰 추가
        },
        data: {
            path
        },
        success: function(response) {
            console.log(response);
            let childFiles = response.file.files;

            const fileContainer = document.getElementById("fileContainer");
            fileContainer.innerHTML='';

            let div = document.createElement("div");
            div.className = "folderAddress";
            let previousPath = response.file.parentPath;
            let previousButton = document.createElement("img");
            previousButton.src = "/css/image/left-arrow.png";
            previousButton.className = "previousButton";

            if(previousPath != null) {
                previousPath = previousPath.replace("\\", "\\\\") + "\\\\";
                previousButton.onclick = () => loadFile(previousPath);
            }
            
            let folderAddress = document.createElement("span");
            folderAddress.textContent = response.file.path;
            folderAddress.className="address";
            div.appendChild(previousButton);
            div.appendChild(folderAddress);

            fileContainer.appendChild(div);
            

            let ul = document.createElement("ul");
            childFiles.forEach(file => {
                let li = document.createElement("li");
                let nameNode = document.createTextNode(file.fileName);
                let img = document.createElement("img");
                img.className="folderButton";
                if(file.type === "folder") {
                    img.src="/css/image/folder.png";
                    let folderPath = file.path;
                    folderPath = folderPath.replace("\\", "\\\\") + "\\\\";
                    img.setAttribute("path", folderPath);
                    img.onclick = () => loadFile(folderPath);
                } else if(file.type === "file") {
                    img.src="/css/image/file.png";
                }
                
                /*
                img.onclick = function() {
                    // 클릭 시 실행될 기능을 이곳에 작성
                    console.log("이미지를 클릭했습니다.");
                };
                */
                
                li.appendChild(img);
                li.appendChild(nameNode);
                ul.appendChild(li);
            })

            
            fileContainer.appendChild(ul);
        }
    })
}