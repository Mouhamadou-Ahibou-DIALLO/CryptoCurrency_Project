import "../static/css/dashboard.css";
import React, { useEffect, useState } from "react";
import { Line } from "react-chartjs-2";
import "./ChartConfig";

const Dashboard = () => {
    const [cryptos, setCryptos] = useState([]);
    const [filteredCryptos, setFilteredCryptos] = useState([]);
    const [selectedCrypto, setSelectedCrypto] = useState(null);
    const [priceHistory, setPriceHistory] = useState([]);
    const [loading, setLoading] = useState(true);
    const [startDate, setStartDate] = useState("2024-12-27T00:00:00");
    const [endDate, setEndDate] = useState("2025-01-31T23:59:59");

    const username = "momo";
    const password = "Avignon2024@?";
    const credentials = btoa(`${username}:${password}`);

    useEffect(() => {
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

    const handleLogout = () => {
        fetch("/api/users/logout", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({ email: localStorage.getItem("email") }),
        })
            .then((response) => {
                if (response.ok) {
                    localStorage.removeItem("tokenHash");
                    localStorage.removeItem("email");
                    window.location.href = "/Login";
                    console.log("Logout successful");
                } else {
                    console.error("Erreur lors de la déconnexion");
                }
            })
            .catch((error) => console.error("Erreur réseau :", error));
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
        ],
    };

    const chartOptions = {
        responsive: true,
        plugins: {
            legend: {
                position: "top",
            },
        },
    };

    if (loading) return <div>Chargement des données...</div>;

    return (
        <div className="dashboard-container">
            <header className="dashboard-header">
                <h1>La cryptomonnaie de l'avenir</h1>
                <div className="header-buttons">
                    <button>Créer une alerte</button>
                    <button>Notifications</button>
                    <button>Profil utilisateur</button>
                    <button onClick={handleLogout}>Déconnexion</button>
                </div>
            </header>
            <main>
                <h2>Bienvenue dans votre compte utilisateur</h2>
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
        </div>
    );
};

export default Dashboard;
