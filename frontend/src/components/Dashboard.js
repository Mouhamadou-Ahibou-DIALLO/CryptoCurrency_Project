import React, { useEffect, useState } from "react";
import { Line } from "react-chartjs-2";
import "./ChartConfig";
import {useParams} from "react-router-dom";
import "../static/css/dashboard.css";
import {Chart} from "chart.js";

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
    const [enteredToken, setEnteredToken] = useState("");
    const [showAlerts, setShowAlerts] = useState(false);
    const [showPopup, setShowPopup] = useState(false);
    const [newPassword, setNewPassword] = useState("");
    const [confirmPassword, setConfirmPassword] = useState("");
    const [passwordError, setPasswordError] = useState("");
    const [oldPassword, setOldPassword] = useState("");

    const [showModal, setShowModal] = useState(false);
    const [breakContrat, setBreakContrat] = useState(false);

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
                fetchPredictions(selectedCrypto.name, 7);
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

    const fetchPredictions = async (cryptoName) => {
        try {
            const [movingAverageResponse, linearRegressionResponse, marginErrorResponse] = await Promise.all([
                fetch(`/api/predictions/moving-average?name=${cryptoName}&start=${startDate}&end=${endDate}`, {
                    headers: {
                        Authorization: `Basic ${credentials}`,
                    },
                }),
                fetch(`/api/predictions/linear-regression?name=${cryptoName}&start=${startDate}&end=${endDate}`, {
                    headers: {
                        Authorization: `Basic ${credentials}`,
                    },
                }),
                fetch(`/api/predictions/marging-error?name=${cryptoName}&start=${startDate}&end=${endDate}`, {
                    headers: {
                        Authorization: `Basic ${credentials}`,
                    },
                }),
            ]);

            if (!movingAverageResponse.ok || !linearRegressionResponse.ok || !marginErrorResponse.ok) {
                throw new Error("Erreur lors de la récupération des données de prédiction.");
            }

            const movingAverage = await movingAverageResponse.json();
            const linearRegression = await linearRegressionResponse.json();
            const marginError = await marginErrorResponse.json();

            console.log("Moving Average Data: ", movingAverage);
            console.log("Linear Regression Data: ", linearRegression);
            console.log("Margin Error Data: ", marginError);

            setMovingAveragePrediction(movingAverage.map((entry) => entry.price));
            setLinearRegressionPrediction(linearRegression.map((entry) => entry.price));
            setMarginError(marginError.map((entry) => entry.price));
        } catch (error) {
            console.error(error);
        }
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
                data: movingAveragePrediction || [],
                fill: false,
                borderColor: "rgba(255, 99, 132, 1)",
                borderDash: [5, 5],
            },
            {
                label: "Prédiction Régression Linéaire",
                data: linearRegressionPrediction || [],
                fill: false,
                borderColor: "rgba(255, 159, 64, 1)",
                borderDash: [5, 5],
            },
            // {
            //     label: "Prédiction Erreur de Margin",
            //     data: marginError || [],
            //     fill: false,
            //     borderColor: "rgba(255, 159, 64, 1)",
            //     borderDash: [15, 5],
            //     tension: 0.4,
            // },
        ],
    };

    const chartOptions = {
        responsive: true,
        // animation: {
        //     duration: 1000,
        //     easing: 'easeInOutQuad',
        // },
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

    // const myChart = new Chart(document.getElementById('myChart'), {
    //     type: 'line',
    //     data: chartData,
    //     options: chartOptions,
    // });
    //
    // const updateChartData = (newPriceHistory, newMovingAveragePrediction, newLinearRegressionPrediction, marginError) => {
    //     myChart.data.labels = newPriceHistory.map((entry) => new Date(entry.timestamp).toLocaleString());
    //     myChart.data.datasets[0].data = newPriceHistory.map((entry) => entry.price);
    //     myChart.data.datasets[1].data = newMovingAveragePrediction || [];
    //     myChart.data.datasets[2].data = newLinearRegressionPrediction || [];
    //     myChart.data.datasets[3].data = marginError || [];
    //
    //     myChart.update(undefined);
    // };
    //
    // const fetchNewData = () => {
    //     updateChartData(priceHistory, movingAveragePrediction, linearRegressionPrediction, marginError);
    // };
    //
    // setInterval(fetchNewData, 30000);

    const handleChange = (e) => {
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
        if (!oldPassword) {
      		setPasswordError("L'ancien mot de passe est requis.");
      		return false;
      	}

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

    console.log(id);

    const handleSubmit = (e) => {
        if (!validatePasswords()) return;

        e.preventDefault();
        const payload = {
            id: id,
            username: userData.username,
            email: userData.email,
            passwordHash: newPassword,
        };

        fetch(`/api/users/update/${id}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(payload),
        })
            .then((response) => {
                if (response.ok) {
                    alert("Profil mis à jour avec succès !");
                    setShowPopup(false);
                } else {
                    return response.json().then((err) => {
                        throw new Error(err.error || "Erreur lors de la mise à jour");
                    });
                }
            })
            .catch((error) => alert(error.message));
    };


    const handleLogout = () => {
        window.location.href = "/Login";
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
                window.location.href = `/PageAlerts/${userData.id}`;
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

    const handlePremiumUpgrade = () => {
        setShowModal(true);
    };

    const closeModal = () => {
        setShowModal(false);
    };

    const handleBreakContrat = () => {
        setBreakContrat(true);
    }

    const handleContinueContrat = () => {
        setBreakContrat(false);
    }

    const upgradeToPremium = () => {
        fetch('/api/users/upgrade-to-premium', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({userId: id}),
        })
            .then((response) => response.json())
            .then((data) => {
                if (data.success) {
                    alert('Votre statut a été mis à jour à Premium !');
                    closeModal();
                    window.location.href = `/Dashboard/${id}`;
                } else {
                    alert('Erreur lors de la mise à jour de votre statut.');
                }
            })
            .catch((error) => {
                console.error('Erreur:', error);
                alert('Une erreur est survenue. Veuillez réessayer.');
            });
    };

    const handlePagePortefeuille = () => {
        window.location.href = `/Porfolio/${id}`;
    };

    const handleContrat = () => {
        fetch('/api/users/downgrade-to-standard', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({userId: id}),
        })
            .then((response) => response.json())
            .then((data) => {
                if (data.success) {
                    alert('Votre statut a été mis à jour à normal !');
                    handleContinueContrat();
                    window.location.href = `/Dashboard/${id}`;
                } else {
                    alert('Erreur lors de la mise à jour de votre statut.');
                }
            })
            .catch((error) => {
                console.error('Erreur:', error);
                alert('Une erreur est survenue. Veuillez réessayer.');
            });
    };

    if (loading) return <div>Chargement des données...</div>;

    return (
        <div className="dashboard-container">
            <header className="dashboard-header">
                <h1>La cryptomonnaie de l'avenir</h1>
                <div className="header-buttons">
                    {userData.statut === 'normal' && (
                        <button className="btn-upgrade-premium-dashboard" onClick={handlePremiumUpgrade}>Passer à un abonnement premium</button>
                    )}
                    {userData.statut === 'premium' && (
                        <button className="btn-portefeuille-dashboard" onClick={handlePagePortefeuille}>Gérer votre portefeuille virtuel</button>
                    )}
                    {userData.statut === 'premium' && (
                        <button className="btn-break-contrat-dashboard" onClick={handleBreakContrat}>Annuler votre contrat prémium</button>
                    )}
                    <button onClick={handleShowAlerts}>Page pour les alertes</button>
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
                        <div className="crypto-details-dashboard">
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
                            <div className="chart-container-dashboard">
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
                    <button onClick={() => setShowPopup(true)} className="modify-PU">Modifier Profil</button>
                    <button onClick={handleGenerateToken} className="genere-PU">Générer un nouveau token</button>
                    <button onClick={handleDeleteAccount} className="sup-PU">Supprimer Profil</button>
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

            {showPopup && (
                <div className="popup-overlay">
                    <div className="popup-container">
                        <h2>Modifier le profil</h2>
                        <form onSubmit={handleSubmit}>
                            <div className="form-group">
                                <label htmlFor="username">Nom d'utilisateur</label>
                                <input
                                    type="text"
                                    id="username"
                                    name="username"
                                    value={userData.username}
                                    onChange={handleChange}
                                    required
                                />
                            </div>
                            <div className="form-group">
                                <label htmlFor="email">Email</label>
                                <input
                                    type="email"
                                    id="email"
                                    name="email"
                                    value={userData.email}
                                    onChange={handleChange}
                                    required
                                />
                            </div>
                            <div className="form-group">
                                <label htmlFor="oldPassword">Ancien mot de passe</label>
                                <input
                                    type="password"
                                    id="oldPassword"
                                    name="oldPassword"
                                    value={oldPassword}
                                    onChange={(e) => setOldPassword(e.target.value)}
                                    required
                                />
                            </div>
                            <div className="form-group">
                                <label htmlFor="newPassword">Nouveau mot de passe</label>
                                <input
                                    type="password"
                                    id="newPassword"
                                    name="newPassword"
                                    value={newPassword}
                                    onChange={(e) => setNewPassword(e.target.value)}
                                    required
                                />
                            </div>
                            <div className="form-group">
                                <label htmlFor="ConfirmPassword">Confirmer le mot de passe</label>
                                <input
                                    type="password"
                                    id="confirmPassword"
                                    name="confirmPassword"
                                    value={confirmPassword}
                                    onChange={(e) => setConfirmPassword(e.target.value)}
                                    required
                                />
                            </div>
                            <div className="form-actions">
                                {passwordError && <p className="error-message">{passwordError}</p>}
                                <button type="button" onClick={() => setShowPopup(false)}>
                                    Annuler
                                </button>
                                <button onClick={handleSubmit}>Sauvegarder</button>
                            </div>
                        </form>
                    </div>
                </div>
            )}

            {breakContrat && (
                <div className="popup">
                    <div className="popup-content">
                        <h2>Confirmer l'annulation de votre contrat'</h2>
                        <p>Êtes-vous sûr de vouloir supprimer votre contrat premium ?</p>
                        <button onClick={handleContrat}>Oui</button>
                        <button onClick={() => setBreakContrat(false)}>Non</button>
                    </div>
                </div>
            )}

            {showModal && (
                <div className="modal">
                    <div className="modal-content">
                        <h2>Devenez Premium</h2>
                        <p>En passant à un abonnement Premium, vous pourrez :</p>
                        <ul>
                            <li>Créer plus de 10 alertes</li>
                            <li>Obtenir un portefeuille actif</li>
                        </ul>
                        <p>Le prix de l'abonnement est de 9,99€ par mois. Pas cher et durable.</p>
                        <p>Voulez-vous passer à Premium ? Saisissez votre carte de débit pour confirmer. ------>>>>>>></p>
                        <p>
                            N'ayez aucun peur, vous pouvez annuler votre abonnement en tout temps.
                            En plus, cette offre est <strong>gratuite pendant 1 mois</strong>. Votre carte ne sera
                            débitée qu'après 30 jours.
                        </p>
                        <div className="modal-actions">
                            <button onClick={upgradeToPremium}>Oui, je veux passer à Premium</button>
                            <button onClick={closeModal}>Non, merci</button>
                        </div>
                    </div>
                </div>
            )}

        </div>
    );
};

export default Dashboard;
