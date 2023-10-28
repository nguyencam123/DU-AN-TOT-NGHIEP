import React, { useEffect } from 'react'
import { Space, Typography, Button, Table, Popconfirm, Modal, Form, Input, Row, Col } from 'antd'
import { useDispatch, useSelector } from 'react-redux'
import { QuestionCircleOutlined, EditOutlined, DeleteOutlined, EyeOutlined } from '@ant-design/icons'
import { useState } from 'react'
<<<<<<< HEAD
import { fetchProduct } from '../../../features/owner_homestay/homestayThunk'
=======
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


const { RangePicker } = DatePicker;
const { TextArea } = Input;

>>>>>>> 5f04fb9c734cac93ecde44d475d163a8a7758d24
const { Title } = Typography

const normFile = (e) => {
  if (Array.isArray(e)) {
    return e;
  }
  return e?.fileList;
};


const HomeStayProduct = () => {
<<<<<<< HEAD
=======
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

>>>>>>> 5f04fb9c734cac93ecde44d475d163a8a7758d24
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
  const [homestay, setHomestay] = useState({
    name: '',
    address: '',
    province: '',
    startDate: '',
    endDate: '',
    numberPerson: 0,
    price: 10.0,
    desc: '',
    cancelPolicy: 1.0,
  });
  const [imgUrl, setImgUrl] = useState([]);
  let plainOptionConvenient = convenients;
  console.log(convenients);
  let plainProvince = [];
  province.forEach((province) => {
    plainProvince.push({
      value: province,
      label: province,
    })
  })


  const onChangeConvenient = (list) => {
    console.log(list);
    setCheckedList(list);
  };

  const onChangeImg = (list) => {
    setImgUrl(list);
    console.log(list);
  }

  const onChangeName = (e) => {
    setHomestay({ ...homestay, name: e.target.value });
  };

  const onChangePrice = (e) => {
    setHomestay({ ...homestay, price: e.target.value });
  };

  const onChangeNumberPerson = (value) => {
    setHomestay({ ...homestay, numberPerson: value });
  };

  const onChangeDesc = (e) => {
    setHomestay({ ...homestay, desc: e.target.value });
  };

  const onChangeAddress = (e) => {
    setHomestay({ ...homestay, address: e.target.value });
  };

  const onChangeProvince = (value) => {
    setHomestay({ ...homestay, province: value });
  };

  const clickViewHomestayDetail = (value) => {
    setHomestay({ ...homestay, province: value });
  };

  const onChangeCancelPolicy = (value) => {
    setHomestay({ ...homestay, cancelPolicy: value });
  };

  const onChangeStartdate = (value) => {
    setHomestay({ ...homestay, startDate: value });
  };
  const onChangeEnddate = (value) => {
    setHomestay({ ...homestay, endDate: value });
  };

  const [isModalOpen, setIsModalOpen] = useState(false);
  const showModal = () => {
    setIsModalOpen(true);
  };
  const handleOk = () => {
    setIsModalOpen(false);
    console.log("???")
    dispatch(addHomestay(homestay, imgUrl))
  }
  const handleCancel = () => {
    setIsModalOpen(false);
  }

  return (
    <section>
      <Title level={5}>HomeStay</Title>
      <Button type="primary" onClick={showModal}>
        Thêm mới HomeStay
      </Button>
      <Modal title="Basic Modal" open={isModalOpen} onOk={() => handleOk()} onCancel={handleCancel} width={1200} okText='Thêm mới' cancelText='Hủy' >
        <Row>
          <Col span={6}>
            <Form.Item label="Tên">
              <Input onChange={onChangeName} value={homestay.name} />
            </Form.Item>
          </Col>
          <Col span={16} push={2}>
            <Form.Item label="Địa chỉ cụ thể">
              <Input onChange={onChangeAddress} value={homestay.address} />
            </Form.Item>
          </Col>
        </Row>
        <Row>
          <Col span={6}>
            <Form.Item label="Thành Phố">
              <Select onChange={onChangeProvince} options={plainProvince} />
            </Form.Item>
          </Col>
          <Col span={6} push={2}>
            <Form.Item label="Ngày bắt đầu">
              <DatePicker onChange={onChangeStartdate} value={homestay.startDate} />
            </Form.Item>
          </Col>
          <Col span={6} push={2}>
            <Form.Item label="Ngày bắt đầu">
              <DatePicker onChange={onChangeEnddate} value={homestay.endDate} />
            </Form.Item>
          </Col>
        </Row>
        <Row>
          <Col span={8}>
            <Form.Item label="Giá">
              <Input onChange={onChangePrice} value={homestay.price} />
            </Form.Item>
          </Col>
          <Col span={10} push={2}>
            <Form.Item label="Số người ở">
              <InputNumber onChange={onChangeNumberPerson} value={homestay.numberPerson} />
            </Form.Item>
          </Col>
        </Row>
        <Form.Item label="Mô tả">
          <TextArea rows={4} onChange={onChangeDesc} />
        </Form.Item>
        <Row>
          <Col>
            <Form.Item label="Số phần trăm tiền hóa đơn khách phải trả khi hủy phòng">
              <InputNumber max={100} min={0} onChange={onChangeCancelPolicy} />
            </Form.Item>
          </Col>
        </Row>
        <Row>
          <Form.Item label='Tiện nghi'>
            <Checkbox.Group value={checkedList} onChange={onChangeConvenient} >
              {plainOptionConvenient.map((option) => {
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