import { useParams, useSearchParams, useLocation } from 'react-router-dom';

const data = [
  {id: "01", content: 'Hello World'},
  {id: "02", content: 'Hello China'},
  {id: "03", content: 'Hello My Love'}
];

// V6 使用 useParams, useSearchParams, useLocation hook, 只能用在函数式组件中
export default function Detail() {
  // 接收 param 參數
  // const params = useParams();
  // console.log('Detail@useParams', params);
  // const {id, title} = params;

  // 接收 search 參數
  // const [params] = useSearchParams(); 
  // console.log('Detail@useSearchParams', params);
  // const id = params.get('id');
  // const title = params.get('title');

  // 接收 state 參數
  console.log('Detail@useLocation', useLocation());
  const {state:{id, title}} = useLocation();
  
  console.log(id, title);
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
