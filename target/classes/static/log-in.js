const loginForm = document.getElementById("login-form");
const loginButton = document.getElementById("login-form-submit");
const registerButton = document.getElementById("register-submit");
const loginErrorMsg = document.getElementById("login-error-msg");

loginButton.addEventListener("click", (e) => {
    e.preventDefault();

    // Getting the username and password from form
    const username = loginForm.username.value;
    const password = loginForm.password.value;

    // Check if it matches the database entry:


    if (username == null || username === "") {
        window.alert('Please enter valid user name !');
    } else if (password == null || password === "") {
        window.alert('Please enter valid password !');
    } 
        // Add else if to check if username and password exist in the database: ToDo - Make it DYNAMIC!!
        else if (username != "mrinaaall" || password != "abc123") {
            window.alert('Incorrect LogIn Details')
        }

        else {
        const url = "http://localhost:8080/api/login";

        const body = {
            "userName": username,
            "password": password
        }

        fetch(url, {
            headers: {
                'content-type': 'application/json;charset=utf-8',
            },
            method: "POST",
            body: JSON.stringify(body)
        }).then(res => res.json())
            .then(data => {
                sessionStorage.setItem("userName", data.userName);
                sessionStorage.setItem("firstName", data.firstName);
                sessionStorage.setItem("lastName", data.lastName);
                sessionStorage.setItem("id", data.id);
                sessionStorage.setItem("email", data.email);
                console.log('data', data);
                location.href = "claimsData.html";
            }).catch(err => console.log('err', err)
        )
    }
})

registerButton.addEventListener("click", (e)=>{
    e.preventDefault();
    document.location.href="register.html"
})