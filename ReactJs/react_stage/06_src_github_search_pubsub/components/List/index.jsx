import React, { Component } from 'react'
import PubSub from 'pubsub-js';

import './index.css'

export default class List extends Component {
  state = {
    users: [],
    isFirst: true,
    isLoading: false,
    err: ''
  }

  componentDidMount() {
    this.token = PubSub.subscribe('search', (msg, stateObj)=> {
      console.info('List@', msg, stateObj);
      this.setState(stateObj);
    });
  }

  componentWillUnmount() {
    PubSub.unsubscribe(this.token);
  }

  render() {
    const {users, isFirst, isLoading, err} = this.state;

    return (

      <div className='row'>
        {
            // 可以浏览器访问一下，看看返回的数据结构。https://api.github.com/search/users?q=Bobby

            isFirst ? <h2>ENter name to search</h2> :
            isLoading ? <h3>Loading......</h3> :
            err ? <h3 style={{color:'red'}}>{err}</h3> :
            users.map(userObj => {
                return(
                    <div key={userObj.id} className="card">
                        <a rel="noreferrer" href={userObj.html_url} target='_blank'>
                            <img alt="avatar" src={userObj.avatar_url}/>
                            <p className='card-text'>{userObj.login}</p>
                        </a>
                    </div>
                );
            })
        }

      </div>
    )
  }
}
