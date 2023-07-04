import React, { Component } from 'react'

export default class Message extends Component {
  render() {
    return (
      <div>
        <ul>
            <li>
                <a href="/message1">Message 1</a> &nbsp; &nbsp;
            </li>
            <li>
                <a href="/message2">Message 2</a> &nbsp; &nbsp;
            </li>
            <li>
                <a href="/message3">Message 3</a> &nbsp; &nbsp;
            </li>
        </ul>

      </div>
    )
  }
}
