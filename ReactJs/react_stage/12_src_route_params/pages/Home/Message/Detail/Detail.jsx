// import React, { Component } from 'react'
import { useParams } from 'react-router-dom';

const data = [
  {id: "01", content: 'Hello World'},
  {id: "02", content: 'Hello China'},
  {id: "03", content: 'Hello My Love'}
];

// V6 使用 useParams hook, 只能用在函数式组件中
export default function Detail() {
  const params = useParams();
  console.log('Detail@', params);
  const {id, title} = params;

  const findResult = data.find(detailObj => {
    return detailObj.id === id;
  });
  console.log('Detail@findResult', findResult);

  return (
    <ul>
      <li>ID: {id}</li>
      <li>Title: {title}</li>
      <li>Content: {findResult.content}</li>
    </ul>
  )
}


// -------------- V6 以前 -----------------

// export default class Detail extends Component {
//   render() {
//     console.log('Detail@', this.props);
//     // V6 以前以前接收 params 参数
//     const {id, title} = this.props.match.params;

//     const findResult = data.find(detailObj => {
//       return detailObj.id === id;
//     });

//     return (
//       <ul>
//         <li>ID: {id}</li>
//         <li>Title: {title}</li>
//         <li>Content: {findResult.content}</li>
//       </ul>
//     )
//   }
// }
