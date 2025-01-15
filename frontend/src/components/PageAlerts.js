import React, { useEffect, useState } from "react";
import '../static/css/pageAlerts.css';
import {useParams, useNavigate} from 'react-router-dom';
import axios from "axios";

const PageAlerts = () => {
    const { id } = useParams();

    const [alerts, setAlerts] = useState([]);
    const [cryptos, setCryptos] = useState([]);
    const [userData, setUserData] = useState({});

    const [showCreatePopup, setShowCreatePopup] = useState(false);
    const [showUpdatePopup, setShowUpdatePopup] = useState(false);
    const [showDeletePopup, setShowDeletePopup] = useState(false);

    const [currentAlert, setCurrentAlert] = useState(null);
    const [showModal, setShowModal] = useState(false);
    const [breakContrat, setBreakContrat] = useState(false);

    const [newAlert, setNewAlert] = useState({
        name: '',
        crypto_id: '',
        priceThreshold: '',
        variationThreshold: '',
    });

    const username = "momo";
    const password = "Avignon2024@?";
    const credentials = btoa(`${username}:${password}`);

    const navigate = useNavigate();

    useEffect(() => {

        const authToken = localStorage.getItem("authToken");
        if (!authToken) {
            alert("Vous devez vous connecter pour accéder au Dashboard.");
            navigate("/Login");
        }

        const fetchData = async () => {
            try {
                const userResponse = await fetch(`/api/users/${id}`);
                if (!userResponse.ok) throw new Error("Erreur lors de la récupération des données utilisateur.");
                const user = await userResponse.json();
                setUserData(user);

                console.log(userData);
                const alertsResponse = await axios.get(`/api/alerts/${id}`, {
                    headers: { Authorization: `Basic ${credentials}` },
                });
                if (alertsResponse.status === 200) {
                    //const userAlerts = await alertsResponse.json();
                    setAlerts(alertsResponse.data);
                    console.log("Alertes récupérées :", alertsResponse.data);
                } else {
                    console.warn("Aucune alerte disponible ou erreur lors de la récupération des alertes.");
                    setAlerts([]);
                }

                const cryptosResponse = await fetch('/api/cryptocurrencies', {
                    headers: { Authorization: `Basic ${credentials}` },
                });
                if (!cryptosResponse.ok) throw new Error("Erreur lors de la récupération des cryptomonnaies.");
                console.log("Réponse reçue:", cryptosResponse);
                const cryptoData = await cryptosResponse.json();
                setCryptos(cryptoData);
                console.log("alerts in fetch crypto: ", alerts);
                console.log("cryptos: ", cryptoData);
            }
            catch (error) {
                console.log("error: ", error);
                console.error("Erreur lors de la récupération des données:", error);
            }
        };

        setInterval(() => {
            fetchData();
        }, 60000);

        fetchData();
    }, [id, navigate]);

    const getCrypto = async (idCrypto) => {
        const username = "momo";
        const password = "Avignon2024@?";
        const credentials = btoa(`${username}:${password}`);

        try {
            const responseIdCrypto = await fetch(`/api/cryptocurrencies/${idCrypto}`, {
                headers: {Authorization: `Basic ${credentials}`},
            });
            if (!responseIdCrypto.ok) throw new Error("Erreur lors de la récupération des données d'un crypto à partir de l'id. ");
            console.log("Réonse reçue:", responseIdCrypto);
            const data = await responseIdCrypto.json();
            console.log("crypto id: ", data);
            return data;
        } catch (error) {
            console.error("Erreur lors de la récupération des données d'un crypto à partir de l'id. ", error);
        }
    }

    const handleCreateAlert = async () => {
        if (alerts.length >= 10) {
            alert("Vous avez atteint la limite de 10 alertes. Passez à un abonnement premium pour ajouter plus d'alertes.");
            return;
        }

        if (!newAlert.crypto_id) {
            alert("Veuillez sélectionner une cryptomonnaie.");
            return;
        }

        if (!newAlert.priceThreshold) {
            alert("Veuillez entrer un prix valide.");
            return;
        }

        if (!newAlert.variationThreshold) {
            alert("Veuillez entrer une variation valide.");
            return;
        }

        try {
            const cryptoCurrency = await getCrypto(newAlert.crypto_id);

            if (!cryptoCurrency) {
                alert("La cryptomonnaie sélectionnée est invalide ou introuvable.");
                return;
            }

            const payload = {
                name: newAlert.name,
                priceThreshold: newAlert.priceThreshold,
                variationThreshold: newAlert.variationThreshold,
                user: userData,
                cryptoCurrency: cryptoCurrency,
            };

            console.log("Payload envoyé :", payload);

            const response = await fetch('/api/alerts/create', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(payload),
            });

            if (response.ok) {
                const createdAlert = await response.json();
                console.log("alerte cree avec success: ", createdAlert);
                setAlerts([...alerts, createdAlert]);
                console.log("alerts in create alert: ", alerts);
                setShowCreatePopup(false);
                window.location.href = `/PageAlerts/${id}`;
            } else {
                const error = await response.text();
                console.log("Statut de la réponse :", response.status);
                console.log("Corps de la réponse :", await response.text());
                alert(`Erreur : ${error}`);
            }
        } catch (error) {
            console.error("Erreur lors de la création de l'alerte:", error);
            alert("Une erreur est survenue lors de la création de l'alerte.");
        }
    };

    const handleUpdateAlert = async () => {
        try {
            const response = await fetch(`/api/alerts/update/${currentAlert.id}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(currentAlert),
            });
            if (response.ok) {
                const updatedAlert = await response.json();
                setAlerts(alerts.map(alert => (alert.id === updatedAlert.id ? updatedAlert : alert)));
                console.log("done update alert");
                setShowUpdatePopup(false);
                window.location.href = `/PageAlerts/${id}`;
            } else {
                const error = await response.text();
                alert(`Erreur : ${error}`);
            }
        } catch (error) {
            console.error("Erreur lors de la mise à jour de l'alerte:", error);
        }
    };

    const handleDeleteAlert = async () => {
        try {
            const response = await fetch(`/api/alerts/delete/${currentAlert.id}?userId=${id}`, {
                method: 'DELETE',
            });
            if (response.ok) {
                setAlerts(alerts.filter(alert => alert.id !== currentAlert.id));
                setShowDeletePopup(false);
                //window.location.href = `/PageAlerts/${id}`;
                console.log("done delete alert");
            } else {
                const error = await response.text();
                alert(`Erreur : ${error}`);
            }
        } catch (error) {
            console.error("Erreur lors de la suppression de l'alerte:", error);
        }
    };

    const handleLogout = () => {
        localStorage.removeItem("authToken");
        window.location.href = "/Login";
    };

    const handleReturnToAccount = () => {
        window.location.href = `/Dashboard/${userData.id}`;
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
                    window.location.href = `/PageAlerts/${id}`;
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
                    window.location.href = `/PageAlerts/${id}`;
                } else {
                    alert('Erreur lors de la mise à jour de votre statut.');
                }
            })
            .catch((error) => {
                console.error('Erreur:', error);
                alert('Une erreur est survenue. Veuillez réessayer.');
            });
    };

    return (
        <div className="app">
            <header className="app-header">
                <h1>La Cryptomonnaie de l'avenir</h1>
                {userData.statut === 'normal' && (
                    <button className="btn-upgrade-premium" onClick={handlePremiumUpgrade}>Passer à un abonnement premium</button>
                )}
                {userData.statut === 'premium' && (
                    <button className="btn-portefeuille" onClick={handlePagePortefeuille}>Gérer votre portefeuille virtuel</button>
                )}
                {userData.statut === 'premium' && (
                    <button className="btn-break-contrat" onClick={handleBreakContrat}>Annuler votre contrat prémium</button>
                )}
                <button className="btn-return-to-account" onClick={handleReturnToAccount}>Retour à ton compte</button>
                <button className="btn-logout" onClick={handleLogout}>Déconnexion</button>
            </header>

            <div className="container">
                <button className="btn-create-alert" onClick={() => setShowCreatePopup(true)}>
                    Créer une alerte
                </button>

                <h2>Vos alertes</h2>
                <ul className="alerts-list">
                    {alerts.map(alert => (
                        <li key={alert.id} className="alert-item">
                            <div>
                                <h3>{alert.name}</h3>
                                <p>Crypto : {alert.cryptoCurrency.name}</p>
                                <p>Prix seuil : ${alert.priceThreshold}</p>
                                <p>Taux de variation : {alert.variationThreshold}%</p>
                            </div>
                            <div className="alert-buttons">
                                <button className="btn-update-alert" onClick={() => { setCurrentAlert(alert); setShowUpdatePopup(true); }}>Modifier</button>
                                <button className="btn-delete-alert" onClick={() => { setCurrentAlert(alert); setShowDeletePopup(true); }}>Supprimer</button>
                            </div>
                        </li>
                    ))}
                </ul>
            </div>

            {showCreatePopup && (
                <Popup
                    title="Créer une nouvelle alerte"
                    alert={newAlert}
                    setAlert={setNewAlert}
                    cryptos={cryptos}
                    onSave={handleCreateAlert}
                    onCancel={() => setShowCreatePopup(false)}
                />
            )}

            {showUpdatePopup && (
                <PopupUpdate
                    title="Modifier une alerte"
                    alert={currentAlert}
                    setAlert={setCurrentAlert}
                    onSave={handleUpdateAlert}
                    onCancel={() => setShowUpdatePopup(false)}
                />
            )}

            {showDeletePopup && (
                <div className="popup">
                    <div className="popup-content">
                        <h2>Confirmer la suppression</h2>
                        <p>Êtes-vous sûr de vouloir supprimer cette alerte ?</p>
                        <button onClick={handleDeleteAlert}>Oui</button>
                        <button onClick={() => setShowDeletePopup(false)}>Non</button>
                    </div>
                </div>
            )}

            {breakContrat && (
                <div className="popup-contrat">
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

const Popup = ({ title, alert, setAlert, cryptos, onSave, onCancel }) => (
    <div className="popup">
        <div className="popup-content">
            <h2>{title}</h2>
            <label>
                Nom de l'alerte :
                <input
                    type="text"
                    value={alert.name}
                    onChange={e => setAlert({ ...alert, name: e.target.value })}
                />
            </label>
            <label>
                Cryptomonnaie :
                <select
                    value={alert.crypto_id}
                    onChange={e => setAlert({ ...alert, crypto_id: e.target.value })}
                >
                    <option value="">--Choisir--</option>
                    {cryptos && cryptos.length > 0 && cryptos.map(crypto => (
                        <option key={crypto.id} value={crypto.id}>{crypto.name}</option>
                    ))}
                </select>
            </label>
            <label>
                Prix seuil ($) :
                <input
                    type="number"
                    name="priceThreshold"
                    value={alert.priceThreshold}
                    onChange={e => setAlert({ ...alert, priceThreshold: e.target.value })}
                />
            </label>
            <label>
                Taux de variation (%) :
                <input
                    type="number"
                    name="variationThreshold"
                    value={alert.variationThreshold}
                    onChange={e => setAlert({ ...alert, variationThreshold: e.target.value })}
                />
            </label>
            <div className="popup-buttons">
                <button onClick={onSave}>Enregistrer</button>
                <button onClick={onCancel}>Annuler</button>
            </div>
        </div>
    </div>
);

const PopupUpdate = ({ title, alert, setAlert, onSave, onCancel }) => (
    <div className="popup">
        <div className="popup-content">
            <h2>{title}</h2>
            <label>
                Nom de l'alerte :
                <input
                    type="text"
                    value={alert.name}
                    onChange={e => setAlert({ ...alert, name: e.target.value })}
                />
            </label>
            <label>
                Prix seuil ($) :
                <input
                    type="number"
                    name="priceThreshold"
                    value={alert.priceThreshold}
                    onChange={e => setAlert({ ...alert, priceThreshold: e.target.value })}
                />
            </label>
            <label>
                Taux de variation (%) :
                <input
                    type="number"
                    name="variationThreshold"
                    value={alert.variationThreshold}
                    onChange={e => setAlert({ ...alert, variationThreshold: e.target.value })}
                />
            </label>
            <div className="popup-buttons">
                <button onClick={onSave}>Enregistrer</button>
                <button onClick={onCancel}>Annuler</button>
            </div>
        </div>
    </div>
);

export default PageAlerts;

