function manejarSesion() {
    const sesionIniciada = localStorage.getItem("usuario");
    const loginBtn = document.getElementById("loginBtn");
    const profileIcon = document.getElementById("profileIcon");

    if (loginBtn && profileIcon) {
        if (sesionIniciada) {
            loginBtn.style.display = "none";
            profileIcon.style.display = "inline-block";
        } else {
            loginBtn.style.display = "inline-block";
            profileIcon.style.display = "none";
        }
    }
}
function manejarMenuPerfil() {
    const profileIcon = document.getElementById("profileIcon");
    const profileMenu = document.getElementById("profileMenu");

    if (!profileIcon || !profileMenu)
        return;

    profileIcon.addEventListener("click", (e) => {
        e.preventDefault();
        profileMenu.style.display = profileMenu.style.display === "block" ? "none" : "block";
    });

    document.addEventListener("click", function (e) {
        if (!profileIcon.contains(e.target) && !profileMenu.contains(e.target)) {
            profileMenu.style.display = "none";
        }
    });
}
document.addEventListener("DOMContentLoaded", manejarSesion);

