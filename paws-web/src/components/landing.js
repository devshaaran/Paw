import React, { Component } from 'react';
import {Jumbotron, Container, Row, Col, Image} from 'react-bootstrap';
export default class Landing extends Component {
    render(){
        return (
            <div className="landing-div"> 
             <Jumbotron style={{backgroundColor: 'white'}}>
            <Container>
                <Row className="h-100">
                    <Col className="my-auto">
                        <h1 style={{textAlign: 'left'}}>Explore with your pet.</h1>
                        <p style={{paddingTop: '10px', textAlign: 'left'}}>
                            Find a community for yourself, and your furr-friend
                        </p>
                        <p>
                        </p>
                    </Col>
                    <Col>
                        <Image src="./fogg-searching-2.png" fluid/>
                    </Col>
                </Row>
            </Container>
            </Jumbotron>

              


            </div>
        )
    }
}