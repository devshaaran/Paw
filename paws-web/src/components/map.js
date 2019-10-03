import React, {Component} from 'react';
import ReactMapboxGl, { Layer, Feature, Source, GeoJSONLayer } from 'react-mapbox-gl';
import DrawControl from 'react-mapbox-gl-draw';
import turf from 'turf';
import 'whatwg-fetch';
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

        this.state = {
            geoJsonSourceOptions: {
                    type: "geojson",
                    data: {
                    type: "Feature",
                    properties: {},
                    geometry: {
                    type: "LineString",
                    coordinates: [
                        [-77.0366048812866, 38.89873175227713],
                        [-77.03364372253417, 38.89876515143842],
                        [-77.03364372253417, 38.89549195896866],
                        [-77.02982425689697, 38.89549195896866],
                        [-77.02400922775269, 38.89387200688839],
                        [-77.01519012451172, 38.891416957534204],
                        [-77.01521158218382, 38.892068305429156],
                        [-77.00813055038452, 38.892051604275686],
                        [-77.00832366943358, 38.89143365883688],
                        [-77.00818419456482, 38.89082405874451],
                        [-77.00815200805664, 38.88989712255097]
                    ]
                    }
                    }
                
            }
        }
    }

   

    onDrawCreate(features){

        // 'https://api.mapbox.com/optimized-trips/v1/mapbox/driving/' + coordinates.join(';') + '?distributions=' + distributions.join(';') + '&overview=full&steps=true&geometries=geojson&source=first&access_token=' + mapboxgl.accessToken;

        fetch('https://api.mapbox.com/optimized-trips/v1/mapbox/driving/' + features.features[0].geometry.coordinates.join(';') + '?access_token=pk.eyJ1IjoiamFrZW9scyIsImEiOiJjazE5cWJrZmEwMTN6M21ucnJuOWlkcnpyIn0.cR5SgRCmYX0xA9gUCBliIw')
        .then((response) => {
            return response.json()
        }).then((data) => {
            console.log(data, 'data returned')
            var routeGeoJSON = turf.featureCollection([turf.feature(data.trips[0].geometry)]);

            console.log(routeGeoJSON, 'route geojson');
            if (!data.trips[0]) {

            }
            else {// good to go
                this.setState({
                    geoJsonSourceOptions: data,
                });
                

            }


        })

       
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
                <Source id="source_id" geoJsonSource={this.state.geoJsonSourceOptions} />
                <GeoJSONLayer
                type="Feature"
                sourceId="source_id"
                />
                    
                <DrawControl onDrawCreate={this.onDrawCreate} onDrawUpdate={this.onDrawUpdate} />

                </Map>

            </div>
        )
    }
}