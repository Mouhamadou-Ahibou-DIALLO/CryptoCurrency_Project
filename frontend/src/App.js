import React from "react";
import { Route, Routes } from "react-router-dom";
import Header from "./components/Header";
import CryptoList from "./components/CryptoList";
import CryptoDetails from "./components/CryptoDetails";

function App() {
    return (
        <div>
            <Header />
            <Routes>
                <Route path="/" element={<CryptoList />} />
                <Route path="/cryptocurrencies/:id" element={<CryptoDetails />} />
            </Routes>
        </div>
    );
}

export default App;
