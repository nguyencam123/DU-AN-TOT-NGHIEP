import React from 'react'
import { Space, Typography, Button, Table, Popconfirm, Modal, Form, Input, Select } from 'antd'
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
    title: 'Type',
    dataIndex: 'type',
    key: 'type'
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

const HomeStayConvenient = () => {
  const [isModalOpen, setIsModalOpen] = useState(false);


  const handleOk = () => {
    setIsModalOpen(false);
  };

  const showModal = () => {
    setIsModalOpen(true);
  };

  const handleCancel = () => {
    setIsModalOpen(false);
  };

  return (
    <section>
      <Title level={5}>Convenient</Title>
      <Button type="primary" onClick={showModal} style={{ margin: '10px 0px'}}>
        Add
      </Button>
      <Modal title="Add" open={isModalOpen} onOk={handleOk} onCancel={handleCancel}>
        <Form>
          <Form.Item label="Input">
            <Input />
          </Form.Item>
          <Form.Item label="Select">
            <Select>
              <Select.Option value="demo">Demo</Select.Option>
            </Select>
          </Form.Item>
        </Form>
      </Modal>
      <Table columns={columns} />
    </section>
  )
}
export default HomeStayConvenient