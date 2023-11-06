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
import { fetchProvince } from '../../../features/owner_homestay/region/provinceThunk'
import axios from 'axios'
import * as Yup from 'yup'

const { RangePicker } = DatePicker;
const { TextArea } = Input;
const { Option } = Select;
const { Title } = Typography


const HomeStayProduct = () => {
  const [messageApi, contextHolder] = message.useMessage();
  const [cities, setCities] = useState([]);
  const [selectedCity, setSelectedCity] = useState('');
  const [districts, setDistricts] = useState([]);
  const [selectedDistrict, setSelectedDistrict] = useState('');
  const [wards, setWards] = useState([]);
  const [selectedWard, setSelectedWard] = useState('');


  useEffect(() => {
    dispatch(fetchHomestay());
    dispatch(fetchConvenient());
    dispatch(fetchProvince());
  }, []);
  const dispatch = useDispatch();
  const products = useSelector((state) => state.ownerHomestay.homestays)
  const convenients = useSelector((state) => state.convenient.convenients)
  const provinces = useSelector((state) => state.province.provinces)
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
    // setaddress(record.address)
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
    // setaddress('')
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
  const [formErrors, setFormErrors] = useState({});
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
  const updateAddress = () => {
    const selectedCityName = cities.find(city => city.Id === selectedCity)?.Name || '';
    const selectedDistrictName = districts.find(district => district.Id === selectedDistrict)?.Name || '';
    const selectedWardName = wards.find(ward => ward.Id === selectedWard)?.Name || '';

    const newAddress = `${selectedWardName}, ${selectedDistrictName}, ${selectedCityName}`;
    setaddress(newAddress);
  }

  // Use useEffect to call updateAddress whenever the selectedCity, selectedDistrict, or selectedWard changes
  useEffect(() => {
    updateAddress();
  }, [selectedCity, selectedDistrict, selectedWard]);

  const [province, setprovince] = useState(null)
  const [region, setregion] = useState([])

  const [recordid, setRecordid] = useState("");
  const [image, setIamge] = useState([]);
  //getuserid
  const userDetail = JSON.parse(localStorage.getItem('userDetail'));
  const UserID = userDetail.data.id;
  //
  const homestay = {
    name: name,
    startDate: startDate,
    endDate: endDate,
    desc: desc,
    price: parseFloat(price),
    numberPerson: parseInt(numberPerson),
    address: address,
    province: province,
    region: "fa8dbcc1-801c-4345-b5e5-2c4c91b90059",
    ownerHomestay: UserID
  };
  //validateform
  const validationSchema = Yup.object().shape({
    name: Yup.string().required('Vui lòng nhập tên sản phẩm'),
    price: Yup.number()
      .required('Vui lòng nhập giá sản phẩm')
      .typeError('Vui lòng nhập giá sản phẩm')
      .positive('Giá phải là số dương'),
    file: Yup.array()
      .min(5, 'Vui lòng tải lên ít nhất 5 ảnh')
      .max(20, 'Vui lòng tải lên nhiều nhất 20 ảnh'),
    numberPerson: Yup.number()
      .required('Vui lòng nhập số lượng người')
      .typeError('Vui lòng nhập số lượng người')
      .positive('Số lượng người phải là số dương'),
    address: Yup.string().required('Vui lòng chọn đầy đủ địa chỉ của bạn'),
    province: Yup.string().required('Vui lòng chọn thành phố homestay'),
    startDate: Yup.number().required('Vui lòng chọn ngày bắt đầu'),
    endDate: Yup.number().required('Vui lòng chọn ngày kết thúc'),
  })
  const handleSubmit = async (e) => {
    e.preventDefault();
    setIsModalOpen(false);
    try {
      await validationSchema.validate(homestay, { abortEarly: false });
      if (isAddFrom) {
        await message.info('Đang tiến hành thêm bạn vui lòng đợi một vài giây nhé!');
        await dispatch(addHomestay(homestay, file));
        message.info('Thêm thành công');
        setname('')
        setprice(0)
        setdesc('')
        setnumberPerson(0)
      } else {
        await message.info('Đang tiến hành sửa bạn vui lòng đợi một vài giây nhé!');
        await dispatch(EditHomestay(homestay, file, recordid));
        message.info('Sửa thành công');
      }
      dispatch(fetchHomestay());
      setFormErrors({});
    } catch (errors) {
      const errorObject = {};
      errors.inner.forEach(error => {
        errorObject[error.path] = error.message;
      });
      setFormErrors(errorObject);
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
    setFormErrors({});
  }
  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get(
          'https://raw.githubusercontent.com/kenzouno1/DiaGioiHanhChinhVN/master/data.json'
        );
        setCities(response.data);
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    };

    fetchData();
  }, []);

  useEffect(() => {
    if (selectedCity) {
      const selectedCityData = cities.find(city => city.Id === selectedCity);
      if (selectedCityData) {
        setDistricts(selectedCityData.Districts);
      }
    }
  }, [selectedCity, cities]);

  useEffect(() => {
    if (selectedDistrict) {
      const selectedDistrictData = districts.find(district => district.Id === selectedDistrict);
      if (selectedDistrictData) {
        setWards(selectedDistrictData.Wards);
      }
    }
  }, [selectedDistrict, districts]);

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
          <Row gutter={24} style={{ marginLeft: 1 }}>
            {/* Trường thứ nhất */}
            <Col span={12}>
              <Form.Item
                label={<Title level={5}>Tên homestay</Title>}
                validateStatus={formErrors.name ? 'error' : ''} // Hiển thị lỗi cho trường name nếu có
                help={formErrors.name} // Hiển thị thông báo lỗi cho trường name nếu có
              >
                <Input value={name} onChange={(e) => setname(e.target.value)} />
              </Form.Item>
            </Col>
            {/* Trường thứ hai */}
            <Col span={12}>
              <Form.Item
                label={<Title level={5}>Giá homestay</Title>}
                validateStatus={formErrors.price ? 'error' : ''}
                help={formErrors.price}
              >
                <Input value={price} onChange={(e) => setprice(e.target.value)} />
              </Form.Item>
            </Col>
          </Row>
          <Row gutter={24} style={{ marginLeft: 3 }}>
            <Col span={12}>
              <Form.Item
                label={<Title level={5}>Số người ở</Title>}
                validateStatus={formErrors.numberPerson ? 'error' : ''}
                help={formErrors.numberPerson}
              >
                <Input value={numberPerson} onChange={(e) => setnumberPerson(e.target.value)} />
              </Form.Item>
            </Col>
            {/* <Col span={12}>
              <Title level={5}>Chọn Thành phố</Title>
              <Select defaultValue="Chọn thành phố homstay" onChange={(value) => setprovince(value)}>
                {provinces.map((province) => (
                  <Option key={province.id} value={province.id}>
                    {province.name}
                  </Option>
                ))}
              </Select>
            </Col> */}
          </Row>
          <Row gutter={24} style={{ marginTop: 20, marginBottom: 20, marginLeft: 20 }}>
            {/* <DatePicker /> */}
            <div style={{ display: 'flex' }}>
              <div style={{ marginRight: 30, marginLeft: 20 }}>
                <Title level={5}>Tỉnh/Thành phố:</Title>
                <select value={selectedCity} onChange={(e) => setSelectedCity(e.target.value)} style={{ borderRadius: 10 }}>
                  <option value="">Chọn tỉnh/thành phố</option>
                  {cities.map(city => (
                    <option key={city.Id} value={city.Id}>
                      {city.Name}
                    </option>
                  ))}
                </select>
                <div style={{ color: 'red' }}>{formErrors.selectedCity}</div>
              </div>
              <div style={{ marginRight: 30 }}>
                <Title level={5}>Quận/Huyện:</Title>
                <select value={selectedDistrict} onChange={(e) => setSelectedDistrict(e.target.value)} style={{ borderRadius: 10 }}>
                  <option value="">Chọn quận/huyện</option>
                  {districts.map(district => (
                    <option key={district.Id} value={district.Id}>
                      {district.Name}
                    </option>
                  ))}
                </select>
                <div style={{ color: 'red' }}>{formErrors.selectedDistrict}</div>
              </div>
              <div>
                <Title level={5}>Phường/Xã:</Title>
                <select value={selectedWard} onChange={(e) => setSelectedWard(e.target.value)} style={{ borderRadius: 10 }}>
                  <option value="">Chọn phường/xã</option>
                  {wards.map(ward => (
                    <option key={ward.Id} value={ward.Id}>
                      {ward.Name}
                    </option>
                  ))}
                </select>
                <div style={{ color: 'red' }}>{formErrors.selectedWard}</div>
              </div>
            </div>
          </Row>
          <Row gutter={24} style={{ marginLeft: 4 }}>
            <Col span={12}>
              <Form.Item
                label={<Title level={5}>Ngày bắt đầu</Title>}
              >
                <DatePicker onChange={handleDateChangestart} style={{ width: '100%' }} />
                <div style={{ color: 'red' }}>{formErrors.startDate}</div>
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item
                label={<Title level={5}>Ngày kết thúc</Title>}
              >
                <DatePicker onChange={handleDateChangeend} style={{ width: '100%' }} />
                <div style={{ color: 'red' }}>{formErrors.endDate}</div>
              </Form.Item>
              {/* <DatePicker /> */}
            </Col>
          </Row>
          <Row gutter={24} >
            <Col span={24}>
              <Title level={5}>Mô tả homestay</Title>
              <TextArea style={{ width: '900px' }} value={desc} onChange={(e) => setdesc(e.target.value)} />
            </Col>
            {/* <Col span={12}>
              <Title level={5}>Ngày kết thúc</Title>
              <DatePicker onChange={handleDateChangeend} />
            </Col> */}
          </Row>
        </Form>
        <form onSubmit={handleSubmit} style={{ marginTop: 20 }}>
          <div>
            <label htmlFor="image">Chọn ảnh</label>
            <input type="file" id="image" multiple accept="image/*" onChange={handleFileChange} />
          </div>
          <div style={{ color: 'red' }}>{formErrors.file}</div>
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
            <div style={{ width: 700, padding: 20, flexWrap: 'wrap', borderRadius: 10, display: 'flex', justifyContent: 'center', border: '1px solid black' }}>
              {
                image.map((imageurl, index) => (
                  <Image
                    key={index}
                    src={imageurl.imgUrl}
                    alt={`Homestay Image ${index}`}
                    style={{
                      maxWidth: '200px', // Đảm bảo ảnh không vượt quá chiều rộng của phần tử cha
                      margin: '0 10px 10px 0', // Thêm khoảng cách giữa các ảnh
                    }} preview={{
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

