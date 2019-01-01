import React from 'react';

import { Layout, Input, Checkbox, Icon, Row, Col, List, Divider } from 'antd';

//引入自定义模块
import storage from '../model/storage';

import '../assets/css/index.css';

const {Header, Footer, Content } = Layout;

class TodoList2 extends React.Component {
    /**
     * 注意： this.setState({}) 是异步的
     * 
     * 参见官方文档: https://reactjs.org/docs/react-component.html#setstate
     * 
     * setState() does not always immediately update the component. It may batch or defer the update until later. 
     * This makes reading this.state right after calling setState() a potential pitfall. 
     * Instead, use componentDidUpdate or a setState callback (setState(updater, callback)), 
     * either of which are guaranteed to fire after the update has been applied. 
     * If you need to set the state based on the previous state, read about the updater argument below.
     */

    constructor(props) {
        super(props);

        this.state = { 
            header: "This is a TodoList demo",
            // list: [ /* Testing data */
            //     {title: "111111", checked: true},
            //     {title: "222222", checked: false},
            //     {title: "333333", checked: true},
            //     {title: "444444", checked: false}
            // ],
            list: [],
            todoList: [],
            doneList: []
        };
    }

    addTodo = (e)=> {
        if(e.keyCode === 13){
            //alert('add todo');
            //let value = this.refs.task.value;
            let value = e.target.value;
            let tempList = this.state.list;
            tempList.push({
                title:value, 
                checked:false
            });

            this.setState({
                list: tempList
            });

            e.target.value = ''; //clear the input
            
            // Store in localStorage
            storage.put("todolist", this.state.list);

            //refresh list
            this.updateList();
        }
    }

    removeTask = function(key){
        //console.log(key);
        var tempList = this.state.list;
        tempList.splice(key, 1);

        this.setState({
            list: tempList
        });
        
        // Store in localStorage
        storage.put("todolist", this.state.list);

        //refresh list
        this.updateList(this.state.list);
    }

    changeStatus = function(key){
        //console.log(key);
        let tempList = this.state.list;
        tempList[key].checked = !tempList[key].checked;

        this.setState({
            list: tempList
        });

        // Store in localStorage
        storage.put("todolist", this.state.list);

        //refresh list
        this.updateList(this.state.list);
    }


    //-----------------------------------------
    updateList = () => {
        let tempList = this.state.list;
        var tempTodoList = [], tempDoneList = [];

        // tempList.map((value, key) => {
        //     //console.log(key, value);
        //     value['aid'] = key;

        //     if(value.checked){
        //         tempDoneList.push(value);
        //     }else{
        //         tempTodoList.push(value);
        //     }
        // });

        tempList.forEach(function(curVal, idx, arr){
            // console.log(curVal, idx);
            let temp = curVal;
            temp['aid'] = idx;

            if(temp.checked){
                tempDoneList.push(temp);
            }else{
                tempTodoList.push(temp);
            }
        });
        
        this.setState({
            todoList: tempTodoList,
            doneList: tempDoneList
        });
    }

    componentWillMount(){
        // console.log('Will Mount');
    }

    componentDidMount(){
        //console.log('Did Mount');
        let tempList = storage.get("todolist");
        //console.log(tempList);

        if(tempList){
            this.setState({
                list: tempList
            }, () => {this.updateList()});

            // 由于 this.setState() 方法是异步的，所以在这里updateList的时候 list 为空
            //this.updateList();
        }
    }

    componentDidUpdate(){
        console.log('Did update');
        //this.updateList(); //放在这里会引起循环调用
    }

    render() {
        return (
            <div>
                <Layout>
                    <Header>
                        <Row type="flex" justify="center">
                            <Col span={4} style={{fontSize:'18px', color:'#FFFFFF'}}>Todo List</Col>
                            <Col span={8}><Input size="default" required placeholder="Press Enter to add" onPressEnter={this.addTodo} /></Col>
                        </Row>
                    </Header>
                        
                    <Content>
                        <Row type="flex" justify="center">
                            <Col span={10}>
                                <List
                                    header="待办事项"
                                    dataSource={this.state.todoList}
                                    renderItem={item => (
                                        <List.Item actions={[<Icon type="delete" onClick={this.removeTask.bind(this, item.aid)}/>]}>
                                            <Checkbox checked={item.checked} onChange={this.changeStatus.bind(this, item.aid)}/>&nbsp;&nbsp;
                                            {item.title}
                                        </List.Item>
                                    )}
                                />
                            </Col>
                        </Row>

                        <Row type="flex" justify="center">
                            <Col span={10}>
                                <List
                                    header="已办事项"
                                    dataSource={this.state.doneList}
                                    renderItem={item => (
                                        <List.Item actions={[<Icon type="delete" theme="twoTone" onClick={this.removeTask.bind(this, item.aid)}/>]}>
                                            <Checkbox checked={item.checked} onChange={this.changeStatus.bind(this, item.aid)}/>&nbsp;&nbsp;
                                            {item.title}
                                        </List.Item>
                                    )}
                                />
                            </Col>
                        </Row>
                    </Content>

                    <Footer style={{ textAlign: 'center' }}>
                        <Divider dashed />
                        Copyright &copy;
                    </Footer>
                </Layout>
            </div>
        );
    }
}

export default TodoList2;