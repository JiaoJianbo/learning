import React from 'react'
import {useNavigationType, useResolvedPath} from 'react-router-dom'

export default function News() {
  console.log('useNavigationType@News', useNavigationType());
  console.log('useResolvedPath@News', useResolvedPath('/user?id=001&name=User1#qwer'));

  return (
    <ul>
      <li>News 1</li>
      <li>News 2</li>
      <li>News 3</li>
    </ul>
  )
}
