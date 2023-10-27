import React, { useEffect } from 'react'
import { Space, Typography, Button, Table, Popconfirm, Modal, Form, Input } from 'antd'
import { useDispatch, useSelector } from 'react-redux'
import { QuestionCircleOutlined, EditOutlined, DeleteOutlined, EyeOutlined } from '@ant-design/icons'
import { useState } from 'react'
<<<<<<< HEAD
=======
import { fetchProduct } from '../../../features/owner_homestay/homestayThunk'
>>>>>>> 393e16589ab436b495a97f11da3ea0429f044cce
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
        <a> <EyeOutlined /> </a>
        <a> <EditOutlined /> </a>
        <Popconfirm
          title="Xóa mục này"
          description="Bạn chắc chắn muốn xóa mục này chứ?"
          icon={<QuestionCircleOutlined style={{ color: 'red' }} />}
          cancelText="hủy"
          okText="xóa"
        >
          <a><DeleteOutlined /></a>
        </Popconfirm>
      </Space>
    ),
  },
];

const HomeStayProduct = () => {
<<<<<<< HEAD

  return (
    <section>
      <Title level={5}>HomeStay</Title>
      <Table columns={columns} />
=======
  useEffect(() => {
    dispatch(fetchProduct());
  }, []);
  const dispatch = useDispatch();
  const products = useSelector((state) => state.ownerHomestay.homestays)
  return (
    <section>
      <Title level={5}>HomeStay</Title>
      <Table columns={columns} dataSource={products} />
>>>>>>> 393e16589ab436b495a97f11da3ea0429f044cce
    </section>
  )
}
export default HomeStayProduct