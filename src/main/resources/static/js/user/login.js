$(document).ready(function() {
    $('#login').click(function() {
        let id = $('#id').val();
        let password = $('#password').val();

        login(id, password);

    })
})

const login = async (id, password) => {
    const response = await fetch("/user/login" , {
        headers : {
            "content-type" : "application/json",
        },
        body : "{\"id\":\"" + id + "\",\"password\":\""+ password+"\"}",
        method : "POST"
    });

    const token = response.headers.get("Authorization");
    console.log("token : " + token);
    if(token) {
        console.log("save");
        localStorage.setItem('token', token);
    }

    const jsonData = await response.json();
    console.log(jsonData);
    location.replace("/file/file");
};

const login2 = async (id, password) => {
    const response = await fetch("/user/login", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            id: id,
            password: password
        })
    });
};