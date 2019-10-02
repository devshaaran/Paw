import React, { Component } from 'react';
import Navbar from './components/navbar';
import Searchbar from './components/searchbar';
import Mapviewer from './components/map';

import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';

export default class App extends Component {
  render(){
    return (
      <div className="App container">
      <div>
        Paws Explorer
      </div>
      <Searchbar />

      <h3 style={{textAlign: "left"}}>Create a Dog-Walking Route</h3>
      <Mapviewer />

      <h3>Explore Your Area</h3>
      </div>
    )
  }
}


