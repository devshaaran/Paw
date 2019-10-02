import React, { Component } from 'react';
import {Nav} from 'react-bootstrap';
export default class Navbar extends Component {

    render(){
        return (
            <Nav
            activeKey="/home"
            onSelect={selectedKey => alert(`selected ${selectedKey}`)}
            >
            <Nav.Item>
                <Nav.Link href="/home">Home</Nav.Link>
            </Nav.Item>
            </Nav>
        )
    }

}