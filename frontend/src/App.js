import React, { useState, useEffect } from "react";
import { Route, Routes } from "react-router-dom";
import CryptoList from "./components/CryptoList";
import CryptoDetails from "./components/CryptoDetails";
import Register from "./components/Register";
import Login from "./components/Login";
import Dashboard from "./components/Dashboard";
import ProtectedRoute from "./components/ProtectedRoutes";

const App = () => {
    const [isAuthenticated, setIsAuthenticated] = useState(false);

    const checkAuth = () => {
        const token = localStorage.getItem("authToken");
        console.log("token: ", token);
        setIsAuthenticated(!!token);
    };

    useEffect(() => {
        checkAuth();
    }, []);

    return (
        <div>
            <Routes>
                {/* Routes accessibles sans authentification */}
                <Route path="/Register" element={<Register />} />
                <Route path="/Login" element={<Login />} />
                <Route path="/Dashboard" element={<Dashboard />} />
                <Route path="/" element={<CryptoList />} />
                <Route path="/cryptocurrencies/:id" element={<CryptoDetails />} />
                <Route path="*" element={<div>Page non trouvée</div>} />

                {/*/!* Routes protégées *!/*/}
                {/*<Route*/}
                {/*    path="/Dashboard"*/}
                {/*    element={*/}
                {/*        <ProtectedRoute isAuthenticated={isAuthenticated}>*/}
                {/*            <Dashboard />*/}
                {/*        </ProtectedRoute>*/}
                {/*    }*/}
                {/*/>*/}
            </Routes>
        </div>
    );
};

export default App;
