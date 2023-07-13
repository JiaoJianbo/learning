import {Link, Routes, Route, useNavigate } from 'react-router-dom';
import Detail from './Detail/Detail';

export default function Message() {
  const state = {messageArr:[
    {id: "01", title: 'Message01'},
    {id: "02", title: 'Message02'},
    {id: "03", title: 'Message03'}
  ]};

  // ------ V6 的写法 -------
  let navigate = useNavigate();
  // console.log('Message@navigate', navigate);

  // Message 中发送参数的方式不同，Detail 中就要采用不同的接收方式

  function pushShow (id, title) {
    // 携带 params 参数
    // navigate(`detail/${id}/${title}`, {replace: false});
    // 携带 search 参数
    // navigate(`detail?id=${id}&title=${title}`, {replace: false});
    // 携带 state 参数
    navigate('detail', {replace: false, state: {id, title}});
  }


  function replaceShow (id, title) {
    // 携带 params 参数
    // navigate(`detail/${id}/${title}`, {replace: true});
    // 携带 search 参数
    // navigate(`detail?id=${id}&title=${title}`, {replace: true});
    // 携带 state 参数
    navigate('detail', {replace: true, state: {id, title}});
  }

  function goBack () {
    alert('NA');
  }

  function goForward () {
    alert('NA');
  }

  return (
    <div>
    <ul>
        {
        state.messageArr.map(msgObj => {
            //console.log(msgObj);
            return(
            <li key={msgObj.id}>
                {/* 向路由组件传递 params 参数 */}
                <Link to={`detail/${msgObj.id}/${msgObj.title}`}>params - {msgObj.title}</Link>&nbsp;&nbsp;

                {/* 向路由组件传递 search 参数 */}
                <Link to={`detail?id=${msgObj.id}&title=${msgObj.title}`}>search - {msgObj.title}</Link>&nbsp;&nbsp;

                {/* 向路由组件传递 state 参数 */}
                <Link to={`detail`} state={{id: msgObj.id, title:msgObj.title}}>state - {msgObj.title}</Link>&nbsp;&nbsp;

                <button onClick={()=> pushShow(msgObj.id, msgObj.title)}>push 查看</button> &nbsp;&nbsp;
                <button onClick={()=> replaceShow(msgObj.id, msgObj.title)}>replace 查看</button>
            </li>
            );
        })
        }
    </ul>

    <div>
        <Routes>
        {/* 声明接收 params 参数 - V6 以前写法 */}
        {/* <Route path='/home/message/detail/:id/:title' conponent={Detail}/> */}
        {/* 声明接收 params 参数 - V6 写法 */}
        <Route path='detail/:id/:title' element={<Detail/>}/>

        {/* 声明接收 search 参数 - V6 写法 */}
        <Route path='detail' element={<Detail/>}/>

        {/* 声明接收 state 参数 - V6 写法 */}
        <Route path='detail' element={<Detail/>}/>

        </Routes>
    </div>

    <div>
        <button onClick={goBack}>后退</button> &nbsp;
        <button onClick={goForward}>前进</button>
    </div>
    </div>
  )

}
