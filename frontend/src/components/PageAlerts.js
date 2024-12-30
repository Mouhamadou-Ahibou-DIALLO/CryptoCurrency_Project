import React, { useState, useEffect } from 'react';
import '../static/css/pageAlerts.css';
import {useParams} from "react-router-dom";

const PageAlerts = () => {
    const {id} = useParams();
    const [alerts, setAlerts] = useState([]);
    const [cryptos, setCryptos] = useState([]);
    const [userData, setUserData] = useState({});
    const [showCreatePopup, setShowCreatePopup] = useState(false);
    const [showUpdatePopup, setShowUpdatePopup] = useState(false);
    const [currentAlert, setCurrentAlert] = useState(null);
    const [showDeletePopup, setShowDeletePopup] = useState(false);

    const [newAlert, setNewAlert] = useState({
        name: '',
        crypto: '',
        priceThreshold: '',
        variationThreshold: '',
    });

    const username = "momo";
    const password = "Avignon2024@?";
    const credentials = btoa(`${username}:${password}`);

    useEffect(() => {
        const fetchData = async () => {
            try {
                const userResponse = await fetch(`/api/users/${id}`);
                const user = await userResponse.json();
                setUserData(user);

                const alertsResponse = await fetch(`/api/alerts?user=${userData}`, {
                    headers: {
                        Authorization: `Basic ${credentials}`,
                    },});
                const userAlerts = await alertsResponse.json();
                setAlerts(userAlerts);

                const cryptosResponse = await fetch('/api/cryptocurrencies', {
                    headers: {
                        Authorization: `Basic ${credentials}`,
                    },});
                const cryptoData = await cryptosResponse.json();
                setCryptos(cryptoData);
            } catch (error) {
                console.error("Erreur lors de la récupération des données :", error);
            }
        };

        fetchData();
    }, [userId]);

    const handleCreateAlert = async () => {
        try {
            const response = await fetch('/api/alerts/create', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ ...newAlert, user: userData }),
            });
            if (response.ok) {
                const createdAlert = await response.json();
                setAlerts([...alerts, createdAlert]);
                setShowCreatePopup(false);
            } else {
                const error = await response.text();
                alert(`Erreur : ${error}`);
            }
        } catch (error) {
            console.error("Erreur lors de la création de l'alerte :", error);
        }
    };

    const handleUpdateAlert = async () => {
        try {
            const response = await fetch(`/api/alerts/update/${currentAlert.id}`, {
                method: 'PUT',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ ...currentAlert, user: userData }),
            });
            if (response.ok) {
                const updatedAlert = await response.json();
                setAlerts(alerts.map(alert => (alert.id === updatedAlert.id ? updatedAlert : alert)));
                setShowUpdatePopup(false);
            } else {
                const error = await response.text();
                alert(`Erreur : ${error}`);
            }
        } catch (error) {
            console.error("Erreur lors de la mise à jour de l'alerte :", error);
        }
    };

    const handleDeleteAlert = async () => {
        try {
            const response = await fetch(`/api/alerts/delete/${currentAlert.id}`, {
                method: 'DELETE',
            });
            if (response.ok) {
                setAlerts(alerts.filter(alert => alert.id !== currentAlert.id));
                setShowDeletePopup(false);
            } else {
                const error = await response.text();
                alert(`Erreur : ${error}`);
            }
        } catch (error) {
            console.error("Erreur lors de la suppression de l'alerte :", error);
        }
    };

    return (
        <div className="app">
            <header className="app-header">
                <h1>La cryptomonnaie de l'avenir</h1>
                <button id="account-btn">Retour à votre compte</button>
                <button id="logout-btn">Déconnexion</button>
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
                                <h3>{alert.title}</h3>
                                <p>Crypto : {alert.crypto}</p>
                                <p>Prix seuil : ${alert.priceThreshold}</p>
                                <p>Taux d'échange : {alert.variationThreshold}%</p>
                            </div>
                            <div className="alert-buttons">
                                <button
                                    onClick={() => {
                                        setCurrentAlert(alert);
                                        setShowUpdatePopup(true);
                                    }}
                                >
                                    Modifier
                                </button>
                                <button
                                    onClick={() => {
                                        setCurrentAlert(alert);
                                        setShowDeletePopup(true);
                                    }}
                                >
                                    Supprimer
                                </button>
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
                <Popup
                    title="Modifier une alerte"
                    alert={currentAlert}
                    setAlert={setCurrentAlert}
                    cryptos={cryptos}
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
        </div>
    );
};

const Popup = ({ title, alert, setAlert, cryptos, onSave, onCancel }) => (
    <div className="popup">
        <div className="popup-content">
            <h2>{title}</h2>
            <label>
                Titre :
                <input
                    type="text"
                    value={alert.title}
                    onChange={e => setAlert({ ...alert, title: e.target.value })}
                />
            </label>
            <label>
                Crypto :
                <select
                    value={alert.crypto}
                    onChange={e => setAlert({ ...alert, crypto: e.target.value })}
                >
                    <option value="">--Choisir--</option>
                    {cryptos.map(crypto => (
                        <option key={crypto.name} value={crypto.name}>
                            {crypto.name}
                        </option>
                    ))}
                </select>
            </label>
            <label>
                Prix seuil ($) :
                <input
                    type="number"
                    value={alert.priceThreshold}
                    onChange={e => setAlert({ ...alert, priceThreshold: e.target.value })}
                />
            </label>
            <label>
                Taux d'échange (%) :
                <input
                    type="number"
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
