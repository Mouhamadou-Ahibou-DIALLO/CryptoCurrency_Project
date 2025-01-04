import React, { useEffect } from "react";
import { Route, Routes } from "react-router-dom";
import CryptoList from "./components/CryptoList";
import CryptoDetails from "./components/CryptoDetails";
import Register from "./components/Register";
import Login from "./components/Login";
import Dashboard from "./components/Dashboard";
import About from "./components/About";
import ForgotPassword from "./components/ForgotPassword";
import ResetPassword from "./components/ResetPassword";
import PageAlerts from "./components/PageAlerts";

const App = () => {
    return (
            <div>
                <Routes>
                    <Route path="/About" element={<About />} />
                    <Route path="/Register" element={<Register />} />
                    <Route path="/Login" element={<Login />} />
                    <Route path="/PageAlerts/:id" element={<PageAlerts />} />
                    <Route path="/Dashboard/:id" element={<Dashboard />} />
                    <Route path="/ForgotPassword" element={<ForgotPassword />} />
                    <Route path="/ResetPassword" element={<ResetPassword />} />
                    <Route path="/" element={<CryptoList />} />
                    <Route path="/cryptocurrencies/:id" element={<CryptoDetails />} />
                    <Route path="*" element={<div>Page non trouvée</div>} />
                </Routes>
            </div>
    );
};

export default App;
