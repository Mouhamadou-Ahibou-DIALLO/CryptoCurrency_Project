import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import CryptoList from "./components/CryptoList";
import CryptoDetails from "./components/CryptoDetails";
import Register from "./components/Register";
import Login from "./components/Login";
import Dashboard from "./components/Dashboard";
import About from "./components/About";
import ForgotPassword from "./components/ForgotPassword";
import ResetPassword from "./components/ResetPassword";
import PageAlerts from "./components/PageAlerts";
import Porfolio from "./components/Porfolio";
import ProtectedRoute from "./components/ProtectedRoutes";

const App = () => {
    return (
            <div>


                    <Routes>

                        <Route path="/About" element={<About />} />
                        <Route path="/Register" element={<Register />} />
                        <Route path="/Login" element={<Login />} />
                        <Route path="/ForgotPassword" element={<ForgotPassword />} />
                        <Route path="/ResetPassword" element={<ResetPassword />} />
                        <Route path="/" element={<CryptoList />} />
                        <Route path="/cryptocurrencies/:name" element={<CryptoDetails />} />
                        <Route path="*" element={<div>Page non trouvée</div>} />

                        {/* Route protégée */}
                        <Route path="/Dashboard/:id" element={<ProtectedRoute />}>
                            <Route path="" element={<Dashboard />} />
                        </Route>

                        <Route path="/PageAlerts/:id" element={<ProtectedRoute />}>
                            <Route path="" element={<PageAlerts />} />
                        </Route>

                        <Route path="/Porfolio/:id" element={<ProtectedRoute />}>
                            <Route path="" element={<Porfolio />} />
                        </Route>

                    </Routes>

            </div>
    );
};

export default App;
