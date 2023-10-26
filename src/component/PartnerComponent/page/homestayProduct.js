import React from 'react'
import { Space, Typography, Button, Table, Popconfirm, Modal, Form, Input } from 'antd'
import { useDispatch, useSelector } from 'react-redux'
import { QuestionCircleOutlined } from '@ant-design/icons'
import { useState } from 'react'
import { fetchHomestay } from '../../../features/owner_homestay/homestayThunk'
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
<<<<<<< HEAD
  
=======
  const products = useSelector((state) => state.homestay.homestays)
>>>>>>> 88c1a1fa43c86faf6c78f0a22d6acc01c2f12ba0
  return (
    <section>
      <Title level={5}>HomeStay</Title>
      <Table columns={columns} dataSource={products}/>
    </section>
  )
}
export default HomeStayProduct