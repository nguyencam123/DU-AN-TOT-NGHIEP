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

const CategoryList = () => {
  const columns = [
    {
      title: 'Tên',
      dataIndex: 'name',
    },
    {
      title: 'Loại',
      dataIndex: 'convenientHomestayType',
      render: (data) => {
        return data?.name
      }
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
  const categorylist = useSelector((state) => state.admin.category);
  const listType = useSelector((state) => state.admin.categoryType)
  console.log(listType);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [isUpdate, setIsUpdate] = useState(false);
  const [modalType, setModalType] = useState(false);
  const [convenient, setConvenient] = useState({
    desc: 'mo ta'
  });
  const [convenientUpdate, setConvenientUpdate] = useState({
    desc: 'mo ta'
  });
  const [convenientType, setConvenientType] = useState({
    descType: 'mo ta'
  });
  const handleChangeConvenientType = (data) => {
    setConvenientType({ ...convenientType, nameType: data.target.value })
  }
  const handleChangeType = (data) => {
    setConvenient({ ...convenient, idType: data })
  }
  const handleChangeName = (data) => {
    setConvenient({ ...convenient, name: data.target.value })
  }
  const showModal = () => {
    setIsModalOpen(true);
  };
  const showTypeModal = () => {
    setModalType(true);
  };
  const handleOk = async () => {
    console.log(convenient);
    await dispatch(addConvenient(convenient));
    await dispatch(getConvenient());
    message.info('Thêm thành công');
    setIsModalOpen(false)
  };
  const addConvenientType = async () => {
    await dispatch(addType(convenientType));
    await dispatch(getConvenientType());
    message.info('Thêm thành công');
    setModalType(false);
  };
  const handleCancel = () => {
    setIsModalOpen(false);
  };
  const handleTypeModalCancel = () => {
    setModalType(false);
  };
  const updateConvenient = (data) => {
    setConvenientUpdate({ ...convenientUpdate })
    console.log(convenientUpdate);
  }
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
        <Button
          type='primary'
          style={{ float: 'right', marginBottom: '20px', marginRight : '10px' }}
          onClick={showTypeModal}
        >
          Thêm loại tiện nghi
        </Button>
        <Table columns={columns} dataSource={categorylist} />
      </div>
      <Modal
        title='Thêm tiện nghi'
        open={isModalOpen}
        onOk={handleOk}
        onCancel={handleCancel}
      >
        <Form>
          <Form.Item
            label='Tên tiện nghi'
            name='name'
            rules={[
              {
                required: true,
                message: 'Vui lòng điền tên'
              }
            ]}
          >
            <Input value={convenient?.name} onChange={(data) => handleChangeName(data)} />
          </Form.Item>
          <Form.Item
            label='Loại tiện nghi'
            name='type'
          >
            <Select
              style={{ width: 143 }}
              onChange={(data) => handleChangeType(data)}
              options={listType.map((filter) => ({ value: filter.id, label: filter.name }))}
              defaultValue={listType?.[0]?.name}
            />
          </Form.Item>
        </Form>
      </Modal>
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
            <Input value={convenientType?.nameType} onChange={(data) => handleChangeConvenientType(data)} />
          </Form.Item>
        </Form>
      </Modal>

      <Modal
        title='Sửa loại tiện nghị'
        open={isModalOpen}
        onOk={handleOk}
        onCancel={handleCancel}
      >
        <Form>
          <Form.Item
            label='Tên tiện nghi'
            name='name'
            rules={[
              {
                required: true,
                message: 'Vui lòng điền tên'
              }
            ]}
          >
            <Input value={convenient?.name} onChange={(data) => handleChangeName(data)} />
          </Form.Item>
          <Form.Item
            label='Loại tiện nghi'
            name='type'
          >
            <Select
              style={{ width: 143 }}
              onChange={(data) => handleChangeType(data)}
              options={listType.map((filter) => ({ value: filter.id, label: filter.name }))}
              value={listType?.[0]?.name}
            />
          </Form.Item>
        </Form>
      </Modal>
    </>
  );
};
export default CategoryList;
