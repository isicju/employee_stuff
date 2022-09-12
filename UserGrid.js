import React from 'react';
import {AgGridReact} from 'ag-grid-react';

import 'ag-grid-community/dist/styles/ag-grid.css';
import 'ag-grid-community/dist/styles/ag-theme-alpine.css';

class UserGrid extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            rowData: [],
            columnDefs: [
                {field: 'id'},
                {field: 'firstName'},
                {field: 'lastName'},
                {field: 'hireDate'},
                {field: 'email'},
                {field: 'salary'},
            ]
        };
    }

    componentDidMount() {
        fetch('http://localhost:8080/employees/')
            .then(response => response.json())
            .then(response => {
                const rowData = [];

                response.forEach(user => {
                        rowData.push({
                            id: user.id,
                            firstName: user.firstName,
                            lastName: user.lastName,
                            hireDate: user.hireDate,
                            email: user.email,
                            salary: user.salary
                        })
                    }
                );
                this.setState(prevState => {
                        prevState.rowData = rowData
                    }
                )
            });
    }

    render() {
        return <div className="ag-theme-alpine" style={{height: 500, width: 1200}}>
            <AgGridReact
                rowData={this.state.rowData}
                columnDefs={this.state.columnDefs}>
            </AgGridReact>
        </div>
    };
}

export default UserGrid;