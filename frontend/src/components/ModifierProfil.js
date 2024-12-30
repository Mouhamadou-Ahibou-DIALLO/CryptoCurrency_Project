import React, { useState, useEffect } from "react";
import "../static/css/editProfile.css";
import {useParams} from "react-router-dom";

const EditProfile = () => {
    const {id} = useParams()
    const [userData, setUserData] = useState(null);

    const [newPassword, setNewPassword] = useState("");
    const [confirmPassword, setConfirmPassword] = useState("");
    const [passwordError, setPasswordError] = useState("");

    useEffect(() => {
        const fetchId = async () => {
            try {
                const response = await fetch(`/api/users/${id}`, {
                });
                if (!response.ok) {
                    throw new Error("Erreur lors de la récupération des données.");
                }
                const data = await response.json();
                setUserData(data);
            } catch (error) {
                console.error(error);
            }
        };
        fetchId();
    }, []);

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setUserData({
            ...userData,
            [name]: value,
        });
    };

    const validatePasswordCriteria = (password) => {
        const criteria = /^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
        return criteria.test(password);
    };

    const validatePasswords = () => {
        if (!validatePasswordCriteria(newPassword)) {
            setPasswordError(
                "Le mot de passe doit contenir au moins 8 caractères, une majuscule, une minuscule, un chiffre et un caractère spécial."
            );
            return false;
        }
        if (newPassword !== confirmPassword) {
            setPasswordError("Les mots de passe ne correspondent pas.");
            return false;
        }
        setPasswordError("");
        return true;
    };

    const handleSave = async () => {
        if (!validatePasswords()) return;

        const token = localStorage.getItem("token");
        const response = await fetch("/api/update-profile", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${token}`,
            },

            body: JSON.stringify({
                id: userData.id,
                username: userData.username,
                email: userData.email,
                passwordHash: newPassword
            }),
        });

        if (response.ok) {
            alert("Profil mis à jour !");
            const updatedUser = await response.json();
            localStorage.setItem("user", JSON.stringify(updatedUser));
            window.location.href = "/dashboard/{id}";
        } else {
            console.error("Erreur de mise à jour");
        }
    };

    const handleLogout = () => {
        localStorage.removeItem("token");
        localStorage.removeItem("user");
        window.location.href = "/Login";
    };

    const handleReturnToAccount = () => {
        window.location.href = "/dashboard/{id}";
    };

    return (
        <div className="edit-profile-container">
            <header className="edit-profile-header">
                <h1>La cryptomonnaie de l'avenir</h1>
                <div className="header-buttons">
                    <button onClick={handleReturnToAccount}>Retour à ton compte</button>
                    <button onClick={handleLogout}>Déconnexion</button>
                </div>
            </header>
            <div className="edit-profile-form">
                <h2>Modifier Profil</h2>
                <div className="form-group">
                    <label>Nom d'utilisateur</label>
                    <input
                        type="text"
                        name="username"
                        value={userData.username}
                        onChange={handleInputChange}
                    />
                </div>
                <div className="form-group">
                    <label>Email</label>
                    <input
                        type="email"
                        name="email"
                        value={userData.email}
                        onChange={handleInputChange}
                    />
                </div>
                <div className="form-group">
                    <label>Mot de passe actuel</label>
                    <input
                        type="password"
                        name="passwordHash"
                        value={userData.passwordHash}
                        onChange={handleInputChange}
                    />
                </div>
                <div className="form-group">
                    <label>Nouveau mot de passe</label>
                    <input
                        type="password"
                        value={newPassword}
                        onChange={(e) => setNewPassword(e.target.value)}
                    />
                </div>
                <div className="form-group">
                    <label>Confirmer le mot de passe</label>
                    <input
                        type="password"
                        value={confirmPassword}
                        onChange={(e) => setConfirmPassword(e.target.value)}
                    />
                </div>
                {passwordError && <p className="error-message">{passwordError}</p>}
                <button onClick={handleSave}>Sauvegarder</button>
            </div>
        </div>
    );
};

export default EditProfile;
