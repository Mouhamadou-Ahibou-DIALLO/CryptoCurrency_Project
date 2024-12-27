import React, { useState } from "react";
import "../static/css/header.css";
import { Link } from "react-router-dom";

const Header = ({ onSearch, onReset }) => {
    const [search, setSearch] = useState("");

    const handleSearchChange = (e) => {
        setSearch(e.target.value);
    };

    const handleSearch = () => {
        if (search.trim() === "") {
            alert("Veuillez entrer une valeur pour la recherche !");
            return;
        }
        onSearch(search);
    };

    const handleReset = () => {
        setSearch("");
        onReset();
    };

    return (
        <header className="header">
            <h1>La cryptomonnaie de l'avenir</h1>
            <div className="search-container">
                <input
                    type="text"
                    placeholder="Rechercher par symbole, nom ou rang..."
                    value={search}
                    onChange={handleSearchChange}
                />
                <button onClick={handleSearch}>Rechercher</button>
                <button onClick={handleReset} className="reset-button">
                    Réinitialiser
                </button>
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