import React, { useState, useEffect } from 'react';
import '../static/css/pageAlerts.css';
import { useParams } from 'react-router-dom';

const PageAlerts = () => {
    const { id } = useParams();
    const [alerts, setAlerts] = useState([]);
    const [cryptos, setCryptos] = useState([]);
    const [userData, setUserData] = useState({});
    const [showCreatePopup, setShowCreatePopup] = useState(false);
    const [showUpdatePopup, setShowUpdatePopup] = useState(false);
    const [showDeletePopup, setShowDeletePopup] = useState(false);
    const [currentAlert, setCurrentAlert] = useState(null);

    const [newAlert, setNewAlert] = useState({
        name: '',
        crypto_id: '',
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
                if (!userResponse.ok) throw new Error("Erreur lors de la récupération des données utilisateur.");
                const user = await userResponse.json();
                setUserData(user);

                const alertsResponse = await fetch(`/api/alerts?user_id=${id}`, {
                    headers: { Authorization: `Basic ${credentials}` },
                });
                if (!alertsResponse.ok) throw new Error("Erreur lors de la récupération des alertes utilisateur.");
                const userAlerts = await alertsResponse.json();
                setAlerts(userAlerts);

                const cryptosResponse = await fetch('/api/cryptocurrencies', {
                    headers: { Authorization: `Basic ${credentials}` },
                });
                if (!cryptosResponse.ok) throw new Error("Erreur lors de la récupération des cryptomonnaies.");
                const cryptoData = await cryptosResponse.json();
                setCryptos(cryptoData);
            } catch (error) {
                console.error("Erreur lors de la récupération des données:", error);
            }
        };

        fetchData();
    }, [id]);

    const handleCreateAlert = async () => {
        if (alerts.length >= 10) {
            alert("Vous avez atteint la limite de 10 alertes. Passez à un abonnement premium pour ajouter plus d'alertes.");
            return;
        }

        try {
            const response = await fetch('/api/alerts/create', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    Authorization: `Basic ${credentials}`,
                },
                body: JSON.stringify({ ...newAlert, user_id: id }),
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
            console.error("Erreur lors de la création de l'alerte:", error);
        }
    };

    const handleUpdateAlert = async () => {
        try {
            const response = await fetch(`/api/alerts/update/${currentAlert.id}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                    Authorization: `Basic ${credentials}`,
                },
                body: JSON.stringify(currentAlert),
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
            console.error("Erreur lors de la mise à jour de l'alerte:", error);
        }
    };

    const handleDeleteAlert = async () => {
        try {
            const response = await fetch(`/api/alerts/delete/${currentAlert.id}`, {
                method: 'DELETE',
                headers: { Authorization: `Basic ${credentials}` },
            });
            if (response.ok) {
                setAlerts(alerts.filter(alert => alert.id !== currentAlert.id));
                setShowDeletePopup(false);
            } else {
                const error = await response.text();
                alert(`Erreur : ${error}`);
            }
        } catch (error) {
            console.error("Erreur lors de la suppression de l'alerte:", error);
        }
    };
    
    const handleLogout = () => {
        window.location.href = "/Login";
    };

    const handleReturnToAccount = () => {
        window.location.href = `/Dashboard/${userData.id}`;
    };

    return (
        <div className="app">
            <header className="app-header">
                <h1>La Cryptomonnaie de l'avenir</h1>
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
                                <p>Crypto : {cryptos.find(c => c.id === alert.crypto_id)?.name || 'N/A'}</p>
                                <p>Prix seuil : ${alert.priceThreshold}</p>
                                <p>Taux de variation : {alert.variationThreshold}%</p>
                            </div>
                            <div className="alert-buttons">
                                <button onClick={() => { setCurrentAlert(alert); setShowUpdatePopup(true); }}>Modifier</button>
                                <button onClick={() => { setCurrentAlert(alert); setShowDeletePopup(true); }}>Supprimer</button>
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
                    {cryptos.map(crypto => (
                        <option key={crypto.id} value={crypto.id}>{crypto.name}</option>
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
                Taux de variation (%) :
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

