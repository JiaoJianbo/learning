import React, { Component } from 'react';
// import logo from './assets/images/logo.svg';

import { BrowserRouter as Router, Route, Link } from "react-router-dom";

import './assets/css/App.css';

import TodoList from './components/TodoList';
import TodoList2 from './components/TodoList2';

class App extends Component {
  render() {
    return (
      <Router>
        <div className="App">


          <Link to="/">TodoList</Link>
          &nbsp;|&nbsp; 
          <Link to="/todolist2">TodoList2</Link>

          <Route exact path="/" component={TodoList} />
          <Route path="/todolist2" component={TodoList2} />

          {/* 
          <header className="App-header">
            <img src={logo} className="App-logo" alt="logo" />
            <p>
              Edit <code>src/App.js</code> and save to reload.
            </p>
            <a
              className="App-link"
              href="https://reactjs.org"
              target="_blank"
              rel="noopener noreferrer"
            >
              Learn React
            </a>
          </header> 
          */}
        </div>
      </Router>
    );
  }
}

export default App;
