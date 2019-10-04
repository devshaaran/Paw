import React, { Component } from 'react';
import Navigation from './components/navbar';
import Searchbar from './components/searchbar';
import Mapviewer from './components/map';
import Explore from './components/explore';
import Landing from './components/landing';
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';

export default class App extends Component {
  render(){
    return (
      <div className="App">
        
      <Navigation />
      <Landing />
      {/* <Searchbar /> */}
<div className="container">
      <h3 style={{textAlign: "left"}}>Create a Dog-Walking Route</h3>
      <Mapviewer />
      <h3>Explore Your Area</h3>
      <Explore />
      </div>
      </div>
    )
  }
}


