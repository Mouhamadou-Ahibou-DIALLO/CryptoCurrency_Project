import React from "react";
import "../static/css/dashboard.css";

function Dashboard() {
    const handleLogout = () => {
        window.location.href = "/Login";
    };

    return (
        <div className="dashboard-container">
            <header className="dashboard-header">
                <h1>La cryptomonnaie de l'avenir</h1>
                <div className="header-buttons">
                    <button>Créer une alerte</button>
                    <button>Notifications</button>
                    <button>Profil utilisateur</button>
                    <button onClick={handleLogout}>Déconnexion</button>
                </div>
            </header>
            <main>
                <h2>Bienvenue dans votre compte utilisateur</h2>
            </main>
        </div>
    );
}

export default Dashboard;
