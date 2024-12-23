import React, { useState } from "react";
import '../static/css/headerDetails.css';

const HeaderDetails = () => {
    return (
        <header className="header">
            <h1>La cryptomonnaie de l'avenir</h1>
            <div>
                <button>Inscription</button>
                <button>Connexion</button>
            </div>
        </header>
    );
}

export default HeaderDetails;