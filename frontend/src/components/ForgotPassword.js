import React, {useState} from "react";
import {Link} from "react-router-dom";
import "../static/css/forgotPassword.css";

const ForgotPassword = () => {
    const [email, setEmail] = useState("");
    const [message, setMessage] = useState("");

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await fetch("/api/users/forgot-password", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ email }),
            });
            const data = await response.json();
            if (response.ok) {
                setMessage("Un lien de réinitialisation a été envoyé à votre email.");
            } else {
                setMessage(data.error || "Erreur lors de la demande.");
            }
        } catch (error) {
            setMessage("Erreur de connexion au serveur.");
        }
    };

    return (
        <div className="forgot-password">
            <header className="header">
                <h1>La Cryptomonnaie de l'avenir</h1>
                <Link to="/">
                    <button>Accueil</button>
                </Link>
                <Link to="/Register">
                    <button>Inscription</button>
                </Link>
                <Link to="/Login">
                    <button className="connexion">Connexion</button>
                </Link>
            </header>

            <form onSubmit={handleSubmit}>
                <h2>Mot de passe oublié</h2>
                <input
                    type="email"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                    placeholder="Votre email"
                    required
                />
                <button type="submit">Envoyer</button>
                <p>{message}</p>
            </form>
        </div>
)
};
export default ForgotPassword;
