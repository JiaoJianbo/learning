import React from 'react'
import { useSearchParams, useLocation} from 'react-router-dom';

export default function Detail() {
  // const a = useSearchParams();
  // console.log(a);
  const b = useLocation();
  console.log(b);

  const [search, setSearch] = useSearchParams();
  // console.log(search);

  return (
    <ul>
      <li>{search.get('id')}</li>
      <li>{search.get('title')}</li>
      <li>{search.get('content')}</li>

      <li><button onClick={()=>setSearch('id=888&title=update&content=更xin了')}>更新 Search 参数</button></li>
    </ul>
  )
}
