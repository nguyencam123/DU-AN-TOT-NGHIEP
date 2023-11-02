import React, { useEffect } from 'react'
import { Space, Typography, Button, Table, Popconfirm, Modal, Form, Input, Row, Col, message, Image } from 'antd'
import { useDispatch, useSelector } from 'react-redux'
import { QuestionCircleOutlined, EditOutlined, DeleteOutlined, EyeOutlined, DownloadOutlined, SwapOutlined, RotateLeftOutlined, RotateRightOutlined, ZoomOutOutlined, ZoomInOutlined } from '@ant-design/icons'
import { useState } from 'react'
import { EditHomestay, addHomestay, fetchHomestay } from '../../../features/owner_homestay/homestayThunk'
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


const HomeStayProduct = () => {
  useEffect(() => {
    dispatch(fetchHomestay());
    dispatch(fetchConvenient());
  }, []);
  const dispatch = useDispatch();
  const products = useSelector((state) => state.ownerHomestay.homestays)
  const convenients = useSelector((state) => state.convenient.convenients)
  //upload file
  // const normFile = (e) => {
  //   if (Array.isArray(e)) {
  //     return e;
  //   }
  //   return e?.fileList;
  // };

  const [file, setFile] = useState([]);
  // const handleFileChange = ({ fileList: newFileList }) => {
  //   setFile(newFileList);
  // };
  const handleFileChange = (e) => {
    let selectedFile = e.target.files;
    let fileList = [...selectedFile]; // Chuyển đổi FileList thành mảng
    setFile(fileList);
  };
  //modal
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [isViewmodal, setIsviewmodal] = useState(false)
  const [isAddFrom, setIsAddForm] = useState(true)
  const showModalView = (record) => {
    console.log(record)
    setIsviewmodal(true)
    setname(record.name)
    setdesc(record.desc)
    setprice(record.price)
    setnumberPerson(record.numberPerson)
    setaddress(record.address)
    setprice(record.price)
    setstartDate(record.startDate)
    setendDate(record.endDate)
    setIamge(record.images)
  }
  const showModal = () => {
    setIsModalOpen(true);
    setIsAddForm(true);
    setname('')
    setdesc('')
    setprice(null)
    setnumberPerson(null)
    setaddress('')
    setprice(null)
  };
  const handleOk = () => {
    setIsModalOpen(false);
  }
  const handleCancel = () => {
    setIsModalOpen(false);
    setIsviewmodal(false)
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
          <a> <EyeOutlined onClick={() => showModalView(record)} /> </a>
          <a> <EditOutlined onClick={() => handleEditRow(record)} /> </a>
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

  //

  const [name, setname] = useState("")

  const [startDate, setstartDate] = useState(null)
  const handleDateChangestart = (dates) => {
    setstartDate(moment(dates).valueOf());
  };
  const [endDate, setendDate] = useState(null)
  const handleDateChangeend = (dates) => {
    setendDate(moment(dates).valueOf());
  };
  const [desc, setdesc] = useState("")
  const [price, setprice] = useState(0)
  const [numberPerson, setnumberPerson] = useState(0)
  const [address, setaddress] = useState("")
  const [province, setprovince] = useState([])
  const [region, setregion] = useState([])
  // const [province, setprovince] = useState("2695a00e-a933-4c28-819c-9b66f3184e8d")
  // const [region, setregion] = useState("a72af500-5ee5-4268-8d91-d7383d4a9011")
  const [recordid, setRecordid] = useState("");
  const [image, setIamge] = useState([]);
  //
  const homestay = {
    name: name,
    startDate: startDate,
    endDate: endDate,
    desc: desc,
    price: price,
    numberPerson: numberPerson,
    address: address,
    province: "ffd39119-662e-4c12-9571-8344f8e94893",
    region: "56000efa-316d-4627-a0c7-c48f1873082f",
    ownerHomestay: "e482121b-28df-4ec0-96b7-c37251b6f1d1"
  };
  const handleSubmit = async (e) => {
    e.preventDefault();
    setIsModalOpen(false);
    if (isAddFrom) {
      dispatch(addHomestay(homestay, file));
      dispatch(fetchHomestay());
      message.info('Thêm thành công');
    } else {
      dispatch(EditHomestay(homestay, file, recordid));
      dispatch(fetchHomestay());
      message.info('Sửa thành công');
    }
  };
  const handleEditRow = (record) => {
    setIsModalOpen(true);
    setIsAddForm(false);
    setRecordid(record.id)
    setname(record.name)
    setdesc(record.desc)
    setprice(record.price)
    setnumberPerson(record.numberPerson)
    setaddress(record.address)
    setprice(record.price)
    setstartDate(record.startDate)
    setendDate(record.endDate)
    setFile(record.images)
  }
  return (
    <>
      <Title>Quản lý Homestay</Title>
      <div style={{ marginBottom: 20, float: 'right' }}>
        <Button type="primary" onClick={showModal}>
          Thêm mới HomeStay
        </Button>
      </div>
      <Modal title={isAddFrom == true ? <div style={{ fontSize: 24 }}>Thêm homstay </div> : <div style={{ fontSize: 24 }}>Sửa homestay</div>} open={isModalOpen} onCancel={handleCancel}
        width={900} okText={isAddFrom == true ? "Thêm homstay" : "Sửa homestay"} cancelText='Hủy' onOk={handleSubmit}>
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
              <Title level={5}>Tên homestay</Title>
              <Input value={name}
                onChange={(e) => setname(e.target.value)} />
            </Col>
            {/* Trường thứ hai */}
            <Col span={12}>
              <Title level={5}>Giá</Title>
              <Input value={price}
                onChange={(e) => setprice(e.target.value)} />

            </Col>

          </Row>
          <Row gutter={24}>
            <Col span={12}>
              <Title level={5}>Mô tả</Title>
              <Input value={desc}
                onChange={(e) => setdesc(e.target.value)} />

            </Col>
            <Col span={12}>
              <Title level={5}>Số người ở</Title>
              <Input value={numberPerson}
                onChange={(e) => setnumberPerson(e.target.value)} />

            </Col>
          </Row>
          <Row gutter={24}>
            {/* <DatePicker /> */}
            <Col span={12}>
              <Title level={5}>Địa chỉ</Title>
              <Input value={address}
                onChange={(e) => setaddress(e.target.value)} />
            </Col>
            <Col span={12}>
              <Title level={5}>Địa chỉ chi tiết</Title>
              <Input value={address}
                onChange={(e) => setaddress(e.target.value)} />
            </Col>
          </Row>
          <Row gutter={24}>
            <Col span={12}>
              <Title level={5}>Ngày bắt đầu</Title>
              <DatePicker onChange={handleDateChangestart} />
            </Col>
            <Col span={12}>
              <Title level={5}>Ngày kết thúc</Title>
              <DatePicker onChange={handleDateChangeend} />
              {/* <DatePicker /> */}
            </Col>
          </Row>
        </Form>
        <form onSubmit={handleSubmit} style={{ marginTop: 20 }}>
          <div>
            <label htmlFor="image">Chọn ảnh</label>
            <input type="file" id="image" multiple accept="image/*" onChange={handleFileChange} />
          </div>
          {/* <Form.Item label="Upload" valuePropName="fileList" getValueFromEvent={normFile}>
            <Upload action="image/*" listType="picture-card" multiple maxCount={50} onChange={handleFileChange}>
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
          </Form.Item> */}
        </form>
      </Modal>
      <Modal title={<div style={{ fontSize: '22px' }}>Xem thông tin chi tiết homstay</div>} open={isViewmodal} onCancel={handleCancel}
        width={800} style={{ fontSize: '40px' }}>
        <div style={{ fontSize: 18, fontWeight: 600 }}>
          <div style={{ display: 'flex' }}><div style={{ width: 150 }}>Tên homestay </div> : {name}</div><br />
          <div style={{ display: 'flex' }}><div style={{ width: 150 }}>Mô tả          </div> : {desc}</div><br />
          <div style={{ display: 'flex' }}><div style={{ width: 150 }}>Giá            </div> : {price}</div><br />
          <div style={{ display: 'flex' }}><div style={{ width: 150 }}>Số lượng người </div> : {numberPerson}</div><br />
          <div style={{ display: 'flex' }}><div style={{ width: 150 }}>Địa chỉ        </div> : {address}</div><br />
          <div style={{ display: 'flex' }}><div style={{ width: 150 }}>Ngày bắt đầu   </div> : {startDate}</div><br />
          <div style={{ display: 'flex' }}><div style={{ width: 150 }}>Ngày kết thúc  </div> : {endDate}</div><br />
          <div>Ảnh homstay  <br />
            <div style={{ width: 700, padding: 20, borderRadius: 10, display: 'flex', justifyContent: 'center', border: '1px solid black' }}>
              {
                image.map((imageurl, index) => (
                  <Image key={index} src={imageurl.imgUrl} alt={`Homestay Image ${index}`}
                    style={{ width: 130, height: 80, marginRight: 30, marginTop: 10 }} preview={{
                      toolbarRender: (
                        _,
                        {
                          transform: { scale },
                          actions: { onFlipY, onFlipX, onRotateLeft, onRotateRight, onZoomOut, onZoomIn },
                        },
                      ) => (
                        <Space className="toolbar-wrapper">
                          <SwapOutlined rotate={90} onClick={onFlipY} />
                          <SwapOutlined onClick={onFlipX} />
                          <RotateLeftOutlined onClick={onRotateLeft} />
                          <RotateRightOutlined onClick={onRotateRight} />
                          <ZoomOutOutlined disabled={scale === 1} onClick={onZoomOut} />
                          <ZoomInOutlined disabled={scale === 50} onClick={onZoomIn} />
                        </Space>
                      ),
                    }} />
                ))
              }
            </div>
          </div>
        </div>
      </Modal>
      <Table columns={columns} dataSource={products} rowKey="key" />
    </>
  );
}
export default HomeStayProduct

