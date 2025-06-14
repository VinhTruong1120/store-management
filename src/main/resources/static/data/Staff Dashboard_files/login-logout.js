
function capitalizeFirstLetter(text) {
    return text.charAt(0).toUpperCase() + text.slice(1);
}

document.getElementById("login-form").addEventListener("submit", function (e) {
    e.preventDefault();

    const usernameInput = document.getElementById("username").value;
    const passwordInput = document.getElementById("password").value;
    const status = document.getElementById("login-status");

    fetch("./data/user.json")
        .then(response => {
            if (!response.ok) throw new Error("Cannot load user.json");
            return response.json();
        })
        .then(users => {
            const found = users.find(u => u.username === usernameInput && u.password === passwordInput);
            if (found) {
                localStorage.setItem("loggedInUser", JSON.stringify(found));
                status.innerText = `✅ Welcome, ${found.username} (${found.role})`;
                showDashboard();
            } else {
                status.innerText = "❌ Wrong Username or Password.";
            }
        })
        .catch(error => {
            console.error("Error when opening user.json", error);
            status.innerText = "⚠️ Error when connecting to server.";
        });
});

function showDashboard() {
    const user = JSON.parse(localStorage.getItem("loggedInUser"));
    const loginScreen = document.getElementById("login-screen");
    const sidebar = document.getElementById("sidebar");
    const main = document.getElementById("main-content");
    const roleDisplay = document.getElementById("role");

    if (loginScreen) loginScreen.classList.add("hidden");
    if (sidebar) sidebar.style.display = "flex";
    if (main) main.style.display = "block";
    if (roleDisplay && user?.role) {
        const roleText = capitalizeFirstLetter(user.role);
        roleDisplay.innerHTML = `<i class="fa-solid fa-store"></i> ${roleText}`;

        if (user.role === "cashier") {
            document.querySelector(".nav-goods")?.classList.add("d-none");
            document.querySelector(".nav-staff")?.classList.add("d-none");
            document.querySelector(".nav-after-sale")?.classList.add("d-none");
        }
    }
}


function logout() {
    localStorage.removeItem("loggedInUser");
    location.reload();
}

window.addEventListener("DOMContentLoaded", () => {
    const user = localStorage.getItem("loggedInUser");
    if (user) {
        showDashboard();
    } else {
        const loginScreen = document.getElementById("login-screen");
        const sidebar = document.getElementById("sidebar");
        const main = document.getElementById("main-content");
        if (loginScreen) loginScreen.classList.remove("hidden");
        if (sidebar) sidebar.style.display = "none";
        if (main) main.style.display = "none";
    }
});
