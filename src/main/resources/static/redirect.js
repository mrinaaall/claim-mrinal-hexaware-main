// ToDo: Write code to redirect this back to LogIn page upon successful submission of details.
const registerButton = document.getElementById("register-submit");
const registerForm = document.getElementById("login-form");

registerButton.addEventListener("click", (e) => {
    e.preventDefault();
    const username = registerForm.username.value;
    const password = registerForm.password.value;
    const firstName = registerForm.firstname.value;
    const lastName = registerForm.lastname.value;
    const email = registerForm.email.value;

    var regEmail = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/g;  //Javascript reGex for Email Validation.
    var regName = /\d+$/g;                                    // Javascript reGex for Name validation

    if (firstName == null || firstName === "" || regName.test(firstName)) {
        window.alert('Please enter valid first name !');
    } else if (lastName == null || lastName === "") {
        window.alert('Please enter valid last name !');
    } else if (email == null || email === "" ) {
        window.alert('Please enter valid email !');
    } else if (username == null || username === "") {
        window.alert('Please enter valid user name !');
    } else if (password == null || password === "") {
        window.alert('Please enter valid password !');
    } else {

        const body = {
            "firstName": firstName,
            "lastName": lastName,
            'email': email,
            "userName":username,
            "password":password
        }

        const url = "http://localhost:8080/api/register"

        fetch(url,{
            headers: {
                'content-type': 'application/json;charset=utf-8',
            }, method: 'POST', body: JSON.stringify(body)
        }).then(res => res.json())
            .then(data => {
                console.log('Data', data);
                location.href = "index.html";
        }).catch(err=>{
            window.alert("Username already exists !")
            console.log('err', err);
        })


    }
})
