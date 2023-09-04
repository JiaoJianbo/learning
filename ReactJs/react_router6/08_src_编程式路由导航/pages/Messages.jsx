import React, { useState } from 'react'
import {Link, Outlet, useNavigate} from 'react-router-dom'

export default function Messages() {
  const [messages] = useState([
    {id: "001", title: 'message1', content: '落花人独立'},
    {id: "002", title: 'message2', content: '微雨燕双飞'}
  ]);

  const navigate = useNavigate();
  // console.log(navigate);

  function showDetail(m) {
    navigate('detail', {
      replace: false,
      state: {
        id: m.id,
        title: m.title,
        content: m.content
      }
    });
  }

  return (
    <div>
      <ul>
        {
          messages.map(m => {
            return (
              <li key={m.id}>
                <Link to='detail' state={{id: m.id, title: m.title, content: m.content}}>{m.title}</Link> &nbsp;&nbsp;
                <button onClick={()=>showDetail(m)}>Detail</button>
              </li>
            );
          })
        }
      </ul>
      {/* 指定路由组件呈现的位置 */}
      <Outlet/>
    </div>
  )
}
