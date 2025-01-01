import React, { createContext, useContext, useState } from "react";

const AuthContext = createContext();

export const useAuth = () => useContext(AuthContext);

export const AuthProvider = ({ children }) => {
    const [authToken, setAuthToken] = useState(null);
    const [isAuthenticated, setIsAuthenticated] = useState(false);

    const login = (isAuth) => {
        // setAuthToken(token);
        // localStorage.setItem("authToken", token);
        isAuth = true
        setIsAuthenticated(isAuth);
    };

    const logout = () => {
        // setAuthToken(null);
        // localStorage.removeItem("authToken");
        setIsAuthenticated(false);
    };

    return (
        <AuthContext.Provider value={{ isAuthenticated, login, logout }}>
            {children}
        </AuthContext.Provider>
    );
};