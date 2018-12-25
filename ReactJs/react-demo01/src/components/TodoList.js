import React from 'react';

import Header from './Header';

import '../assets/css/index.css';

class TodoList extends React.Component {
    constructor(props) {
        super(props);

        this.state = { 
            header: "This is a TodoList demo",
            list: [
                {title: "111111", checked: true},
                {title: "222222", checked: false},
                {title: "333333", checked: true},
                {title: "444444", checked: false}
            ]
        };
    }

    addTodo = (e)=> {
        if(e.keyCode === 13){
            //alert('add todo');
            let value = this.refs.task.value;
            let tempList = this.state.list;
            tempList.push({
                title:value, 
                checked:false
            });

            this.setState({
                list: tempList
            });

            e.target.value = ''; //clear the input
        }
    }

    removeTask = function(key){
        //alert(key);
        var tempList = this.state.list;
        tempList.splice(key, 1);

        this.setState({
            list: tempList
        });
    }

    changeStatus = function(key){
        let tempList = this.state.list;
        
        tempList[key].checked = !tempList[key].checked;

        this.setState({
            list: tempList
        });
    }

    render() {
        return (
            <div className="todolist">
                <Header title={this.state.header}/>
                <hr />

                <div className="todotitle">
                    <input ref="task" type="text" placeholder="Press Enter to add" onKeyUp={this.addTodo} />
                </div>

                <ul>
                    {
                        this.state.list.map((value, key) => {
                            if(!value.checked){
                                return (
                                <li key={key}>
                                    <input type="checkbox" checked={value.checked} onChange={this.changeStatus.bind(this, key)}/>
                                    {value.title}
                                    -- 
                                    <button onClick={this.removeTask.bind(this, key)}>Delete</button>
                                </li>
                                )
                            }
                        })
                    }
                </ul>

                <hr />
                <ul>
                    {
                        this.state.list.map((value, key) => {
                            if(value.checked){
                                return (
                                <li key={key}>
                                    <input type="checkbox" checked={value.checked} onChange={this.changeStatus.bind(this, key)}/>
                                    {value.title}
                                    -- 
                                    <button onClick={this.removeTask.bind(this, key)}>Delete</button>
                                </li>
                                )
                            }
                        })
                    }
                </ul>
            </div>
        );
    }
}

export default TodoList;