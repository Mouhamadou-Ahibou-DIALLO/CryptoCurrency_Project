import React from "react";
import "../static/css/about.css";
import {Link} from "react-router-dom";
import logo from "../static/images/about.jpg";

const About = () => {
    return (
        <div className="about-page">
            <header className="header">
                <h1>À Propos de nous</h1>
                <Link to="/">
                    <button className="about-accueil">Accueil</button>
                </Link>
                <Link to="/Register">
                    <button className="about-register">Inscription</button>
                </Link>
                <Link to="/Login">
                    <button className="connexion">Connexion</button>
                </Link>
            </header>
            <section className="about-section">
                <h2>La Cryptomonnaie de l'Avenir</h2>
                <p>
                    Bienvenue dans l'univers de la nouvelle génération de cryptomonnaie !
                    Nous croyons en un avenir où les transactions financières sont
                    rapides, sécurisées, et accessibles à tous. Notre projet est conçu
                    pour offrir une alternative moderne et durable, en intégrant les
                    dernières technologies blockchain.
                </p>
                <p>
                    Notre mission est de simplifier l'accès à la cryptomonnaie pour tous,
                    tout en maintenant un haut niveau de sécurité et de transparence.
                    Rejoignez-nous et soyez un acteur du changement vers un système
                    financier global plus équitable.
                </p>
                <p>
                    <img
                        src={logo}
                        alt="La Cryptomonnaie de l'Avenir"
                        className="about-image"
                    />
                </p>
            </section>
            <section className="contact-section">
                <h2>Contactez-nous</h2>
                <p>Pour toute question ou collaboration, n'hésitez pas à nous écrire :</p>
                <p>
                <strong>Email :</strong>{" "}
                    <a href="mailto:ahiboudiallo2018@gmail.com">
                        ahiboudiallo2018@gmail.com
                    </a>
                </p>
            </section>
            <footer className="footer">
                <p>&copy; 2024 - Cryptomonnaie de l'Avenir. Tous droits réservés.</p>
            </footer>
        </div>
    );
};

export default About;
