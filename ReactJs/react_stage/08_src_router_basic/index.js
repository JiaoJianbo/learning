// 引入 React 核心库
import React from 'react'
// React18 from 'react-dom/client'
import ReactDOM from 'react-dom/client';
import {BrowserRouter, HashRouter} from 'react-router-dom'


import App from './App';

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(<BrowserRouter><App/></BrowserRouter>);