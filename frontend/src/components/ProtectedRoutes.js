import React from 'react';
import { Navigate, Outlet } from 'react-router-dom';

const ProtectedRoute = () => {
    const token = localStorage.getItem("authToken");

    // Si pas de token, redirige vers la page de login
    return token ? <Outlet /> : <Navigate to="/login" />;
};

export default ProtectedRoute;
