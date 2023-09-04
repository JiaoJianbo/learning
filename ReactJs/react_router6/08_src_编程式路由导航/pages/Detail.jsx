import React from 'react'
import { useLocation} from 'react-router-dom';

export default function Detail() {
  // const a = useLocation();
  // console.log(a);

  // const {id, title, content} = useLocation().state;
  // 解构赋值的连续写法
  const {state: {id, title, content}} = useLocation();
  return (
    <ul>
      <li>{id}</li>
      <li>{title}</li>
      <li>{content}</li>
    </ul>
  )
}
