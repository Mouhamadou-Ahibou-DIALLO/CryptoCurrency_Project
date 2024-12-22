import React, { useState, useEffect } from 'react';
import {Link, useNavigate} from 'react-router-dom';
import '../static/css/cryptoList.css';

function CryptoList() {
    const [cryptos, setCryptos] = useState([]);

    useEffect(() => {
        const username = "momo";
        const password = "Diallo1957@";
        const credentials = btoa(`${username}:${password}`);

        fetch("api/cryptocurrencies", {
            headers: {
                Authorization: `Basic ${credentials}`,
            },
        })
            .then((response) => response.json())
            .then((data) => setCryptos(data))
            .catch((error) => console.error("Erreur lors du chargement des cryptos :", error));
    }, []);

    return (
        <div className="crypto-list">
            <h1>Liste des Cryptomonnaies</h1>
            <table>
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
                    <tr key={crypto.id}>
                        <td>{crypto.rank}</td>
                        <td>
                            <Link to={`/crypto/${crypto.id}`}>{crypto.name}</Link>
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