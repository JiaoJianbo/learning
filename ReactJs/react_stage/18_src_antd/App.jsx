import React, { Component } from 'react'
import { Button, Divider, DatePicker, Space, Typography} from 'antd';
import {WechatOutlined,SmileOutlined,HeartTwoTone} from '@ant-design/icons';

const { RangePicker } = DatePicker;
const { Title, Text, Link } = Typography;

export default class App extends Component {

  render() {
    return (
      <div className="App">
        <Divider />
        <Button type="primary">Button</Button>
        <Button type="ghost">Button</Button>
        <Button type="dashed">Button</Button>
        <Button type="link">Button</Button>
        <Button type="text">Button</Button>
        <Button type="default">Button</Button>
        <Divider dashed />
        <Space direction="vertical">
          <DatePicker/>
          <RangePicker />
        </Space>
        <Divider dashed />
        <WechatOutlined style={{ fontSize: '48px', color: 'green' }} />
        <SmileOutlined rotate={180} />
        <HeartTwoTone twoToneColor="#eb2f96" />
        <Divider dashed />
        <Space direction="vertical">
          <Title level={2}>h2. Ant Design</Title>
          <Text code>Ant Design (code)</Text>
          <Text keyboard>Ant Design (keyboard)</Text>
          <Link href="https://ant.design" target="_blank">
            Ant Design (Link)
          </Link>
        </Space>
      </div>
    )
  }
}
