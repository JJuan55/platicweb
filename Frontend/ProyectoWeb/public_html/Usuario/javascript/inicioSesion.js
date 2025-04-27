document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('loginForm');

    form.addEventListener('submit', function (e) {
        if (!form.checkValidity()) {
            e.preventDefault(); // Previene el envío si hay campos inválidos
            alert('Por favor completa todos los campos requeridos con un formato valido.');
        }
    });
});

