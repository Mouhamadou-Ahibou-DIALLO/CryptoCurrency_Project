// import React, { useEffect, useState } from "react";
// import { useParams } from "react-router-dom";
// import { Line } from "react-chartjs-2";
// import api from "./api";
//
// const CryptoDetails = () => {
//     const { id } = useParams();
//     const [crypto, setCrypto] = useState(null);
//
//     useEffect(() => {
//         api.get(`/cryptocurrencies/${id}`)
//             .then((response) => setCrypto(response.data))
//             .catch((error) => console.error(error));
//     }, [id]);
//
//     if (!crypto) return <div>Chargement...</div>;
//
//     const data = {
//         labels: crypto.timestamps,
//         datasets: [
//             {
//                 label: "Prix",
//                 data: crypto.price,
//                 borderColor: "blue",
//                 fill: false,
//             },
//         ],
//     };
//
//     return (
//         <div className="crypto-details">
//             <h2>{crypto.name} ({crypto.symbol})</h2>
//             <p>Rang: {crypto.rank}</p>
//             <Line data={data} />
//         </div>
//     );
// };
//
// export default CryptoDetails;

import React, { useEffect, useState } from "react";
import { useParams, Link } from "react-router-dom";
import "../static/css/detailsCrypto.css";

function CryptoDetails() {
    const { id } = useParams();
    const [crypto, setCrypto] = useState(null);

    useEffect(() => {
        setloading(true);

        const username = "momo";
        const password = "Diallo1957@";
        const credentials = btoa(`${username}:${password}`)

        fetch(`api/cryptocurrencies/${id}`, {
            headers: {
                Authorization: `Basic ${credentials}`,
            },
        })
            .then((response) => response.json())
            .then((data) => setCrypto(data))
            .catch((error) => console.error("Erreur lors du chargement des détails :", error));
    }, [id]);

    if (!crypto) {
        return <p className="crypto-details p">Chargement des détails...</p>;
    }

    return (
        <div className="crypto-details">
            <Link to="/">Retour à la liste</Link>
            <h1>Détails de {crypto.name}</h1>
            <ul>
                <li><strong>Nom:</strong> {crypto.name}</li>
                <li><strong>Symbole:</strong> {crypto.symbol}</li>
                <li><strong>Rang:</strong> {crypto.rank}</li>
                <li><strong>Prix ($):</strong> {crypto.price.toFixed(2)}</li>
                <li><strong>Volume d'échange en ($):</strong> {crypto.volume.toFixed(2)}</li>
                <li><strong>Market Cap ($):</strong> {crypto.market.toFixed(2)}</li>
                <li><strong>Pourcentage de changement en 24h:</strong> {crypto.change.toFixed(2)}%</li>
                <li><strong>VWAP en 24H:</strong> {crypto.vwap.toFixed(2)}</li>
                <li><strong>Timestamp:</strong> {new Date(crypto.timestamp).toLocaleString()}</li>
            </ul>
        </div>
    );
}

export default CryptoDetails;