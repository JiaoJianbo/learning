import React, { Component } from 'react'
import axios from 'axios';
import PubSub from 'pubsub-js';

export default class Search extends Component {

    search = () => {
        // 解构赋值的连续写法，相当于 {keyWordElement} = this; {value} = keyWordElement;
        // const {keyWordElement: {value}} = this;
        // console.log(value);
        // 连续解构赋值，并重命名
        const {keyWordElement: {value: keyword}} = this;
        console.log(keyword);

        // 发送请求前通知 App 更新 state
        // this.props.updateAppState({isFirst: false, isLoading: true});
        // 发送请求前通知 List 更新 state
        PubSub.publish('search', {isFirst: false, isLoading: true});

        // Github 的这个 API 允许跨域请求
        axios.get(`https://api.github.com/search/users?q=${keyword}`).then(
            response => {
                console.log('Success', response.data);
                // 请求成功后通知 App 更新 state
                // this.props.updateAppState({users:response.data.items, isLoading: false});
                // 请求成功后通知 List 更新 state
                PubSub.publish('search', {users:response.data.items, isLoading: false});

            },
            error => {
                console.log('faild, ', error);
                // 请求失败后通知 App 更新 state
                // this.props.updateAppState({err: error.message, isLoading: false});
                // 请求失败后通知 List 更新 state
                PubSub.publish('search', {err: error.message, isLoading: false});
            }
        );
    }


  render() {
    return (
      <section className='jumbotron'>
        <h3 className='jumbotron-heading'>Search Github User</h3>
        <div>
            <input ref={c => this.keyWordElement = c} type="text" placeholder='enter the name you searh'/> &nbsp;
            <button onClick={this.search}>Search</button>
        </div>
      </section>
    )
  }
}
