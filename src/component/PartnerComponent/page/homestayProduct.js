import React, { useEffect } from 'react'
import { Space, Typography, Button, Table, Popconfirm, Modal, Form, Input, Row, Col } from 'antd'
import { useDispatch, useSelector } from 'react-redux'
import { QuestionCircleOutlined, EditOutlined, DeleteOutlined, EyeOutlined } from '@ant-design/icons'
import { useState } from 'react'
import { fetchHomestay } from '../../../features/owner_homestay/homestayThunk'
import { fetchConvenient } from '../../../features/owner_homestay/convenientThunk'
import { PlusOutlined } from '@ant-design/icons';
import {
  Cascader,
  Checkbox,
  DatePicker,
  InputNumber,
  Radio,
  Select,
  Divider,
  Slider,
  Switch,
  TreeSelect,
  Upload
} from 'antd';


const { RangePicker } = DatePicker;
const { TextArea } = Input;

const { Title } = Typography

const normFile = (e) => {
  if (Array.isArray(e)) {
    return e;
  }
  return e?.fileList;
};

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
  useEffect(() => {
    dispatch(fetchHomestay());
    dispatch(fetchConvenient());
  }, []);
  const dispatch = useDispatch();
  const products = useSelector((state) => state.ownerHomestay.homestays)
  const convenients = useSelector((state) => state.convenient.convenients)
  const [checkedList, setCheckedList] = useState([{
    name: '',
    type: ''
  }]);
  const [imgUrl, setImgUrl] = useState([]);
  let plainOptions = convenients;
  console.log(convenients);


  const onChangeConvenient = (list) => {
    console.log(list);
    setCheckedList(list);
  };

  const onChangeImg = (list) => {
    setImgUrl(list);
    console.log(list);
  }

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
    <section>
      <Title level={5}>HomeStay</Title>
      <Button type="primary" onClick={showModal}>
        Thêm mới HomeStay
      </Button>
      <Modal title="Basic Modal" open={isModalOpen} onOk={handleOk} onCancel={handleCancel} width={1200} okText='Thêm mới' cancelText='Hủy' >
        <Row>
          <Col span={6}>
            <Form.Item label="Tên">
              <Input />
            </Form.Item>
          </Col>
          <Col span={16} push={2}>
            <Form.Item label="Địa chỉ cụ thể">
              <Input />
            </Form.Item>
          </Col>
        </Row>
        <Row>
          <Col span={6}>
            <Form.Item label="Thành Phố">
              <Select>
                <Select.Option value="demo">Demo</Select.Option>
              </Select>
            </Form.Item>
          </Col>
          <Col span={10} push={2}>
            <Form.Item label="Thời hạn đăng">
              <RangePicker />
            </Form.Item>
          </Col>
          <Col span={4} push={2}>
            <Form.Item label="Số người ở">
              <InputNumber />
            </Form.Item>
          </Col>
        </Row>
        <Form.Item label="Giá">
          <Input />
        </Form.Item>
        <Form.Item label="Mô tả">
          <TextArea rows={4} />
        </Form.Item>
        <Row>
          <Col>
            <Form.Item label="Số phần trăm tiền hóa đơn khách phải trả khi hủy phòng">
              <InputNumber max={100} min={0} />
            </Form.Item>
          </Col>
        </Row>
        <Row>
          <Form.Item label='Tiện nghi'>
            <Checkbox.Group value={checkedList} onChange={onChangeConvenient} >
              {plainOptions.map((option) => {
                return (<Checkbox value={option.name}>{option.name}</Checkbox>
                )
              })}
            </Checkbox.Group>
          </Form.Item>
        </Row>
        <Row style={{ marginTop: '15px' }}>
          <Form.Item label="Upload" valuePropName="fileList" getValueFromEvent={normFile}>
            <Upload action="/upload.do" listType="picture-card" multiple maxCount={50} onChange={onChangeImg}>
              <div>
                <PlusOutlined />
                <div
                  style={{
                    marginTop: 8,
                  }}
                >
                  Upload
                </div>
              </div>
            </Upload>
          </Form.Item>
        </Row>
      </Modal>
      <Table columns={columns} dataSource={products} /> 
    </section>
  )
}
export default HomeStayProduct