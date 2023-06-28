import React, { Component } from 'react'

import './List.css'

export default class List extends Component {
  render() {
    const {users, isFirst, isLoading, err} = this.props;

    return (

      <div className='row'>
        {/*
        <div className="card">
            <a rel="noreferrer" href="http://localhost:3000/" target='_blank'>
                <img alt="avatar" src="https://img0.baidu.com/it/u=773393399,1606947630&fm=253&fmt=auto&app=138&f=JPEG?w=200&h=200"/>
                <p className='card-text'>React User</p>
            </a>
        </div>
        <div className="card">
            <a rel="noreferrer" href="http://localhost:3000/" target='_blank'>
                <img alt="avatar" src="https://img0.baidu.com/it/u=773393399,1606947630&fm=253&fmt=auto&app=138&f=JPEG?w=200&h=200"/>
                <p className='card-text'>React User</p>
            </a>
        </div> 
        */}

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
