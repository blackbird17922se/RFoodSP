
async function login(params) {

    const form       = document.getElementById('login-form');
    const formData   = new FormData(form);
    const nomUsuario = formData.get('nomUsuario');
    const contrasena = formData.get('contrasena');
    const url        = 'http://localhost:8080/login';


    try {
        const response = await fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({nomUsuario, contrasena})
        });

        if (response.ok) {
            const result = await response.text();
            // alert(result);
            window.location.href="pages/main.html";
        } else {
            alert('Credenciales incorrectas');
        }
    } catch (error) {
        console.error('Error al autenticar:', error);
    }
    
}