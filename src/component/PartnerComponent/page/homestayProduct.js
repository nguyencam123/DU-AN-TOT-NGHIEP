import React from 'react'
import { Space, Typography, Button, Table, Popconfirm, Modal, Form, Input } from 'antd'
import { useDispatch, useSelector } from 'react-redux'
import { QuestionCircleOutlined } from '@ant-design/icons'
import { useState } from 'react'
const { Title } = Typography

const columns = [
  {
    title: 'Name',
    dataIndex: 'name',
    key: 'name'
  },
  {
    title: 'Address',
    dataIndex: 'address',
    key: 'address'
  },
  {
    title: 'Price',
    dataIndex: 'price',
    key: 'price'
  },
  {
    title: 'Status',
    dataIndex: 'status',
    key: 'status'
  },
  {
    title: 'Action',
    key: 'action',
    render: (_, record) => (
      <Space size="middle">
        <a>Sửa</a>
        <Popconfirm
          title="Xóa mục này"
          description="Bạn chắc chắn muốn xóa mục này chứ?"
          icon={<QuestionCircleOutlined style={{ color: 'red' }} />}
          cancelText="hủy"
          okText="xóa"
        >
          <a>Delete</a>
        </Popconfirm>
      </Space>
    ),
  },
];

const HomeStayProduct = () => {
  return (
    <section>
      <Title level={5}>HomeStay</Title>
      <Table columns={columns} />
    </section>
  )
}
export default HomeStayProduct