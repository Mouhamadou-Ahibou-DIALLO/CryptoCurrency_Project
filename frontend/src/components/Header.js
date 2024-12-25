import React, { useState } from "react";
import '../static/css/header.css';
import {Link} from "react-router-dom";

const Header = () => {
    const [search, setSearch] = useState("");

    const handleSearchChange = (e) => {
        setSearch(e.target.value);
    };

    const handleSearch = () => {
        console.log("Searching for:", search);
        // Implémentez la logique pour effectuer la recherche.
    };

    return (
        <header className="header">
            <h1>La cryptomonnaie de l'avenir</h1>
            <div>
                <input
                    type="text"
                    placeholder="Rechercher..."
                    value={search}
                    onChange={handleSearchChange}
                />
                <button onClick={handleSearch}>Rechercher</button>
            </div>
            <div>
                <Link to="/Register">
                    <button>Inscription</button>
                </Link>

                <Link to="/Login">
                    <button className="connexion">Connexion</button>
                </Link>
            </div>
        </header>
    );
};

export default Header;
