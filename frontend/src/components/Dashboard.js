import "../static/css/dashboard.css";
import React, { useEffect, useState } from "react";
import { Line } from "react-chartjs-2";
import "./ChartConfig";
import {useParams} from "react-router-dom";

const Dashboard = () => {
    const {id} = useParams()
    const [userData, setUserData] = useState(null);
    const [cryptos, setCryptos] = useState([]);
    const [filteredCryptos, setFilteredCryptos] = useState([]);
    const [selectedCrypto, setSelectedCrypto] = useState(null);

    const [priceHistory, setPriceHistory] = useState([]);
    const [movingAveragePrediction, setMovingAveragePrediction] = useState(null);
    const [linearRegressionPrediction, setLinearRegressionPrediction] = useState(null);
    const [marginError, setMarginError] = useState(null);
    const [loading, setLoading] = useState(true);

    const [startDate, setStartDate] = useState("2024-12-27T00:00:00");
    const [endDate, setEndDate] = useState("2025-01-31T23:59:59");

    const [showProfileOptions, setShowProfileOptions] = useState(false);
    const [showGenerateModal, setShowGenerateModal] = useState(false);
    const [showDeleteModal, setShowDeleteModal] = useState(false);
    const [generatedToken, setGeneratedToken] = useState("");
    const [showModifyModal, setShowModifyModal] = useState(false);
    const [enteredToken, setEnteredToken] = useState("");
    const [showAlerts, setShowAlerts] = useState(false);

    const username = "momo";
    const password = "Avignon2024@?";
    const credentials = btoa(`${username}:${password}`);

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

        const fetchCryptos = async () => {
            try {
                const response = await fetch("/api/cryptocurrencies", {
                    headers: {
                        Authorization: `Basic ${credentials}`,
                    },
                });
                if (!response.ok) {
                    throw new Error("Erreur lors de la récupération des données.");
                }
                const data = await response.json();
                setCryptos(data);
                setFilteredCryptos(data);
                setLoading(false);
            } catch (error) {
                console.error(error);
                setLoading(false);
            }
        };
        fetchCryptos();
    }, []);

    useEffect(() => {
        if (selectedCrypto?.name && startDate && endDate) {
            fetchPriceHistory(selectedCrypto.name).then(() => {
                fetchPredictions(selectedCrypto.name, 7, priceHistory[priceHistory.length - 1].price);
            });
        }
    }, [selectedCrypto?.name, startDate, endDate]);

    const fetchPriceHistory = async (cryptoName) => {
        try {
            const response = await fetch(
                `/api/cryptocurrencies/${cryptoName}/price-history?start=${startDate}&end=${endDate}`,
                {
                    headers: {
                        Authorization: `Basic ${credentials}`,
                    },
                }
            );
            if (!response.ok) {
                throw new Error("Erreur lors de la récupération des données.");
            }
            const data = await response.json();
            setPriceHistory(data);
        } catch (error) {
            console.error(error);
        }
    };

    const handleCryptoSelect = (cryptoName) => {
        const selected = cryptos.find((crypto) => crypto.name === cryptoName);
        setSelectedCrypto(selected);
        fetchPriceHistory(cryptoName);
    };

    const fetchPredictions = async (cryptoName, period, predictedPrice) => {
        try {
            const [movingAverageResponse, linearRegressionResponse, errorMarginResponse] = await Promise.all([
                fetch(`/api/predictions/moving-average?name=${cryptoName}&period=${period}`, {
                    headers: {
                        Authorization: `Basic ${credentials}`,
                    },
                }),
                fetch(`/api/predictions/linear-regression?name=${cryptoName}`, {
                    headers: {
                        Authorization: `Basic ${credentials}`,
                    },
                }),
                fetch(`/api/predictions/error-margin?name=${cryptoName}&predictedPrice=${predictedPrice}`, {
                    headers: {
                        Authorization: `Basic ${credentials}`,
                    },
                })
            ]);

            if (!movingAverageResponse.ok || !linearRegressionResponse.ok  || !errorMarginResponse.ok) {
                throw new Error("Erreur lors de la récupération des données de prédiction.");
            }

            const movingAverage = await movingAverageResponse.json();
            const linearRegression = await linearRegressionResponse.json();
            const marginError = await errorMarginResponse.json();

            setMovingAveragePrediction(movingAverage);
            setLinearRegressionPrediction(linearRegression);
            setMarginError(marginError);
        } catch (error) {
            console.error(error);
        }
    };

    const handleLogout = () => {
        localStorage.removeItem("authToken");
        localStorage.removeItem("id");
        window.location.href = "/Login";
    };

    const chartData = {
        labels: priceHistory.map((entry) => new Date(entry.timestamp).toLocaleString()),
        datasets: [
            {
                label: "Prix ($)",
                data: priceHistory.map((entry) => entry.price),
                fill: false,
                backgroundColor: "rgba(75, 192, 192, 0.4)",
                borderColor: "rgba(75, 192, 192, 1)",
            },
            {
                label: "Prédiction Moyenne Mobile",
                data: priceHistory.map((_, index) =>
                    index === priceHistory.length - 1 ? movingAveragePrediction : null
                ),
                fill: false,
                borderColor: "rgba(255, 99, 132, 1)",
                borderDash: [5, 5],
            },
            {
                label: "Prédiction Régression Linéaire",
                data: priceHistory.map((_, index) =>
                    index === priceHistory.length - 1 ? linearRegressionPrediction : null
                ),
                fill: false,
                borderColor: "rgba(153, 102, 255, 1)",
                borderDash: [10, 5],
            },
            {
                label: "Marge d'erreur",
                data: priceHistory.map((_, index) =>
                    index === priceHistory.length - 1 ? marginError : null
                ),
                fill: false,
                borderColor: "rgba(255, 159, 64, 1)",
                borderDash: [10, 5],
            },
        ],
    };

    const chartOptions = {
        responsive: true,
        plugins: {
            legend: {
                position: "top",
            },
            tooltip: {
                callbacks: {
                    label: (tooltipItem) => {
                        const value = tooltipItem.raw;
                        return value ? `${tooltipItem.dataset.label}: $${value.toFixed(2)}` : null;
                    },
                },
            },
        },
    };

    const handleProfileClick = () => {
        setShowProfileOptions(!showProfileOptions);
    };

    const handleGenerateToken = async () => {
        setShowGenerateModal(true);
        try {
            const response = await fetch(`/api/users/update-token/${userData.id}`, { method: "PUT" });
            const data = await response.json();
            if (response.ok) {
                setGeneratedToken(data.token);
                setShowGenerateModal(true);
            } else {
                alert("Erreur lors de la génération du token.");
            }
        } catch (error) {
            console.error("Erreur lors de la requête:", error);
        }
    };

    const handleModifyProfile = () => {
        setShowModifyModal(!showModifyModal);
    };

    const handleShowAlerts = () => {
        setShowAlerts(!showAlerts);
    };

    const handlePageAlerts = async () => {
        try {
            const response = await fetch(`/api/users/verify-token`, {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ email: userData.email, token: enteredToken }),
            });

            if (response.ok) {
                window.location.href = "/PageAlerts/{userData.id}";
            } else {
                alert("Token invalide.");
            }
        } catch (error) {
            console.error("Erreur lors de la vérification du token:", error);
        }
    };

    const handleTokenVerificationModifyProfile = async () => {
        try {
            const response = await fetch(`/api/users/verify-token`, {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ email: userData.email, token: enteredToken }),
            });

            if (response.ok) {
                window.location.href = "/ModifierProfil/{userData.id}";
            } else {
                alert("Token invalide.");
            }
        } catch (error) {
            console.error("Erreur lors de la vérification du token:", error);
        }
    };

    const handleDeleteAccount = () => {
        setShowDeleteModal(!showDeleteModal);
    };

    const handleDeleteProfile = async () => {
        try {
            const response = await fetch(`/api/users/verify-token`, {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ email: userData.email, token: enteredToken }),
            });

            if (response.ok) {
                await fetch(`/api/users/delete/${userData.id}`, { method: "DELETE" });
                alert("Compte supprimé. Nous espérons vous revoir bientôt.");
                window.location.href = "/";
            } else {
                alert("Token invalide.");
            }
        } catch (error) {
            console.error("Erreur lors de la suppression du profil:", error);
        }
    };


    if (loading) return <div>Chargement des données...</div>;

    return (
        <div className="dashboard-container">
            <header className="dashboard-header">
                <h1>La cryptomonnaie de l'avenir</h1>
                <div className="header-buttons">
                    <button onClick={handleShowAlerts}>Page pour les alertes</button>
                    <button>Notifications</button>
                    <button onClick={handleProfileClick}>Profil utilisateur</button>
                    <button onClick={handleLogout}>Déconnexion</button>
                </div>
            </header>
            <main>
                <h2>Bienvenue dans votre compte utilisateur: {userData.username}</h2>
            </main>

            <div className="crypto-app">
                <h1>Liste des Cryptomonnaies</h1>
                <p>
                    Veuillez sélectionner une cryptomonnaie dans la liste déroulante ci-dessous pour voir les détails et
                    l'historique des prix.
                </p>
                <div className="crypto-selector">
                    <select
                        size="20"
                        onChange={(e) => handleCryptoSelect(e.target.value)}
                    >
                        {filteredCryptos.map((crypto) => (
                            <option key={crypto.id} value={crypto.name}>
                            {crypto.name}
                                </option>
                            ))}
                        </select>
                </div>
                    {selectedCrypto && (
                        <div className="crypto-details">
                            <h2>Détails de {selectedCrypto.name}</h2>
                            <ul>
                                <li><strong>Symbole:</strong> {selectedCrypto.symbol}</li>
                                <li><strong>Rang:</strong> {selectedCrypto.rank}</li>
                                <li><strong>Prix ($):</strong> {selectedCrypto.price?.toFixed(2) || "N/A"}</li>
                                <li><strong>Volume ($):</strong> {selectedCrypto.volume?.toFixed(2) || "N/A"}</li>
                                <li><strong>Market Cap ($):</strong> {selectedCrypto.market?.toFixed(2) || "N/A"}</li>
                            </ul>
                            <h3>Historique des prix</h3>
                            <div>
                                <input
                                    type="datetime-local"
                                    value={startDate}
                                    onChange={(e) => setStartDate(e.target.value)}
                                />
                                <input
                                    type="datetime-local"
                                    value={endDate}
                                    onChange={(e) => setEndDate(e.target.value)}
                                />
                                <button onClick={() => fetchPriceHistory(selectedCrypto.name)}>
                                    Mettre à jour
                                </button>
                            </div>
                            <div className="chart-container">
                                <Line data={chartData} options={chartOptions} />
                            </div>
                        </div>
                    )}
            </div>

            {showProfileOptions && (
                <div className="profile-options">
                    <button onClick={() => setShowProfileOptions(false)} className="close-PU">Fermer</button>
                    <h3>Options du profil</h3>
                    <p><strong>Votre nom d'utilisateur :</strong> {userData.username}</p>
                    <p><strong>Votre email :</strong> {userData.email}</p>
                    <button onClick={handleModifyProfile} className="modify-PU">Modifier Profil</button>
                    <button onClick={handleGenerateToken} className="genere-PU">Générer un nouveau token</button>
                    <button onClick={handleDeleteAccount} className="sup-PU">Supprimer Profil</button>
                </div>
            )}

            {showModifyModal && (
                <div className="modal">
                    <div className="modal-content">
                        <h2>Vérification du Token</h2>
                        <p>Veuillez entrer votre token pour accéder à la modification du profil :</p>
                        <input
                            type="text"
                            placeholder="Entrer votre token"
                            value={enteredToken}
                            onChange={(e) => setEnteredToken(e.target.value)}
                        />
                        <button onClick={handleTokenVerificationModifyProfile}>Vérifier</button>
                        <button onClick={() => setShowModifyModal(false)}>Annuler</button>
                    </div>
                </div>
            )}

            {showAlerts && (
                <div className="modal">
                    <div className="modal-content">
                        <h2>Vérification du Token</h2>
                        <p>Veuillez entrer votre token pour accéder à la page des alertes :</p>
                        <input
                            type="text"
                            placeholder="Entrer votre token"
                            value={enteredToken}
                            onChange={(e) => setEnteredToken(e.target.value)}
                        />
                        <button onClick={handlePageAlerts}>Vérifier</button>
                        <button onClick={() => setShowAlerts(false)}>Annuler</button>
                    </div>
                </div>
            )}

            {showGenerateModal && (
                <div className="modal">
                    <div className="modal-content">
                        <h2>Générer un nouveau token</h2>
                        <p>Votre nouveau token a été généré avec succès :</p>
                        <div className="token-display">
                            <span>{generatedToken}</span>
                        </div>
                        <button onClick={() => setShowGenerateModal(false)}>OK</button>
                    </div>
                </div>
            )}

            {showDeleteModal && (
                <div className="modal">
                    <div className="modal-content">
                        <h2>Vérification du Token</h2>
                        <p>Veuillez entrer votre token pour confirmer la suppression :</p>
                        <input
                            type="text"
                            placeholder="Entrer votre token"
                            value={enteredToken}
                            onChange={(e) => setEnteredToken(e.target.value)}
                        />
                        <button onClick={handleDeleteProfile}>Supprimer</button>
                        <button onClick={() => setShowDeleteModal(false)}>Annuler</button>
                    </div>
                </div>
            )}
        </div>
    );
};

export default Dashboard;
