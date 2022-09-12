import React from 'react';
import ReactDOM from 'react-dom';
import 'bootstrap/dist/css/bootstrap.css';
import {AgChartsReact} from 'ag-charts-react';
import UserGrid from './UserGrid'

import Col from 'react-bootstrap/Col';
import Nav from 'react-bootstrap/Nav';
import Row from 'react-bootstrap/Row';
import Tab from 'react-bootstrap/Tab';


class ChartExample extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            options: {
                data: [
                    {label: 'Android', value: 56.9},
                    {label: 'iOS', value: 22.5},
                    {label: 'BlackBerry', value: 6.8},
                    {label: 'Symbian', value: 8.5},
                    {label: 'Bada', value: 2.6},
                    {label: 'Windows', value: 1.9},
                ],
                series: [
                    {
                        type: 'pie',
                        angleKey: 'value',
                        labelKey: 'label',
                    },
                ],
            }
        };
    }

    componentDidMount() {
        fetch('http://localhost:8080/userstats/')
            .then(response => response.json())
            .then(response => {
                const data = [];
                response.forEach(country => {
                        data.push({
                            label: country.country + ' ' + country.occurance,
                            value: country.occuranceRate
                        })
                    }
                );
                this.setState(
                    {
                        options: {
                            data: data,
                            series: [
                                {
                                    type: 'pie',
                                    angleKey: 'value',
                                    labelKey: 'label',
                                },
                            ],
                        },
                    }
                )
            });
    }

    render() {
        return (
            <Tab.Container id="left-tabs-example" defaultActiveKey="first">
                <Row>
                    <Col sm={3}>
                        <Nav variant="pills" className="flex-column">
                            <Nav.Item>
                                <Nav.Link eventKey="first">Users</Nav.Link>
                            </Nav.Item>
                            <Nav.Item>
                                <Nav.Link eventKey="second">Analytics</Nav.Link>
                            </Nav.Item>
                        </Nav>
                    </Col>
                    <Col sm={9}>
                        <Tab.Content>
                            <Tab.Pane eventKey="first">
                                <UserGrid/>
                            </Tab.Pane>
                            <Tab.Pane eventKey="second">
                                <h1>KEK</h1>
                                <AgChartsReact options={this.state.options}/>
                            </Tab.Pane>
                        </Tab.Content>
                    </Col>
                </Row>
            </Tab.Container>
        )

    }
}

ReactDOM.render(
    <ChartExample/>
    ,
    document.getElementById('root')
);
