import React, { Component } from 'react'
import Search from './componets/Search/Search'
import List from './componets/List/List'

export default class App extends Component {
    state = {
      users: [],
      isFirst: true,
      isLoading: false,
      err: ''
    }

    updateAppState = (stateObj) => {
      this.setState(stateObj)
    }

  render() {
    return (
      <div className='container'>
        <Search updateAppState={this.updateAppState}/>
        <List {...this.state}/>
      </div>
    )
  }
}
