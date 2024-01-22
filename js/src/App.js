import React from 'react'
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom'
import './App.css';
import Home from './pages/Home'
import Login from './pages/Login';
import SignUp from './pages/SignUp';
import Analytics from './pages/Analytics';
import Expenses from './pages/Expenses';
import MyAccount from './pages/MyAccount';

function App() {
  return (
    <Router>
      <Routes>
        <Route path='/' exac Component={Home} />
        <Route path='/login' exac Component={Login} />
        <Route path='/signup' exac Component={SignUp} />
        <Route path='/analytics' exac Component={Analytics} />
        <Route path='/expenses' exac Component={Expenses} />
        <Route path='/account' exac Component={MyAccount} />
      </Routes>
    </Router>
    // <div className="App">
    //   <header className="App-header">
    //     <img src={logo} className="App-logo" alt="logo" />
    //     <p>
    //       Edit <code>src/App.js</code> and save to reload.
    //     </p>
    //     <a
    //       className="App-link"
    //       href="https://reactjs.org"
    //       target="_blank"
    //       rel="noopener noreferrer"
    //     >
    //       Learn React
    //     </a>
    //   </header>
    // </div>
  );
}

export default App;
