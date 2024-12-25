import React, { useState } from "react";
import "../static/css/login.css";

function Login() {
    const [formData, setFormData] = useState({
        email: "",
        password: "",
    });
    const [errors, setErrors] = useState({});
    const [successMessage, setSuccessMessage] = useState("");
    const [tokenPopup, setTokenPopup] = useState(false);
    const [token, setToken] = useState("");
    const [email, setEmail] = useState("");

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setFormData((prev) => ({ ...prev, [name]: value }));
    };

    const validateForm = () => {
        const newErrors = {};

        if (!formData.email.trim()) {
            newErrors.email = "L'email est obligatoire.";
        }

        if (!formData.password.trim()) {
            newErrors.password = "Le mot de passe est obligatoire.";
        }

        setErrors(newErrors);
        return Object.keys(newErrors).length === 0;
    };

    const handleLogin = async (e) => {
        e.preventDefault();
        if (!validateForm()) return;

        const username = "momo";
        const password = "Avignon2024@?";
        const credentials = btoa(`${username}:${password}`);

        try {
            const response = await fetch("/api/users/login", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    Authorization: `Basic ${credentials}`,
                },
                body: JSON.stringify(formData),
            });

            const data = await response.text();
            if (response.ok) {
                setSuccessMessage("Connexion réussie !");
                setEmail(formData.email);
                setTokenPopup(true);
                setErrors({});
            } else {
                setErrors({ apiError: data });
            }
        } catch (error) {
            setErrors({ apiError: "Erreur de connexion au serveur." });
        }
    };

    const handleVerifyToken = async () => {
        const username = "momo";
        const password = "Avignon2024@?";
        const credentials = btoa(`${username}:${password}`);

        try {
            const response = await fetch("/api/users/verify-token", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    Authorization: `Basic ${credentials}`,
                },
                body: JSON.stringify({ email, token }),
            });

            const data = await response.text();
            if (response.ok) {
                alert("Token vérifié avec succès !");
                window.location.href = "/Dashboard";
            } else {
                alert(data);
            }
        } catch (error) {
            alert("Erreur de connexion au serveur.");
        }
    };

    return (
        <div className="login-container">
            <header className="login-header">
                <h1>La cryptomonnaie de l'avenir</h1>
                <button className="header-btn" onClick={() => (window.location.href = "/register")}>
                    S'inscrire
                </button>
            </header>
            <form className="login-form" onSubmit={handleLogin}>
                <h2>Connexion</h2>

                <label>
                    Email :
                    <input
                        type="email"
                        name="email"
                        value={formData.email}
                        onChange={handleInputChange}
                    />
                </label>
                {errors.email && <p className="error">{errors.email}</p>}

                <label>
                    Mot de passe :
                    <input
                        type="password"
                        name="password"
                        value={formData.password}
                        onChange={handleInputChange}
                    />
                </label>
                {errors.password && <p className="error">{errors.password}</p>}

                {errors.apiError && <p className="error">{errors.apiError}</p>}

                <button type="submit">Se connecter</button>
                <p className="register-link">
                    Vous n'avez pas de compte ?{" "}
                    <a href="/Register">Inscrivez-vous ici</a>
                </p>

                {successMessage && <p className="success">{successMessage}</p>}
            </form>

            {tokenPopup && (
                <div className="token-popup">
                    <h2>Entrez votre token</h2>
                    <input
                        type="text"
                        placeholder="Token"
                        value={token}
                        onChange={(e) => setToken(e.target.value)}
                    />
                    <button onClick={handleVerifyToken}>Vérifier</button>
                </div>
            )}
        </div>
    );
}

export default Login;
