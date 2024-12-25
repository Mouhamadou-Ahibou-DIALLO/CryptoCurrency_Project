import React, { useState, useEffect } from 'react';
import {Link, useNavigate} from 'react-router-dom';
import Header from "./Header";
import '../static/css/cryptoList.css';

const CryptoList = () => {
    const [cryptos, setCryptos] = useState([]);
    const [loading, setLoading] = useState(true);

    useEffect(() => {

        const username = "momo";
        const password = "Avignon2024@?";
        const credentials = btoa(`${username}:${password}`);

        fetch("/api/cryptocurrencies", {
            headers: {
                Authorization: `Basic ${credentials}`,
            },
        })
            .then((response) => {
                if (!response.ok) {
                    throw new Error("Erreur lors de la récupération des données");
                }
                return response.json();
            })
            .then((data) => {
                setCryptos(data);
                setLoading(false);
            })
            .catch((error) => {
                console.error("Erreur :", error);
                setLoading(false);
            });
    }, []);

    if (loading) {
        return <p>chargement des cryptomonnaies...</p>;
    }

    return (
        <div>
            <Header />
            <h1 className="title">Liste des Cryptomonnaies</h1>
            <table className="crypto-table">
                <thead>
                <tr>
                    <th>Rang</th>
                    <th>Nom</th>
                    <th>Symbole</th>
                    <th>Prix ($)</th>
                </tr>
                </thead>
                <tbody>
                {cryptos.map((crypto) => (
                    <tr key={crypto.id} className="crypto-row">
                        <td>{crypto.rank}</td>
                        <td>
                            <Link to={`/cryptocurrencies/${crypto.id}`}>{crypto.name}</Link>
                        </td>
                        <td>{crypto.symbol}</td>
                        <td>{crypto.price.toFixed(2)}</td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
}
    export default CryptoList