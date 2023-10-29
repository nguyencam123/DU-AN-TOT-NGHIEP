import React, { useEffect } from 'react'
import { Space, Typography, Button, Table, Popconfirm, Modal, Form, Input, Row, Col } from 'antd'
import { useDispatch, useSelector } from 'react-redux'
import { QuestionCircleOutlined, EditOutlined, DeleteOutlined, EyeOutlined } from '@ant-design/icons'
import { useState } from 'react'
import { addHomestay, fetchHomestay } from '../../../features/owner_homestay/homestayThunk'
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
import { province } from './province'
import moment from 'moment';

const { RangePicker } = DatePicker;
const { TextArea } = Input;

const { Title } = Typography

const normFile = (e) => {
  if (Array.isArray(e)) {
    return e;
  }
  return e?.fileList;
};


const HomeStayProduct = () => {
  useEffect(() => {
    dispatch(fetchHomestay());
    dispatch(fetchConvenient());
  }, []);
  const dispatch = useDispatch();
  const products = useSelector((state) => state.ownerHomestay.homestays)
  const convenients = useSelector((state) => state.convenient.convenients)
  //upload file
  const [file, setFile] = useState(null);
  const handleFileChange = (e) => {
    const selectedFile = e.target.files[0];
    setFile(selectedFile);
  };
  //modal
  const [isModalOpen, setIsModalOpen] = useState(false);
  const showModal = () => {
    setIsModalOpen(true);
  };
  const handleOk = () => {
    setIsModalOpen(false);
  }
  const handleCancel = () => {
    setIsModalOpen(false);
  }
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
          <a onClick={(row) => { console.log(row.data); }}> <EyeOutlined /> </a>
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
  //
  const [name, setname] = useState("")
  const [startDate, setstartDate] = useState(1666838400000)
  const [endDate, setendDate] = useState(1666838400000)
  const [desc, setdesc] = useState("")
  const [price, setprice] = useState(0)
  const [numberPerson, setnumberPerson] = useState(0)
  const [address, setaddress] = useState("")
  const [province, setprovince] = useState("2695a00e-a933-4c28-819c-9b66f3184e8d")
  const [region, setregion] = useState("a72af500-5ee5-4268-8d91-d7383d4a9011")
  //
  const homestay = {
    name: name,
    startDate: startDate,
    endDate: endDate,
    desc: desc,
    price: price,
    numberPerson: numberPerson,
    address: address,
    province: province,
    region: region
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setIsModalOpen(false);
    dispatch(addHomestay(homestay, file))
  };
  return (
    <>
      <Title>Quản lý Homestay</Title>
      <div style={{ marginBottom: 20, float: 'right' }}>
        <Button type="primary" onClick={showModal}>
          Thêm mới HomeStay
        </Button>
      </div>
      <Modal title="Basic Modal" open={isModalOpen} onCancel={handleCancel}
        width={1200} okText='Thêm mới' cancelText='Hủy' onOk={handleSubmit}>
        <Form
          name="basic"
          labelCol={{
            span: 8,
          }}
          wrapperCol={{
            span: 24,
          }}
          style={{
            maxWidth: 800,
          }}
          initialValues={{
            remember: true,
          }}
          autoComplete="off"
        >
          <Row gutter={24}>
            {/* Trường thứ nhất */}
            <Col span={12}>
              <Form.Item
                label="Name product"
                name="name"
              >
                <Input value={name}
                  onChange={(e) => setname(e.target.value)} />
              </Form.Item>
            </Col>
            {/* Trường thứ hai */}
            <Col span={12}>
              <Form.Item
                label="Price"
                name="price"
              >
                <Input value={price}
                  onChange={(e) => setprice(e.target.value)} />
              </Form.Item>
            </Col>

          </Row>
          <Row gutter={24}>
            <Col span={12}>
              <Form.Item
                label="mô tả"
                name="desc"
              >
                <Input value={desc}
                  onChange={(e) => setdesc(e.target.value)} />
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item
                label="numberPerson"
                name="numberPerson"
              >
                <Input value={numberPerson}
                  onChange={(e) => setnumberPerson(e.target.value)} />
              </Form.Item>
            </Col>
          </Row>
          <Row gutter={24}>
            <Col span={12}>
              <Form.Item
                label="địa chỉ"
                name="address"
              >
                <Input value={address}
                  onChange={(e) => setaddress(e.target.value)} />
              </Form.Item>
            </Col>
          </Row>
        </Form>
        <form onSubmit={handleSubmit}>
          <div>
            <label htmlFor="image">Chọn ảnh</label>
            <input type="file" id="image" accept="image/*" onChange={handleFileChange} />
          </div>
        </form>
      </Modal>
      <Table columns={columns} dataSource={products} />
    </>
  );
}
export default HomeStayProduct