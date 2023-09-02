import React from 'react'
import { useParams, useMatch } from 'react-router-dom';

export default function Detail() {
  // const a = useParams();
  // console.log(a);

  // const m = useMatch('/home/messages/detail/:id/:title/:content');
  // console.log(m);

  const {id, title, content} = useParams();

  return (
    <ul>
      <li>{id}</li>
      <li>{title}</li>
      <li>{content}</li>
    </ul>
  )
}
