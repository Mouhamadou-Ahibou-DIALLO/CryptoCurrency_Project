import React, { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import axios from 'axios';
import { Line } from 'react-chartjs-2';
import './ChartConfig';
import '../static/css/porfolio.css';

const Porfolio = () => {
    const { id } = useParams();
    const navigate = useNavigate();
    const [cryptos, setCryptos] = useState([]);
    const [userData, setUserData] = useState({});
    const [transactions, setTransactions] = useState([]);
    const [error, setError] = useState(null);
    const [loading, setLoading] = useState(true);

    const [showAddPopup, setShowAddPopup] = useState(false);
    const [newTransaction, setNewTransaction] = useState({
        cryptoId: '',
        amountInvested: '',
    });

    const [showEditPopup, setShowEditPopup] = useState(false);
    const [transactionToEdit, setTransactionToEdit] = useState(null);
    const [performance, setPerformance] = useState({
        cryptoPerformances: [],
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

                console.log(user);

                const response = await fetch('/api/cryptocurrencies', {
                    headers: { Authorization: `Basic ${credentials}` },
                });

                if (!response.ok) throw new Error("Erreur lors de la récupération des cryptomonnaies.");
                console.log("Réponse reçue:", response);
                const cryptoData = await response.json();
                setCryptos(cryptoData);
                console.log("cryptos: ", cryptoData);
            }

            catch (error) {
                console.log("error: ", error);
                console.error("Erreur lors de la récupération des données:", error);
            }
        };

        fetchData();

        const fetchTransactions = async () => {
            const response = await axios.get(`/api/portfolio/${id}`, {
                headers: {Authorization: `Basic ${credentials}`},
            });

            if (response.status === 200) {
                setTransactions(response.data);
            } else {
                console.warn("Aucune alerte disponible ou erreur lors de la récupération des alertes.");
                setTransactions([]);
            }


            // } catch (error) {
            //     setError("Erreur lors de la récupération des transactions.");
            //     console.error("Erreur :", error);
            // }
            // finally {
            //     setLoading(false)
            // }
        };

        fetchTransactions();

        setInterval(() => {
            fetchData();
            fetchTransactions();
        }, 60000);

        const fetchPerformance = async () => {
            try {
                const response = await axios.get(`/api/portfolio/performance/${id}`, {
                    headers: { Authorization: `Basic ${credentials}` },
                });
                const data = response.data;
                if (response.status === 200 && data) {
                    setPerformance(response.data);
                    console.log("data in fetch performance: ", data);
                } else {
                    console.warn("Aucune performance disponible.");
                    setPerformance([]);
                }
            } catch (error) {
                console.error("Erreur lors de la récupération des performances :", error);
                setPerformance([]);
            }
        };

        fetchPerformance();

    }, [id]);

    const getCryptoById = async (idCrypto) => {
        const username = "momo";
        const password = "Avignon2024@?";
        const credentials = btoa(`${username}:${password}`);

        try {
            const response = await fetch(`/api/cryptocurrencies/${idCrypto}`, {
                headers: {Authorization: `Basic ${credentials}`},
            });
            if (!response.ok) throw new Error("Erreur lors de la récupération des données d'un crypto à partir de l'id. ");
            console.log("Réonse reçue:", response);
            const data = await response.json();
            console.log("crypto id: ", data);
            return data;
        } catch (error) {
            console.error("Erreur lors de la récupération des données d'un crypto à partir de l'id. ", error);
        }
    }

    const handleNavigate = (path) => {
        navigate(path);
    };

    const handleAddTransaction = async () => {
        try {
            const response = await axios.post('/api/portfolio/create', {
                userId: id,
                cryptoId: newTransaction.cryptoId,
                amountInvested: newTransaction.amountInvested,
            });

            setTransactions([...transactions, response.data]);
            setShowAddPopup(false);

        } catch (error) {
            console.error("Erreur lors de l'ajout de la transaction :", error);
            setError("Erreur lors de l'ajout de la transaction.");
        }
    };

    const openEditPopup = (transaction) => {
        setTransactionToEdit(transaction);
        setShowEditPopup(true);
    };

    const handleEditTransaction = async () => {
        try {
            await axios.put(`/api/portfolio/update/${transactionToEdit.id}`, {
                amountInvested: transactionToEdit.amountInvested,});

            setTransactions(transactions.map((t) => (t.id === transactionToEdit.id ? transactionToEdit : t)));
            setShowEditPopup(false);

        } catch (error) {
            console.error("Erreur lors de la modification de la transaction :", error);
            setError("Erreur lors de la modification de la transaction.");
        }
    };

    const handleDeleteTransaction = async (transactionId) => {
        if (window.confirm("Êtes-vous sûr de vouloir supprimer cette transaction ?")) {
            try {
                await axios.delete(`/api/portfolio/delete/${transactionId}`, {
                    headers: { Authorization: `Basic ${credentials}` },
                });
                setTransactions(transactions.filter((t) => t.id !== transactionId));
            } catch (error) {
                console.error("Erreur lors de la suppression de la transaction :", error);
                setError("Erreur lors de la suppression de la transaction.");
            }
        }
    };

    const chartData = {
        labels: performance.cryptoPerformances.map(perf => perf.cryptoId),
        datasets: [
            {
                label: 'Valeur actuelle',
                data: performance.cryptoPerformances.map(perf => perf.currentValue),
                borderColor: 'rgba(75,192,192,1)',
                fill: false,
            },
            {
                label: 'Montant investi',
                data: performance.cryptoPerformances.map(perf => perf.investedAmount),
                borderColor: 'rgba(255,99,132,1)',
                fill: false,
            },
        ],
    };


    return (
        <div className="portfolio-page">
            <header className="portfolio-header">
                <h1>La cryptomonnaie de l’avenir</h1>
                <div className="header-buttons">
                    <button className="accueil" onClick={() => handleNavigate(`/Dashboard/${id}`)}>Retour à votre compte
                        d'accueil
                    </button>
                    <button className="alertes" onClick={() => handleNavigate(`/Dashboard/${id}`)}>Page des Alertes
                    </button>
                    <button className="deconnect" onClick={() => handleNavigate('/Login')}>Déconnexion</button>
                </div>
            </header>

            <main>
                <button className="btn-ajout" onClick={() => setShowAddPopup(true)}>Ajouter une transaction</button>
                <h2>Portfolio de l'utilisateur {userData.username}</h2>
                {error ? (
                    <p className="error-message">{error}</p>
                ) : (
                    <table className="transaction-table">
                        <thead>
                        <tr>
                            <th>Nom de la cryptomonnaie</th>
                            <th>Montant investi</th>
                            <th>Prix à la transaction</th>
                            <th>Quantité</th>
                            <th>Date</th>
                            <th>Option modifier</th>
                            <th>option supprimer</th>
                        </tr>
                        </thead>
                        <tbody>
                        {transactions.map((transaction) => (
                            <tr key={transaction.id}>
                                <td>{transaction.cryptoCurrency.name}</td>
                                <td>{transaction.amountInvested} €</td>
                                <td>{transaction.priceAtTransaction} €</td>
                                <td>{transaction.quantity}</td>
                                <td>{new Date(transaction.transactionDate).toLocaleString()}</td>
                                <td>
                                    <button className="btn-edit" onClick={() => openEditPopup(transaction)}>Modifier
                                    </button>
                                </td>
                                <td>
                                    <button className="btn-delete"
                                            onClick={() => handleDeleteTransaction(transaction.id)}>Supprimer
                                    </button>
                                </td>
                            </tr>
                        ))}
                        </tbody>
                    </table>
                )}
            </main>

            {showAddPopup && (
                <div className="popup">
                    <div className="popup-content">
                        <h3>Ajouter une transaction</h3>
                        <form onSubmit={(e) => {
                            e.preventDefault();
                            handleAddTransaction();
                        }}>
                            <label>Cryptomonnaie :</label>
                            <select
                                value={newTransaction.cryptoId}
                                onChange={(e) => setNewTransaction({...newTransaction, cryptoId: e.target.value})}
                            >
                                <option value="">Sélectionner</option>
                                {cryptos.map((crypto) => (
                                    <option key={crypto.id} value={crypto.id}>{crypto.name}</option>
                                ))}
                            </select>
                            <label>Montant investi :</label>
                            <input
                                type="number"
                                value={newTransaction.amountInvested}
                                onChange={(e) => setNewTransaction({...newTransaction, amountInvested: e.target.value})}
                            />
                            <button type="submit">Ajouter</button>
                            <button type="button" onClick={() => setShowAddPopup(false)}>Annuler</button>
                        </form>
                    </div>
                </div>
            )}

            {showEditPopup && (
                <div className="popup">
                    <div className="popup-content">
                        <h3>Modifier une transaction</h3>
                        <form onSubmit={(e) => {
                            e.preventDefault();
                            handleEditTransaction();
                        }}>
                            <label>Montant investi :</label>
                            <input
                                type="number"
                                value={transactionToEdit.amountInvested}
                                onChange={(e) => setTransactionToEdit({
                                    ...transactionToEdit,
                                    amountInvested: e.target.value
                                })}
                            />
                            <button type="submit">Modifier</button>
                            <button type="button" onClick={() => setShowEditPopup(false)}>Annuler</button>
                        </form>
                    </div>
                </div>
            )}

            <h2>Performance du portefeuille</h2>
            <table className="performance-table">
                <thead>
                <tr>
                    <th>Cryptomonnaie</th>
                    <th>Montant investi (€)</th>
                    <th>Valeur actuelle (€)</th>
                    <th>Gains/Pertes (€)</th>
                </tr>
                </thead>
                <tbody>
                {performance.cryptoPerformances.map((perf, index) => (
                    <tr key={index}>
                        <td>{perf.cryptoId}</td>
                        <td>{perf.investedAmount.toFixed(2)}</td>
                        <td>{perf.currentValue.toFixed(2)}</td>
                        <td style={{color: perf.gainOrLoss >= 0 ? 'green' : 'red'}}>
                            {perf.gainOrLoss.toFixed(2)}
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>

            <h3>Graphique de performance</h3>
            <div className="chart-container-performance">
                <Line data={chartData}/>
            </div>

        </div>
    );
};

export default Porfolio;
