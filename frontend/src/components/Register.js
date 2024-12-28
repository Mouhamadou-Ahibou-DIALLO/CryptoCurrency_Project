import React, { useState } from "react";
import '../static/css/register.css';
import {Link} from "react-router-dom";

function Register() {
    const [formData, setFormData] = useState({
        username: "",
        email: "",
        passwordHash: "",
        confirmPassword: "",
    });
    const [errors, setErrors] = useState({});
    const [showPopup, setShowPopup] = useState(false);
    const [token, setToken] = useState("");

    const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setFormData((prev) => ({ ...prev, [name]: value }));
        console.log("handle input changed");
    };

    const validateForm = () => {
        const newErrors = {};

        if (!formData.username.trim()) {
            newErrors.username = "Le nom d'utilisateur est obligatoire.";
        }

        if (!formData.email.trim()) {
            newErrors.email = "L'email est obligatoire.";
        } else if (!/\S+@\S+\.\S+/.test(formData.email)) {
            newErrors.email = "Veuillez entrer une adresse email valide.";
        }

        if (!formData.passwordHash) {
            newErrors.passwordHash = "Le mot de passe est obligatoire.";
        } else if (!passwordRegex.test(formData.passwordHash)) {
            newErrors.passwordHash = "Le mot de passe doit contenir au moins 8 caractères, une lettre majuscule, une lettre minuscule, un chiffre, et un caractère spécial.";
        }

        if (formData.passwordHash !== formData.confirmPassword) {
            newErrors.confirmPassword = "Les mots de passe ne correspondent pas.";
        }

        console.log("valid form");
        console.log("Erreurs détectées :", newErrors);

        setErrors(newErrors);
        return Object.keys(newErrors).length === 0;
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        if (!validateForm()) return;

        const { confirmPassword, ...dataToSend } = formData;

        try {
            const response = await fetch("/api/users/create", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(dataToSend),
            });

            const data = await response.json();
            console.log("done submit");

            if (response.ok) {
                setToken(data.token);
                setShowPopup(true);
                setFormData({ username: "", email: "", passwordHash: "", confirmPassword: "" });
                setErrors({});
            } else {
                setErrors({ apiError: data.error || "Une erreur est survenue." });
            }
        } catch (error) {
            setErrors({ apiError: "Erreur de connexion au serveur." });
        }
    };

    const closePopup = () => {
        setShowPopup(false);
        window.location.href = "/Login";
    };

    return (
        <div className="signup-container">
            <header className="signup-header">
                <h1>La cryptomonnaie de l'avenir</h1>
                <Link to="/" className="header-btn-accueilRegister">Accueil</Link>
                <button className="header-btn-register" onClick={() => (window.location.href = "/Login")}>
                    Connexion
                </button>
            </header>
            <form className="signup-form" onSubmit={handleSubmit}>
                <h2>S'inscrire</h2>

                <label>
                    Nom d'utilisateur :
                    <input
                        type="text"
                        name="username"
                        value={formData.username}
                        onChange={handleInputChange}
                    />
                </label>
                {errors.username && <p className="error">{errors.username}</p>}

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
                        name="passwordHash"
                        value={formData.passwordHash}
                        onChange={handleInputChange}
                    />
                </label>
                {errors.passwordHash && <p className="error">{errors.passwordHash}</p>}

                <label>
                    Confirmer le mot de passe :
                    <input
                        type="password"
                        name="confirmPassword"
                        value={formData.confirmPassword}
                        onChange={handleInputChange}
                    />
                </label>
                {errors.confirmPassword && <p className="error">{errors.confirmPassword}</p>}

                {errors.apiError && <p className="error">{errors.apiError}</p>}

                <button type="submit" className="signup-btn">S'inscrire</button>

                <p>
                    Vous avez déjà un compte ? <a href="/Login">Connectez-vous</a>.
                </p>
            </form>

            {showPopup && (
                <div className="popup">
                    <div className="popup-content">
                        <h3>Inscription réussie !</h3>
                        <p>
                            Copiez votre token et gardez-le avec sécurité pour vos prochains mouvements sur le
                            site.
                        </p>
                        <p className="token">{token}</p>
                        <button onClick={closePopup}>OK</button>
                    </div>
                </div>
            )}
        </div>
    );
}

export default Register;