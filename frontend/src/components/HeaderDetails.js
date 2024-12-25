import React, { useState } from "react";
import '../static/css/headerDetails.css';
import {Link} from "react-router-dom";

const HeaderDetails = () => {
    return (
        <header className="header">
            <h1>La cryptomonnaie de l'avenir</h1>
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
}

export default HeaderDetails;