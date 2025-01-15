import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import Header from './Header';
import '../static/css/cryptoList.css';

const CryptoList = () => {
    const [cryptos, setCryptos] = useState([]);
    const [filteredCryptos, setFilteredCryptos] = useState([]);
    const [loading, setLoading] = useState(true);
    const [animationState, setAnimationState] = useState(false);

    useEffect(() => {
        const fetchCryptos = () => {
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
                    updateCryptoList(data);
                    setLoading(false);
                })
                .catch((error) => {
                    console.error("Erreur :", error);
                    setLoading(false);
                });
        };

        fetchCryptos();
        const interval = setInterval(fetchCryptos, 59000);

        return () => clearInterval(interval);
    }, []);

    const updateCryptoList = (newData) => {
        setCryptos((prevCryptos) => {
            const updatedCryptos = newData.map((newCrypto) => {
                const existingCrypto = prevCryptos.find(c => c.name === newCrypto.name);
                if (existingCrypto) {
                    return {
                        ...newCrypto,
                        priceChange:
                            newCrypto.price > existingCrypto.price
                                ? 'up'
                                : newCrypto.price < existingCrypto.price
                                    ? 'down'
                                    : 'same',
                    };
                }
                return { ...newCrypto, priceChange: 'new' };
            });
            setAnimationState(true);
            setTimeout(() => setFilteredCryptos(updatedCryptos), 100);
            return updatedCryptos;
        });
        setTimeout(() => setAnimationState(false), 1000);
        setFilteredCryptos(newData);
    };

    const handleSearch = async (query) => {
        const username = "momo";
        const password = "Avignon2024@?";
        const credentials = btoa(`${username}:${password}`);

        try {
            const response = await fetch(`/api/cryptocurrencies/search/${query}`, {
                headers: {
                    Authorization: `Basic ${credentials}`,
                },
            });
            if (response.ok) {
                const result = await response.json();
                setFilteredCryptos([result]);
            } else {
                alert("Aucun résultat trouvé !");
            }
        } catch (error) {
            console.error("Erreur lors de la recherche :", error);
        }
    };

    const handleLocalSearch = (query) => {
        const trimmedQuery = query.trim().toLowerCase();
        if (!trimmedQuery) {
            alert("Veuillez entrer une valeur pour la recherche !");
            return;
        }

        const filtered = cryptos.filter(
            (crypto) =>
                crypto.name.toLowerCase().includes(trimmedQuery) ||
                crypto.symbol.toLowerCase().includes(trimmedQuery) ||
                crypto.rank.toString() === trimmedQuery
        );

        if (filtered.length === 0) {
            alert("Aucun résultat trouvé !");
        } else {
            setFilteredCryptos(filtered);
        }
    };

    const handleReset = () => {
        setFilteredCryptos(cryptos);
    };

    if (loading) {
        return <p>Chargement des cryptomonnaies...</p>;
    }

    return (
        <div>
            <Header onSearch={handleLocalSearch} onReset={handleReset} />
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
                {filteredCryptos.map((crypto) => (
                    <tr key={crypto.id} className={`crypto-row ${animationState ? crypto.priceChange : ''}`}>
                        <td>{crypto.rank}</td>
                        <td>
                            <Link to={`/cryptocurrencies/${crypto.name}`}>{crypto.name}</Link>
                        </td>
                        <td>{crypto.symbol}</td>
                        <td>{crypto.price.toFixed(2)}</td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
};

export default CryptoList;
