import React, {Component} from 'react';
import ReactMapboxGl, { Layer, Feature, Source, GeoJSONLayer } from 'react-mapbox-gl';
import DrawControl from 'react-mapbox-gl-draw';
import turf from 'turf';
import 'whatwg-fetch';
import '@mapbox/mapbox-gl-draw/dist/mapbox-gl-draw.css';

const mbxClient = require('@mapbox/mapbox-sdk');
const baseClient = mbxClient({ accessToken: 'pk.eyJ1IjoiamFrZW9scyIsImEiOiJjazE5cWJrZmEwMTN6M21ucnJuOWlkcnpyIn0.cR5SgRCmYX0xA9gUCBliIw' });
const mapService = require('@mapbox/mapbox-sdk/services/map-matching');
const mapClient = mapService(baseClient);

const Map = ReactMapboxGl({
    accessToken:
      'pk.eyJ1IjoiamFrZW9scyIsImEiOiJjazE5cWJrZmEwMTN6M21ucnJuOWlkcnpyIn0.cR5SgRCmYX0xA9gUCBliIw'
  });

  const linePaint = {
    'line-color': 'red',
    'line-width': 5
  };
export default class Mapviewer extends Component {

    constructor(props){
        super(props);
        this.onDrawCreate = this.onDrawCreate.bind(this);
        this.onDrawUpdate = this.onDrawUpdate.bind(this);

        this.state = {
            geoJsonSourceOptions: {
                
                "id": "route",
                "type": "line",
                "source": {
                  "type": "geojson",
                  "data": {
                    "type": "Feature",
                    "properties": {},
                    "geometry": {
                        "coordinates": [
                            [-122.48369693756104, 37.83381888486939],
                            [-122.48348236083984, 37.83317489144141],
                            [-122.48339653015138, 37.83270036637107],
                            [-122.48356819152832, 37.832056363179625],
                            [-122.48404026031496, 37.83114119107971],
                            [-122.48404026031496, 37.83049717427869],
                            [-122.48348236083984, 37.829920943955045],
                            [-122.48356819152832, 37.82954808664175],
                            [-122.48507022857666, 37.82944639795659],
                            [-122.48610019683838, 37.82880236636284],
                            [-122.48695850372314, 37.82931081282506],
                            [-122.48700141906738, 37.83080223556934],
                            [-122.48751640319824, 37.83168351665737],
                            [-122.48803138732912, 37.832158048267786],
                            [-122.48888969421387, 37.83297152392784],
                            [-122.48987674713133, 37.83263257682617],
                            [-122.49043464660643, 37.832937629287755],
                            [-122.49125003814696, 37.832429207817725],
                            [-122.49163627624512, 37.832564787218985],
                            [-122.49223709106445, 37.83337825839438],
                            [-122.49378204345702, 37.83368330777276]
                        ],
                        "type": "LineString"
                    },
                  }
                },
                "layout": {
                  "line-join": "round",
                  "line-cap": "round"
                },
                "paint": {
                  "line-color": "#03AA46",
                  "line-width": 200,
                  "line-opacity": 0.8
                }
              
            }
        }
    }

   

    onDrawCreate(features){

        console.log(features);

        let pointObjects = features.features[0].geometry.coordinates.map((item) => {
            return {coordinates: item}
        });
        console.log(pointObjects)

        mapClient.getMatch({
            points: pointObjects,
            tidy: false,
            geometries: 'geojson'
          })
            .send()
            .then(response => {
              const matching = response.body;
              if(matching.matchings.length > 0){
                var coords = matching.matchings[0].geometry;
                console.log(coords)
                let updateOjb = {...this.state.geoJsonSourceOptions};
                updateOjb.source.data.geometry = coords;
                console.log(updateOjb)
                 this.setState({geoJsonSourceOptions: updateOjb})
              }
             
            })
       
    }

    onDrawUpdate(features){
        console.log('drow updated', features);
    }

    componentWillUpdate(nextProps, nextState) {
        const { map, geoJsonSourceOptions } = nextState;
        if (map) {
            console.log(geoJsonSourceOptions, 'geosource')
          map.getSource('source_id').setData(geoJsonSourceOptions.source.data);
        }
      }

      onStyleLoad = (map, e) => {
        this.setState( {map} );
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
                onStyleLoad={this.onStyleLoad}
                >
                <Layer type="symbol" id="marker" layout={{ 'icon-image': 'marker-15' }}>
                    <Feature coordinates={[-0.481747846041145, 51.3233379650232]} />
                </Layer>
               
                <Source id="source_id" geoJsonSource={this.state.geoJsonSourceOptions.source} />
                <Layer type="line" id="layer_id" sourceId="source_id" />
               
                    
                <DrawControl onDrawCreate={this.onDrawCreate} onDrawUpdate={this.onDrawUpdate} 
                controls= {{
                    line_string: true,
                    trash: true
                  }} 
                  displayControlsDefault={false}/>

                </Map>

            </div>
        )
    }
}