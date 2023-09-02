import React, {useState} from 'react'
import { Navigate } from 'react-router-dom';

export default function Home() {
  const [sum, setSum] = useState(1);
  return (
    <div><h3>我是 Home 的内容</h3>
      {/* Navigate 用于重定向，只要经过渲染，就会引起视图切换 */}
      {sum === 2 ? <Navigate to='/about' replace={false}/> : <h5>当前 sum 值为： {sum}</h5>}
      <button onClick={()=> setSum(2)}>设置 sum 值为 2</button>
    </div>
  )
}
