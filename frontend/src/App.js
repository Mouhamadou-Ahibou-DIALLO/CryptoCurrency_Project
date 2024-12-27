import React from "react";
import { Route, Routes } from "react-router-dom";
import CryptoList from "./components/CryptoList";
import CryptoDetails from "./components/CryptoDetails";
import Register from "./components/Register";
import Login from "./components/Login";
import Dashboard from "./components/Dashboard";

function App() {
    return (
        <div>
            <Routes>
                <Route path="/Dashboard" element={<Dashboard />} />
                <Route path="/Register" element={<Register />} />
                <Route path="/Login" element={<Login />} />
                <Route path="/cryptocurrencies/:id" element={<CryptoDetails />} />
                <Route path="/" element={<CryptoList />} />
                <Route path="*" element={<div>Page non trouvée</div>} />
            </Routes>
        </div>
    );
}

export default App;
