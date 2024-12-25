import React, { useEffect, useState } from "react";
import { useParams, Link } from "react-router-dom";
import HeaderDetails from "./HeaderDetails";
import "../static/css/detailsCrypto.css";

const CryptoDetails = () => {
    const { id } = useParams();
    const [crypto, setCrypto] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    console.log("redirect successful");
    console.log("ID de la crypto:", id);

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
                console.log("Données récupérées de l'API : ", data);
                setCrypto(data);
                setLoading(false);
            })
            .catch((error) => {
                console.error("Erreur lors de la récupération des données : ", error);
                setError(error.message);
                setLoading(false);
            });
    }, [id]);

    if (loading) {
        return <div>Chargement des données...</div>;
    }

    if (error) {
        return <div>Erreur : {error}</div>;
    }

    if (!crypto) {
        return <div>Aucune donnée trouvée pour cette cryptomonnaie.</div>;
    }

    return (
        <div className="crypto-details">
            <HeaderDetails />
            <Link to="/">Retour à la liste</Link>
            <h1>Détails de {crypto.name}</h1>
            <ul>
                <li><strong>Nom:</strong> {crypto.name}</li>
                <li><strong>Symbole:</strong> {crypto.symbol}</li>
                <li><strong>Rang:</strong> {crypto.rank}</li>
                <li><strong>Prix ($):</strong> {crypto.price ? crypto.price.toFixed(2) : 'N/A'}</li>
                <li><strong>Volume d'échange ($):</strong> {crypto.volume ? crypto.volume.toFixed(2) : 'N/A'}</li>
                <li><strong>Market Cap ($):</strong> {crypto.market ? crypto.market.toFixed(2) : 'N/A'}</li>
                <li><strong>Pourcentage de changement en 24h:</strong> {crypto.change ? crypto.change.toFixed(2) : 'N/A'}</li>
                <li><strong>VWAP en 24H:</strong> {crypto.vwap ? crypto.vwap.toFixed(2) : 'N/A'}</li>
                <li><strong>Timestamp:</strong> {crypto.timestamp ? new Date(crypto.timestamp).toLocaleString() : 'N/A'}</li>
            </ul>
        </div>
    );
}

export default CryptoDetails;
