import React, {Component} from 'react';
import ReactMapboxGl, { Layer, Feature } from 'react-mapbox-gl';
import DrawControl from 'react-mapbox-gl-draw';
import '@mapbox/mapbox-gl-draw/dist/mapbox-gl-draw.css';


const Map = ReactMapboxGl({
    accessToken:
      'pk.eyJ1IjoiamFrZW9scyIsImEiOiJjazE5cWJrZmEwMTN6M21ucnJuOWlkcnpyIn0.cR5SgRCmYX0xA9gUCBliIw'
  });

export default class Mapviewer extends Component {

    constructor(props){
        super(props);
        this.onDrawCreate = this.onDrawCreate.bind(this);
        this.onDrawUpdate = this.onDrawUpdate.bind(this);
    }

    onDrawCreate(features){
        console.log('draw created', features);
    }

    onDrawUpdate(features){
        console.log('drow updated', features);
    }


    
    render(){
        return (
            <div>
                <p style={{textAlign: 'left'}}>Display heatmap? <input type="checkbox" /></p>
                <Map
                style="mapbox://styles/jakeols/ck19qs4jl2yqt1ck09g1amjcz"
                containerStyle={{
                    height: '600px',
                    width: '100%'
                }}
                >
                <Layer type="symbol" id="marker" layout={{ 'icon-image': 'marker-15' }}>
                    <Feature coordinates={[-0.481747846041145, 51.3233379650232]} />
                </Layer>
                <DrawControl onDrawCreate={this.onDrawCreate} onDrawUpdate={this.onDrawUpdate} />

                </Map>

            </div>
        )
    }
}