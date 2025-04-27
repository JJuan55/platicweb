document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('recuperarForm');

    form.addEventListener('submit', function (e) {
        if (!form.checkValidity()) {
            e.preventDefault();
            alert('Por favor ingresa un correo válido para recuperar tu contraseña.');
        } else {
            e.preventDefault();
            alert('Se ha enviado un enlace de recuperación a tu correo (simulado).');
            form.reset();
        }
    });
});
