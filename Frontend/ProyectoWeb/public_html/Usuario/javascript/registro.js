document.addEventListener("DOMContentLoaded", () => {
    const registroForm = document.getElementById("registroForm");
    const mensajeError = document.getElementById("mensajeError");

    registroForm.addEventListener("submit", (e) => {
        e.preventDefault(); // Prevenir el envío del formulario
        mensajeError.innerHTML = ""; // Limpiar mensajes de error

        if (!registroForm.checkValidity()) {
            mensajeError.innerHTML = 'Por favor completa todos los campos requeridos.';
            return; 
        }

        const nombres = document.getElementById("nombres").value.trim();
        const apellidos = document.getElementById("apellidos").value.trim();
        const correo = document.getElementById("correo").value.trim();
        const verCorreo = document.getElementById("vercorreo").value.trim();
        const contrasena = document.getElementById("contrasena").value;
        const verContrasena = document.getElementById("vercontrasena").value;

        // Validar el formato del correo electrónico
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!emailRegex.test(correo)) {
            mensajeError.innerHTML = "Por favor, ingrese un correo electrónico válido.";
            return;
        }

        // Validar que se ingresen al menos dos apellidos
        const apellidosArray = apellidos.split(" ");
        if (apellidosArray.length < 2) {
            mensajeError.innerHTML = "Por favor, ingrese al menos dos apellidos.";
            return;
        }

        // Validar que el correo y la contraseña coincidan
        if (correo !== verCorreo) {
            mensajeError.innerHTML = "Los correos electrónicos no coinciden.";
            return;
        }

        // Validar que las contraseñas coincidan
        if (contrasena !== verContrasena) {
            mensajeError.innerHTML = "Las contraseñas no coinciden.";
            return;
        }

        // Crear el objeto JSON con los datos del formulario
        const datosRegistro = {
            nombre: nombres,
            apellidos: apellidos,
            correo: correo,
            clave: contrasena
        };

        // Enviar los datos como JSON a través de fetch
        fetch('URL_DEL_SERVIDOR', {  // Reemplaza con la URL de tu servidor
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(datosRegistro)
        })
        .then(response => response.json())
        .then(data => {
            // Respuestas del server
            if (data.success) {
                mensajeError.innerHTML = "Registro exitoso.";
            } else {
                mensajeError.innerHTML = "Hubo un problema con el registro.";
            }
        })
        .catch(error => {
            mensajeError.innerHTML = "Error al enviar los datos: " + error.message;
        });
    });
});




