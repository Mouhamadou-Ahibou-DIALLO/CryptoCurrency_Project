import React, {useState} from "react";
import {Link} from "react-router-dom";
import '../static/css/resetPassword.css';

const ResetPassword = () => {
    const [passwordHash, setPassword] = useState("");
    const [confirmPassword, setConfirmPassword] = useState("");
    const [message, setMessage] = useState("");

    const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;

    const handleSubmit = async (e) => {
        e.preventDefault();
        const urlParams = new URLSearchParams(window.location.search);
        const token = urlParams.get("token");

        if (!passwordRegex.test(passwordHash)) {
            setMessage("Le mot de passe doit contenir au moins 8 caractères, une lettre majuscule, une lettre minuscule, un chiffre, et un caractère spécial.");
            return;
        }

        if (passwordHash !== confirmPassword) {
            setMessage("Les mots de passe ne correspondent pas.");
            return;
        }

        try {
            const response = await fetch("/api/users/reset-password", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ token, passwordHash }),
            });
            const data = await response.json();
            if (response.ok) {
                setMessage("Mot de passe réinitialisé avec succès.");
            } else {
                setMessage(data.error || "Erreur lors de la réinitialisation.");
            }
        } catch (error) {
            setMessage("Erreur de connexion au serveur.");
        }
    };

    return (
        <div className="reset-password">
            <header className="header">
                <h1>La Cryptomonnaie de l'avenir</h1>
                <Link to="/">
                    <button className="reset-accueil">Accueil</button>
                </Link>
                <Link to="/Register">
                    <button className="reset-register">Inscription</button>
                </Link>
                <Link to="/Login">
                    <button className="connexion">Connexion</button>
                </Link>
            </header>
            <form onSubmit={handleSubmit} className="reset-password-form">
                <h2>Réinitialiser le mot de passe</h2>
                <input
                    type="password"
                    value={passwordHash}
                    onChange={(e) => setPassword(e.target.value)}
                    placeholder="Nouveau mot de passe"
                    required
                />
                <input
                    type="password"
                    value={confirmPassword}
                    onChange={(e) => setConfirmPassword(e.target.value)}
                    placeholder="Confirmez le mot de passe"
                    required
                />
                <button type="submit">Réinitialiser</button>
                <p className={message.includes("Erreur") ? "error" : "success"}>{message}</p>
            </form>
        </div>
    );
};

export default ResetPassword;