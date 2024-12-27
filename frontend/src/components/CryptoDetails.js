import React, { useEffect, useState } from "react";
import { useParams, Link } from "react-router-dom";
import { Line } from "react-chartjs-2";
import "../static/css/detailsCrypto.css";
import "./ChartConfig";

const CryptoDetails = () => {
    const { id } = useParams();
    const [crypto, setCrypto] = useState(null);
    const [loading, setLoading] = useState(true);
    const [priceHistory, setPriceHistory] = useState([]);
    const [startDate, setStartDate] = useState("2024-12-27T00:00:00");
    const [endDate, setEndDate] = useState("2025-01-31T23:59:59");
    const [error, setError] = useState(null);

    useEffect(() => {
        const username = "momo";
        const password = "Avignon2024@?";
        const credentials = btoa(`${username}:${password}`);

        fetch(`/api/cryptocurrencies/${id}`, {
            headers: {
                Authorization: `Basic ${credentials}`,
            },
        })
            .then((response) => {
                if (!response.ok) {
                    throw new Error(`Erreur HTTP : ${response.statusText}`);
                }
                return response.json();
            })
            .then((data) => {
                setCrypto(data);
                return fetch(`/api/cryptocurrencies/${data.name}/price-history?start=${startDate}&end=${endDate}`, {
                    headers: {
                        Authorization: `Basic ${credentials}`,
                    },
                });
            })
            .then((response) => {
                if (!response.ok) {
                    throw new Error(`Erreur HTTP : ${response.statusText}`);
                }
                return response.json();
            })
            .then((historyData) => {
                setPriceHistory(historyData);
            })
            .catch((error) => {
                console.error("Erreur lors de la récupération des données : ", error);
                setError(error.message);
            })
            .finally(() => setLoading(false));
    }, [id, startDate, endDate]);

    const fetchPriceHistory = () => {
        const username = "momo";
        const password = "Avignon2024@?";
        const credentials = btoa(`${username}:${password}`);
        fetch(`/api/cryptocurrencies/${crypto.name}/price-history?start=${startDate}&end=${endDate}`, {
            headers: {
                Authorization: `Basic ${credentials}`,
            },
        })
            .then((response) => {
                if (!response.ok) {
                    throw new Error(`Erreur HTTP : ${response.statusText}`);
                }
                return response.json();
            })
            .then((data) => setPriceHistory(data))
            .catch((error) => console.error(error));
    };

    if (loading) return <div>Chargement des données...</div>;
    if (error) return <div>Erreur : {error}</div>;
    if (!crypto) return <div>Aucune donnée trouvée pour cette cryptomonnaie.</div>;

    const chartData = {
        labels: priceHistory.map((entry) => new Date(entry.timestamp).toLocaleString()),
        datasets: [
            {
                label: "Prix ($)",
                data: priceHistory.map((entry) => entry.price),
                fill: false,
                backgroundColor: "rgba(75,192,192,0.4)",
                borderColor: "rgba(75,192,192,1)",
            },
            {
                label: "Volume ($)",
                data: priceHistory.map((entry) => entry.volume),
                fill: false,
                backgroundColor: "rgba(255,159,64,0.4)",
                borderColor: "rgba(255,159,64,1)",
            },
            {
                label: "Market Cap ($)",
                data: priceHistory.map((entry) => entry.market),
                fill: false,
                backgroundColor: "rgba(153,102,255,0.4)",
                borderColor: "rgba(153,102,255,1)",
            },
        ],
    };

    const options = {
        responsive: true,
        plugins: {
            legend: {
                position: "top",
            },
        },
    };

    return (
        <div className="crypto-details">
            <header className="header">
                <h1>La cryptomonnaie de l'avenir</h1>
                <div className="button-container">
                    <Link to="/Register">
                        <button className="inscription-details">Inscription</button>
                    </Link>
                    <Link to="/Login">
                        <button className="connexion-details">Connexion</button>
                    </Link>
                </div>
            </header>
            <Link to="/">Retour à la liste</Link>
            <h1>Détails de {crypto.name}</h1>
            <ul>
                <li><strong>Nom:</strong> {crypto.name}</li>
                <li><strong>Symbole:</strong> {crypto.symbol}</li>
                <li><strong>Rang:</strong> {crypto.rank}</li>
                <li><strong>Prix ($):</strong> {crypto.price?.toFixed(2) || "N/A"}</li>
                <li><strong>Volume d'échange ($):</strong> {crypto.volume?.toFixed(2) || "N/A"}</li>
                <li><strong>Market Cap ($):</strong> {crypto.market?.toFixed(2) || "N/A"}</li>
            </ul>
            <div>
                <h2>Historique des données</h2>
                <div className="date-filters">
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
                    <button onClick={fetchPriceHistory}>Rafraîchir</button>
                </div>
                <div className="chart-container">
                    <Line data={chartData} options={options} />
                </div>
            </div>
        </div>
    );
};

export default CryptoDetails;
