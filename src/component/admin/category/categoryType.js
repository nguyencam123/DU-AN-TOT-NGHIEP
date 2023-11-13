import React, { useEffect } from 'react';
import {
  Space,
  Typography,
  Button,
  Table,
  Popconfirm,
  Modal,
  Form,
  Input,
  Select,
  message
} from 'antd';
import { useDispatch, useSelector } from 'react-redux';
import { CheckOutlined, DeleteOutlined, DeleteTwoTone, EditOutlined, EditTwoTone, QuestionCircleOutlined } from '@ant-design/icons';
import { useState } from 'react';
import { addConvenient, addType, getConvenient, getConvenientType } from '../../../features/admin/adminThunk';
const { Title } = Typography;

const CategoryType = () => {
  const columns = [
    {
      title: 'Tên',
      dataIndex: 'name',
    },
    {
      title: 'Loại',
      dataIndex: 'desc'
    },
    {
      title: 'Action',
      key: 'action',
      render: (_, record) => (
        <Space size='middle'>
          <a onClick={() => updateConvenient(record)}><EditTwoTone /></a>
        </Space>
      )
    }
  ];

  const dispatch = useDispatch();
  useEffect(() => {
    dispatch(getConvenient());
    dispatch(getConvenientType());
  }, []);
  const listType = useSelector((state) => state.admin.categoryType)
  console.log(listType);
  const [modalType, setModalType] = useState(false);
  const [convenientUpdate, setConvenientUpdate] = useState({
    desc: 'mo ta'
  });
  const [convenientType, setConvenientType] = useState({
    nameType: '',
    descType: ''
  });
  const handleChangeConvenientType = (data) => {
    setConvenientType({ ...convenientType, nameType: data.target.value })
  }
  const handleChangeDesc = (data) => {
    setConvenientType({ ...convenientType, descType: data.target.value })
  }
  const showTypeModal = () => {
    setModalType(true);
  };

  const addConvenientType = async () => {
    await dispatch(addType(convenientType));
    await dispatch(getConvenientType());
    message.info('Thêm thành công');
    setModalType(false);
  };
  const handleTypeModalCancel = () => {
    setModalType(false);
  };
  const updateConvenient = (data) => {
  }
  return (
    <>
      <div style={{ marginTop: '30px' }}>
        <Title level={2}>Quản trị thê loại tiện nghi homestay</Title>
        <Title level={4}>Danh mục</Title>
        <Button
          type='primary'
          style={{ float: 'right', marginBottom: '20px' }}
          onClick={showTypeModal}
        >
          Thêm loại tiện nghi
        </Button>
        <Table columns={columns} dataSource={listType} />
      </div>
      <Modal
        title='Thêm loại tiện nghi'
        open={modalType}
        onOk={addConvenientType}
        onCancel={handleTypeModalCancel}
      >
        <Form>
          <Form.Item
            label='Tên loại tiện nghi'
            name='name'
            rules={[
              {
                required: true,
                message: 'Vui lòng điền tên'
              }
            ]}
          >
            <Input value={convenientType.nameType} onChange={(data) => handleChangeConvenientType(data)} />
          </Form.Item>
          <Form.Item
            label='Mô tả loại tiện nghi'
            name='type'
          >
            <Input value={convenientType.descType} onChange={(data) => handleChangeDesc(data)} />
          </Form.Item>
        </Form>
      </Modal>
    </>
  );
};
export default CategoryType;
