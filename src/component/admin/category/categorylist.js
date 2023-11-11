import React, { useEffect } from 'react';
import {
  Space,
  Typography,
  Button,
  Table,
  Popconfirm,
  Modal,
  Form,
  Input
} from 'antd';
import { useDispatch, useSelector } from 'react-redux';
import { CheckOutlined, DeleteOutlined, DeleteTwoTone, EditOutlined, EditTwoTone, QuestionCircleOutlined } from '@ant-design/icons';
import { useState } from 'react';
import { getConvenient } from '../../../features/admin/adminThunk';
const { Title } = Typography;
const columns = [
  {
    title: 'Tên',
    dataIndex: 'convenient_Name',
  },
  {
    title: 'Loại',
    dataIndex: 'convenient_Type_Name',
  },
  {
    title: 'Action',
    key: 'action',
    render: (_, record) => (
      <Space size='middle'>
        <a><EditTwoTone /></a>
        <Popconfirm
          title='Xóa mục này'
          description='Bạn chắc chắn muốn xóa mục này chứ?'
          icon={<QuestionCircleOutlined style={{ color: 'red' }} />}
          cancelText='hủy'
          okText='xóa'
        >
          <a><DeleteTwoTone /></a>
        </Popconfirm>
      </Space>
    )
  }
];
const onFinish = (values) => {
  console.log('Success:', values);
};
const onFinishFailed = (errorInfo) => {
  console.log('Failed:', errorInfo);
};
const CategoryList = () => {
  const dispatch = useDispatch();
  useEffect(() => {
    dispatch(getConvenient());
  }, []); 
  const categorylist = useSelector((state) => state.admin.category);
  console.log(categorylist);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const showModal = () => {
    setIsModalOpen(true);
  };
  const handleOk = () => {
    setIsModalOpen(false);
  };
  const handleCancel = () => {
    setIsModalOpen(false);
  };
  return (
    <>
      <div style={{ marginTop: '30px' }}>
        <Title level={2}>Quản trị tiện nghi homestay</Title>
        <Title level={4}>Danh mục</Title>
        <Button
          type='primary'
          style={{ float: 'right', marginBottom: '20px' }}
          onClick={showModal}
        >
          Thêm
        </Button>
        <Table columns={columns} dataSource={categorylist} />
      </div>
      <Modal
        title='Thêm loại sản phẩm'
        open={isModalOpen}
        onOk={handleOk}
        onCancel={handleCancel}
      >
        <Form
          name='basic'
          labelCol={{
            span: 8
          }}
          wrapperCol={{
            span: 16
          }}
          style={{
            maxWidth: 600
          }}
          initialValues={{
            remember: true
          }}
          onFinish={onFinish}
          onFinishFailed={onFinishFailed}
          autoComplete='off'
        >
          <Form.Item
            label='id'
            name='id'
            rules={[
              {
                required: true,
                message: 'Please input your id category!'
              }
            ]}
          >
            <Input />
          </Form.Item>
          <Form.Item
            label='name'
            name='name'
            rules={[
              {
                required: true,
                message: 'Please input your name category!'
              }
            ]}
          >
            <Input />
          </Form.Item>
        </Form>
      </Modal>
    </>
  );
};
export default CategoryList;
